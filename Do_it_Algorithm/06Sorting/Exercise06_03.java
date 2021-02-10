import java.util.Scanner;

class Exercise06_03 {
	// Q2. 버블 정렬 버전2의 수행과정을 출력하는 프로그램 작성
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static void bubbleSort_print(int[] a, int n) {
		for (int i = 0; i < n - 1; i++) {
			int exchg = 0;
			System.out.println("패스" + (i + 1) + ":");

			for (int j = n - 1; j > i; --j) {

				for (int k = 0; k < j; ++k) {
					System.out.print(" " + a[k] + " ");
				}

				if (a[j - 1] > a[j]) {
					System.out.print("+");
				} else
					System.out.print("-");

				for (int k = j; k < n; ++k) {
					System.out.print(" " + a[k] + " ");
				}

				if (a[j - 1] > a[j]) {
					swap(a, j - 1, j);
					exchg++;
				}
				
				System.out.println();
			}
			for (int j = 0; j < n; ++j)
				System.out.print(" " + a[j] + " ");
			System.out.println();
			if (exchg == 0) break;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("버블정렬 버전2(과정)");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		bubbleSort_print(x, nx);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}