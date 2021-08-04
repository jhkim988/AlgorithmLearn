import java.util.Scanner;

class CardConRev {
	static int cardConvR(int x, int r, char[] d) {
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// My Try
		do {
			d[digits++] = dchar.charAt(x % r);
		} while ((x /= r) > 0);

		// Exercise Test
		// d[0] ~ d[digits - 1]���� �������� ���ش�.
//		for (int i = 0; i < digits/2; ++i) {
//			char temp = d[i];
//			d[i] = d[digits - 1 - i];
//			d[digits - 1 - i] = temp;
//		}
		
		return digits;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int no; // ��ȯ�ϴ� ����
		int cd; // ���
		int dho; // ��ȯ ���� �ڸ���
		int retry; // �ٽ� �� ��?
		char[] cno = new char[32]; // ��ȯ �� �� �ڸ��� ���ڸ� �־�δ� ������ �迭

		System.out.println("10������ ��� ��ȯ�մϴ�.");
		do {
			do {
				System.out.print("��ȯ�ϴ� ���� �ƴ� ���� : ");
				no = stdIn.nextInt();
			} while (no < 0);

			do {
				System.out.print("� ������ ��ȯ�ұ��? (2 ~ 36) : ");
				cd = stdIn.nextInt();
			} while (cd < 2 || cd > 36);
			stdIn.close();
			
			dho = cardConvR(no, cd, cno);

			System.out.print(cd + "�����δ� ");
			for (int i = dho - 1; i >= 0; --i)
//			for (int i = 0; i < dho; ++i) // Exercise Test
				System.out.print(cno[i]);
			System.out.println("�Դϴ�.");

			System.out.print("�� �� �� �ұ��? (1. �� / 2. �ƴϿ�) : ");
			retry = stdIn.nextInt();
		} while (retry == 1);
	}
}