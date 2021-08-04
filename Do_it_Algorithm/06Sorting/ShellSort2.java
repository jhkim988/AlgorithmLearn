import java.util.Scanner;

class ShellSort2 {
	// h�� �����ϴ� ����� ���ؼ�.
	// n = 8�̰�, h�� 4, 2, 1�� �����Ѵٰ� �����Ѵ�.
	// x = [8, 1, 4, 2, 7, 6, 3, 5]
	// h = 4�� ���� [8, 7], {1, 6}, [4, 3], {2, 5}���� �����Ѵ�.
	// h = 2�� ���� [8, 4, 7, 3], {1, 2, 6, 5}���� �����Ѵ�.
	// ��, []�� {}�� ������ �ʱ� ������ �ⲯ �׷��� ����� h = 1�� �� ������ ���°� �� ������.
	// �̸� �ذ��ϱ� ���ؼ��� h�� ���� ����� ���� �ʵ��� �ؾ��Ѵ�.
	// ���� h�� �ʱ갪�� �ʹ� ũ�� ȿ���� ���� ������ h < n / 9�� �����Ѵ�.

	// �� ������ �ð� ���⵵�� O(n^1.25)��, ����/�ܼ�����/�ܼ������� �ð����⵵�� O(n^2)���� ������.
	// �ָ� ������ �ִ� ��Ҹ� ��ȯ�ϱ� ������ ���������� �ʴ�.
	
	static void shellSort(int[] a, int n) {
		int h;
		for (h = 1; h < n / 9; h = 3 * h + 1)
			;

		for (; h > 0; h /= 3) {
			for (int i = h; i < n; ++i) {
				int j;
				int temp = a[i];
				for (j = i - h; j >= 0 && a[j] > temp; j -= h)
					a[j + h] = a[j];
				a[j + h] = temp;
			}
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("Shell ����");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		shellSort(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}