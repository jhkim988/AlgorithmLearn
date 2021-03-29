
public class binarySearch {
	public static int binarySearch(int[] a, int key) {
		int lo = 0; int hi = a.length-1;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if (a[mid] < key) lo = mid + 1;
			else if (a[mid] > key) hi = mid - 1;
			else return mid;
		}
		return -1;
	}
	
	// size N의 정렬된 배열에 대해 binarySearch는 많아야 1 + lg(N)회 연산한다.
	// T(N) = 연산횟수
	// T(N) <= T(N/2) + 1 (비교를 1회 함) for N > 1 with T(1) = 1
	// T(N) <= T(N/2) + 1 <= T(N/4) + 1 + 1 ... <= 1 + lg(N)
}
