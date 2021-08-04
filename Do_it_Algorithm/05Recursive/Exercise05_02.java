import java.util.Scanner;

class Exercise05_02{
	// Q2 재귀 메서드 호출을 사용하지 않고 gcd 메서드를 작성하세요
	static int gcd(int x, int y) {
		// My Try
//		if (x > y) { // swap
//			int temp = x;
//			x = y;
//			y = temp;
//		}
//		
//		int result = 1;
//		
//		for (int i = 1; i <= x; ++i)
//			if (x % i == 0 && y % i == 0)
//				result = i;
//		return result;
		
		while (y != 0) {
			int temp = x;
			x = y;
			y = temp % y;
		}
		return x;
	}
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("두 정수의 최대공약수를 구합니다.");
		
		System.out.print("정수를 입력하세요 : ");
		int x = stdIn.nextInt();
		
		System.out.print("정수를 입력하세요 : ");
		int y = stdIn.nextInt();
		
		stdIn.close();
		
		System.out.println("최대공약수는 " + gcd(x , y) + "입니다.");
	}
}