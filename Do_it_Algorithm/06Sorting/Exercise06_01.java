import java.util.Scanner;

class Exercise06_01 {
	// Q1. ���������� �� �н����� ��, ��ȯ�� ó��(����)���� �����ص� �˴ϴ�. �׷��� ������ ���α׷��� �ۼ��ϼ���.
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static void bubbleSort(int[] a, int n) {
		for (int i = n - 1; i > 0; --i)
			for (int j = 0; j < i; ++j)
				if (a[j] > a[j + 1])
					swap(a, j, j + 1);
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("��������(Q1)");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		bubbleSort(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}