import java.util.Scanner;

class Exercise06_15_Recursive {
	// Q15 피벗 선택하기의 방법 2를 사용하여 Q13의 메서드를 수정하세요
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	// 인덱스를 반환하도록 함수를 수정했다.
	static int med_idx(int[] x, int a, int b, int c) {
		if (x[a] >= x[b]) {
			if (x[b] >= x[c]) { // a >= b >= c
				return b;
			} else if (x[a] <= x[c]) { // a >= b, c > b
				return a;
			} else
				return c;
		} else if (x[a] > x[c]) // b > a
			return a;
		else if (x[b] > x[c]) // b > a, c >= a
			return c;
		else // c >= b > a
			return b;
	}

	static int max_idx(int[] x, int a, int b, int c) {
		int min_idx = a;
		if (x[min_idx] < x[b])
			min_idx = b;
		if (x[min_idx] < x[c])
			min_idx = c;
		return min_idx;
	}

	static int min_idx(int[] x, int a, int b, int c) {
		int min_idx = a;
		if (x[min_idx] > x[b])
			min_idx = b;
		if (x[min_idx] > x[c])
			min_idx = c;
		return min_idx;
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

		int x = a[pc];
		
		if (right - left + 1 >= 3) {
			int minIdx = min_idx(a, pl, pc, pr);
			int medIdx = med_idx(a, pl, pc, pr);
			int maxIdx = max_idx(a, pl, pc, pr);
			
			int temp1 = a[minIdx];
			int temp2 = a[medIdx];
			int temp3 = a[maxIdx];
			
			a[pl] = temp1;
			a[pc] = temp2;
			a[pr] = temp3;
			
			x = a[pc];
			swap(a, pc, right - 1);
			
			pl = left + 1;
			pr = right - 2;
		}
		
		boolean flag1 = false;
		boolean flag2 = false;

		do {
			while (a[pl] < x)
				pl++;
			while (x < a[pr])
				pr--;
			if (pl <= pr)
				swap(a, pl++, pr--);
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