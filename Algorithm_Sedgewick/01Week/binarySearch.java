
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
	
	// size N�� ���ĵ� �迭�� ���� binarySearch�� ���ƾ� 1 + lg(N)ȸ �����Ѵ�.
	// T(N) = ����Ƚ��
	// T(N) <= T(N/2) + 1 (�񱳸� 1ȸ ��) for N > 1 with T(1) = 1
	// T(N) <= T(N/2) + 1 <= T(N/4) + 1 + 1 ... <= 1 + lg(N)
}
