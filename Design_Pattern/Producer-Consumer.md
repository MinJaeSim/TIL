## Producer-Consumer Pattern
### 멀티쓰레드 디자인패턴 

-----

Producer-Consumer 패턴에서 producer[생산자]는 데이터를 작성하는 쓰레드, consumer[소비자]는 데이터를 이용하는 클래스를 말한다.  
Producer-Consumer 패턴에서는 생산자와 소비자 사이에 중개 역할이 존재하는데 이 중개 역할이 양쪽 쓰레드간 처리 속도의 차이를 메우게 된다. 대개는 생산자나 소비자나 모두 여럿인 경우를 떠올리지만 생산자와 소비자가 모두 단수인 경우도 있따, 단수일 때는 Pipe 패턴이라 부르기도 한다.

예제 프로그램  
: 3명의 요리사가 음식을 만들어 테이블에 놓고, 3명의 손님이 음식을 먹는 프로그램

1. Main        : 동작 테스트용 클래스
2. MakerThread : 요리사를 나타내는 클래스
3. EaterThread : 손님을 나타내는 클래스
4. Table       : 테이블을 나타내는 클래스

Main 클래스  
: 테이블을 준비하고 요리사와 손님을 타내는 쓰레드를 기동한다.
```java
public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        new MakerThread("Maker-1",table,1).start();
        new MakerThread("Maker-2",table,2).start();
        new MakerThread("Maker-3",table,3).start();
        new EatherThread("Eater-1",table,9).start();
        new EatherThread("Eater-2",table,8).start();
        new EatherThread("Eater-3",table,7).start();
    }
}
```

MakerThread 클래스  
: 음식을 만들어 테이블에 놓는다. MakerThread는 얼마간의 시간(0 이상 약 10000밀리 초 미만) 동안 일시 정지한다. 그 후 만들어진 음식을 Table 클래스의 put 메소드로 테이블에 놓는다. 일시 정지 시간은 예상하는 음식을 만드는 작업에 걸리는 시간이다. MakerThread는 음식을 만들어서 -> 테이블에 놓는다. 영원히 반복하는 음식의 생산이다.
```java
import java.util.Random;

public class MakerThread extends Thread {
    private final Random random;
    private final Table table;
    private static int id = 0;
    public MakerThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    public void run() {
        try {
            while (True) {
                Thread.sleep(random.nextInt(1000));
                String cake = "[Cake No." + nextId() + " by " + getName()+ "]";
                table.put(cake);  
            }
        } catch (InterruptedException e) {
        }
    }

    private static synchronized int nextId() {
        return id++;
    }
}
```

EaterThread 클래스  
: 이 클래스는 테이블에 놓인 음식을 먹는 손님을 나타내는 클래스(단순히 sleep을 하는 클래스)이다. 테이블에 놓인 음식을 Table 클래스의 takex 메소드로 먹는다. 그 후, MakerThread 클래스와 마찬가지로 얼마의 시간동안 일지정지 한다. 이 일시 정지 시간은 예상하는 음식을 먹는 시간이다. EaterThread는 테이블에서 음식을 집어서 -> 먹는다 를 영원히 반보가는 음식의 소비자이다.
```java
import java.util.Random;

public class EaterThread extends Thread {
    private final Random random;
    private final Table table;
    public EaterThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    public void run() {
        try {
            while (True) {
                String cake = table.take();
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
        }
    }

    private static synchronized int nextId() {
        return id++;
    }
}
```

Table 클래스  
: 이 클래스는 음식을 올려놓는 테이블을 나타낸다, 테이블에 올려 놓을 수 있는 음식의 개수는 생성자로 지정한다. Table 클래스는 음식을 실제로 놓아둘 장소로서, buffer 필드라고 하는 String의 배열(String[])을 준비한다.  
- tail 필드 : 다음에 음식을 put할 장소를 나타낸다.
- head 필드 : 다음에 으식을 take할 장소를 나타낸다.
- count 필드 : 현재 이 테이블에 놓여 있는 음식의 개수를 나타낸다.
```java
public class Table {
    private final String[] buffer;
    private int tail;
    private int head;
    private int count;

    public Table(int count) {
        this.buffer = new String[count];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    // 음식을 놓는다.
    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        while (count >= buffer.length) {
            wait();
        }
        buffer[tail] = cake;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();
    }

    // 음식을 먹는다.
    public synchronized String take() throws InterruptedException {
        while (count <> 0 {
            wait();
        }
        String cake = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }
}
```

Producer-Consumer 패턴의 요소들

Data  
: Data는 Producer에 의해 만들어지며 Consumer에 의해 이용된다. 위의 예제에서는 String 클래스(음식 cake)가 이 역할을 담당했다.

Producer  
: Producer는 Data를 작성하여 Channel에 건넨다. 예제 프로그램에서는 MakerThread 클래스가 이 역할을 담당한다.

Consumer  
: Consumer는 Channel로 부터 Data를 받아 이용한다. 예제 프로그램에서는 EaterThread 클래스가 이 역할을 담당했다.

Channel  
: Channel은 producer로 부터 Data를 받아서 보관한다. 또한 Consumer가 요구할 때 Data를 건낸다. Channel의 역할은 안전성을 확보하기 위해 Producer과 Consumer가 액세스하는 것에 대하여 배타 제어를 실행한다. Channel 역할은 Producer나 Consumer가 여러 개 존재하는 경우에도 서로 처리를 간섭 받지 않게끔 배타제어를 실행한다. 이처럼 Channel은 Producer와 Consumer 사이에서 Data를 전달하는 중계 지점으로서의 역할, 통신로의 역할을 담당한다. 예제 프로그램에서는 Table 클래스가 이 역할을 담당했다.  



Producer-Consumer 패턴에서 안전성을 지킬 책임은 Channel 에 있다. Channel은 쓰레드 사이에서 배타제어를 실행하여 Producer 역할로부터 Consumer 역할에게 정확하게 Data를 전달한다.  
Prouducer가 Consumer에게 Channel을 안거치고 직접 Data를 전달하면 안될까 라는 의문이 생길 수 있다. 메소드를 직접 부르는 방법과 Channel 역할을 중간에 세우는 방법의 차이가 존재하다.

- 메소드를 직접 호출하는 방법   
Consumer가 Data 를 받아들이려 하는 건 대체로 그 Data를 사용하여 어떠한 처리를 하기 위함이다. Consumer의 메소드를 Producer가 직접 호출하면 처리를 실행하는 것이 Consumer 쓰레드가 아닌 Producer 쓰레드가 된다.  
그렇게 되면 그 처리에 걸리는 시간은 Producer 역할의 쓰레드가 부담해야 하며 그만큼 다음 데이터를 준비하는 것이 늦어지게 된다.

- Channel 역할을 중간에 세우는 방법  
Producer 역할은 Data를 Channel에게 건네게 되면 Consumer가 그 Data를 처리하는 것을 기다리지 않고 곧바로 Data를 준비를 시작할 수 있다. 즉, Producer가 계속해서 Data를 만들 수가 있다. 즉 Producer가 Consumer의 처리 상황에 좌우되지 않게 된다.


