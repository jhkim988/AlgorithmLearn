import java.util.Scanner;

class Exercise05_01{
	// Q1 ��� �ż��� ȣ���� ������� �ʰ� factorial �޼��带 �ۼ��ϼ���
	static int factorial_for(int n) {
		int result = 1;
		for (int i = 2; i <= n; ++i)
			result *= i;
		return result;
	}
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.print("������ �Է��ϼ���. : ");
		int x = stdIn.nextInt();
		stdIn.close();
		System.out.println(x + "�� ���丮���� " + factorial_for(x) + " �Դϴ�.");
	}
}