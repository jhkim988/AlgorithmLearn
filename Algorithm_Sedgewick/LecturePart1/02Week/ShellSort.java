
public class ShellSort {
	// h-������ �� �Ŀ� k-������ �ص� ������ h-������ �� �����̴�.
	// h�� ��� ���� ���ΰ�?
	public static void hSort(Comparable[] a, int h) {
		int N = a.length;
		for (int i = h; i < N; i++) {
			for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
				exch(a, i, j);
			}
		}
	}

	public static void shellSort(Comparable[] a) {
		int N = a.length;
		int h = 1; // h�� 3h + 1�� ������..
		while (h < N / 3)
			h = 3 * h + 1;
		while (h > -1) {
			hSort(a, h);
			h /= 3;
		}
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	// h�� ������ 3h + 1�� ������, ~ O(N^(3/2))
	// ����� �ſ� ū �迭�� �ƴ� �� ȿ���� ����.(�߰� ũ���� �迭����)
	// �ڵ尡 �ܼ��ϴ�.
}
