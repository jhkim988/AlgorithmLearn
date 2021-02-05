import java.util.Scanner;

class Median {
	// 세 개의 정수를 입력 받아 중앙값을 구한 후 출력한다.

	static int med3(int a, int b, int c) {
		if (a >= b) {
			if (b >= c) { // a >= b >= c
				return b;
			} else if (a <= c) { // a >= b, c > b
				return a;
			} else
				return c;
		} else if (a > c) // b > a
			return a;
		else if (b > c) // b > a, c >= a
			return c;
		else // c >= b > a
			return b;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("세 정수의 중앙값을 구합니다.");
		System.out.print("a의 값 : "); int a = stdIn.nextInt();
		System.out.print("b의 값 : "); int b = stdIn.nextInt();
		System.out.print("c의 값 : "); int c = stdIn.nextInt();
		
		stdIn.close();
		
		System.out.println("중앙값은 " + med3(a, b, c) + " 입니다.");
	}
}