import java.util.Scanner;

class BubbleSort {
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	// a.length = n
	// a[0], a[1], ... , a[n-1]
	static void bubbleSort(int[] a, int n) {
		for (int i = 0; i < n - 1; ++i)
			for (int j = n - 1; j > i; --j)
				if (a[j - 1] > a[j])
					swap(a, j -1, j);
	}


	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("��������(���� 2)");
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