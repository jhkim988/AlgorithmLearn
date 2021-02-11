import java.util.Scanner;

class QuickSort {
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
			while(a[pl] < x) pl++;
			while(x < a[pr]) pr--;
			if (pl <= pr)
				swap(a, pl++, pr--);
		} while(pl <= pr);
		
		if(left < pr) quickSort(a, left, pr);
		if(pl < right) quickSort(a, pl, right);
		// left < pr, pl < right 모두 요소가 1개면 성립하지 않는다. 즉 요소가 1개이면 재귀호출을 멈춘다.
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