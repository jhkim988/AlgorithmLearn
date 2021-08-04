import java.util.Scanner;

class SumWhile{
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n의 값 : ");
		int n = stdIn.nextInt();
		stdIn.close();
		
		int sum = 0;
		int i = 1;
		while (i <= n) {
			sum += i++;
		}
		
		System.out.println("1부터 " + n + "까지의 합은 " + sum + "입니다.");
	}
}