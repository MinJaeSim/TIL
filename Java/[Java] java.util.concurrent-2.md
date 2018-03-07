# java.util.concurrent
### 간단하게 다중스레드 프로그램을 짤 수 있도록 하는 패키지

-------

### 5. Semaphore - Class
일부 시스템에서는 개발자가 특정 리소스에 대한 열린 요청(스레드/조치)의 수를 제한해야 하는 경우가 종종 발생한다. 실제로 이러한 제한은 특정 리소스에 대한 경합을 줄여서 시스템의 처리 속도를 향상시킬 수도 있다. 물론 제한 코드를 직접 작성할 수도 있겠지만 Semaphore 클래스를 사용하면 자동으로 손쉽게 제한 작업을 수행할 수 있다.  

항목을 얻기 전에 각 스레드는 항목을 사용할 수 있음을 보장하면서 세마포어에서 허가를 얻어야한다. 스레드가 항목을 끝내면 풀로 되돌려 보내지고 허가는 세마포어로 반환되어 다른 스레드가 해당 항목을 얻을 수있게한다. acquire()가 호출되면 항목이 풀에 반환되지 않도록 동기화 잠금이 유지되지 않는다. 세마포어는 풀 자체의 일관성을 유지하는 데 필요한 동기화와 별도로 풀에 대한 액세스를 제한하는 데 필요한 동기화를 캡슐화한다.

하나에 초기화 된 세마포어는 하나 이상의 허가 만 사용할 수 있도록 사용되며 상호 배제 잠금 역할을 할 수 있습니다. 이는 바이너리 세마포어 (binary semaphore)로 더 많이 알려져 있습니다. 두 가지 상태, 즉 사용 가능한 허가 하나 또는 사용 가능한 허가 0 개만 있기 때문입니다. 이런 식으로 사용될 때, 바이너리 세마포어는 (많은 Lock 구현과는 달리) 세마포어가 소유권 개념을 갖지 않으므로 소유자가 아닌 다른 스레드에 의해 "잠금"이 해제 될 수있는 속성을가집니다. 교착 상태 복구와 같은 특수한 상황에서 유용 할 수 있습니다.

    public static void main(String[] args) {
        Runnable limitedCall = new Runnable() {
            final Random rand = new Random();
            final Semaphore available = new Semaphore(3);
            int count = 0;
            public void run()
            {
                int time = rand.nextInt(15);
                int num = count++;
                 
                try
                {
                    available.acquire();
                     
                    System.out.println("Executing " + 
                        "long-running action for " + 
                        time + " seconds... #" + num);
                 
                    Thread.sleep(time * 1000);
 
                    System.out.println("Done with #" + 
                        num + "!");
 
                    available.release();
                }
                catch (InterruptedException intEx)
                {
                    intEx.printStackTrace();
                }
            }
        };
         
        for (int i=0; i<10; i++)
            new Thread(limitedCall).start();
    }

### 6. CountDownLatch - Class
하나 이상의 스레드가 다른 스레드에서 수행되는 일련의 작업이 완료 될 때까지 대기 할 수있는 동기화 보조 도구다. Semaphore가 한 번에 하나의 스레드만 허용하도록 설계된 동시성 클래스, 상영관 입구의 티켓 검사원, 이라면 CountDownLatch는 경마장의 출발선에 있는 문이다. 이 클래스는 특정 조건이 충족될 때까지 모든 스레드를 가지고 있다가 조건이 충족되는 시점에 모든 스레드를 동시에 풀어놓는다.  

CountDownLatch는 주어진 카운트로 초기화된다. await 메소드는 countDown() 메소드의 호출로 인해 현재 카운트가 0이 될 때까지 블록한다. 그 후에는 대기중인 모든 스레드가 해제되고 모든 후속 호출 대기가 즉시 반환된다. 이것은 일회성 현상이다. 카운트를 재설정 할 수 없다. 개수를 다시 설정하는 버전이 필요한 경우 CyclicBarrier 사용을 고려해라.

CountDownLatch는 다목적 동기화 도구이며 다양한 용도로 사용할 수 있다. count가 1로 초기화 된 CountDownLatch는 간단한 on / off 래치 또는 gate 역할을 한다. wait 대기 호출하는 모든 스레드는 countDown()을 호출하는 스레드가 열 때까지 게이트에서 대기한다. N으로 초기화 된 CountDownLatch는 N 개의 스레드가 어떤 동작을 완료하거나 N 개의 동작이 완료 될 때까지 하나의 스레드를 대기 시키도록 사용될 수 있다.

CountDownLatch의 유용한 속성은 countDown을 호출하는 스레드가 카운트가 0이 될 때까지 기다리지 않고 모든 스레드가 통과 할 때까지 모든 스레드가 기다리지 않고 진행하는 것을 막을 수 있다는 것다.

