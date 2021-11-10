package basicJava;

public class DynamicBinding {
	
	public static void polymorphismTest(BindingSuperClass test) {
		test.hello();
	}

	public static void main(String[] args) {
		/*
		* 바인딩은 프로그램에 사용되는 값 또는 프로퍼티를 결정짓는 행위를 의미하는데, 동적 바인딩은 runtime때에 결정이 되든 것이고,
		* 정적 바인딩은 컴파일 시간에 결정이 되는 것이다.
		* 동적 바인딩의 예시로는 오버라이딩이 있으며, 정적 바인딩은 private, final, static이 붙은 메서드와 인스턴스 변수들이 존재한다.
		* 오버로딩의 경우 컴파일 시간에 컴파일러가 판별을 하여 메서드를 결정 짓기에 정적 바인딩에 포함
		* 오버라이딩의 경우 컴파일러 입장에서 오버라이딩 된 같은 이름의 메서드가 동일하다고 판단(부모 메서드인지, 자식 메서드인지 판별 불가)
		* 그렇기에 런타임시에 결정하는 동적 바인딩에 포함되는 것이다.
		* 동적 바인딩은 런타임시에 바인딩이 이루어지기 때문에 성능에 뒤쳐지지만, 객체지향에서 중요한 특징중 하나인 다형성과 밀접한 관련이 있기에 사용
		* 동적 바인딩은 다형성과 밀접한 관련이 있으며, 업캐스팅의 이해가 필요
		*/
		
		
		// 업 캐스팅으로, 참조변수 타입은 부모 클래스 타입이지만 객체 자체는 자식 객체인 상태
		// 이러면 부모 클래스의 멤버들에만 접근이 가능
		BindingSuperClass superClassObject1 = new BindingSubClass();
		//superClassObject1.subInstanceValue => 접근 불가
		
		//접근하기 위해서는 다시 다운캐스팅을 해주어야 하는데, 다운캐스팅의 선행조건은 1. 업캐스팅이 되어 있을것  2.형변환을 명시해줄 것 이다.
		//다운캐스팅
		BindingSubClass subClassObject1 = (BindingSubClass) superClassObject1;
		System.out.println(subClassObject1.subInstanceValue);
		
		
		//동적 바인딩 동작을 구현한 코드
		//런타임 때에 실행할 메서드를 찾아 결정을 내리는 동적 바인딩은 업캐스팅에 의하여 부모 클래스의 hello 메서드로 접근하지만 해당 메서드는 자식클래스에서
		//오버라이딩으로 재정의를 한 상태이다. 메모리에서 실제 객체에서도 자식클래스로 객체를 만들었기에 오버라이딩을 통해 정상적으로 재정의가 되었고
		//이는 오버라이딩 된 메서드를 호출하게 되는 것이다.
		BindingSuperClass superClassObject2 = new BindingSubClass();
		superClassObject2.hello(); // 동적 바인딩 자식 클래스에서 오버라이딩한 메서드 호출
		
		//위의 업 캐스팅과 동적바인딩을 활용한 다형성 동작 구현
		//파라미터로 넘겨준 객체의 모양(값)에 따라 각각 다르게 기능이 동작이 됨
		polymorphismTest(new BindingSuperClass()); //부모 클래스 hello()
		polymorphismTest(new BindingSubClass()); // 첫번째 자식 클래스 hello()
		polymorphismTest(new BindingSubClass2()); // 두번째 자식 클래스 hello()
		
		
	}

}

class BindingSuperClass{
	int instanceValue = 1;
	
	public void hello() {
		System.out.println("안녕 난 슈퍼 클래스");
	}
}

class BindingSubClass extends BindingSuperClass{
	int subInstanceValue = 2;
	
	@Override
	public void hello() {
		System.out.println("안녕 난 첫번째 자식인  BindingSubClass 내 부모는 BindingSuperClass");
	}
	
}

class BindingSubClass2 extends BindingSuperClass{

	@Override
	public void hello() {
		System.out.println("안녕 난 두번째 자식인 BindingSubClass2 내 부모는 BindingSuperClass");
	}
	
}
