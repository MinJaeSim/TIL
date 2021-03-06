## Iterator 반복자
### collection의 내부 구조에 상관없이 요소를 열거할 수 있는 객체

-----

Map | Collection
------|------
키(Key) 와 값(Value)의 쌍으로 이루어진 자료구조. 순서는 유지되지 않고, 키는 중복을 허용하지 않으며 값의 중복을 허용한다. | 순서가 존재하고 중복을 허용하는 선형적인 자료 구조 (List, Vector, Array)와 순서가 존재하지 않고 중복을 허용하지 않는 집합적인 자료 구조 (Set)로 구성되어 있다.

------

**순회(Iteration)**  or  **열거(Enumeration)**
Design Pattern 23가지, Iterator Pattern
의도(Intent) - **Collection**의 내부 구조와 상관없이 요소를 열거하는 객체

#### Iterator 와 Enumeration의 차이
Iterator는 Enumeration을 더 쉽게 사용하기 위해 만든 것으로 Enumeration에 비해 메소드 이름이 개선되었고, 순회 중 remove() 메소드를 통해 element를 제거할 수 있다. 

----------

### Iterator Pattern
Pattern은 Iterator를 사용하여 집합 객체 내부의 기본 표현을 노출시키지 않으면서 집합 객체의 요소에 순차적으로 액세스 할 수있는 방법을 제공하는 것이다.  
이 패턴의 장점은 집합 객체의 코드가 변경되어도 순회하는 코드에 영향이 없는것이다.  또한 하나의 집합객체에 여러가지 순회방법을 정의할 수 있는데,  즉,  List로 구현하든 Vector로 구현하든 집합객체를 순회하는 코드에는 영향이 없다고 볼 수 있다. 

![패턴 구조](https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Iterator_UML_class_diagram.svg/1000px-Iterator_UML_class_diagram.svg.png)

이름 | 역할
---|---
Aggregate (집합체) | Iterator역할을 만들어내는 인터페이스를 결정한다.
ConcreteAggregate (구체적 집합체) | Aggregate역할이 결정한 인터페이스를 실제로 구현하는 일을 한다다. 구체적인 Iterator 역할, 즉 ConcreteIterator역할의 인스턴스를 만들어 낸다.
Iterator (반복자) | 요소를 순서대로 검색해가는 인터페이스를 결정하고, 다음 요소가 존재하는지를 얻기위한 hasNext()와 다음 요소를 얻기 위한 next()를 결정한다.
ConcreteIterator (구체적 반복자) | Iterator가 결정한 인터페이스를 실제로 구현한다.


#### Iterable
Iterator(collection의 내부 구조에 상관없이 요소를 열거할 수 있는 객체)를 가지고 있음을 명시한다.

#### Iterator
collection의 내부 구조에 상관없이 요소를 열거할 수 있는 객체로써 아래와 같은 메소드를 제공한다.
- boolean hasNext();  // 다음 위치로 이동 가능한가?
- E next(); 			// 현재의 값을 리턴하고, 다음 위치로 이동한다. 
- default void remove();
- default void forEachRemaining(Consumer<? super E> action)

#### ListIterator
ListIterator는 기존의 Iterator와는 다르게 
- boolean hasPrevious();
- E previous();
와 같은 메소드가 존재하여 뒤로도 갈 수 있다. 

```java
    ArrayList<Integer> test = new ArrayList<>();
    test.add(1);
    test.add(2);
    test.add(3);
    test.add(4);

    /*----명령형 프로그래밍----*/

    // 자바5 이전 컬렉션을 순회하기 위한 코드
    Iterator iterator = test.iterator();
    while (iterator.hasNext()) {
    System.out.println(iterator.next());
    }

    // 자바5 이후 향상된 for (for-each문) 지원
    for (Integer e : s) {
    System.out.println(e);
    }

    // 향상된 for문의 경우 순회 중 Collection 내부가 변경된 경우 ConcurrentModificationException이 발생 한다.
    for (Integer e : s) {
    System.out.println(e);
    test.remove(s) <- 런타임 중 에러 발생
    }


    /*----선언형 프로그래밍----*/
    // 자바8 이후 람다 기능이 추가된 forEach문 제공
    // test.forEach(t -> System.out.println(t)); 를 더 간단히 하여 아래와 같이 표현 가능
    test.forEach(System.out::println);


    Iterator i = test.iterator();
    i.forEachRemaining(t -> System.out.println(t));
```

#### 선언형 프로그래밍(Declarative Programming)
> 한 정의에 따르면, 프로그램이 어떤 방법으로 해야 하는지를 나타내기보다 무엇과 같은지를 설명하는 경우에 "선언형"이라고 한다.
- 장점 : 이해하기 쉽고 간결한 코드를 제공한다. 명령형 구조를 사용할때 보다 재사용 가능한 코드를 만드는것이 더 쉽다.


#### 명령형 프로그래밍(Imperative Programming)
> 컴퓨터 과학에서 명령형 프로그래밍(Imperative programming)은 선언형 프로그래밍과 반대되는 개념으로, 프로그래밍의 상태와 상태를 변경시키는 구문의 관점에서 연산을 설명하는 프로그래밍 패러다임의 일종이다.
- 장점 : 익숙한 프로그래밍 방식이다.


----------

참고:

https://en.wikipedia.org/wiki/Iterator_pattern#JavaIterator

http://idsg.tistory.com/8

