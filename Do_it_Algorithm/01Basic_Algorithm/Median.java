import java.util.Scanner;

class Median {
	// �� ���� ������ �Է� �޾� �߾Ӱ��� ���� �� ����Ѵ�.

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
		
		System.out.println("�� ������ �߾Ӱ��� ���մϴ�.");
		System.out.print("a�� �� : "); int a = stdIn.nextInt();
		System.out.print("b�� �� : "); int b = stdIn.nextInt();
		System.out.print("c�� �� : "); int c = stdIn.nextInt();
		
		stdIn.close();
		
		System.out.println("�߾Ӱ��� " + med3(a, b, c) + " �Դϴ�.");
	}
}