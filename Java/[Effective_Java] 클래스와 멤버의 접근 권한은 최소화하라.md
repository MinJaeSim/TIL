## 클래스와 멤버의 접근 권한은 최소화 하라.
### 각 클래스와 멤버는 가능한 한 접근 불가능하도록 만들어라!

-----

잘 설계된 모듈은 구현 세부사항을 전부 API 뒤쪽에 감춘다. 모듈들은 이 API를 통해서만 서로 통신하며, 각자 내부적으로 무엇을 하지는 신경 쓰지 않는다.
이러한 개념을 **정보 은닉** 또는 **캡슐화** 라고한다.

**정보 은닉은 시스템을 구성하는 모듈 사이의 의존성을 낮춰준다. -> 시스템의 개발속도 상승, 유지보수의 부담 감소, 재사용 가능성 증가**

자바는 정보 은닉 원칙을 위해 접근 제어 메커니즘은 클래스와 인터페스, 그리고 그 멤버들의 접근 권한을 규정한다.  
어떤 개체의 접근 권한은 해당 개체가 선언된 위치와 권한 수정자(private, protected, public 등)에 의해 결정된다.  
**권한 수정자의 적절한 사용은 정보 은닉 원칙의 핵심이다**

### 각 클래스와 멤버는 가능한 한 접근 불가능하도록 만들어라!

최상위 레벨 클래스와 인터페이스에 부여할 수 있는 접근 권한은 package-private(=default)과 public 두 가지다. public을 붙이면 해당 개체는 전역적 개체가 되고, 붙이지 않는다면 해당 패키지 안에서만 유효한 개체가 된다.
**최상위 클래스나 인터페이스는 가능한 package-private으로 선언해야 한다.**  
package-private으로 선언하면 API의 일부가 아니라 구현 세부사항에 속하게 되므로, 다음번 릴리스에 클라이언트 코드를 깨뜨릴 걱정 없이 자유로이 변경하거나 삭제하거나, 대체할 수 있게 된다.

만일 package-private로 선언된 최상위 레벨 클래스나 인터페이스를 사용하는 클래스, 즉 사용자 클래스가 하나뿐이라면, 해당 최상위 레벨 클래스를 사용자 클래스의 private 중첩 클래스로 만들 것을 고려해 보라.

필드나 메서드, 중첩 클래스, 중첩 인터페이스 같은 멤버의 접근 권한은 아래의 네 개 중 하나로 설정할 수 있다.

private = 최상위 레벨 클래스 내부에서만 접근 가능하다.

package-private = 같은 패키지 내의 아무 클래스나 사용할 수 잇다. 기본 접근 권한(default access)으로 알려져 있는데, 멤버를 선언할 때 아무런 접근 권한 수정자도 붙이지 않으면, 이 권한이 주어진다.

protected = 선언된 클래스 및 그 하위 클래스만 사용할 수 있다. 선언된 클래스와 같은 패키지에 있는 클래스에서도 사용 가능하다.

public = 이렇게 선언된 멤버는 어디서도 사용이 가능하다.

protected로 선언한 멤버는 해당 클래스의 공개 API이며, 영원히 유지해야 한다. 또한, 공개된 클래스의 protected 멤버는 해당 클래스의 구현 세부사항에 대한 공개적 약속과도 같으므로 **protected 멤버 사용은 자제해야 한다**.

**위 클래스 메소드를 재정의할 때는 원래 메소드의 접근 권한보다 낮은 권한을 설정할 수 없다.**  
따라서 특정한 인터페이스를 구현하는 클래스를 만들 때는 인터페이스의 모든 멤버는 원래 public이므로 인터페이스에 속한 모든 메소드를 해당 클래스의 public 메소드로 선언해야 한다.

### 객체 필드는 절대로 public으로 선언하면 안된다.

    //이런 저급한 클래스는 절대로 public으로 선언하지 말것
    class Point {
        public double x;
        public double y;
    }
이런 클래스는 데이터 필드를 직접 조작할 수 있어서 캡슐화의 이점을 누릴수가 없다. private 필드와 public 접근자 메소드(getter)로 바꿔야 한다.

     class Point {
         private double x;
         private double y;

         public Point(double x, double y) {
             this.x = x;
             this.y = y;
         }

         public double getX() {return x;}
         public double getY() {return y;}

         public void setX(double x) {this.x = x;}
         public void setY(double y) {this.y = y;}
     }

비-final 필드나 변경 가능 객체에 대한 final 참조 필드를 public으로 선언하면, 메소드를 통하지 않고도 필드의 값을 편경할 수 있기 때문에 필드에 저장될 값을 제한할 수 없게 된다. 따라서 그 필드에 관계된 불변식을 강제할 수 없다.  
필드가 변경될 때 특정한 동작이 실행되도록 할 수 없음로, 변경 가능 public 필드를 가진 클래스는 다중 스레드에 안전하지 않다.

**public** 클래스는 변경 가능 필드를 외부로 공개하면 안된다. 변경 불가능 필드인 경우에는 외부로 공개하더라도 많이 위험하진 않지만, 그럴 필요가 있는지는 여전히 의문이다. 하지만 package-private나 private로 선언된 중첩 클래스의 필드는 그 변경 가능 여부와는 상관없이 외부로 공개하는 것이 바람직할 때도 있다.

**어떤 상수들이 클래스로 추상화된 결과물의 핵심적 부분을 구성한다고 판단되는 경우, 해당 상수들은 public static final 필드들로 선언하여 공개할 수 있다.**  
이런 필드들은 관습적으로 대문자로 구성된 이름을 가지며, 이름을 구성하는 단어들은 밑줄 기호로 구분한다. 또한 이런 필드들은 반드시 기본 자료형 값들을 갖거나, 변경 불가능 객체를 참조해야 한다.

길이가 0이 아닌 배열은 언제나 변경 가능하므로, public static final 배열 필드를 두거나, 배열 필드를 반환하는 접근자를 정의하면 안된다.  
그런 멤버를 두면 클라이언트가 배열 내용을 변경할 수 있게 되므로, 보안에 문제가 생긴다.  

    // 보안 문제를 초래할 수 있는 코드
    public static fianl Thing[] VALUES = {...};

이 문제를 해결하기 위한 방법은 아래의 2가지 방법이 있다.  

1.  
```java
    private static final Thing[] PRIVATE_VALUES = {...};
    public static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
```

2.  
```java
    private static final Thing[] PRIVATE_VALUES = {...};
    public static final Thing[] values() {
        return PRIVATE_VALUES.clone();
    }
```
클라이언트가 어떤 작업을 원하는지에 따라 두 가지 방법 중 하나를 골라야한다.


요약  
**접근 권한은 가능한 낮춰라.** 최소한의 public API를 설계한 다음, 다른 모든 클래스, 인터페이스, 멤버는 API에서 제외하라. public static final필드를 제외한 어떤 필드도 public으로 선언하지 마라. 그리고 public static final 필드가 참조하는 객체는 변경 불가능 객체로 만들라.

-------

참고:

Bloch, Joshua. Effective Java (2/E). 인사이트, 2014