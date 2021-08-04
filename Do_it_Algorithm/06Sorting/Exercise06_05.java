import java.util.Scanner;

class Exercise06_05 {
	// Q5. 9 1 3 4 6 7 8
	// �� �迭�� �� ��° ��Һ��ʹ� ������ �� ������, ���� 3�� ���� �˰����� ����ص� ���� �ð� �ȿ� ���� �۾��� ��ĥ ���� �����ϴ�.
	// �н��� ��ĵ ������ ����� �ٲٸ� �̷� ������ �� ���� Ƚ���� ���� �� �ֽ��ϴ�.
	// ����� ���� ����(Ĭ���� ����, ����Ŀ ����)�� �����ϴ� ���α׷��� �ۼ��ϼ���
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static int right_bubble(int[] a, int n, int end) {
		// �������� �н��� 1ȸ end���� �����Ѵ�.
		int last = n - 1;
		for (int i = n - 1; i > end; --i)
			if (a[i - 1] > a[i]) {
				swap(a, i - 1, i);
				last = i;
			}
		System.out.println("right_bubble " + last);
		return last;

	}

	static int left_bubble(int[] a, int n, int end) {
		// �������� �н��� 1ȸ end���� �����Ѵ�.
		int last = 0;
		for (int i = 0; i < end; ++i)
			if (a[i] > a[i + 1]) {
				swap(a, i, i + 1);
				last = i + 1;
			}
		System.out.println("left_bubble " + last);
		return last;
	}

	static void bidirection_bubbleSort(int[] a, int n) {
		boolean flag = true;

		int left_end = n - 1;
		int right_end = 0;

		while (left_end != 0 && right_end != n - 1) {
			if (flag)
				right_end = right_bubble(a, n, right_end);
			else
				left_end = left_bubble(a, n, left_end);
			flag = !flag;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("����� ��������");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		bidirection_bubbleSort(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}