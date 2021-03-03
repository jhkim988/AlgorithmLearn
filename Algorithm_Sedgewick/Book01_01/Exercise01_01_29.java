import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_29 {
	static int rank(int key, int[] arr) {
		int lo = 0;
		int hi = arr.length - 1;
		int mid = 0;
		while (lo <= hi) {
			mid = (lo + hi) / 2;
			if (arr[mid] < key) {
				lo = mid + 1;
			} else if (arr[mid] > key) {
				hi = mid - 1;
			} else {
				while (mid + 1 < arr.length && arr[mid] == arr[mid + 1])
					mid++;
				return mid;
			}
		}
		while (arr[mid] < key && mid + 1 < arr.length && arr[mid] == arr[mid + 1])
			mid++;
		
		return -mid - 1; // point
	}

	static int smallCount(int key, int[] arr) {
		int idx = rank(key, arr);
		if (idx < 0) {
			return -(idx + 1);
		}
		while (idx + 1 < arr.length && arr[idx] == arr[idx + 1])
			idx++;
		return idx + 1;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 1, 1, 3, 3, 5, 5, 6, 7, 8, 9 };
		Arrays.sort(arr);
		StdOut.println(rank(9, arr));
		StdOut.println(smallCount(9, arr));
	}
}
