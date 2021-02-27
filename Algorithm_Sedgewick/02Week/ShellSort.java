
public class ShellSort {
	// h-정렬을 한 후에 k-정렬을 해도 여전히 h-정렬이 된 상태이다.
	// h를 어떻게 정할 것인가?
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
		int h = 1; // h를 3h + 1인 수열로..
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
	// h의 수열을 3h + 1로 잡으면, ~ O(N^(3/2))
	// 사이즈가 매우 큰 배열이 아닐 땐 효율이 좋다.(중간 크기의 배열에서)
	// 코드가 단순하다.
}
