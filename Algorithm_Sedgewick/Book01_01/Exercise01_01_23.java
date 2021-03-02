import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_23 {
	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (a[mid] < key)
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public static void main(String[] args) {
		
		if (args[0].equals("+")) {
			In in = new In(args[1]);
			int[] whiteList = in.readAllInts();
			Arrays.sort(whiteList);
			while (!StdIn.isEmpty()) {
				int key = StdIn.readInt();
				if (rank(key, whiteList) == -1)
					StdOut.println(key);
			}
		} else if (args[0].equals("-")) {
			In in = new In(args[1]);
			int[] whiteList = in.readAllInts();
			Arrays.sort(whiteList);
			while (!StdIn.isEmpty()) {
				int key = StdIn.readInt();
				if (rank(key, whiteList) != -1)
					StdOut.println(key);
			}
		}
		
	}
}
