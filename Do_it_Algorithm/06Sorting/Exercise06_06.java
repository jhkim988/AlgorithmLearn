import java.util.Scanner;

class Exercise06_06 {
	// Q6 �ܼ� ���� ���� ������ ���
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static String space(int n) {
		String result = "";
		for (int i = 0; i < n; ++i)
			result += " ";
		return result;
	}

	static void selectionSort_print(int[] a, int n) {
		for (int i = 0; i < n - 1; ++i) {
			System.out.print(space(i*2) + "*");
			int min = i;
			for (int j = i + 1; j < n; ++j) {
				if (a[min] > a[j])
					min = j;
			}
			System.out.print(space(2*(min - i)-1) + "+\n");
			for (int j = 0; j < n; ++j) {
				System.out.printf("%-2d", a[j]);
			}
			System.out.println();
			swap(a, i, min);
		}

		for (int j = 0; j < n; ++j) {
			System.out.printf("%-2d", a[j]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("�ܼ���������(���)");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		selectionSort_print(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}