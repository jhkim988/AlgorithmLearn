import java.util.Scanner;

class TriangleLB{
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int n;
		
		System.out.println("���� �Ʒ��� ������ �̵ �ﰢ���� ����մϴ�.");
		do {
			System.out.println("�� �� �ﰢ���Դϱ�? : ");
			n = stdIn.nextInt();
		} while (n <= 0);
		stdIn.close();
		
		for (int i = 0; i < n; ++i) {
			for(int j = 0; j <= i; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
}