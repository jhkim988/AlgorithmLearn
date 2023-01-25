import java.util.*;

public class Bound {
	public static void main(String[] args) {
		final int len = 10;
		final int lim = 8;

		int[] seq = new int[len];
		for (int i = 0; i < len; i++) {
			seq[i] = (int) (Math.random() * lim);
		}

		Arrays.sort(seq);
		System.out.println(Arrays.toString(seq));
		System.out.println(upperBound(seq, 5));
		System.out.println(lowerBound(seq, 5));
	}

	static int upperBound(int[] seq, int key) {
		// find min { x : key < seq[x] }
		int lo = -1;
		int hi = seq.length;

		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (seq[mid] <= key) { // check(x) = seq[x] <= key
				lo = mid;
			} else {
				hi = mid;
			}
		}
		return hi;
	}

	static int lowerBound(int[] seq, int key) {
		// find max { x : seq[x] < key }
		int lo = -1;
		int hi = seq.length;

		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (seq[mid] < key) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		return hi;
	}
}