### 7. Executor - Class
Thread를 직접 작성해야 하기 때문에 상당히 어려운 작업이 될 수 있다. 이 인터페이스는 까다로운 문제를 손쉽게 해결할 수 있도록 도와 준다. 왜냐하면 일부 JVM에서는 Thread를 작성하기가 매우 까다로와서 새 스레드를 작성하는 것보다는 기존 Thread를 다시 사용하는 것이 훨씬 쉽기 때문이다. 하지만 다른 JVM에서는 이와는 상황이 정반대이다. 즉, Thread가 매우 가볍기 때문에 필요할 때 new를 사용하여 새 스레드를 작성하는 것이 훨씬 효율적이다. 물론, 언제나 그렇듯이 머피의 법칙은 존재한다. 어떤 방법을 선택하더라도 하필이면 선택한 방법이 배치 중인 플랫폼에 맞지 않는 방법인지 그 이유를 도무지 알 수가 없다.

Java 개발자가 Thread를 직접 작성하도록 하지 않고 새 스레드를 작성하기 위한 추상 인터페이스인 Executor 인터페이스를 도입했다. Executor를 사용하면 사용자가 직접 new 메소드를 이용해서 Thread 오브젝트를 작성하지 않고도 스레드를 작성할 수 있다.
    
    Executor exec = getAnExecutorFromSomeplace();
    exec.execute(new Runnable() { ... });

Executor를 사용할 때의 주요 단점은 모든 팩토리를 사용할 때 겪게 되는 단점과 같다. 즉, 어디에선가 팩토리를 가져와야 한다는 것이다.
Executor 클래스는 Executor를 구현한 인스턴스를 얻을 수 있는 공통 장소로서의 역할을 수행한다. 하지만 new 메소드(예를 들어, 새 스레드 풀 작성)만 있기 때문에 미리 작성된 인스턴스를 가지고 있지 않다. 따라서 Executor 인스턴스를 작성해서 코드 전체에서 사용하려면 사용자가 직접 모든 작업을 수행해야 한다. (또는 선택한 컨테이너/플랫폼에서 제공하는 인스턴스를 사용할 수 있는 경우도 있다.)

### 8. ExecutorService - Interface
Executor 인터페이스는 Thread의 출처를 고려하지 않아도 되기 때문에 유용하기는 하지만 Java 개발자가 기대하는 일부 기능(예를 들어, 결과를 생성하도록 설계된 스레드를 시작한 후 결과를 사용할 수 있을 때까지 비차단 방식으로 대기하는 기능)을 제공하지 않는다. (이 기능은 데스크탑 애플리케이션에서 일반적으로 사용되는 기능으로, 사용자가 데이터베이스에 액세스해야 하는 UI 작업을 실행한 후 완료되기 전까지 시간이 너무 오래 걸릴 때 해당 작업을 취소하려는 경우에 사용된다.)
이를 위해 훨씬 더 유용한 추상 인터페이스인 ExecutorService 인터페이스를 작성했다. 이 인터페이스는 스레드를 시작하는 팩토리를 통합적으로 제어할 수 있는 서비스로 모델링한다. 예를 들어, 작업별로 execute()를 한 번씩 호출하는 대신 ExecutorService는 작업의 콜렉션을 사용해서 각 작업의 미래 결과를 나타내는 미래 목록을 리턴한다.

### 9. ScheduledExecutorServices
지정된 지연 후에 실행하거나 주기적으로 실행하도록 명령을 예약 할 수있는 ExecutorService이다. 일정 메서드는 다양한 지연을 사용하여 작업을 만들고 취소 또는 실행을 확인하는 데 사용할 수있는 작업 개체를 반환한다. scheduleAtFixedRate 및 scheduleWithFixedDelay 메서드는 취소 될 때까지 주기적으로 실행되는 작업을 만들고 실행한다.
ExecutorService 인터페이스가 유용하기는 하지만 지정된 작업을 정해진 간격이나 특정 시간에 실행하듯이 스케줄링된 방식으로 수행해야 하는 작업도 있다. 이러한 작업은 ExecutorService를 확장한 ScheduledExecutorService를 사용해서 수행할 수 있다.
5초마다 "ping"을 보내는 "하트비트" 명령을 작성하려는 경우 아래 코드와 같이 ScheduledExecutorService를 사용하면 쉽게 작성할 수 있다.

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable pinger = new Runnable() {
            public void run() {
                System.out.println("PING!");
            }
        };
        ses.scheduleAtFixedRate(pinger, 5, 5, TimeUnit.SECONDS);
    }

스레드를 신경 쓰지 않아도 되고, 사용자가 "하트비트" 취소하려는 경우 어떻게 할 것인지를 고려하지 않아도 되며, 명시적으로 스레드를 포그라운드 또는 백그라운드로 표시하지 않아도 된다. 단지 스케줄링 세부 사항을 ScheduledExecutorService에 맡겨 두면 된다.
또한 사용자가 "하트비트" 취소하려고 할 경우 scheduleAtFixedRate 호출은 ScheduledFuture 인스턴스를 리턴한다. 이 인스턴스는 결과가 있을 경우 결과를 랩핑할 뿐만 아니라 스케줄링된 작업을 종료할 수 있는 cancel 메소드가 가지고 있다.

--------

참고 사이트 :

https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/package-summary.html

http://steinfrei.blogspot.kr/2015/03/javautilconcurrent-5-part-2.html

