import java.util.Scanner;

class InsertionSort {

	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static void insertionSort(int[] a, int n) {
		for (int i = 1; i < n; ++i) {
			int j;
			int temp = a[i];
			for (j = i; j > 0 && a[j - 1] > temp; --j)
				a[j] = a[j - 1];
			a[j] = temp;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("�ܼ���������");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		insertionSort(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}