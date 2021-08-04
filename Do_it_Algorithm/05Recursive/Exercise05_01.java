import java.util.Scanner;

class Exercise05_01{
	// Q1 재귀 매서드 호출을 사용하지 않고 factorial 메서드를 작성하세요
	static int factorial_for(int n) {
		int result = 1;
		for (int i = 2; i <= n; ++i)
			result *= i;
		return result;
	}
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.print("정수를 입력하세요. : ");
		int x = stdIn.nextInt();
		stdIn.close();
		System.out.println(x + "의 팩토리얼은 " + factorial_for(x) + " 입니다.");
	}
}