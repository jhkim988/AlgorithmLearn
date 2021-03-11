import edu.princeton.cs.algs4.StdOut;

// Q2 Counting inversions.
// An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but a[i]>a[j].
// Given an array, design a linearithmic algorithm to count the number of inversions.

// sol)
// In Merge Sort Process, Counting the number of inversions.
// Suppose array a[] has two sorted part. (a[0], ..., a[mid], a[mid + 1], ..., a[N-1])
// In this case, we can count the number of inversions in O(N) times.
// Let i be index of front half of array, and j be index of rear half of array.(i.e. i < j)
// If a[i] is larget than a[j], then a[i+1] is larget than a[j], since a[0] ~ a[mid] are sorted.
// (a[i] > a[j] => a[i+1] > a[j])
// Therefore, when we want to find the number of j such that a[i+1] > a[j] some fixed i,
// we need not to check j in the first(of rear part) index(a[mid + 1]).
// From j' where a[i] becomes less(or equal) than a[j'], we just need to check, and sum.
// By above method, we can count the number of inversions in O(N) times.

public class Interview05_02 {
	private static String toStringArray(int[] a, int lo, int hi) {
		String result = "[ ";
		for (int i = lo; i <= hi; i++) {
			result += a[i] + " ";
		}
		result += "]";
		return result;
	}

	public static int counterInversion(int[] a, int[] aux, int lo, int hi) {
		StdOut.println(toStringArray(a, lo, hi));
		// precondition - sorted : a[lo ~ mid], a[mid+1 ~ hi]
		int N = hi - lo + 1;
		if (N < 2)
			return 0;
		if (N == 2) {
			if (a[lo] > a[hi]) {
				StdOut.println("Front Inversion Occur : " + a[lo] + ", " + aux[hi]);
				int temp = a[lo];
				a[lo] = a[hi];
				a[hi] = temp;
				return 1;
			}
			return 0;
		}

		// Suppose we know the number of inversion
		// a[lo], ..., a[mid], a[mid + 1], ..., a[hi]
		int mid = (lo + hi) / 2;
		int sum = counterInversion(aux, a, lo, mid) + counterInversion(aux, a, mid + 1, hi);
		
		// count - O(n)
		int ptr1 = lo;
		int ptr2 = mid + 1;
		int sumTmp = 0;
		while(ptr1 <= mid) {
//			StdOut.println("aux[] - " + lo + " ~ " + mid + " : " + toStringArray(aux, lo, mid));
//			StdOut.println("aux[] - " + (mid + 1) + " ~ " + hi + " : " + toStringArray(aux, (mid + 1), hi));
			while(ptr2 <= hi && aux[ptr1] > aux[ptr2]) {
				sumTmp++;
				ptr2++;
			}
			sum += sumTmp;
			ptr1++;
		}

		// merge
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (aux[i] < aux[j]) {
				a[k] = aux[i++];
			} else {
				a[k] = aux[j++];
			}
		}
		StdOut.println(toStringArray(a, lo, hi));
		return sum;
	}

	public static int counterInversion(int[] a) {
		// copy
		int N = a.length;
		int[] aux1 = new int[N];
		int[] aux2 = new int[N];
		for (int i = 0; i < N; i++) {
			aux1[i] = a[i];
			aux2[i] = a[i];
		}

		return counterInversion(aux1, aux2, 0, N - 1);
	}

	public static void main(String[] args) {
		int[] test = { 1, 5, 3, 2, 4, 0 };
		StdOut.println("The Number of Inversion of test : " + counterInversion(test));
	}
}
