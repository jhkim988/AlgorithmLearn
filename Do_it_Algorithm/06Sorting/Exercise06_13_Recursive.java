import java.util.Scanner;

class Exercise06_13_Recursive {
	// Q13 �������� ����� ������ ���� �迭�� ���ؼ��� ó���� ���� ������ ������� �ʴ´ٰ� �˷��� �ֽ��ϴ�.
	// �������� Q12���� �ۼ��� �� quickSort �޼��带 ���� �׷��� ��� ������ 9�� �����̸� �ܼ� ���� ���ķ� �����ϴ� �޼���� �����ϼ���
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static void insertionSort(int[] a, int n) {
		insertionSort(a, 0, n - 1);
	}

	static void insertionSort(int[] a, int start, int end) {
		// a[start] ~ a[end]�� �ܼ����������Ѵ�.
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
		int x = a[(pl + pr) / 2];

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

		System.out.println("�� ����");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		quickSort(x, 0, nx - 1);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}