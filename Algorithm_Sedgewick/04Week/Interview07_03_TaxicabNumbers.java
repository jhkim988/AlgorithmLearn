import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

// Q3. Taxicab numbers.
// A taxicab number is an integer that can be expressed as the sum of two cubes of positive integers in two different ways: a^3 + b^3 = c^3 + d^3.
// For example, 1729 is the smallest taxicab number: 9^3 + 10^3 = 1^3 + 12^3.
// Design an algorithm to find all  taxicab numbers with a, b, c, and d less than n.
// Version 1: Use time proportional to n^2 log n and space proportional to n^2.
// Version 2: Use time proportional to n^2 log n and space proportional to n.

import edu.princeton.cs.algs4.MinPQ;

public class Interview07_03_TaxicabNumbers {
	private static int cubes(int i, int j) {
		return i * i * i + j * j * j;
	}

	private static class SumOfCubes implements Comparable<SumOfCubes> {
		public int A;
		public int B;
		public int result;

		public SumOfCubes(int a, int b) {
			A = a;
			B = b;
			result = a * a * a + b * b * b;
		}

		public int compareTo(SumOfCubes that) {
			if (this.result > that.result) {
				return 1;
			} else if (this.result < that.result) {
				return -1;
			} else {
				if (this.A > that.A) {
					return 1;
				} else if (this.A < that.A) {
					return -1;
				}
				return 0;
			}
		}

		public String toString() {
			return "[" + A + ", " + B + ", " + result + "]";
		}
	}

	public static void findTaxicabNumberVer1(int N) {
		SumOfCubes[] arr = new SumOfCubes[N * (N + 1) / 2];
		int auxIdx = 0;

		// initialize N(N+1)/2, space N(N+1)/2
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				arr[auxIdx + j] = new SumOfCubes(i + 1, j + 1);
			}
			auxIdx += N - i - 1;
		}

		// sorting -> N(N+1)log(N(N+1)/2)/2 ~ N^2logN
		StdOut.println(Arrays.toString(arr));
		Arrays.sort(arr);
		StdOut.println(Arrays.toString(arr));

		// Find -> N(N+1)
		for (int i = 1; i < N * (N + 1) / 2; i++) {
			if (arr[i].result == arr[i - 1].result) {
				StdOut.println(arr[i - 1].A + "^3 + " + arr[i - 1].B + "^3 = " + arr[i].A + "^3 + " + arr[i].B + "^3 = "
						+ cubes(arr[i - 1].A, arr[i - 1].B) + " = " + cubes(arr[i].A, arr[i].B));
			}
		}
	}

	public static void findTaxicabNumberVer2(int N) {
		MinPQ<SumOfCubes> pq = new MinPQ<SumOfCubes>();
		
		// space - O(N)
		for (int i = 1; i <= N; i++)
			pq.insert(new SumOfCubes(i, i));
		
		SumOfCubes prev = new SumOfCubes(0, 0);
		while (!pq.isEmpty()) { // N^2
			SumOfCubes crnt = pq.delMin(); // log(N)
			if (prev.result == crnt.result)
				StdOut.println(prev.A + "^3 + " + prev.B + "^3 = " + crnt.A + "^3 + " + crnt.B + " = "
						+ cubes(prev.A, prev.B) + " = " + cubes(crnt.A, crnt.B));
			if (crnt.B < N)
				pq.insert(new SumOfCubes(crnt.A, crnt.B + 1));
			prev = crnt;
		}
	}

	public static void main(String[] args) {
		int N = 30;
		findTaxicabNumberVer1(N);
		StdOut.println();
		findTaxicabNumberVer2(N);
	}
}
