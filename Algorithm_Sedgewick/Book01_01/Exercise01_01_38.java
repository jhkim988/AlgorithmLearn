import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Exercise01_01_38 {
	// 1.1.38 ���� Ž���� ������(brute-force) Ž��
	// ������ Ž�� ����� �̿��ϴ� BGruteForceSearch ���α׷��� �ۼ��ϰ�
	// �Է� �����ͷ� largeW.txt�� largeT.txt�� ����� BinarySearch�� ���� �ð��� ���غ���.
	static int bruteForce(int key, int[] arr) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == key)
				return i;
		return -1;
	}

	static int binarySearch(int key, int[] arr) {
		int lo = 0;
		int hi = arr.length - 1;
		int mid = 0;
		while (lo <= hi) {
			mid = (lo + hi) / 2;
			if (arr[mid] < key) {
				lo = mid + 1;
			} else if (arr[mid] > key) {
				hi = mid - 1;
			} else
				return mid;
		}
		return -1;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		int[] whiteList = in.readAllInts();
		
		In inTest = new In(args[1]);
		int[] test = inTest.readAllInts();
		
		Stopwatch timer1 = new Stopwatch();
		for(int el : test) {
			if (bruteForce(el, whiteList) != -1) {
				// do something
//				StdOut.println(el);
			}
		}
		StdOut.println("Brute-Force Running Time : " + timer1.elapsedTime());

		Stopwatch timer2 = new Stopwatch();
		Arrays.sort(whiteList);
		for(int el : test) {
			if (binarySearch(el, whiteList) != -1) {
				// do something
//				StdOut.println(el);
			}
		}
		StdOut.println("Binary Search Running Time : " + timer2.elapsedTime());
	}
}
