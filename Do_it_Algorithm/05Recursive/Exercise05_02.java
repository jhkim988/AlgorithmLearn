import java.util.Scanner;

class Exercise05_02{
	// Q2 ��� �޼��� ȣ���� ������� �ʰ� gcd �޼��带 �ۼ��ϼ���
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
		
		System.out.println("�� ������ �ִ������� ���մϴ�.");
		
		System.out.print("������ �Է��ϼ��� : ");
		int x = stdIn.nextInt();
		
		System.out.print("������ �Է��ϼ��� : ");
		int y = stdIn.nextInt();
		
		stdIn.close();
		
		System.out.println("�ִ������� " + gcd(x , y) + "�Դϴ�.");
	}
}