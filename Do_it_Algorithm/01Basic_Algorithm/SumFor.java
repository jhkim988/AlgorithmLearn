import java.util.Scanner;

class SumFor{
	public static void main(String[] args) {
		// �ϳ��� ������ ����ϴ� �ݺ����� while���� for�� �̿��ϴ� ���� ����.
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n�� �� : ");
		int n = stdIn.nextInt();
		stdIn.close();
		
		int sum = 0;
		for (int i = 1; i <= n; ++i)
			sum += i;
		System.out.println("1���� " + n + "������ ���� " + sum + "�Դϴ�.");
	}
}