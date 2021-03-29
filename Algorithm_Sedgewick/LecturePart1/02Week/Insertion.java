
public class Insertion {
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			for (int j = i; j > 0; j--) {
				if (less(a[j], a[j - 1]))
					exch(a, j - 1, j);
				else
					break;
			}
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
	
	// randomly-ordered array ~N^2/4
	// 포인터가 i에 있을 때 평균적으로 i/2번 exch한다.
	// 1/2 + 2/2 + ... + N/2 ~ N^2/4
	// input 데이터에 따라 다르다.
	
	// Best case : 배열이 정렬돼 있을 경우, N-1회 비교하고, 0회 바꾼다.
	// Worst case : 배열이 거꾸로 정렬돼 있을 경우, ~ N^/2회 비교, ~ N^2/2회 교환
	// 배열이 부분적으로 정렬돼 있을 때 빠르다.
	
	// Inversion - pair of keys that are out of order.\
	// Ex) A E E L M O T R X P S
	// inversion : T-R, T-P, T-S, R-P, X-P, X-S 6개
	// Inversion 횟수가 선형이라면, (c*N보다 작다면) 배열이 부분적으로 정렬돼 있다고 정의한다.
	
	// Insertion Sort는 Partial Sorted array에 대해 선형시간이 걸린다.
	// 비교 횟수, 교환 횟수 = 역위의 수 
}
