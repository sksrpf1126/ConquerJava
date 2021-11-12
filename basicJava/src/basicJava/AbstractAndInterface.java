package basicJava;

public class AbstractAndInterface {

	/**
	 * 추상클래스와 인터페이스 차이
	 * 출처
	 * https://myjamong.tistory.com/150
	 * https://velog.io/@new_wisdom/Java-%EC%B6%94%EC%83%81-%ED%81%B4%EB%9E%98%EC%8A%A4%EC%99%80-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4%EC%9D%98-%EC%B0%A8%EC%9D%B4
	 * 추상클래스는 미완성 설게도라고 표현하며, 인터페이스는 규격 또는 기본 설게도라고 표현한다.
	 * 자바의 정석 책에서는 인터페이스는 일종의 추상 클래스라고 표현하며, 추상화 정도가 더 높다고 적혀있다.
	 * 인터페이스는 상수,디폴트 메서드, 정적 메서드, 추상 메서드만을 가지지만 추상 클래스의 경우에는 인터페이스가 가질 수 있는 멤버들도 다 가질 뿐더러 일반적인 클래스의
	 * 멤버들 또한 가질 수 있기에, 어찌 보면 인터페이스가 쓸모가 없어 보인다.
	 * 하지만, 클래스간에는 단일 상속만 가능하고, 인터페이스는 다중 구현이 가능하다는 점에서 추상클래스와 인터페이스의 차이점이 나타난다.
	 * 기본적으로 추상클래스나 인터페이스는 공통인 부분을 하나로 묶어서 정의하고 추상화를 함으로써, 유지보수와 코드 생산성 등을 높이기 위해 사용함
	 * 추상 클래스의 경우에는 간단하게 조상까지 쭉 타고 올라가며, 상속관계에서 공통으로 정의해야할 부분이 있다면 추상 클래스로 정의하여 상속을 받게 함으로써 효율을 극대화시킨다
	 * 인터페이스의 경우에는 클래스들 간에 아무 연관이 없지만 공통으로 정의해야하는 부분이 있다면 그 부분을 정의하여 구현하게 하면 된다.
	 * 이 외의 용도는 추가적으로 공부가 필요
	 * 
	 * 구체적인 예시는 위 출처 코드가 아닌 스타크래프트라는 게임의 "유닛"에 빗대어 코드를 구현
	 * 스타크래프트에서 최상위 클래스는 추상 클래스인 Unit 클래스
	 * 바로 아래 추상 클래스의 경우에 유닛에는 각각의  종족이 있기에, Terran, Zerg, Protoss 종족의 특성은 추상메서드로 오버라이딩해서 구현하기 위해 Unit에 추가
	 * 해당 종족에 SCV, Drone, Probe 는 일꾼으로 각각 건물짓는 방식이 다름 하지만 같은 조상은 Unit 밖에 없으며, 건물을 짓는다라는 동작은 일꾼들만 동작하기에 Unit에 추가하는건 옳은 방법이 아님
	 * 그렇기에 인터페이스로 구현
	 * Unit에는 간단하게 Marine, Zealot, Zergling 정도의 기본 유닛
	 * 마린은 스팀팩이라는 기술을, 저글링에게는 버로우라는 기술이 있으며 질럿은 기술이 없음
	 * 해당 기술이라는 부분은 인터페이스로 오버라이딩을 하여 구현.
	 */
	public static void main(String[] args) {
		/**
		 * 제대로 된 설계없이 원리를 설명하기 위한 코드이기에 하드코딩 및 간단한 동작만 구현하였고
		 * 상속관계 또한 종족이 맨 위로 오는것이 더 적합할 수도 있다고 생각
		 * usingSkill과 building은 유닛에 따라 유무와 방식이 다르기에 공통적인 동작이 아니므로 인터페이스로 구현
		 * Attack의 경우에는 모든 유닛이 공통으로 가지고 있는 동작이기에 Unit 추상클래스에서 받아서 오버라이딩해서 구현
		 * 이러한 부분이 추상클래스와 인터페이스의 가장 큰 차이점이라 생각
		 */
		
		SCV scv1 = new SCV(60, 10, 10, 5);//hp, x좌표, y좌표, 공격데미지
		scv1.building("배럭");
		Drone drone1 = new Drone(40, 30, 30, 5);
		drone1.building("스포닝 폴");
		Probe probe1 = new Probe(20, 50, 50, 5, 20);//hp, x좌표, y좌표, 공격데미지, 쉴드
		probe1.building("파일런");
		probe1.building("게이트웨이");
		
		Marine marine1 = new Marine(40, 20, 20, 6);
		Zergling zergling1 = new Zergling(35, 40, 40, 5);
		Zealot zealot1 = new Zealot(100, 60, 60, 16, 60);
		
		marine1.usingSkill();
		zergling1.usingSkill();
		
		marine1.Attack();
		zealot1.Attack();

	}

}

/**
 * 유닛에게는 공통적으로 HP 해당 유닛의 위치인 X와 Y 좌표, 공격력 그리고 공격하는 행위가 존재 (일꾼들과 마린,질럿,저글링정도의 유닛만 있다고 가정)
 * 각각은 유닛마다 다르기에 유닛을 생성하는 시점에 할당
 * 원래는 하나의 클래스, 인터페이스마다 하나의 자바파일을 만드는 것을 권장
 */
abstract class Unit{
	private int HP = 0;
	private int X = 0;
	private int Y = 0;
	private int attackDamage = 0;
	
