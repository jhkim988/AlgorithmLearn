import java.util.Scanner;

class Exercise05_03 {
	// Q3 배열 a의 모든 요소의 최대공약수를 구하는 다음 메서드를 작성하세요
	// My Try :
//	static int gcdArray(int[] a) {
//		int min = a[0];
//		int minIdx = 0;
//		for (int i = 0; i < a.length; ++i)
//			if (min > a[i] && a[i] > 0) {
//				min = a[i];
//				minIdx = i;
//			} // 0이 아닌 최솟값과 그 때의 인덱스를 찾는다.
//
//		for (int i = 0; i < a.length; ++i) {
//			if (i != minIdx)
//				a[i] %= min;
//		} // 최솟값으로 배열의 각각의 성분에 % 연산을 한다.
//
//		for (int i = 0; i < a.length; ++i) {
//			if (i != minIdx && a[i] != 0)
//				return gcdArray(a);
//		} // 하나라도 0이 아니라면 해당 배열로 재귀한다.
//
//		return min; // 최솟값을 제외한 게 모두 0이라면 최솟값을 반환한다.
//
//	}

	// Solution
	static int gcd (int x, int y) {
		while (y != 0) {
			int temp = x;
			x = y;
			y = temp % y;
		}
		return x;
	}
	
	static int gcdArray(int[] a, int start, int num) {
		if (num == 1)
			return a[start];
		if (num == 2)
			return gcd(a[start], a[start+ 1]);
		else
			return gcd(a[start], gcdArray(a, start + 1, num - 1));
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("정수의 최대공약수를 구합니다.");

		System.out.print("입력할 정수의 개수 : ");
		int num = stdIn.nextInt();

		int[] a = new int[num];
		for (int i = 0; i < num; ++i) {
			System.out.print((i + 1) + "번째 정수 : ");
			a[i] = stdIn.nextInt();
		}

		stdIn.close();

//		System.out.println("최대공약수는 " + gcdArray(a) + "입니다.");
		System.out.println("최대공약수는 " + gcdArray(a, 0, num) + "입니다.");
	}
}