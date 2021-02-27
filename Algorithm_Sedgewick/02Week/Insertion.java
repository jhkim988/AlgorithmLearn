
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
	// �����Ͱ� i�� ���� �� ��������� i/2�� exch�Ѵ�.
	// 1/2 + 2/2 + ... + N/2 ~ N^2/4
	// input �����Ϳ� ���� �ٸ���.
	
	// Best case : �迭�� ���ĵ� ���� ���, N-1ȸ ���ϰ�, 0ȸ �ٲ۴�.
	// Worst case : �迭�� �Ųٷ� ���ĵ� ���� ���, ~ N^/2ȸ ��, ~ N^2/2ȸ ��ȯ
	// �迭�� �κ������� ���ĵ� ���� �� ������.
	
	// Inversion - pair of keys that are out of order.\
	// Ex) A E E L M O T R X P S
	// inversion : T-R, T-P, T-S, R-P, X-P, X-S 6��
	// Inversion Ƚ���� �����̶��, (c*N���� �۴ٸ�) �迭�� �κ������� ���ĵ� �ִٰ� �����Ѵ�.
	
	// Insertion Sort�� Partial Sorted array�� ���� �����ð��� �ɸ���.
	// �� Ƚ��, ��ȯ Ƚ�� = ������ �� 
}
