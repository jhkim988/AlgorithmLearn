public class ThreeSum_SortedBased {
	// N^2 logN algorithm for 3-sum
	// 1. 배열을 정렬한다. (N log N 으로도 할 수 있다.)
	// 2. -(a[i] + a[j])을 binarySearch로 찾는다. -> N^2 logN
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
