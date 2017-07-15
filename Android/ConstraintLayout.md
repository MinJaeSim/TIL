# 안드로이드 ConstraintLayout
### 위젯을 유연하게 배치하고 크기를 지정할 수 있는 ViewGroup
-----

![핸들](http://kunny.github.io/assets/posts/lecture/ui/2016/05/22/constraint_layout_1/constraint_handle_both.gif)

ConstraintLayout 내에 배치되는 위젯의 크기 및 Constraint는 세 가지의 핸들을 사용하여 조정할 수 있다.  
위젯과 위젯, 혹은 위젯과 화면간의 관계로 Constraint를 지정하며 관계 설정은 단 방향으로만 가능하므로 상호간에 관계를 맺도록 지정할 수 없다.  

## Developer Guide[^1]

## 1. Relative positioning
Relative positioning은 ConstraintLayout에서 레이아웃을 만드는 기본 구성 요소 중 하나로, 이러한 constraints을 통해 특정 위젯을 다른 위젯에 상대적으로 배치 가능하다. 또한 가로 축과 세로 축에 위젯을 고정 할 수 있다. **reference id로 다른 위젯 또는 부모를 가진다.**

![constraint1](https://developer.android.com/training/constraint-layout/images/parent-constraint_2x.png)  
parent에 수평 Constraint

![constraint2](https://developer.android.com/training/constraint-layout/images/position-constraint_2x.png)  
수평, 수직 Constraint


![constraint3](https://developer.android.com/training/constraint-layout/images/alignment-constraint_2x.png)  
수평 정렬 Constraint

![constraint4](https://developer.android.com/training/constraint-layout/images/alignment-constraint-offset_2x.png)  
Offset 수평 정렬 Constraint

![constraint5](https://developer.android.com/training/constraint-layout/images/baseline-constraint_2x.png)  
Baseline 정렬 Constraint


## 2. Center Constraint[^2]
Center Constraint 를 사용하면 ConstraintLayout의 강력함을 잘 느낄 수 있다.
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
이런 경우 center를 사용한다. bias라는 개념이 있어서 기본 값은 50%이고 위젯의 중앙이다. 이 값을 변경해서 위치를 조정할 수 있고 UI를 좀 더 유연하게 만들 수 있다.

![centering with bias](https://developer.android.com/reference/android/support/constraint/resources/images/centering-positioning-bias.png)

위의 예는 아래의 코드처럼 기본 값인 50% 대신 왼쪽으로 30% 편항된 예시이다.
    
    <Button android:id="@+id/button" ...
        app:layout_constraintHorizontal_bias="0.3"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent/>

## 3. Margins
측면 여백이 설정되면 해당 조건에 적용되어 대상과 면 사이 여백을 적용한다. 일반적인 레이아웃 여백 속성을 사용 할 수 있다.

## 4. GONE 과 Chain [^2]
A 위젯과 A에 constraint를 갖는 B 위젯이 있는데, A 위젯을 GONE으로 마킹하고 싶은 경우, A는 지워졌지만 방정식에서는 여전히 사용된다. A는 한 지점으로 축소돼서 슬라이드 아웃된다. A와 B 사이에 있던 여백은 A가 있을 때만 사용하고 싶은 경우가 있는데, 이와 관련된 강력한 기능으로 Chain이 있다. 양방향으로 작용하는 constraint라고 생각할 수 있는데, 두 위젯 이상의 관계에서 양방향으로 작용한다.

chain의 처음에 특정 속성을 설정할 수 있다. 간단히 chain을 만들고 spread, spread inside, weighted, packed 등의 스타일을 지정해서 chain의 행동을 설정할 수 있다.[^3 

    app:layout_constraintHorizontal_chainStyle="spread"
    app:layout_constraintHorizontal_chainStyle="spread_inside"
    app:layout_constraintHorizontal_chainStyle="packed"

![chain](https://developer.android.com/training/constraint-layout/images/constraint-chain-styles_2x.png)
 
1. Spread : View들이 균등하게 분산된다.   
2. Spread inside : 첫 번째 View와 마지막 View가 체인의 각 끝에 Constraint되고 나머지는 균등하게 분산된다.  
3. Weighted : 체인이 Spread 혹은 Spread inside으로 설정되면 하나 이상의 View를 "match constraints"(0dp)로 설정하여 나머지 공간을 채울 수 있다. 기본적으로 공간은 "match constraints"으로 설정된 각 View 간에 균등하게 분배되지만 layout_constraintHorizontal_weight 및 layout_constraintVertical_weight 속성을 사용하여 각보기에 중요도 가중치를 지정할 수 있다.
4. Packed : View들이 여백을 계산한 후 함께 포장된다. Head view bias를 조정을 통해 
전체 체인의 bias를 조정할 수 있다.

## 5. Dimensions ConstraintLayout
![dimension](https://developer.android.com/reference/android/support/constraint/resources/images/dimension-match-constraints.png)

(a) : 처음 두 개는 다른 레이아웃과 비슷한 방식으로 작동한다. 마지막으로 설정 한 제약 조건을 일치시키는 방식으로 위젯의 크기를 조정한다.  
(b) : wrap_content, 0dp이다.  
(c) : 여백이 설정되면 계산에서 고려된다. 0dp 이다.

**중요 : MATCH_PARENT는 ConstraintLayout에 포함 된 위젯에서는 지원되지 않지만 비슷한 동작은 MATCH_CONSTRAINT를 사용하여 "left"또는 "top / bottom"제약 조건을 "parent"로 설정하여 정의 할 수 있다.**

### Ratio
위젯의 한 차원을 다른 위젯의 비율로 정의할 수도 잌ㅅ다. 이를 수행하려면 최소 하나의 제한된 크기를 0dp (예 : MATCH_CONSTRAINT)로 설정하고 layout_constraintDimentionRatio 속성을 지정된 비율로 설정해야 한다.

    <Button android:layout_width="wrap_content"
        android:layout_height="0dp"
        app:layout_constraintDimensionRatio="1:1" />

"width : height" [^3]

두 치수가 모두 MATCH_CONSTRAINT (0dp)로 설정된 경우에도 비율을 사용할 수 있다. 이 경우 시스템은 모든 제약 조건을 충족시키는 가장 큰 차원을 설정하고 지정된 종횡비를 유지한다. 하나의 특정면을 다른면의 크기에 따라 제약합니다.  
예를 들어, 하나의 차원이 두 개의 대상으로 제한되면 (예 : 너비가 0dp이고 부모를 중심으로), 어떤면이 제한되어야하는지 나타낼 수 있습니다.  
비율 앞에 쉼표로 구분 된 문자 : W (너비를 제한하기 위해) 또는 H (높이를 구속하기 위해)

    <Button android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintDimensionRatio="H,16:9"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toTopOf="parent"/>

16 : 9 비율에 따라 버튼의 높이를 설정하고 버튼의 너비는 부모에 대한 Constraint 일치합니다.

###  툴바 내 기능[^4]

#### 1. 레이아웃 Blueprint 표시
![icon1](http://kunny.github.io/assets/posts/lecture/ui/2016/05/22/constraint_layout_1/show_blueprint.png)
레이아웃 디자이너 화면에 표시되는 위젯의 종류와 테두리만 보여주는 Show Blueprint 기능은 ConstraintLayout을 사용하여 위젯간 관계를 지정할 때 매우 유용하다.

#### 2. Autoconnect ON/OFF
![icon2](http://kunny.github.io/assets/posts/lecture/ui/2016/05/22/constraint_layout_1/turn_on_autoconnect.png)
Autoconnect 기능을 사용하면, 레이아웃 내 위젯을 배치할 때 자동으로 이웃한 위젯이나 화면 경계간 관계를 지정해준다.

#### 3. Constraint 삭제
![icon3](http://kunny.github.io/assets/posts/lecture/ui/2016/05/22/constraint_layout_1/clear_all_constraints.png)
현재 설정되어 있는 Constraint를 모두 제거한다. 위젯을 선택한 상태에서 누르면 선택한 위젯의 Constraint만 제거된다.

#### 4. Infer Constraint
![icon4](http://kunny.github.io/assets/posts/lecture/ui/2016/05/22/constraint_layout_1/infer_constraints.png)
레이아웃 내 배치된 위젯의 현재 상태를 기반으로 관계를 지정한다. Autoconnect 기능은 이웃한 위젯들 간의 관계만 지정하지만, 이 기능은 화면에 포함되어 있는 전체 위젯을 대상으로 하여 관계를 지정한다.

#### 5. 기본 여백 설정
![icon5](http://kunny.github.io/assets/posts/lecture/ui/2016/05/22/constraint_layout_1/default_margin.png)
레이아웃 내 위젯을 배치할 때 적용할 여백 값을 지정한다. 0-8-16dp 단위로 설정 가능하다.

-----

[^1]: https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html

[^2]: https://news.realm.io/kr/news/constraintlayout-it-can-do-what-now/

[^3]: https://developer.android.com/training/constraint-layout/index.html

[^4]: http://kunny.github.io/lecture/ui/2016/05/22/constraint_layout_1/