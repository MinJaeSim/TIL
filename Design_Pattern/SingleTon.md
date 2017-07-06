# SingleTon
### 오직 한개의 객체를 만들고, 언제 어디서든 동일한 방법을 통해 접근 가능한 객체

-------

## SingleTon은 왜 필요한가??
일반적으론 프로그램을 실행할 때 많은 인스턴스가 생성 된다. 예를 들면 String 클래스의 인스턴스는 문자열 1개에 대해서 1개가 생성되기 때문에 문자열 1000개가 등장하는 프로그램이라면 1000개의 인스턴스가 만들어진다. 하지만 *'클래스의 인스턴스가 단 하나만 필요'*한 경우, 즉 시스템 안에서 1개밖에 존재하는 않는 것을 프로그램으로 표현하고 싶을 때 지정한 클래스의 인스턴스가 *'절대로 1개 밖에 존재하지 않는 것'*을 보증하고 싶을때 SingleTon을 사용한다. [^1]

![패턴 구조](https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Singleton_UML_class_diagram.svg/500px-Singleton_UML_class_diagram.svg.png)

장점 | 단점
--- | ---
어디에서든 동일한 방법으로 동일한 객체를 얻을 수 있다. | 테스트 용이성이 낮다, 객체와 객체간의 결합도가 높아진다.

-------

## Eager Initialization
가장 보편적이면서 많이 쓰이는 방법이다. 생성자에 private을 명시하여서 new 키워드를 사용할 수 없게 하여서 다른 클래스에서 Cursor instance = new Cursor(); 방법을 통해 인스턴스를 생성할 수 없게 한다. 다른 클래스에서 Cursor 클래스를 참조하려면 getInstance() 메소드를 사용하는 방법 밖에 없다.

    class Cursor {

      private static final Cursor INSTANCE = new Cursor();
      private Cursor() {
          System.out.println("...");
      }
      public static Cursor getInstance() {
          return INSTANCE;
      }
    }

##### **Eager Initialization 기법의 문제점** 
###### 1. 특수한 경우에 객체가 생성될 수 있다. ex) Reflection, serialization(마샬링이라고도 부른다)

    class Cursor implements Serializable {
      private Cursor() {
      }
   
      private Object readResolve() {
        return INSTANCE;
      }
   
      private static final Cursor INSTANCE = new Cursor();
  
      public static Cursor getInstance() {
        return INSTANCE;
      }
    }[^2]

###### 2. Cursor.class 를 JVM에 로딩할 때 정적 객체가 생성됨으로 프로그램 시작에 악영향을 미칠 수 있다.

##### 문제점 1 해결책 : Enum SingleTon
Reflection을 통해 생성하는 것이 불가능 하고, Serialization에 의한 객체 생성되 자동적으로 처리해준다.

    enum Cursor {
      INSTANCE;
  
      public static Cursor getInstance() {
        return INSTANCE;
      }
    }

## Lazy Initialization
Eager Initialization 방식으로 구현된 SingleTon은 INSTANCE가 static final로 선언 되어 있다. 하지만 static final로 변수를 선언하면 INSTANCE는 프로그램이 시작할 때 생성되고, 프로그램이 종료될 때 파괴된다. 만약 프로그램에서 INSTANCE가 사용되지 않는다면, 프로그램의 초기비용만 높아지고 괜한 자원만 소비된것이다. 아를 해결하기 위해 실제 필요할때 객체를 생성하는 Lazy Initialization 기법이 있다.[^3] 

    class Cursor {
        private static Cursor sInstance;
        private Cursor() {}
        public static Cursor getInstance() {
            if (sInstance == null) {
                sInstance = new Cursor();
            }
            return sInstance;
        }
    }

##### **Lazy Initialization 기법의 문제점** 
Multi Thread 환경에서 Thread Safety 가 없다.

해결책?? Synchronized Method
  
    public synchronized static Cursor getInstance() {
        if (sInstance == null) {
            sInstance = new Cursor();
        }
        return sInstance;
    }

*그러나 위의 방법은 getInstance 메소드를 호출 할때마다 동기화를 하기 때문에 **성능에 악영향**을 미친다.*

## DCLP(Double Check Locking Pattern)

    public static Cursor getInstance() {
      if (sInstance == null) {
        synchronized (Cursor.class) {
            if (sInstance == null) {
            sInstance = new Cursor();
            }
        }
      }

      return sInstance;
    }

객체를 생성하는 코드에만 LOCK을 걸었다.
-> 문제점 : 코드가 선언적이지 않다. 가독성이 떨어진다.

## IODH(Initialization On Demand Holder)
- JLS(Java Language Spec)
- 내부 정적 클래스의 객체는 .class가 로딩되는 시점에 생성되지 않는다.

      class Cursor {
        private Cursor() {
          System.out.println("Cursor()");
        }
        
        private static class Singleton {
            private static final Cursor INSTANCE = new Cursor();
        }
      
        public static Cursor getInstance() {
          return Singleton.INSTANCE;
        }
      }

----------

[^1]:http://jellyms.kr/192

[^2]:http://dev-ahn.tistory.com/119

[^3]:http://cleancodes.tistory.com/14