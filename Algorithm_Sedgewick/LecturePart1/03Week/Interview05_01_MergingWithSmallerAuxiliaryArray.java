import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

// Q1 Merging with smaller auxiliary array.
// Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to a[2*n−1] is sorted.
// How can you merge the two subarrays so that a[0] to a[2*n−1] is sorted using an auxiliary array of length n (instead of 2n)?

// sol)
// copy a[0], ..., a[n-1] to aux.
// merge aux[] and rear half of a[] (a[n], ..., a[2*n-1]) to a[]
public class Interview05_01_MergingWithSmallerAuxiliaryArray {
	static void merge(int[] a) {
		// a[] = a[0], ..., a[N/2 - 1], a[N/2], ..., a[N-1]
		// aux[] = a[0], ..., a[N/2], copy front half
		// aux[], a[] -> merge to a[]

		int N = a.length;
		int mid = N / 2;
		int[] aux = new int[mid]; // copy a[0] ~ a[N/2 - 1]
		for (int i = 0; i < mid; i++)
			aux[i] = a[i];

		int ptr1 = 0; // pointer of aux
		int ptr2 = mid; // pointer of a, rear half
		int ptr3 = 0; // pointer of a

		for (ptr3 = 0; ptr3 < N; ptr3++) {
			if (ptr1 >= mid) {
				a[ptr3] = a[ptr2++];
			} else if (ptr2 >= N) {
				a[ptr3] = aux[ptr1++];
			} else if (aux[ptr1] < a[ptr2]) { // careful - aux[ptr1] < a[ptr2]
				a[ptr3] = aux[ptr1++];
			} else {
				a[ptr3] = a[ptr2++];
			}

		}
	}

	static void merge(Comparator comparator, Object[] a) {
		// a[] = a[0], ..., a[N/2 - 1], a[N/2], ..., a[N-1]
		// aux[] = a[0], ..., a[N/2], copy front half
		// aux[], a[] -> merge to a[]

		int N = a.length;
		int mid = N/2;
		Object[] aux = new Object[N / 2]; // copy a[0] ~ a[N/2]
		for(int i = 0; i < mid; i++)
			aux[i] = a[i];
		
		int ptr1 = 0; // pointer of aux
		int ptr2 = mid; // pointer of a, rear half
		int ptr3 = 0; // pointer of a

		for (ptr3 = 0; ptr3 < N; ptr3++) {
			if (ptr1 >= mid) {
				a[ptr3] = a[ptr2++];
			} else if (ptr2 >= N) {
				a[ptr3] = aux[ptr1++];
			} else if (comparator.compare(aux[ptr1], a[ptr2]) < 0) { // careful - aux[ptr1] < a[ptr2]
				a[ptr3] = aux[ptr1++];
			} else {
				a[ptr3] = a[ptr2++];
			}

		}
	}

	public static void main(String[] args) {
		int[] array = { 0, 2, 5, 6, 7, 8, 1, 3, 5, 7, 9, 11 }; // n = 6
		merge(array);
		StdOut.println(Arrays.toString(array));
	}
}
