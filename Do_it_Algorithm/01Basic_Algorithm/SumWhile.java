import java.util.Scanner;

class SumWhile{
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n�� �� : ");
		int n = stdIn.nextInt();
		stdIn.close();
		
		int sum = 0;
		int i = 1;
		while (i <= n) {
			sum += i++;
		}
		
		System.out.println("1���� " + n + "������ ���� " + sum + "�Դϴ�.");
	}
}