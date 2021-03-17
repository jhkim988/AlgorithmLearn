import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

// Q3. Taxicab numbers.
// A taxicab number is an integer that can be expressed as the sum of two cubes of positive integers in two different ways: a^3 + b^3 = c^3 + d^3.
// For example, 1729 is the smallest taxicab number: 9^3 + 10^3 = 1^3 + 12^3.
// Design an algorithm to find all  taxicab numbers with a, b, c, and d less than n.
// Version 1: Use time proportional to n^2 log n and space proportional to n^2.
// Version 2: Use time proportional to n^2 log n and space proportional to n.

public class Interview07_03_TaxicabNumbers {
	public static void findTaxicabNumberVer1(int N) {
		int[] arr = new int[N*(N+1)/2];
		int auxIdx = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i; j < N; j++) {
				arr[auxIdx + j] = (i + 1) * (i + 1) * (i + 1) + (j + 1) * (j + 1) * (j + 1);
			}
			auxIdx += N - i - 1;
		}
		StdOut.println(Arrays.toString(arr));
		Arrays.sort(arr);
		StdOut.println(Arrays.toString(arr));
	}
	public static void main(String[] args) {
		int N = 13;
		findTaxicabNumberVer1(N);
	}
}
