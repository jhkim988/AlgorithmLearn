import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
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
		Arrays.sort(arr);
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
		return -mid - 1;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		int[] whiteList = in.readAllInts();
		Stopwatch timer1 = new Stopwatch();
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (bruteForce(key, whiteList) != -1) {
//				StdOut.println(key);
				;
			}
		}
		StdOut.println("Brute-Force Running Time : " + timer1.elapsedTime());

		Stopwatch timer2 = new Stopwatch();
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (binarySearch(key, whiteList) != -1) {
//				StdOut.println(key);
				;
			}
		}
		StdOut.println("Binary Search Running Time : " + timer2.elapsedTime());
	}
}
