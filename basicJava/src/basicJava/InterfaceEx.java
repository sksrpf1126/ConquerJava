package basicJava;

// public은 이미 InterfaceEx 클래스가 가지고 있음.
// 하나의 자바 파일에는 하나의 클래스만이 public을 가질 수 있다는데 인터페이스도 포함?
// 인터페이스로도 다형성 구현이 가능
// 인터페이스는 클래스와는 달리 다중 "상속"을 받을 수 있다.
interface IPolymorphism extends ITest1, ITest2{
	static void polymorphismTest(IPolymorphism p) {
		p.hello();
	}
	
	//다형성을 위한 추상메서드(오버라이딩)
	public abstract void hello();
}


interface ITest1{
	public static final int value1 = 10;
}

interface ITest2{
	public static final int value2 = 20;
}

interface ITest3{
	public static final int value3 = 30;
}


//클래스는 인터페이스를 다중 "구현"이 가능
class InterfaceExSubClass1 implements IPolymorphism, ITest3{

	@Override
	public void hello() {
		System.out.println("나는 서브클래스 1");
		
	}
	
}

class InterfaceExSubClass2 implements IPolymorphism{

	@Override
	public void hello() {
		System.out.println("나는 서브클래스 2");
		
	}
	
}

public class InterfaceEx {

	/**
	 * 인터페이스에 대한 내용 또한 추상클래스와 인터페이스의 비교에서 설명
	 */
	public static void main(String[] args) {
		//인터페이스를 통한 다형성 구현
		IPolymorphism.polymorphismTest(new InterfaceExSubClass1());
		IPolymorphism.polymorphismTest(new InterfaceExSubClass2());
		

	}

}

/**
 * 인터페이스에서 선언 가능한 필드 및 메서드이다.
 * 상수의 경우 public static final 이어야 하며, 생략이 가능 예시로 final int a = 10, static int a = 10 등 일부분 생략도 가능
 * 추상 메서드의 경우 public abstract 이어야 하며, 위 상수처럼 생략이 가능함
 * 디폴트 메서드와 정적(static) 메서드는 jdk1.8부터 생긴 문법
 * 정적 메서드가 생긴 이유로는 인터페이스를 구현하는 클래스들간에 공유메서드로 사용하기 위함
 * 디폴트 메서드가 생긴 이유는 기존에는 추상 메서드로만 가능했기 때문에 인터페이스를 구현하는 모든 클래스들은 인터페이스 내에 존재하는 모든 추상메서드를 어쩔 수 없이
 * 오버라이딩을 하여 구현할 수 밖에 없었고, 이는 코드의 중복 발생을 초래한다. 10개의 클래스 중 10개가 모두 공유를 해야한다면(공통 메서드라면) 정적 메서드로 구현하면 되지만,
 * 10개중에 2개~3개 정도가 공통으로 구현하는 메서드의 경우에는 중복이 발생할 수 밖에 없다. 이부분을 디폴트 메서드를 통해 미리 구현을 해두고
 * 필요하지 않는 클래스의 경우 냅두면 되며, 필요한데 바꿔야 한다면 따로 오버라이딩으로 변경하면 되기 때문에 여러 이점이 존재한다.
 * 디폴트 메서드 역시 접근 제어자는 public이며 생략 가능
 */
//public interface 인터페이스명 {
//	//상수
//	(public static final)타입 상수명 = 값;
//	//추상 메소드
//	(public abstract) 타입 메소드명(매개변수, ... );
//	//디폴트 메소드
//	(public) default 타입 메소드명(매개변수, ... ){
//	  //구현부
//	}
//	//정적 메소드
//	static 타입 메소드명(매개변수) {
//	  //구현부
//	}
//}
