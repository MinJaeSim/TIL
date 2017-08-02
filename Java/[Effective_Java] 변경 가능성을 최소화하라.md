## 변경 가능성을 최소화하라
### 가능한 변경 불가능 클래스를 사용하되 변경 불가능 클래스로 만들 수 없다면, 변경 가능성을 최대한 제한하라.

------

변경 불가능(immutable) 클래스는 그 객체를 수정할 수 없는 클래스다. 객체 내부의 정보는 객체가 생성될 때 주어진 것이며, 객체가 살아 있는 동안 그대로 보존된다. String, 기본 자료형 클래스, BigInteger, BigDecimal 등이 그런 클래스다.  
변경 불가능 클래스는 변경 가능 클래스보다 설계하기 쉽고, 구현하기 쉬염, 사용하고기도 쉽다. 오류 가능성도 적고, 더 안전하다.

#### 변경 불가능 클래스를 만들 때는 아래의 다섯 규칙을 따르면 된다.

1. 객체 상태를 변경하는 메소드(수정자 메소드 등)를 제공하지 않는다.
2. 계승할 수 없도록 한다. 잘못 작성되거나 악의적인 하위 클래스가 객체 상태가 변경된 것 처럼 동작해서 변경 불가능성을 깨뜨리는 일을 막을 수 있다. 계승을 금지하려면 보통 클래스를 final로 선언하면 된다.
3. 모든 필드를 final로 선언한다. 그러면 시스템이 강제하는 형태대로 프로그래머의 의도가 분명히 드러난다. 또한 자바 메모리 모델에 명시된 바와 같이, 새로 생성된 객체에 대한 참조가 동기화 없이 다른 스레드로 전달되어도 안전하다.
4. 모든 필드를 private로 선언한다. 그러면 클라이언트 필드가 참조하는 변경 가능 객체를 직접 수정하는 일을 막을 수 있다. 
5. 변경 가능 컴포넌트에 대한 독점적 접근권을 보장한다. 클래스에 포함된 변경 가능 객체에 대한 참조를 클라이언트는 획득할 수 없어야 한다. 그런 필드는 클라이언트가 초기화해서는 안되고, 접근자 또한 그런 필드를 반환해서는 안 된다. 따라서 생성자나 접근자, readObject 메소드 안에서는 방어적 복사본을 만들어야 한다.
```java
    public final class Complex {
        private final double re;
        private final double im;

        public Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public double realPart() { return re; }
        public double imaginaryPart() { return im; }
        
        public Complex add(Complex c) {
            return new Complex(re + c.re, im + c.im);
        }

        public Complex substract(Complex c) {...}
        public Complex multiply(Complex c) {...}
        public Complex divide(Complex c) {...}

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Complex)) return false;
            Complex c = (Complex) o;

            return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
        }
        
        @Override
        public int hashCode() {
            int result = 17 + hashDouble(re);
            result = 31 * result + hashDouble(im);
            return result;
        }

        private static int hashDouble(double bal) {
            long longBits = Double.doubleToLongBits(val);
            return (int) (longBits ^ (longBits >>> 32));
        }

        @Override
        public String toString() {
            return "(" + re + " + " + im + "i)";
        }
    }
```
위 클래스는 복소수를 표현하는 클래스다. 사칙연산 각각은 this 객체를 변경하는 대신 새로운 Complex 객체를 만들어 반환하도록 구현되어 있음에 유의하기 바란다. 대부분의 변경 불가능 클래스가 따르는 패턴이다.  
함수형 접근법(functional approach)으로도 알려져 있는데, 피연산자를 변경하는 대신, 연산을 적용한 결과를 새롭게 만들어 반환하기 때문이다. 

변경 불가능한 객체는 단순하다. 생성될 때 부여된 한 가지 상태만 갖는다. 따라서 생성자가 불변식을 확실히 따른다면, 해당 객체는 불변식을 절대로 어기지 않게 된다.  

변경 불가능 객체는 스레드에 안전할 수 밖에 없다. 어떤 동기화도 필요없으며, 여러 스레드가 동시에 사용해도 상태가 훼손될 일이 없다. 따라서 변경 불가능한 객체는 자유롭게 공유할 수 있다.

변경 불가능한 객체는 그 내부도 공유할 수 있다. 예를 들어 BigInteger 클래스는 값의 부호와 크기를 각각 int 변수와 int 배열로 표현한다. negate 메소드는 같은 크기의 값을 부호만 바꿔서 새로운 BigInteger 객체로 반환한다. 그러나 배열은 복사하지 않는다. 새로운 BigInteger 객체도 원래 객체와 같은 내부 배열을 참조한다.

변경 불가능한 객체는 다른 객체의 구성요소로도 훌륭하다. 구성요소들의 상태가 변경되지 않는 객체는 설사 복잡하다 해도 훨씬 쉽게 불변식을 준수할 수 있다.

변경 불가능 객체의 유일한 단점은 값마다 별도의 객체를 만들어야 한다는 점이다. 따라서 객체 생성 비용이 높을 가능성이 있다.

-----

클래스를 final로 선언하면 하위 클래스 정의가 불가능하게 할 수 있지만 그보다 더 유연한 방법으로는 모든 생성자를 private이나 package-private로 선언하고 public 생성자 대신 public 정적 팩토리를 제공하는 것이다. 이 방법을 통해 다시 Complex 클래스를 정의해보자.

    public class Complex {
        private final double re;
        private final double im;

        private Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public static Complex valueOf(double re, double im) {
            return new Complex(re, im)l
        }

        ......
    }

흔히 사용되지는 않지만 최선의 대안일 때가 많은 방법이다. 여러 개의 package-private 구현 클래스를 활용할 수 있게 되므로 유연성도 가장 좋다. 패키지 외부의 클라이언트 입장에서 보면 해당 변경 불가능 클래스는 final로 선언된 것이나 마찬가지인데, 해당 패키지 외부에서는 상속이 불가능한 데다 public 이나 protected 로 선언된 생성자가 없기 때문이다.

요약  
모든 get 메소드마다 그에 대응하는 set 메소드를 두는 것은 피해야 한다. 변경 가능한 클래스로 만들 타당한 이유가 없다면, 반드시 변경 불가능 클래스로 만들어야 한다.  
만약 변경 불가능한 클래스로 만들 수 없다면, 변경 가능성을 최대한 제한하라. 따라서 특별한 이유가 없다면 모든 필드는 final로 선언하라.

--------

참고 :

Bloch, Joshua. Effective Java (2/E). 인사이트, 2014