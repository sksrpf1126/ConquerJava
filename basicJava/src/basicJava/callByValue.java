package basicJava;

import java.util.Scanner;

/**
 * 출처
 * .md파일로 정리할 것
 * HashCode 와 identityHashCode 차이점 : https://codechacha.com/ko/java-system-identityhashcode/
 * HashCode 의 경우 String 클래스에서 오버라이딩에 의하여 같은 문자열일 경우에 같은 값이 나오기 때문에 불안정하다.
 * identityHashCode의 경우 또한 같은 객체라면 같은 값이 나온다. 이것의 대우 명제인 값이 다르다면 같은 객체가 아니다 라는 말도 참이 된다.
 * 단, 값이 같기 때문에 같은 객체라는 것은 참이 아니다. 실제로 identityHashCode는 서로 다른 객체여도 같은 값이 나오는 경우가 있다.
 * C/C++의 경우에는 포인터와 같은 개념 그리고 언어의 특성상 메모리 주소에 직접적으로 접근하는 일이 많기 때문에 이러한 개념들이 중요하지만
 * 자바의 경우, call by value로만 동작하기도 하고, 나의 수준에 깊게 공부하기에는 무리가 있으며, C/C++과 달리 JAVA는 GC가 메모리를 스스로 관리해주므로
 * 현재의 수준까지로만 공부
 * 자바의 값 넘기는 방식 : https://github.com/gyoogle/tech-interview-for-developer/blob/master/Language/%5Bjava%5D%20Call%20by%20value%EC%99%80%20Call%20by%20reference.md
 */

public class callByValue {
	
	public static void callByValueTestCode() {
		System.out.println("===callByValueTestCode start===");
		int a = 10;
		int b = a;
		
		System.out.println(System.identityHashCode(a));//758529971
		System.out.println(System.identityHashCode(b));//758529971
		
		int c = 10; // 새로운 값 할당 -> 그럼 새로운 메모리 주소에 할당이 되는가?(새로운 hashCode가 정해지는가?)
		System.out.println(System.identityHashCode(c));//758529971
		//정말 우연히 identityHashCode값이 중복일 수 있으므로, 하나 더 테스트
		int d = 10;
		System.out.println(System.identityHashCode(d));//758529971
		
		a = 11; // a의 값 변경
		System.out.println(System.identityHashCode(a));//2104457164
		c = 11;
		System.out.println(System.identityHashCode(c));//2104457164
		System.out.println("===callByValueTestCode end===");
		/**
		 * C/C++의 경우 변수마다 새로운 메모리 공간이 할당이 되고, 새로운 값을 대입할 때에 메모리 공간은 그대로에서 값만 덮어씌운다.
		 * 하지만 JAVA의 경우 위처럼 해당 값을 가지고 있는 공간이 있는지 찾고, 존재한다면 그 메모리 공간의 주소값을 저장하여 사용한다.
		 * 위에 a를 11로 변경하였을 때 a에서 새로운 값에 의하여 새로운 메모리 공간을 할당해 준듯하다.
		 * c를 11로 변경하였을 때, 11의 값을 가지고 있는 메모리 공간이 이미 있으므로 그 공간의 주소값을 저장
		 * 그렇다면, a,b,c,d 의 값이 10일 때, 하나의 메모리 공간의 주소값을 저장하여 사용할 뿐인것인가 그 공간은 그럼 stack에 존재하는 것인가..?
		 */
	}
	
	public static void hashcodeTest() {
		System.out.println("===hashcodeTest start===");
		String str1 = "hello"; //상수 풀 (String pool)에 저장
		String str2 = new String("hello"); //Heap 영역에 저장
		
		System.out.println(str1.hashCode() + " | " + str2.hashCode()); //값 동일
		System.out.println(System.identityHashCode(str1) + " | " + System.identityHashCode(str2)); // 다름
		System.out.println("===hashcodeTest end===");
		/**
		 * String에서의 hashcode는 오버라이딩이 되어 있어, 동작방식이 다름
		 */
	}
	
	//출처 : https://hyeran-story.tistory.com/123
	public static void StringConstantPoolTestCode() {
		System.out.println("===StringConstantPoolTestCode start===");
		Scanner sc = new Scanner(System.in);
		System.out.print("select Number : ");
		int select = sc.nextInt();
		
		switch(select) {
		case 1:
			String strConstant1= "study";
			String strConstant2 = "study";
			System.out.println("상수 폴끼리의 객체 비교");
			if(strConstant1 == strConstant2) {
				System.out.println("strConstant1과 strConstant2 객체 동일");
			}else {
				System.out.println("strConstant1과 strConstant2 다름");
			}
			break;
		case 2:
			String strConstant3= "study";
			String strHeap1 = new String("study");
			System.out.println("리터럴 String과 Heap영역의 String 객체 비교 ");
			if(strConstant3 == strHeap1) {
				System.out.println("객체 동일");
			}else {
				System.out.println("객체 다름");
			}
			break;
		case 3:
			String strHeap2 = new String("study");
			String strHeap3 = new String("study");
			System.out.println("Heap영역의 String 객체 간의 비교");
			if(strHeap2 == strHeap3) {
				System.out.println("객체 동일");
			}else {
				System.out.println("객체 다름");
			}
			break;
		}
		
		System.out.println("===StringConstantPoolTestCode end===");
	}

	public static void main(String[] args) {
		callByValueTestCode();
		hashcodeTest();
		StringConstantPoolTestCode();
		

	}

}
