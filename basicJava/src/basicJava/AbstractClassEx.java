package basicJava;

public class AbstractClassEx {

	/**
	 * 추상 클래스 기본 문법
	 * 추상 클래스에 대한 이론은 추상 클래스와 인터페이스 차이에 대한 설명에 추가
	 * public abstract class 클래스명{
	 *  필드, 생성자, 메서드, 추상 메서드가 들어갈 수 있으며, 추상 메서드가 들어가 있지 않아도 됨.
	 * }
	 */
	public static void main(String[] args) {
		
		//ClassEx1 test1 = new ClassEx1(10);  => 오류 발생. 추상 메서드가 없어도 abstract 키워드가 class에 존재한다면 해당 클래스로는 인스턴스 생성 불가
		AbstractSubClass test2 = new AbstractSubClass(10);
		System.out.println(test2.getInstanceValue());//10
		System.out.println(ClassEx1.staticValue);//20

	}

}

//추상 메서드가 존재하지 않는 추상 클래스
abstract class ClassEx1{
	private int instanceValue = 0; // 인스턴스 변수
	public static int staticValue = 20; // 스태틱 변수
	
	public ClassEx1(int instanceValue) { // 생성자
		this.instanceValue = instanceValue;
	}
	
	public int getInstanceValue() { //메서드
		return this.instanceValue;
	}
}

//추상 메서드가 존재하는 추상 클래스
abstract class ClassEx2{
	private int instanceValue = 0; // 인스턴스 변수
	public static int staticValue = 20; // 스태틱 변수
	
	public ClassEx2(int instanceValue) { // 생성자
		this.instanceValue = instanceValue;
	}
	
	public int getInstanceValue() { //메서드
		return this.instanceValue;
	}
	
	//추상 메서드로 {}의 메서드 구현부분은 사용하지 않으며, private 접근 제어자는 당연히 사용하지 못한다. -> 오버라이딩을 해야하기 때문
	abstract public void testAbstractMethod1(int instanceValue);
	abstract void testAbstractMethod2();
}

//추상메서드가 여러개 있을 경우 전부다 오버라이딩을 하여 구현을 해주어야 한다. 하나라도 구현을 해주지 않을 경우에 class에 abstract 키워드를 붙여야 한다.
class AbstractSubClass extends ClassEx2{

	//아래와 같은 생성자가 필요한 이유로는 반드시 부모의 생성자도 호출 및 실행이 되어야 하는데, 기본 생성자가 존재하지 않기에 아래처럼 super(파라미터)를 통해
	//호출하고자 하는 부모 클래스의 생성자를  작성해야 한다.
	public AbstractSubClass(int instanceValue) {
		super(instanceValue);
	}

	@Override
	public void testAbstractMethod1(int instanceValue) {
		System.out.println("추상 메서드1 구현");
	}

	@Override
	void testAbstractMethod2() {
		System.out.println("추상 메서드2 구현");
	}
	
}

