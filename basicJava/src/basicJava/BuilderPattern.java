package basicJava;

// 외부, 내부 클래스를 이용한 builder pattern
// builder pattern을 통한 외부,내부 클래스에 대한 얕은 공부 및 객체지향에 대한 약간의 이해
public class BuilderPattern {

	/**
	 * 자바빈 패턴의 경우 setter를 활용하여 객체를 생성하는 방법이며, 생성자로도 당연히 객체를 생성할 수는 있지만, 클래스의 인스턴스 변수들이 많아질수록
	 * 생성자가 복잡해짐. setter 또한 여러번의 호출로 일일이 값을 할당시켜야 하며, setter의 존재에 의해서 변경 불가능 클래스(불변 객체)를 만들 수가 없다.
	 * setter를 제거하고 생성자로만 객체를 생성하면 불변 객체를 만들 수 있지만, 위에서 말했듯이 매개변수가 많아지면 많아질수록 복잡해지는 문제가 생긴다.
	 * 그래서 등장한 패턴이 builder pattern으로, 가독성이 뛰어나며 불변 객체를 만드는 패턴이다.
	 * 자세한 내용
	 * https://okky.kr/article/396206 ->builder pattern 에 대한 커뮤니티 글
	 * https://johngrib.github.io/wiki/pattern/builder/ -> 참고한 코드 및 내용
	 * 
	 */
	public static void main(String[] args) {
		/**
		 * 코드 분석을 통해 내가 이해한 동작원리 및 내용
		 * 외부 클래스의 생성자를 private으로 설정 함으로써, Builder라는 내부 클래스를 통해서만 외부 클래스에 해당하는 객체를 생성할 수 있음.
		 * 단, 내부 클래스는 외부 클래스의 객체를 생성을 하지 않고 사용하기 위하여 static 키워드를 사용
		 * builder 패턴을 꼭 내부 외부 클래스 방식으로 구현하지 않아도 되지만, builder 클래스를 밖으로 빼낼 경우에 builder 클래스 내부 메서드에서
		 * 만들고자 하는 불변 객체의 클래스의 생성자에 접근을 해야하는데, 그러면 결국 생성자를 private이 아닌 public으로 바꾸어야 하기 때문에 외부,내부의 방식보다
		 * 적합하지 않다고 생각함.
		 * 
		 * 외부 클래스의 인스턴스 변수들은 private final을 통해 외부에서 접근을 불가능하게 하며, 한번 값이 할당 된 이후에는 변경하지 못하게 구현
		 * 내부 클래스인 Builder 클래스의 경우 생성자를 통해 필수 인자와 선택적 인자를 구분하여 만들 수 있음.
		 * 선택적 인자의 경우 메서드를 통해 값을 할당하고, 메서드 체이닝 기법을 사용하기 위하여 return this를 해준다.(값 변경후 변경된 자기 자신의 객체를 반환)
		 * 최종적으로 외부 클래스 타입이 반환형인 build 메서드를 통해 만들어진 내부 클래스 객체를 외부 클래스 생성자에 파라미터로 주고 return으로 외부 클래스 객체를 반환.
		 * 이를 통해 불변 객체가 만들어지는 것이다.
		 * 
		 * 궁금한 점
		 * 바로 아래의 코드를 보면 new 키워드를 통해 heap 메모리 공간을 할당 후에 builder클래스의 생성자 및 선택적 인자를 할당 후 build()를 통해 최종적으로 외부클래스 객체를
		 * 반환하고, 해당 객체의 메모리 주소를 cocaCola라는 참조변수에 저장하게 된다.
		 * 그런데 아래 코드에도 new 키워드가 있고, build()에도 new 키워드를 통해 외부 클래스 객체를 반환한다.
		 * 그렇다면, heap 메모리 공간에는 builder 클래스 객체 하나와, 외부클래스 객체 하나가 만들어지는 것인가?
		 */
		NutritionFacts cocaCola = new NutritionFacts
			    .Builder(240, 8)    // 필수값 입력
			    .calories(100).build();
		
		/**
		 * 궁금한 점 테스트
		 * 그렇다면, builder 객체를 미리 만들어 두고, new 키워드를 안쓰고 build()를 호출하는게 가능해진다면 결국 위 궁금한 점 대로
		 * heap 메모리 공간에 각각의 객체가 만들어지는 것으로 볼 수 있을 것이다.
		 * 생각한대로 되는 것 같다. 단, builder 객체를 하나 만들고 재사용하기 위해서는 필수인자에 대한 해결이 필요할 것 같다.
		 * 
		 */
		
		NutritionFacts.Builder builderObject = new NutritionFacts.Builder(255, 16);
		
		NutritionFacts orangeJuice = builderObject.calories(211).carbohydrate(121).build();
		System.out.println(orangeJuice.getCalories());//변경은 불가능한, 하지만 읽을 순 있는 객체
	}

}

//Effective Java의 Builder Pattern
 class NutritionFacts {
 private final int servingSize;
 private final int servings;
 private final int calories;
 private final int fat;
 private final int sodium;
 private final int carbohydrate;

 public static class Builder {
     // Required parameters(필수 인자)
     private int servingSize;
     private int servings;

     // Optional parameters - initialized to default values(선택적 인자는 기본값으로 초기화)
     private int calories      = 0;
     private int fat           = 0;
     private int carbohydrate  = 0;
     private int sodium        = 0;

     public Builder(int servingSize, int servings) {
         this.servingSize = servingSize;
         this.servings    = servings;
     }

     public Builder calories(int val) {
         calories = val;
         System.out.println("this : " + this);
         return this;    // 이렇게 하면 . 으로 체인을 이어갈 수 있다.
     }
     public Builder fat(int val) {
         fat = val;
         return this;
     }
     public Builder carbohydrate(int val) {
         carbohydrate = val;
         return this;
     }
     public Builder sodium(int val) {
         sodium = val;
         return this;
     }
     public NutritionFacts build() {
         return new NutritionFacts(this);
     }
 }

 private NutritionFacts(Builder builder) {
     servingSize  = builder.servingSize;
     servings     = builder.servings;
     calories     = builder.calories;
     fat          = builder.fat;
     sodium       = builder.sodium;
     carbohydrate = builder.carbohydrate;
 }
 
 public int getCalories() {
	 return calories;
 }
}