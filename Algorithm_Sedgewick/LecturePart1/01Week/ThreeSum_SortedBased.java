public class ThreeSum_SortedBased {
	// N^2 logN algorithm for 3-sum
	// 1. �迭�� �����Ѵ�. (N log N ���ε� �� �� �ִ�.)
	// 2. -(a[i] + a[j])�� binarySearch�� ã�´�. -> N^2 logN
	public static int count(int[] a) {
		int N = a.length;
		int count = 0;
		for (int i = 0; i < N; ++i)
			for (int j = i + 1; j < N; ++j)
				for (int k = j + 1; k < N; ++k)
					if (a[i] + a[j] + a[k] == 0)
						count++;
		return count;
	}

	public static void main(String[] args) {

	}
}
