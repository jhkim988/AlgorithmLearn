import java.util.Scanner;

class MaxOfArray{
	static int maxOf(int[] a) {
		int max = a[0];
		// traverse: 배열의 요소를 하나씩 차례로 살펴보는 과정
		for (int i = 1; i < a.length; ++i) {
			if (a[i] > max) max = a[i];
		}
		return max;
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("키의 최댓값을 구합니다.");
		
		System.out.println("사람 수 : " );
		int num = stdIn.nextInt();
		
		int[] height = new int[num];
		
		for (int i = 0; i < num; ++i) {
			System.out.print("height[" + i + "] : ");
			height[i] = stdIn.nextInt();
		}
		stdIn.close();
		System.out.println("최댓값은 " + maxOf(height) + " 입니다.");
		// main()에서 maxOf() 함수를 호출한다. 인자로 height 배열을 넘겨준다.
		// maxOf()함수 내부적으로 int[] a = height; 가 실행된다. 즉 a와 height는 같은 배열을 참조하게 된다.
		// 즉 maxOf()로 전달한 것은 배열 본체에 대한 '참조'이다.
		// 따라서 maxOf()내부에서 a.length, a[i] 등을 통해 height에 대한 값들을 접근할 수 있다.
		
		// 접근 제한자 정리
		// public: 모든 접근 허용
		// protected: 같은 패키지(폴더)의 객체, 상속 관계의 객체 허용
		// default: 같은 패키지(폴더)의 객체 허용
		// private: 현재의 객체 안에서만 허용
		
		// 접근 제한자의 사용
		// 클래스 - public default
		// 생성자 - public, protected, default, private
		// 멤버 변수 - public, protected, default, private
		// 지역 변수 - 접근 제한자 사용 못함
	}
}