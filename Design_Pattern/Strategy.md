# Strategy Pattern
### 알고리즘군을 정의하고 각각을 캡슐화하여 교환해서 사용할 수 있도록 하는 패턴

-------

## 전략 패턴이란?
전략 패턴은 알고리즘 군을 정의하고 알고리즘을 각각 하나의 클래스로 캡슐화한 다음, 필요할 때 서로 교환해서 사용할 수 있게 해주는 패턴이다.  
이 패턴을 사용하면 클라이언트와 무관하게 독립적으로 알고리즘을 변경할 수 있고, 클라이언트는 독립적으로 원하는 알고리즘을 사용할 수 있다. 즉 이 패턴은 클라이언트에게 알고리즘이 사용하는 데이터나 그 구조를 숨겨주는 역할을 한다. 전략 패턴은 알고리즘을 사용하는 곳과, 알고리즘을 제공하는 곳을 분리시킨 구조로 알고리즘을 동적으로 교체할 수 있다.[^1]

![전략패턴](https://sourcemaking.com/files/v2/content/patterns/Strategy1.svg)

- **Strategy** : 전략을 이용하기 위한 인터페이스 결정

- **ConcreteStrategy** : Strategy의 인터페이스를 실제로 구현, 실제 전략(알고리즘)을 프로그래밍한다.

- **Context** : Context는 Strategy를 이용하는 역할, ConcreteStrategy의 인스턴스를 가지고 있으며 그것을 이용.

## 예제 코드 [^2]

    //Strategy.java
    public interface Strategy {
        public int doOperation(int num1, int num2);
    }

    //OperationAdd.java
    public class OperationAdd implements Strategy{
        @Override
        public int doOperation(int num1, int num2) {
            return num1 + num2;
        }
    }

    //OperationSubstract.java
    public class OperationSubstract implements Strategy{
        @Override
        public int doOperation(int num1, int num2) {
            return num1 - num2;
        }
    }

    //OperationMultiply.java
    public class OperationMultiply implements Strategy{
        @Override
        public int doOperation(int num1, int num2) {
            return num1 * num2;
        }
    }

    //Context.java
    public class Context {
        private Strategy strategy;

        public Context(Strategy strategy){
            this.strategy = strategy;
        }

        public int executeStrategy(int num1, int num2){
            return strategy.doOperation(num1, num2);
        }
    }

    //StrategyPatternDemo.java
    public class StrategyPatternDemo {
        public static void main(String[] args) {
            Context context = new Context(new OperationAdd());		
            System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

            context = new Context(new OperationSubstract());		
            System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

            context = new Context(new OperationMultiply());		
            System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
        }
    }

### 장점[^3]
1. 알고리즘의 재사용이 가능하다. 
2. 알고리즘은 Context와 느슨하게 결합되기 때문에, Context의 코드를 변경하지 않고도 알고리즘을 변경이 가능하다. 
3. 새로운 알고리즘 추가가 용이하다. 

### 단점 [^3]
1. 최선의 전략을 선택하기 위해선 클라이언트가 다양한 전략을 알고있어야 한다. 이는 클라이언트 입장에서는 부담스러운 일이다.
2. 프로그램이 참조해야 할 전체 객체 수가 증가한다.

## Rule of Thumb[^4]
1. 전략 패턴은 세분성을 제외하고는 템플릿 메소드 패턴 방식과 같다.
2. 스테이트 패턴은 의도를 제외하곤 전략과 같다.
3. 스테이트 패턴, 전략 패턴, 브릿지 패턴은 비슷한 솔루션 구조를 가지고 있다. 이것들은 전부 'handle/body' 관용구를 공유한다. 그러나 그들은 각각 서로 다른 문제를 해결한다.


## 전략 패턴과 템플릿 메소드 패턴 비교
템플릿 메소드 패턴은 변하지 앟는 전체 알고리즘은 부모에서 제공하고 변하는 것은 자식이 재정의하는 설계 방법이다. 즉, 부모 클래스의 책임을 하위클래스로 위임시켜 하위클래스가 사용의 주체가 된다. 전략 패턴에서는 책임을 객체로 위임시켜, 부모 클래스안에서 전략 객체가 작동하도록 하는 것이다.  
정리하면 부모클래스 메소드가 if문 등으로 다중 중첩되 있는 경우, 템플릿 메소드 패턴, 전략 패턴 등을 이용해서 부모클래스의 책임을 위임하는 것을 고민하여 재사용성을 높여야 한다.

---------

[^1]:http://terms.naver.com/entry.nhn?docId=3532973&cid=58528&categoryId=58528

[^2]:https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm

[^3]:https://kodelog.wordpress.com/2013/03/27/strategy-design-pattern/

[^4]:https://sourcemaking.com/design_patterns/strategy%20

