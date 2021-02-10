import java.util.Scanner;

class Exercise06_04 {
	// Q4. �������� ����3�� ���� ������ ����ϴ� ���α׷� �ۼ�
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static void bubbleSort_print(int[] a, int n) {
		int k = 0;
		int pass = 0;
		while (k < n - 1) {
			int last = n - 1;

			System.out.println("�н�" + (++pass) + ":");

			for (int j = n - 1; j > k; --j) {
				for (int i = 0; i < j; ++i) {
					System.out.print(a[i] + " ");
				}

				if (a[j - 1] > a[j])
					System.out.print("+");
				else
					System.out.print("-");
					
				for (int i = j; i < n; ++i) {
					System.out.print(a[i] + " ");
				}
				System.out.println();
				
				if (a[j - 1] > a[j]) {
					swap(a, j - 1, j);
					last = j;
				}
			}
			k = last;
			for (int i = 0; i < n; ++i) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("�������� ����3(����)");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		bubbleSort_print(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}