import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_09 {
	// 1.2.9
	// Binary Search를 수정하여 검사된 key의 전체 개수를 Counter 클래스를 이용해 기록하도록 해보라.
	// 즉, 최종 결과를 구하기까지의 모든 탐색 과정 동안 key에 대한 검사가 몇 번이나 이루어졌는지 출력한다.
	// 힌트 - main() 안에서 Counter 객체를 생성하여 rank()의 인수로 넘긴다.
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
