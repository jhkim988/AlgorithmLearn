
public class DuplicateKeys {
	// Merge Sort with Duplicate Keys - Always between (N lg N) / 2 and N lg N compares
	// Quick Sort with Duplicate Keys - Did not stop the partitioning on equal keys -> Quadratic time
	// Mistake - Put all items equal to the partitioning item on one side
	// Ex) B A A B A B B "B" C C C, A A A A A A A A A
	// Consequence - ~ N^2/2 compares when all keys equal
	
	// Recommended - Stop scans on items equal to partitioning item
	// Consequence - ~ N lg N compares when all keys equal
	// Ex) B A A B A "B" C C B C B, A A A A A A A A A
	
	// Desiralbe - Put All items equal to the partitioning item in place. 
	// A A A B B B B B C C C
	
	// 3-way partitioning
	// Interview04_03
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) return;
		int lt = lo, gt = hi;
		Comparable v = a[lo];
		int i = lo;
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) exch(a, lt++, i++);
			else if(cmp > 0) exch(a, i, gt--);
			else i++;
		}
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	
		// worst case - all keys are distinct.
		// Sorting lower bound
		// if there are n distinct keys and the ith one occurs xi times, any compare-based sorting algorithm must use at least
		// lg(N!/(x1!x2!...xn!)) ~ - sigma xi lg(xi/N) compares in the worst case
		// QuickSort with 3 way partitioning is entropy-optimal
		
		// Randomized quick sort with 3 way partitioning reduces running time from linearithmic to linear in board of applications
	}
	
	private static void exch(Comparable[] a, int idx1, int idx2) {
		Comparable tmp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = tmp;
	}
}
