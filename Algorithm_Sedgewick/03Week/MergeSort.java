
public class MergeSort {
	private static boolean less(Comparable el1, Comparable el2) {
		if(el1.compareTo(el2) < 0)
			return true;
		else
			return false;
	}
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for(int i = lo; i < hi; i++) {
			if(less(a[i + 1], a[i])) {
				return false;
			}
		}
		return true;
	}
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// precondition : a[lo ~ mid] and a[mid + 1 ~ hi] sorted
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		// copy
//		for (int k = lo; k <= hi; k++)
//			aux[k] = a[k];

		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++) {
			if(i > mid) a[k] = aux[j++]; // if [lo, mid] part complete
			else if (j > hi) a[k] = aux[i++]; // if [mid + 1, hi] part complete
			else if (less(aux[j], aux[i])) a[k] = aux[j++]; // both parts Not complte. -> small thing insert
			else a[k] = aux[i++];
		}
		assert isSorted(a, lo, hi);
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if(hi <= lo) return;
		int mid = (lo + hi) / 2;
//		 sort(a, aux, lo, mid);
//		 sort(a, aux, mid + 1, hi);
		// switching the role of the input and auxiliary array
		 sort(aux, a, lo, mid);
		 sort(aux, a, mid + 1, hi);
		 if(!less(a[mid + 1], a[mid])) return; // a[mid] < a[mid + 1] -> already sorted, need not to merge
		 merge(a, aux, lo, mid, hi);
		 
		 // MergeSort uses at most NlgN compares and 6NlgN array accesses to sort any array of size N.
		 // The number of Compares : C(N)
		 // The number of Array Access : A(N)
		 // C(N) <= C(N/2) + C(N/2) + N with C(1) = 0. (left half + right half + merge), Therefore, C(N) <= N lg N
		 // A(N) <= A(N/2) + A(N/2) + 6N with A(1) = 0. A(N) <= 6N lg N
		 
		 // 3 proof
		 // 1. graphic - tree... height = lgN
		 // 2. Mathematical
		 // D(N) <= 2D(N/2) + N, N > 1, D(1) = 0
		 // D(N) <= 2D(N/2) + N <= 4D(N/4) + 2N <= 2^k D(N/2^k) + kN = ND(1) + NlgN = NlgN where k = lgN, N = 2^k
		 // 3. Mathematical induction
		 // D(2N) = 2D(N) + 2N = 2NlgN + 2N = 2N(lg(2N) - 1) + 2N = 2Nlg(2N)
		 
		 // MergeSort uses extra space proportional to N(aux array)
		 // MergeSortt has too mush overhead for tiny subarrays. (too complex, we cannot apply tiny array)
		 // partially sorted array(or already sorted) -> stop
		 // save time(not space) by switching the role of the input and auxiliary array in each recursive call
		 
		 // best case(already sorted) - the number of compares
		 // Not optimization version ~ nlgn/2
		 // optimization version ~ n - 1 (a[mid] < a[mid+1])
	}
	
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	public static void main(String[] args) {
		
	}
}
