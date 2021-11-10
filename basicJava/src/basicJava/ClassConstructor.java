package basicJava;

public class ClassConstructor {

	public static void main(String[] args) {
		
		//자식 클래스 생성자 호출 -> 부모 클래스 생성자 호출 -> 부모 클래스 생성자 실행 -> 자식 클래스 생성자 실행 순이다.
		//SubClass subClassObject = new SubClass(); //자식 클래스의 기본 생성자로 객체 생성
		
		//자식 오버로딩 생성자 호출 -> 부모 기본 생성자 호출 -> 부모 기본 생성자 실행 -> 자식 오버로딩 생성자 실행
		SubClass subClassObject1 = new SubClass(10); //오버로딩 된 자식 클래스의 생성자를 호출할 경우
		
		/**
		 * 자식의 생성자 맨 윗줄에는 기본적으로 super() 가 동작이 되기 때문에 위와 같은 결론이 도출이 됨
		 * 부모의 기본 생성자가 아닌 오버로딩된 생성자를 호출하기 위해서는 super(superInstanceValue)처럼 오버로딩 형식에 맞게 매개변수를 지정해주면 된다.
		 * 주의할 점은, 부모 생성자가 우선 동작이 되어야 하기때문에 super()는 반드시 맨 윗줄에 와야 한다.
		 * super가 제일 맨 윗줄에 있어야 하는 이유 즉, 부모 생성자가 먼저 실행이 되어야 하는 이유는 아마도, 생성자로부터 부모 클래스에 대해 인스턴스화가 진행이
		 * 되어야 자식이 이어서 상속을 받을 수 있기 떄문인 듯 하다.(그럼 부모가 먼저 메모리에 올라가고 이어서 자식이 메모리에 올라가는 원리??)
		 */
	}

}

class SuperClass {
	
	int instanceValue = 1;
	//SuperClass(){} -> 기본 생성자로, 개발자가 따로 정의해 놓은 생성자가 없을때만! 컴파일러가 추가해 준다()
	SuperClass(){
		System.out.println("SuperClass Constructor");
	}
	
	SuperClass(int instanceValue){
		System.out.println("부모 클래스에서 오버로딩으로 만든 생성자");
		this.instanceValue = instanceValue;
	}
}

class SubClass extends SuperClass{
	
	SubClass(){
		System.out.println("SubClass Constructor");
	}
	
	SubClass(int superInstanceValue){
		//super(superInstanceValue); //부모의 생성자 호출은 반드시 맨 윗줄로 와야함
		System.out.println("자식 클래스에서 오버로딩으로 만든 생성자");
		super.instanceValue = superInstanceValue;
	}
	
}
