import java.util.Scanner;

class DayOfYear {
	static int[][] mdays = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
			{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };

	static int isLeap(int year) {
		// ���� 1 / ��� 0
		return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0) ? 1 : 0;
	}

	static int dayOfYear(int y, int m, int d) {
		// ���� y�� m�� d���� �� �� ��� �ϼ��� ����
		int days = d;
		for (int i = 1; i < m; ++i) {
			days += mdays[isLeap(y)][i - 1];
		}
		return days;
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int retry;
		
		System.out.println("�� �� ��� �ϼ��� ���մϴ�.");
		
		do {
			System.out.print("�� : "); int year = stdIn.nextInt();
			System.out.print("�� : "); int month = stdIn.nextInt();
			System.out.print("�� : "); int day = stdIn.nextInt();
			System.out.printf("�� �� %d��° �Դϴ�.\n", dayOfYear(year, month, day));
			System.out.print("�� �� �� �ұ��?(1. �� / 2. �ƴϿ�) : ");
			retry = stdIn.nextInt();
		} while (retry == 1);
		stdIn.close();
	}
}