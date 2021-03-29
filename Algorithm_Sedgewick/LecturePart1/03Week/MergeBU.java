
public class MergeBU {
	private static Comparable[] aux;
	private static boolean less(Comparable el1, Comparable el2) {
		if (el1.compareTo(el2) < 0)
			return true;
		else
			return false;
	}
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for(int i = lo; i < hi; i++) {
			if(less(a[i + 1], a[i]))
				return false;
		}
		return true;
	}
	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid+1, hi);
		
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++) {
			if(i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(a[i], a[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
		
		assert isSorted(a, lo, hi);
	}
	public static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for(int sz = 1; sz < N; sz = sz + sz) { // sz = 1, 2, 4, 8, ..., lgN
			for(int lo = 0; lo < N - sz; lo += sz + sz) { // each sz, merge it.
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
			}
		}
	}
	// Complexity of sorting
	// Model of computation : decision tree.(can access information only through compares)
	// cost model : the number of compares
	// upper bound : ~ N lg N from Merge Sort
	// lower bound : ~ N lg N
	
	// Any compare-based sorting algorithm must use at least lg(N!) ~ NlgN compares in worst case.
	// pf) N! <= the number of leaves in decision tree <= 2^h
	// lgN! <= height of decision tree, h
	// h = worst case...

	// Compares? Merge sort is optimal with respect to # compares
	// Space? Not optimal
	
	// partially-ordered arrays : we may not need N lg N compares(insertion sort, N-1 compares)
	// duplicate keys : we may not need N lg N compares
}
