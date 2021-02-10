import java.util.Scanner;

class Exercise06_01 {
	// Q1. 버블정렬의 각 패스에서 비교, 교환은 처음(왼쪽)부터 수행해도 됩니다. 그렇게 수정한 프로그램을 작성하세요.
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

		System.out.println("버블정렬(Q1)");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		bubbleSort(x, nx);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}