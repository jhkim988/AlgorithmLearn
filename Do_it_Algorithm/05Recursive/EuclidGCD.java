import java.util.Scanner;

class EuclidGCD{
	static int gcd(int x, int y) { // y > x��� x % y = x�� �ȴ�.
		if (y == 0)
			return x;
		return gcd(y , x % y);
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