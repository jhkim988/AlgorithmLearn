import java.util.Scanner;

class Exercise06_08 {
	// Q8 �̹� ���ĵ� �κп��� ���� �˻��� ����Ͽ� ������ ��ġ�� ã������.
	static int binary_Search(int[] a, int range_idx, int key) {
		// �迭 a���� a[0] ~ a[range_idx]���� binary search�� �����մϴ�.
		// a[i] <= key < a[i+1]�� �����ϴ� i�� ��ȯ�մϴ�.
		int left_ptr = 0;
		int right_ptr = range_idx;

		if (a[left_ptr] > key)
			return -1;
		if (a[right_ptr] <= key)
			return range_idx;

		do {
			int center = (left_ptr + right_ptr) / 2;
			if (a[center] < key) {
				left_ptr = center;
			} else if (a[center] > key) {
				right_ptr = center;
			} else {
				return center;
			}
		} while (right_ptr - left_ptr > 1);

		return left_ptr;
	}

	static void binary_insertionSort(int[] a, int n) {
		for (int i = 1; i < n; ++i) {
			// a[0], ..., a[i-1]�� a[i]�� �����Ѵ�.
			int idx = binary_Search(a, i - 1, a[i]);
			int temp = a[i];
			
			// a[0] < ... < a[idx] <= a[i] < a[idx+1] < ... a[i-1]
			// idx+1 ~ i-1�� ���������� �� ĭ�� �Űܾ��Ѵ�.
			for (int j = i - 1; j > idx; --j)
				a[j + 1] = a[j];
			a[idx + 1] = temp;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("������������");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		binary_insertionSort(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}