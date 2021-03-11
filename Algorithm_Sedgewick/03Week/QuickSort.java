import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
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
	
	private static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
//		if (hi <= lo + CUTOFF - 1) {
//			Insertion.sort(a);
//			return;
//		}
		
//		int m = medianOf3(a, lo, lo + (hi - lo)/2, hi);
//		swap(a, lo, m);
		
		if (hi <= lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
		
		// Using an extra array makes partitioning easier (and stable), but is not worth the cost.
		// Terminating Loop - duplicated key -> tricker
		// (j == lo) redundant, but (i == hi) test is not.
		// shuffling is need to for performance guarantee.
		// equal keys ...
		// fester than merge sort
		
		// if shuffled array is sorted, ~ N^2/2
		// The number of compares C_N to quick sort an array of N distint keys is ~ 2NlnN
		// The number of exchanges ~ lnN/3
		// C_0 = C_1 = 0
		// C_N = (N+1) + (C_0 + C_(N-1))/N + (C1 + C_(N-2))/N + ... + (C_(N-1) + C_0)/N
		// (N+1) : partitioning, (left + right) / (partitioning probability)
		// NC_N = N(N+1) + 2(C0 + ... + C_(N-1))
		// NC_N - (N-1)C_(N-1) = 2N + 2C_(N-1) => NC_N = (N+1)C_(N-1) + 2N
		// C_N/(N+1) = C_(N-1)/N + 2/(N+1) = C_(N-2)/(N-1) + 2/N + 2/(N+1) = ... = 2/3 + 2/4 + 2/5 + ... + 2/(N+2)
		// C_N/(N+1) ~ 2*integral(3 to N+1) 1/x dx = 2ln(N+1) - 2ln(3)
		// C_N ~ 2(N+1)lnN ~~ 1.39NlgN
		
		// 39% more compares than merge sort
		// But faster than merge sort because of less data movement.
		
		// Quick Sort is an in-place sorting algorithm.(Does not use any extra space)
		// Depth of recursion - logarithmic extra space(with high probability)
		// Quick Sort is not stable. -> using extra space makes quick sort stable.
		
		// small array - too many overhead
		// when we sort small array, use insertion sort.
		
		// best choice of pivot item = median
		// Estimate true median by taking  median of sample.
		// median-of-3(random) items.
		// Not worth for enlarged samples, but for 3, worthwhile.

	}
}
