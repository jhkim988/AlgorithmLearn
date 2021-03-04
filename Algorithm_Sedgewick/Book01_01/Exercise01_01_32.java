import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class Exercise01_01_32 {
	// use 1.1.29
	static int rank(double key, double[] arr) {
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

	static int smallCount(double key, double[] arr) {
		int idx = rank(key, arr);
		if (idx < 0) {
			return -(idx + 1);
		}
		while (idx + 1 < arr.length && arr[idx] == arr[idx + 1])
			idx++;
		return idx + 1;
	}

	public static void histogram(int N, double l, double r, double[] readDouble) {
		double gap = 1.0;
		StdDraw.setXscale(l - gap, r + gap);
		StdDraw.setYscale(0.0, 50.0);
		Arrays.sort(readDouble);
		double width = (r - l) / ((double) N);
		for (int i = 0; i < N; i++) {
			float numData = (float) (smallCount(l + (i + 1) * width, readDouble) - smallCount(l + (i) * width, readDouble));
			StdDraw.filledRectangle(l + (2 * i + 1) * width / 2, numData / 2, width / 3, numData);
		}
	}

	public static void main(String[] args) {
		// N : N°³·Î ±¸°£ ³ª´«´Ù.
		// l : ½ÃÀÛ ÁÂÇ¥
		// r : ³¡ ÁÂÇ¥

		int N = Integer.parseInt(args[0]);
		double l = Double.parseDouble(args[1]);
		double r = Double.parseDouble(args[2]);

//		int N = 10;
//		double l = 0.0;
//		double r = 10.0;

		double[] readDouble = StdIn.readAllDoubles();
//		double[] readDouble = { 1.1, 1.2, 1.3, 2.1, 2.2, 3.3, 4.1, 4.2, 4.3, 5.1, 5.2, 5.3, 5.4, 5.5, 6.1, 7.2, 9.1, 9.2 };
		histogram(N, l, r, readDouble);
	}
}
