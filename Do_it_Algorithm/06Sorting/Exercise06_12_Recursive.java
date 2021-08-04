import java.util.Scanner;

class Exercise06_12_Recursive {
	// Q12 요소의 개수가 적은 그룹을 먼저 나누는 메서드로 수정하세요.
	// 요소수가 적은 배열일수록 적은 횟수로 분할을 종료하기 때문에
	// 요소수가 적은 배열을 먼저 분할하면 스택에 쌓이는 데이터 최대개수를 줄일 수 있다.
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static void quickSort(int[] a, int left, int right) {
		int pl = left;
		int pr = right;
		int x = a[(pl + pr) / 2];

		do {
			while (a[pl] < x)
				pl++;
			while (x < a[pr])
				pr--;
			if (pl <= pr)
				swap(a, pl++, pr--);
		} while (pl <= pr);

		if ((pr - left) < (right - pl)) {
			if (left < pr)
				quickSort(a, left, pr);
			if (pl < right)
				quickSort(a, pl, right);
		} else {
			if (pl < right)
				quickSort(a, pl, right);
			if (left < pr)
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