## 변경 가능 공유 데이터에 대한 접근은 동기화하라
### 

--------

동기화 없이는 한 스레드가 만든 변화를 다른 스레드가 확인할 수 없다. 동기화는 스레드가 일관성이 깨진 객체를 관측할 수 없도록 할 뿐 아니라, 동기화 메소드나 동기화 블록에 진입한 스레드가 동일한 락의 보호 아래 이루어진 모든 변경의 영향을 관측할 수 있도록 보장한다.  

자바의 언어 명세에는 long이나 double 이 아닌 모든 변수는 원자적(atomic)으로 읽고 쓸 수 있다고 되어있다. 다시 말해, long 이나 double이 아닌 변수를 읽으면 나오는 값은 항상 어떤 스레드가 저장한 값이라는 것이다. 설사 여러 스레드가 그 변수를 동기화 없이 변경했다고 해도 말이다.

**상호 배제성뿐 아니라 스레드 간의 안정적 통신을 위해서도 동기화는 반드시 필요하다.** 자바 언어 명세의 일부인 메모리 모델(memory model) 때문이다. 메모리 모델은 한 스레드가 만든 변화를 다른 스레드가 볼 수 있게 되는 시점과, 그 절차를 규정한다.  
변경 가능한 공유 데이터에 대한 접근을 동기화하지 않았을 때 생길 수 있는 결과는, 그 데이터가 원자적으로 읽고 쓸 수 있는 데이터라 해도 심각하다. 한 스레드에서 다른 스레드를 중지시킬 수 있도록 해야 한다는 과제를 받았다고 해 보자. 라이브러리를 보면 Thread.stop 메소드가 있는데, 안전성이 결여되어 있다는 문제로 오래전에 폐기 되었다. Thread.stop은 절대로 이용하지 마라. false로 초기화되는 boolean 필드를 이용하는 것이 바람직하다. 한 스레드는 이 필드의 값이 true로 바뀌는지 계속 검사해서 true로 바뀌면 실행을 중단하고, 해당 스레드를 중지시켜야 하는 다른 스레드는 필요할 때 해당 필드의 값을 true로 바꿔주면 된다. boolean 필드는 원자적으로 읽고 쓸 수 있으므로, 어떤 프로그래머들은 동기화 따위 필요 없는 것 아니냐고 생각한다.

```java
public class StopThread {
    private static boolean stopRequested;
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!stopRequested) i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```
실행한지 1초가 지나면 main 스레드가 stopRequested의 값을 true로 바꾸므로, 후면 스레드(background thread)가 실행하는 순환문도 그때 중지될 것 같다. 그러므로 이 프로그램은 1초면 종료될것 같다. 하지만 이 프로그램은 예상처럼 돌아가지 않는다.

이 프로그램의 문제는 동기화 메커니즘을 적용하지 않은 탓에 main 스레드가 변경한 stopRequest의 새로운 값을 후면 스레드가 언제쯤 보게 될지 알 수가 없다는 것이다.
```java
    wihle(!stopRequested) i++;
```
동기화가 적용되지 않은 경우, 가상 머신은 위의 코드를 아래와 같이 바꿀 수 있다.
```java
    if(!stopRequest)
        while(true)
            i++;
```

이런 최적화를 끌어올리기(hoisting)라고 하는데, HotSpot 서버 VM이 하는일이 바로 그런 것이다. 그 덕에 생기는 문제가 바로 생존 오류다. 살아 있기는 하나 더 진행하지는 못하는 프로그램이 되는 것이다. 이 문제를 수정하는 한 가지 방법은 stopRequested 필드를 동기화하는 것이다. 아래의 프로그램은 예상대로 1초 만아 끝난다.
```java
public class StopThread {
    private static boolean stopRequested;
    private static synchronized void requestStop() {
        stopRequested = true;
    }
    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!stopRequested()) i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
}
```
읽기 연산과 쓰기 연산에 전부 적용하지 않으면 동기화는 아무런 효과도 없다.

