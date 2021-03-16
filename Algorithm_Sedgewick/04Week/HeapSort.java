
public class HeapSort<Key extends Comparable<Key>> {
	// Build Max heap using bottom-up method.
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	private static void exch(Object[] arr, int idx1, int idx2) {
		Object tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;
	}
	
	private static void sink(Comparable[] arr, int k, int N) { 
		while(2 * k <= N) { // have child?
			int j = 2 * k;
			if(j < N && less(arr[j], arr[j + 1])) j++;
			if(!less(arr[k], arr[j])) break;
			exch(arr, k, j);
			k = j;
		}
	}
	
	public static void heapSort(Comparable[] arr) {
		// construct heap sort - <= 2N compares and exchanges
		int N = arr.length; // arr[1] ~ arr[N]
		for(int k = N / 2; k >= 1; k--) { // arr of half -> leaf
			sink(arr, k, N);
		}
		
		// remove max(root) and heap construct - <= 2N log N compares and exchanges
		while(N > 1) {
			exch(arr, 1, N);
			sink(arr, 1, --N);
		}
	}
	// Heap Sort - N log N worst case, in-place algorithm
	// Merge Sort - linear extra space
	// Quick SOrt - quadratic time in worst case
	
	// inner loop longer than quick sort's
	// makes poor use of cache meemory
	// not stable
}
