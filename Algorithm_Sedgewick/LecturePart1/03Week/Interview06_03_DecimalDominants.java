import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Q3 Decimal dominants.
// Given an array with n keys, design an algorithm to find all values that occur more than n/10 times.
// The expected running time of your algorithm should be linear.

// sol)
// In 3-way-partition process, We can count how many times the same element appears.
// If 3-way-partition completed([ smaller | lt ~ gt | i larger]), the number of same elements is gt - lt + 1
// Recursively Repeat smaller and larger parts.
public class Interview06_03_DecimalDominants {
	public static void sort(int[] arr, int num) {
		sort(arr, 0, arr.length - 1, num);
	}

	private static void sort(int[] arr, int lo, int hi, int num) {
		// use 3 way partitioning
		if (lo >= hi)
			return;
		int lt = lo, gt = hi;
		int v = arr[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = (arr[i] > v) ? 1 : ((arr[i] < v) ? -1 : 0);
			if (cmp < 0)
				exch(arr, lt++, i++);
			else if (cmp > 0)
				exch(arr, i, gt--);
			else
				i++;
		}
		int times = gt - lt + 1;
		if (times >= arr.length / num)
			StdOut.println(arr[lo] + " repeats " + times + " times");
		sort(arr, lo, lt - 1, num);
		sort(arr, gt + 1, hi, num);
	}

	private static void exch(int[] arr, int idx1, int idx2) {
		int tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;
	}

	public static void main(String[] args) {
		int size = 100; // array size
		int range = 10; // the number of key
		int num = 10;
		int[] arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = StdRandom.uniform(range);
		}
		StdOut.println(Arrays.toString(arr));
		sort(arr, num);
		StdOut.println(Arrays.toString(arr));
	}
}
