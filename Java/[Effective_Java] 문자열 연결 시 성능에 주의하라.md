# [Java] String 연결 시 성능에 주의하라
### String 타입을 연결(Concatenation) 할 때는 StringBuilder 또는 StringBuffer 을 사용하자. 

-------

## n개의 String에 연결 연산자(+)를 반복 적용해서 연결하는 데 드는 시간은 n^2에 비례한다.
String은 불변 객체이다. 불변 객체는 상태 변경이 불가능하기 때문에 다른 상태를 가지기 위해서는 매번 새로운 객체를 생성해야 한다. + 연산자는 여러 문자열을 하나로 합하는 편리한 수단이다. 한 줄 정도를 출력할 때나, 몇 개 정도의 객체를 문자열로 변환해서 연결할 때는 좋다. 하지만 여러 String을 연결해야 한다면 성능에 문제가 생긴다.  
이를 해결 하기 위해 StringBuilder 또는 StringBuffer을 사용해서 String을 연결해야 한다. 성능을 직접 코드로 확인해보면 더욱 큰 차이를 느낄 수 있다.

    @Test
    public void foo() {
        String s = "";
        for (int i = 0 ; i < 100000 ; ++i)
            s += i;
    }

위의 코드는 문자열을 연결하기 위해 StringBuilder대신 연결 연산자 + 를 사용한 코드이다.  위 코드의 테스트 소요 시간은 평균 약 20초 ~ 22초 사이다.

    @Test
    public void foo2() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < 100000 ; ++i)
            stringBuilder.append(i);
        String s = stringBuilder.toString();
    }

위의 코드는 StringBuilder를 이용해 문자열은 연결한 코드이다. 위 코드의 테스트 소요 시간은 평균 약 0.02초 ~ 0.025초로 결과가 나왔다.


### 불변 객체??
객체 지향 프로그래밍에 있어서 불변객체(immutable object)는 생성 후 그 상태를 바꿀 수 없는 객체를 말한다. 반대 개념으로는 가변(mutable) 객체로 생성 후에도 상태를 변경할 수 있다. 객체 전체가 불변인 것도 있고, C++에서 const 데이터 멤버를 사용하는 경우와 같이 일부 속성만 불변인 것도 있다. 또, 경우에 따라서는 내부에서 사용하는 속성이 변화해도 외부에서 그 객체의 상태가 변하지 않은 것 처럼 보인다면 불변 객체로 보기도 한다.

#### 불변객체의 장점
1. 생성자의 방어 복사 및 접근 메소드의 방어 복사가 필요없다.
2. 병렬 프로그래밍을 작성할 때, 동기화 없이 객체를 공유 가능하다. - "특별한 이유가 없다면 객체를 불변 객체로 설계해야 한다. : Effective Java"

#### 불변객체의 단점 
1. 객체가 가지는 값마다 새로운 객체가 필요하다.

#### String 예시 1)

아래의 코드의 결과값은 어떤 값이 나올까??

    String s = "hello";
    s.replace("h", "x");

    System.out.println(s);

위 코드의 결과값은 hello 이다. String은 불변객체이므로 값이 변경되지 않음을 확인할 수 있다. 그렇다면 우리가 원하는 값인 xello를 얻으려면 어떻게 해야 할까? 이를 제대로 얻으려면 아래와 같이 코드를 수정해야 한다.

    String s = "hello";
    s = s.replace("h", "x");

위와 같이 코드를 수정 하면 결과값은 제대로 xello 가 출력된다. 

#### String 예시 2)

    String s1 = "hello";
    String s2 = "hello";
    String s3 = new String("hello");

    if (s1.equals(s2) && s1.equals(s3) && s2.equals(s3))
        System.out.println("same");
    else
        System.out.println("diffrent");

equal은 내용을 비교하는 연산자 이므로 위의 결과값은 same 출력된다.

    if (s1 == s2)
        System.out.println("same");
    else
        System.out.println("different");

== 연산자는 주소값을 비교하는 연산자 이다. s1 과 s2의 경우 같은 "Hello" 를 가리키고 있기 때문에, 'same'이 출력된다. 그러나 'if (s2 == s3)' 로 조건을 바꾸게 되면 'different' 이 출력된다. new 로 새로운 String 객체를 생성하였기 때문에, s3은 s1,s2와 다르게 새로운 'Hello' 객체를 가르키기 때문이다. 


-------

참고

https://ko.wikipedia.org/wiki/%EB%B6%88%EB%B3%80%EA%B0%9D%EC%B2%B4


http://dev-ahn.tistory.com/126

Bloch, Joshua. Effective Java (2/E). 인사이트, 2014