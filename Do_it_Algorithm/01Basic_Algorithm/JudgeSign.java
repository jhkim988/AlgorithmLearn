import java.util.Scanner;

class JudgeSign{
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("������ �Է��ϼ���.");
		int n = stdIn.nextInt();
		
		if (n > 0) {
			System.out.println("�� ���� ����Դϴ�."); // 1
		} else if (n < 0) {
			System.out.println("�� ���� �����Դϴ�."); // 2
		} else {
			System.out.println("�� ���� 0 �Դϴ�."); // 3
		}
		
		// 1, 2, 3 �߿� ����Ǵ� �κ��� �� �� ������.
		// ���α׷��� �� ������ �б�Ǳ� �����̴�.
		
		// 1, 2, 3 ���� �� �ϳ��� �Է¹޾�, �Է� ���� ���ڰ� �������� ����ϴ� ���α׷��� �����غ���.
		int m = stdIn.nextInt();
		stdIn.close();
		
		if (m == 1)
			System.out.println("�� ���� 1�Դϴ�.");
		else if (m == 2)
			System.out.println("�� ���� 2�Դϴ�.");
		else if (m == 3)
			System.out.println("�� ���� 3�Դϴ�.");
//		else
//			;
		// �����δ� 4������ �б��Ѵ�.
	}
}