StopThread의 동기화 메소드가 하는 일은 동기화가 없이도 원자적이다. 다시 말해서, 이들 메소드에 동기화를 적용한 것은 상호 배제성을 달성하기 위해서가 아니라, 순전히 스레드 간 통신 문제를 해결하기 위해서였다는 것이다. 비록 순환문의 각 단계마다 동기화를 실행하는 비용이 크진 않지만, 그 비용을 줄여서 좋은 성능을 내면서도 간결하기까지 한 대안이 있다. 위 코드에 사용된 boolean 필드 stopRequested를 volatile로 선언하는 것이다. 그러면 락은 없어도 된다. 비록 volatile이 상호 배제성을 실현하진 않지만, 어떤 스레드건 가장 최근에 기록된 값을 읽도록 보장한다.
```java
public class StopThread {
    private static volatile boolean stopRequested;
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!stopRequested) i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```

volatile을 사용할 때는 주의해야 한다. 아래의 메소드를 살펴보자,
```java
private static volatile int nextSerialNumber = 0;

public static int generateSerialNumber() {
    return nextSerialNumber++;
}
```
메소드의 상태를 구성하는 것은 원자적으로 접근 가능한 필드 nextSerialNumber이며, 이 필드가 가질 수 있는 값은 전부 유효하다. 따라서 불변식을 보호하기 위해 동기화 메커니즘을 사용할 필요가 없다. 그런데도 동기화 없이는 제대로 동작하지 않는다.  
문제는 증가 연산자 ++가 원자적이지 않다는 데 있다. 한 스레드가 필드의 값을 읽은 후 새 값을 미처 기록하기 전에 다른 스레드가 필드에서 같은 값을 읽으면, 두 스레드는 같은 일련번호를 얻게 된다. 이것은 안전 오류다.

이 문제를 해결하는 한 가지 방법은, 메소드를 synchronized로 선언하는 것이다. synchronized 키워드를 붙였다면, volatile 키워드는 삭제해야 한다.
더 좋은 방법은 AtomicLong 클래스를 사용하는 것이다. 원하는 일을 해주면서도, synchronized 키워드를 사용한 해법보다 성능도 좋다.
```java
private static final AtomicLong nextSerialNum = new AtomicLong();

public static int generateSerialNumber() {
    return nextSerialNum.getAndIncrement();
}
```
이번 절에서 설명한 문제를 피하는 가장 좋은 방법은 변경 가능 데이터를 공유하지 않는 것이다. 굳이 공유해야 한다면 변경 불가능 데이터를 공유하거나, 그럴 필요가 없다면 아예 공유하지 마라.  
**다시 말해서, 변경 가능 데이터는 한 스레드만 이용하도록 하라는 것이다.** 이 지침을 따를 때는 그 사실을 문서로 남겨놓도록 해야 한다.

특정한 스레드만이 데이터 객체를 변경할 수 있도록 하고, 변경이 끝난 뒤에야 다른 스레드와 공유하도록 할 때는 객체 참조를 공유하는 부분에만 동기화를 적용하면 된다. 객체 참조를 가져온 스레드는, 객체가 더 이상 수정되지 않는 한, 동기화 없이도 객체의 내용을 읽을 수 있다. 이런 객체를 실질적으로 변경 불가능한 객체라고 한다. 또한 이러 객체 참조를 다른 스레드로 전달하는 행위를 안전한 발행이라고 부른다.  
객체 참조를 안전하게 발행하는 방법은 많다. 클래스가 초기화될 때 같이 초기화되는 static 필드에 저장해도 되고, volatile 필드나 final 필드에 저장해도 되고, 락을 걸어야 접근 가능한 필드에 저장해 둘 수도 있다. 아니면 concurrent 컬렉션에 보관해도 된다.


--------

참고 :

Bloch, Joshua. Effective Java (2/E). 인사이트, 2014