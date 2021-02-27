
public class Selection {
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i; // index of min
			for (int j = i + 1; j < N; j++) {
				if (less(a[j], a[min]))
					min = j;
			}
			exch(a, i, min);
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
	
	// (N-1) + (N-2) + ... + 1 + 0 ~ N^2/2
	// 거의 정렬됐거나, 부분적으로 정렬돼 있어도 걸리는 시간이 길다.
	// input data에 의존하지 않는다.
}
