# java.util.concurrent
### 간단하게 다중스레드 프로그램을 짤 수 있도록 하는 패키지

----

java.util.concurrent 패키지에는 간단하게 다중스레드 프로그램을 짤 수 있도록 하는 고수준(high-level) 병행성 유틸리티들과, 전문 프로그래머들이 자신만의 고수준 병행성 클래스나 메서드 등을 작성할 수 있도록 하는 저수준 병행성 기본연산들이 포함되어있다. java.util.concurrent 패키지에는 굳이 노력하지 않고도 일반적인 동시성 문제점을 효과적으로 해결하는 클래스가 많이 들어 있다.

### 1. TimUnit - Enum
TimeUnit은 콜렉션 클래스는 아니지만, java.util.concurrent.TimeUnit을 열거하면 코드를 매우 쉽게 읽을 수 있다. TimeUnit은 세분화 된 단위에서의 지속 시간을 나타내며, 단위를 변환하고 이러한 단위에서 타이밍 및 지연 작업을 수행하는 유틸리티 방법을 제공한다.

    System.out.println(TimeUnit.SECONDS.toMinutes(60)); // 1;
    System.out.println(TimeUnit.DAYS.toHours(1));       // 24;

### 2. CopyOnWriteArrayList - Class
배열의 새로운 사본을 작성하는 것은 일상적인 사용을 고려하면 시간과 메모리 오버헤드 측면에서 너무 많은 비용이 드는 작업이다. 개발자들은 그 대신에 동기화된 ArrayList를 사용하곤 했다. 이 또한 반복할 때마다 콜렉션의 내용 전반에 걸쳐 일관성이 보장되도록 읽기와 쓰기를 비롯한 모든 작업을 동기화해야 하기 떄문에 노력이 많이 드는 옵션이다.
CopyOnWriteArrayList는 기본이 되는 배열을 새로운 카피에 복사하여 모든 변형 작업(추가,설정)이 구현되는 Thread-Safe한 ArrayList이다.

    public class Main {
        public static void main(String[] args) {
    
            final List<integer> list = new CopyOnWriteArrayList<integer>();
    
            new WriterThread(list).start();
            new ReaderThread(list).start();
    
        }
    }
copy-on-write는 'write 할 때 copy한다'는 의미로 컬렉션에 대하여 write를 할 때마다, 내부에 확보된 배열을 통째로 복사한다. 이렇게 통째로 복사를 하면 iterator를 사용하여 element들을 순서대로 읽어가는 도중에 element가 변경될 염려가 없다. 따라서 CopyOnWriteArrayList 클래스와 그 iterator가 ConcurrentModificationException을 발생시키는 일은 절대 없다. 단 write를 할 때마다 배열을 통째로 copy 하므로, write가 잦은 경우 성능이 저하될 수 있다. write가 적고 read가 빈번한 경우에 좋다.

### 3. BlokcingQueue - Interface
BlockingQueue는 Queue이며 특정 순서로 삽입된 항목은 동리한 순서로 검색된다. 그러나 요소를 검색 할 때 큐가 비어 있지 않을 때까지 대기하는 작업을 추가로 지원하고 요소를 저장할 때 큐에서 사용 가능한 공간을 기다린다.
1. ArrayBlockingQueue
    - 고정배열에 일반적인 Queue를 구현한 클래스, 생성 후 크기변경 불가
    - 꽉찼을때 추가를 block, 비어있을땐 추출을 block
    - 선택적으로 공평성 정책을 두어 block한 thread들의 순차적 대기열 생성

2. LinkedBlockingQueue
    - 선택적으로 Bound가 가능한 Linked list로 구현한 Queue
    - capacity를 초기에 정해주지 않는 경우 integer.MAX_VALUE로 자동설정
    - 용량을 초과하지 않는 한에서 node는 동적으로 삽입시마다 생성되며 초과 시 block
    - Linked Queue는 일반적으로 배열 기반 큐 보다 동시성 App에서 높은 throughput을 가짐

3. PriorityBlockingQueue
    - PriorityQueue와 같은 정렬 방식을 지니는 용량제한이 없는 Queue, 하지만 Element 추출에 대해 Block기능을 제공.
    - 입력 무제한으로 기본 설계가 되었기 때문에 추가작업 수행중 Fail이 나면 이것은 자원고갈이 났다는 뜻이다.
    - null element 및 non-comparable object를 수용하지 않으며 natural ordering을 지원한다.

4. SynchronousQueue
    - BlockginQueue의 다른 구현이다. ArrayBlockingQueue를 사용한 브로킹을 활용하여 각 스레드 간에 단일 요소를 교환하는 매우 간단한 방법이다.
    - Queue 내부로의 insert 작업이 다른 스레드의 remove 작업과 반드시 동시에 일어나야한다.
    - 이 queue는 내부용량을 지니지 않는다. (단 1개의 공간도...) / null 값 수용 X
    - remove될때만 새로운 element가 insert되므로 추출(peek이라는 함수)을 할 수 없다. 반대로, 다른 스레드가 remove를 시도하지 않는이상 삽입을 할 수도 없다.
    - Queue의 head는 최초 삽입 시도를 한 Thread의 삽입 element가 된다.
    - poll()을 수행하였을 시 Queue에 삽입시도를 한 thread가 없다면 null을 리턴한다.

### 4. ConcurrentMap
여러 스레드가 Map에 액세스하는 경우 containsKey() 또는 get() 을 사용하여 키/값 쌍을 저장하기 전에 받은 키가 있는지 확인하는 것이 일반적이다. 그러나 동기화된 Map을 사용해도 이 프로세스 도중에 조심스럽게 들어가 Map의 제어를 확보할 수 있다.
문제점은 get() 시작 시에 잠금이 필요하며 put() 호출에 잠금이 다시 필요하기 이전에 잠금해제된다는 것이다. 결과는 경쟁 조건과도 같이 두개의 스레드 사이의 경쟁이며 결과물은 누가 먼저 실행하느냐에 따라 달라진다.
두 개의 스레드가 동일한 시기에 메소드를 호출하는 경우 각 스레드가 테스트하고 프로세스에서 첫 번째 스레드의 값을 잃으면서 각 스레드를 보낸다. 다행히 ConcurrentMap 인터페이스는 하나의 잠금 아래 두 가지를 설계하는 다양한 추가 방법을 지원한다. 예를 들어,putIfAbsent()는 먼저 테스트를 수행하고 키가 Map에 저장되지 않은 경우에만 보낸다.
ConcurrentMap은 기본적으로 put() 메서드에 synchronized 키워드가 없다. 코드를 보면 동일 Entry에 추가 될 경우 해당 Entry에 대해서 synchronized를 적용한 것을 확인할 수 있다. 즉, Entry 배열 아이템 별로 동기화 처리를 하고 있는 것이다. 이런 방식은 MultiThread 환경에서 성능을 크게 향상시킨다. 

--------------

참고:

https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/package-summary.html

http://steinfrei.blogspot.kr/2015/03/javautilconcurrent-5-part-1.html

http://so-blog.net/2015/01/04/collection_with_multi_thread/

http://oniondev.egloos.com/558949