	public Unit() {} //기본 생성자
	public Unit(int Hp, int X, int Y, int attackDamage) {
		this.HP = Hp;
		this.X = X;
		this.Y = Y;
		this.attackDamage = attackDamage;
	}
	
	public abstract void Attack();// 공격 형태로, 종족이 아닌 유닛에서 구현할 메서드
	
	public abstract void tribeCharacteristic();//종족 특성, 종족에서 구현할 메서드
	
	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
}

abstract class Terran extends Unit{
	
	public Terran() {} //기본 생성자
	public Terran(int Hp, int X, int Y, int attackDamage) {
		super(Hp, X, Y, attackDamage);
	}

	@Override
	public void tribeCharacteristic() {
		System.out.println("테란의 특성 : 바이오닉은 메딕으로 치유, 기계 및 건물은 scv로 수리가 가능하다.");
	}
	
}

abstract class Zerg extends Unit{
	
	public Zerg() {} //기본 생성자
	public Zerg(int Hp, int X, int Y, int attackDamage) {
		super(Hp, X, Y, attackDamage);
	}
	@Override
	public void tribeCharacteristic() {
		System.out.println("저그의 특성 : 건물 및 유닛은 일정시간마다 체력이 회복이 된다.");
	}
}

abstract class Protoss extends Unit{

	private int shield = 0; //프로토스의 특성 HP 외에 유닛마다 쉴드가 존재
	
	public Protoss() {} //기본 생성자
	public Protoss(int Hp, int X, int Y, int attackDamage, int shield) {
		super(Hp, X, Y, attackDamage);
		this.shield = shield; //쉴드값 할당
	}
	
	@Override
	public void tribeCharacteristic() {
		System.out.println("프로토스의 특성 : 건물 및 유닛에 쉴드가 존재하며, 일정시간마다 쉴드가 회복이 된다.");
	}
	
	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}
}

//건물을 짓는 행위는 같은 조상인 Unit에 구현하기에는 옳지 않음 -> 일꾼들만 하는 행위이기에
//그렇기에 인터페이스로 구현
interface Buildable{
	public abstract void building(String buildingName); //건물 짓는 동작에 대한 메서드
}

class SCV extends Terran implements Buildable{
	
	public SCV() {} //기본 생성자
	public SCV(int Hp, int X, int Y, int attackDamage) {
		super(Hp, X, Y, attackDamage);
	}
	
	//Unit 추상클래스의 추상메서드
	@Override
	public void Attack() {
		System.out.println(getAttackDamage() + " 데미지로  근접 공격");
	}

	//Buildable 인터페이스의 추상메서드
	@Override
	public void building(String buildingName) {
		System.out.println(buildingName + "을 짓는다. 건물을 짓는 중에는 다른 행동을 할 수 없다.");
	}
}

class Drone extends Zerg implements Buildable{
	
	public Drone() {} //기본 생성자
	public Drone(int Hp, int X, int Y, int attackDamage) {
		super(Hp, X, Y, attackDamage);
	}
	
	//Unit 추상클래스의 추상메서드
	@Override
	public void Attack() {
		System.out.println(getAttackDamage() + " 데미지로  독침 공격");
	}
	
	//Buildable 인터페이스의 추상메서드
	@Override
	public void building(String buildingName) {
		System.out.println("Drone 자신을 소모하여 "+ buildingName +"을 짓는다.");
	}
}

class Probe extends Protoss implements Buildable{
	
	public Probe() {} //기본 생성자
	public Probe(int Hp, int X, int Y, int attackDamage, int shield) {
		super(Hp, X, Y, attackDamage, shield);
	}
	//Unit 추상클래스의 추상메서드
	@Override
	public void Attack() {
		System.out.println(getAttackDamage() + " 데미지로  전기 공격");
	}
	
	//Buildable 인터페이스의 추상메서드
	@Override
	public void building(String buildingName) {
		System.out.println(buildingName +"을 소환한다. 이후 다른 동작이 가능하다.");
	}
}

interface Skillable{
	public abstract void usingSkill();
}

class Marine extends Terran implements Skillable{
	
	public Marine() {} //기본 생성자
	public Marine(int Hp, int X, int Y, int attackDamage) {
		super(Hp, X, Y, attackDamage);
	}

	//Unit 추상클래스의 추상메서드
	@Override
	public void Attack() {
		System.out.println(getAttackDamage() + " 데미지로  원거리 공격");
	}

	@Override
	public void usingSkill() {
		System.out.println("스팀팩 사용");
	}
	
}

class Zergling extends Zerg implements Skillable{
	
	public Zergling() {} //기본 생성자
	public Zergling(int Hp, int X, int Y, int attackDamage) {
		super(Hp, X, Y, attackDamage);
	}
	
	//Unit 추상클래스의 추상메서드
	@Override
	public void Attack() {
		System.out.println(getAttackDamage() + " 데미지로  근접 공격");
	}
	
	@Override
	public void usingSkill() {
		System.out.println("버로우 사용");
	}
}

//사용할 기술이 없기에 인터페이스 Skillable을 구현하지 않는다.
class Zealot extends Protoss{
	
	public Zealot() {} //기본 생성자
	public Zealot(int Hp, int X, int Y, int attackDamage, int shield) {
		super(Hp, X, Y, attackDamage, shield);
	}
	
	//Unit 추상클래스의 추상메서드
	@Override
	public void Attack() {
		System.out.println(getAttackDamage() + " 데미지로  근접 공격");
	}
}


