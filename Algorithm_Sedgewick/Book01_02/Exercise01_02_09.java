import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_09 {
	// 1.2.9
	// Binary Search�� �����Ͽ� �˻�� key�� ��ü ������ Counter Ŭ������ �̿��� ����ϵ��� �غ���.
	// ��, ���� ����� ���ϱ������ ��� Ž�� ���� ���� key�� ���� �˻簡 �� ���̳� �̷�������� ����Ѵ�.
	// ��Ʈ - main() �ȿ��� Counter ��ü�� �����Ͽ� rank()�� �μ��� �ѱ��.
	static int binarySearch(int key, int[] arr, Counter counter) {
		int lo = 0;
		int hi = arr.length - 1;
		while (lo <= hi) {
			counter.increment();
			int mid = (lo + hi) / 2;
			if (arr[mid] > key) {
				hi = mid - 1;
			} else if (arr[mid] < key) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Counter counter = new Counter("binarySearch");
		int key = 10;
		int[] arr = { -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
		binarySearch(key, arr, counter);
		StdOut.println("Check Count : " + counter.tally());
	}
}
