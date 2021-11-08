package basicJava;

public class ArrayType {
	
	/**
	 * ===호기심===
	 * C언어의 경우 int형인 배열 arr이 100번지라면 4byte씩 배열의 길이만큼 순서대로 104 .. 108 .. 이렇게 할당이 된다.
	 * JAVA의 경우도 동일한 혈태로 Heap에 저장이 되는 건가? 아니면 길이가 5라면 5개의 공간이 무작위로 생성되고 연결 리스트처럼 연결이 되어 있는 것인가?
	 * https://okky.kr/article/102566 -> 2007년 글이지만 일단 해당 글에서는 C언어와 동일하다고 한다.
	 * JAVA 는 C언어와 달리 주소값을 찍는 방법(메서드)이 없는걸로 알고 있어서, 메모리 구조에 대한 호기심을 해결하기가 힘듦.
	 */
	public static void main(String[] args) {
		//JS와 달리 하나의 타입만을 저장이 가능하며, 한번 선언된 배열의 길이는 변경할 수 없음. 배열.length 는 결국 상수와 동일
		int[] arr = new int[]{1,2,3}; // 배열의 선언방법은 꽤 다양함
		System.out.println("arr 참조변수의 주소 : " + arr);
		System.out.println(arr.length); //상수와 동일
		arr = new int[5]; //배열의 길이를 3 -> 5로 늘린 것이 절대로 아니다. Heap 영역에 새로운 공간을 만들어서 해당 주소를 arr 참조변수에 넣은 것뿐
		System.out.println("새로운 주소 저장: " + arr); //주소가 일치하지 않음 -> 새로운 객체를 의미
		
		System.out.println("==============================");
		int[] arr1 = new int[]{11,22,33,44,55};
		int[] arr2 = arr1; //arr1이 결국 주소값을 저장하는 참조변수라면 arr1의 주소값을 arr2에 넣는다면 둘은 같은 공간을 참조하는 것인가?
		System.out.println("arr1 주소 : " + arr1 + " arr2 주소 : " + arr2); //같은 주소값 출력
		System.out.println(arr1[0] + "   " + arr2[0]); // 같은 값이 출력
		arr1[0] = 1998;
		System.out.println(arr1[0] + "   " + arr2[0]); // 같은 값이 출력
		
		/**
		 * C/C++에서는 주소값 자체를 인자로 넘겼을 때 값을 변경하면 새로운 값으로 덮어 쓰여 기존 값이 변경되고, 
		 * Java에서는 주소값이 덮어 쓰여지므로 원본 값은 전혀 영향이 가지 않는 것이다. 
		 * (객체의 속성값에 접근하여 변경하는 것은 직접 접근하여 변경하는 것이므로 이를 가리키는 변수들에서 변경이 일어난다.)
		 * 위는 객체의 속성값에 접근하여 변경하는 것이므로 Call By Value에 벗어나지 않는다.
		 */


	}

}
