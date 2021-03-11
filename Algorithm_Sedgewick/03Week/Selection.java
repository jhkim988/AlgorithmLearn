import edu.princeton.cs.algs4.StdRandom;

public class Selection {
	// Given an array of N items, find the k'th largest element.
	// Ex. Min(k=0), Max(k=N-1), median(k=N/2)
	// Application. Order statics. Find the top k.
	
	// N lg N upper bound
	// N upper bound for k = 1, 2, 3
	// N lower bound - 하나라도 보지 않으면 놓칠 수 있다.
	
	// which is true?
	// N lg N lower bound?
	// N upper bound?
	
	// Quick-Select
	// using partition
	public static Comparable select(Comparable[] a, int k) {
		StdRandom.shuffle(a);
		int lo = 0, hi = a.length - 1;
		while(hi > lo) {
			int j = partition(a, lo, hi);
			if(j < k) lo = j + 1;
			else if(j > k) hi = j - 1;
			else return a[k];
		}
		return a[k];
	}
	// Quick-Select takes linear time on average.
	// Intuitively, each partitioning step splits array approximately in half:
	// N + N/2 + N/4 + ... + 1 ~ 2N
	// Formal analysis similar to quicksort analysis yields:
	// C_N = 2N + 2kln(N/k) + 2(N-k)ln(N/(N-k))
	// Median - (2+2ln2)N
	
	private static void exch(Comparable[] a, int idx1, int idx2) {
		Comparable tmp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = tmp;
	}
	
	private static boolean less(Comparable a, Comparable b) {
		if(a.compareTo(b) < 0)
			return true;
		return false;
	}
	
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		while(true) {
			while(less(a[++i], a[lo])) // find item a[lo] < a[i]
				if (i == hi) break;
			while(less(a[lo], a[--j])) // find item a[j] < a[lo]
				if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
}	
