import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Q2 Selection in two sorted arrays.
// Given two sorted arrays a[] and b[], of lengths n1 and n2 and an integer 0 <= k < n1 + n2,
// design an algorithm to find a key of rank k.
// The order of growth of the worst case running time of your algorithm should be logn, where n = n1 + n2.

// sol)
// Let i be index of a[] and j be index of b[].
// Use two pointers of a[] and b[](and pointer that have not progressed is set to -1),
// The candidates of rank k elements are sum of pointers = k - 1.
// The minimum value among candidates is rank k value.
// (i, j) moves from (k, -1) to (-1, k). It is bitonic.(decreasing -> increasing)
// we can find minimum value with log n compares.

public class Interview06_02_SelectionInTwoSortedArrays {
	public static int rank(int[] a, int[] b, int k) {
		int range1 = (k < a.length) ? k : (a.length - 1);
		int range2 = (k < b.length) ? k : (b.length - 1);
		return rank(a, b, k, range1, k - range1 - 1, k - range2 - 1, range2);
	}

	private static int rank(int[] a, int[] b, int k, int xlo, int ylo, int xhi, int yhi) {
		if (xhi - xlo <= 1 && yhi - ylo <= 1) {
			int lo = getVal(a, b, xlo, ylo);
			int hi = getVal(a, b, xhi, yhi);
			return (lo < hi) ? lo : hi;
		}

		int xmid = (xhi + xlo) / 2;
		int ymid = k - xmid - 1;
//		StdOut.println("xlo = " + xlo + " \tylo = " + ylo + " \txhi = " + xhi + " \tyhi = " + yhi);
//		StdOut.println("xmid = " + xmid + " \tymid = " + ymid);
//		StdOut.println(incDec(a, b, xmid, ymid));

		if (incDec(a, b, xmid, ymid) < 0) {
			return rank(a, b, k, xmid, ymid, xhi, yhi);
		} else if (incDec(a, b, xmid, ymid) > 0) {
			return rank(a, b, k, xlo, ylo, xmid, ymid);
		} else {
			int tmp1 = rank(a, b, k, xlo, ylo, xmid, ymid);
 			int tmp2 = rank(a, b, k, xmid, ymid, xhi, yhi);
			return (tmp1 > tmp2) ? tmp2 : tmp1;
		}
	}

	private static int getVal(int[] a, int[] b, int i, int j) {
		if (i < 0)
			return b[j];
		if (j < 0)
			return a[i];
		return (a[i] < b[j]) ? b[j] : a[i];
	}

	private static int incDec(int[] a, int[] b, int x, int y) {
		int val = getVal(a, b, x, y);
		int next = getVal(a, b, x - 1, y + 1);
		int prev = getVal(a, b, x + 1, y - 1);

		if (val < next || prev < val) {
			return 1;
		} else if (prev > val || val > next) {
			return -1;
		} else {
			return 0;
		}
	}

	private static int mergeRank(int[] a, int[] b, int k) {
		int ptrA = -1;
		int ptrB = -1;

		for (int i = 0; i < k; i++) {
			if (ptrA + 1 >= a.length) {
				ptrB++;
				continue;
			}
			if (ptrB + 1 >= b.length) {
				ptrA++;
				continue;
			}
			
			if (a[ptrA + 1] <= b[ptrB + 1]) {
				ptrA++;
			} else {
				ptrB++;
			}
		}
		return (a[ptrA + 1] < b[ptrB + 1]) ? a[ptrA + 1] : b[ptrB + 1];
	}

	private static String idxSum(int[] a, int[] b, int k) {
		String result = "";
		int range1 = (k < a.length) ? k : (a.length - 1);
		int range2 = (k < b.length) ? k : (b.length - 1);
		
		int i = range1;
		int j = k - i - 1;
		while(i >= 0 && j < range2) {
			result += getVal(a, b, i--, j++) + " ";
		}		
		return result;
	}

	public static void main(String[] args) {
		int trial = 10000;
		boolean flag = false;

		int n1 = 30;
		int n2 = 40;
		int rng = 100;
		int k = 35;

		int[] a = new int[n1];
		int[] b = new int[n2];

		for (int t = 0; t < trial; t++) {
			for (int i = 0; i < n1; i++)
				a[i] = StdRandom.uniform(rng);
			for (int i = 0; i < n2; i++)
				b[i] = StdRandom.uniform(rng);

			Arrays.sort(a);
			Arrays.sort(b);
			StdOut.println("a[] = " + Arrays.toString(a));
			StdOut.println("b[] = " + Arrays.toString(b));
			StdOut.println(idxSum(a, b, k));
			int mergeRank = mergeRank(a, b, k);
			int rank = rank(a, b, k);
			StdOut.println("merge rank k : " + mergeRank);
			StdOut.println("rank k : " + rank);
			if (mergeRank != rank) {
				flag = true;
				break;
			}
		}
		
		if (flag)
			StdOut.println("FAIL");
		else
			StdOut.println("SUCCESS");
	}
}
