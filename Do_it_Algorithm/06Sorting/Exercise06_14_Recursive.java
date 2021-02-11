import java.util.Scanner;

class Exercise06_14_Recursive {
	// Q14 피벗 선택하기의 방법 1을 사용하여 Q13의 메서드를 수정하세요
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static int med3(int a, int b, int c) {
		if (a >= b) {
			if (b >= c) { // a >= b >= c
				return b;
			} else if (a <= c) { // a >= b, c > b
				return a;
			} else
				return c;
		} else if (a > c) // b > a
			return a;
		else if (b > c) // b > a, c >= a
			return c;
		else // c >= b > a
			return b;
	}
	
	static void insertionSort(int[] a, int n) {
		insertionSort(a, 0, n - 1);
	}

	static void insertionSort(int[] a, int start, int end) {
		// a[start] ~ a[end]를 단순삽입정렬한다.
		for (int i = 1 + start; i <= end; ++i) {
			int j;
			int temp = a[i];
			for (j = i; j > start && a[j - 1] > temp; --j)
				a[j] = a[j - 1];
			a[j] = temp;
		}
	}

	static void quickSort(int[] a, int left, int right) {
		int pl = left;
		int pr = right;
		int pc = (pl + pr) / 2;
		
		int x = med3(a[pl], a[pc], a[pr]);

		boolean flag1 = false;
		boolean flag2 = false;
		
		do {
			while (a[pl] < x) pl++;
			while (x < a[pr]) pr--;
			if (pl <= pr) swap(a, pl++, pr--);
		} while (pl <= pr);

		if (pr - left + 1 < 10) {
			insertionSort(a, left, pr);
			flag1 = true;
		}

		if (right - pl + 1 < 10) {
			insertionSort(a, pl, right);
			flag2 = true;
		}

		if ((pr - left) < (right - pl)) {
			if (left < pr && !flag1)
				quickSort(a, left, pr);
			if (pl < right && !flag2)
				quickSort(a, pl, right);
		} else {
			if (pl < right && !flag2)
				quickSort(a, pl, right);
			if (left < pr && !flag1)
				quickSort(a, left, pr);
		}

	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("퀵 정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		quickSort(x, 0, nx - 1);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}