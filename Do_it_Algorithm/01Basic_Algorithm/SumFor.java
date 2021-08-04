import java.util.Scanner;

class SumFor{
	public static void main(String[] args) {
		// 하나의 변수를 사용하는 반복문은 while보다 for를 이용하는 것이 좋다.
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n의 값 : ");
		int n = stdIn.nextInt();
		stdIn.close();
		
		int sum = 0;
		for (int i = 1; i <= n; ++i)
			sum += i;
		System.out.println("1부터 " + n + "까지의 합은 " + sum + "입니다.");
	}
}