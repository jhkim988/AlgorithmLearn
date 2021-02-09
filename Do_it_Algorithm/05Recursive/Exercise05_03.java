import java.util.Scanner;

class Exercise05_03 {
	// Q3 �迭 a�� ��� ����� �ִ������� ���ϴ� ���� �޼��带 �ۼ��ϼ���
	// My Try :
//	static int gcdArray(int[] a) {
//		int min = a[0];
//		int minIdx = 0;
//		for (int i = 0; i < a.length; ++i)
//			if (min > a[i] && a[i] > 0) {
//				min = a[i];
//				minIdx = i;
//			} // 0�� �ƴ� �ּڰ��� �� ���� �ε����� ã�´�.
//
//		for (int i = 0; i < a.length; ++i) {
//			if (i != minIdx)
//				a[i] %= min;
//		} // �ּڰ����� �迭�� ������ ���п� % ������ �Ѵ�.
//
//		for (int i = 0; i < a.length; ++i) {
//			if (i != minIdx && a[i] != 0)
//				return gcdArray(a);
//		} // �ϳ��� 0�� �ƴ϶�� �ش� �迭�� ����Ѵ�.
//
//		return min; // �ּڰ��� ������ �� ��� 0�̶�� �ּڰ��� ��ȯ�Ѵ�.
//
//	}

	// Solution
	static int gcd (int x, int y) {
		while (y != 0) {
			int temp = x;
			x = y;
			y = temp % y;
		}
		return x;
	}
	
	static int gcdArray(int[] a, int start, int num) {
		if (num == 1)
			return a[start];
		if (num == 2)
			return gcd(a[start], a[start+ 1]);
		else
			return gcd(a[start], gcdArray(a, start + 1, num - 1));
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("������ �ִ������� ���մϴ�.");

		System.out.print("�Է��� ������ ���� : ");
		int num = stdIn.nextInt();

		int[] a = new int[num];
		for (int i = 0; i < num; ++i) {
			System.out.print((i + 1) + "��° ���� : ");
			a[i] = stdIn.nextInt();
		}

		stdIn.close();

//		System.out.println("�ִ������� " + gcdArray(a) + "�Դϴ�.");
		System.out.println("�ִ������� " + gcdArray(a, 0, num) + "�Դϴ�.");
	}
}