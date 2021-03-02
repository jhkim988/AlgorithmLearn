import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_22 {
	// 1.1.22
	// BinarySearch의 rank() 메서드를 재귀함수로 바꾸어서 구현해보고
	// 메서드의 호출이 어떻게 되는지 따라가 보라.
	// 재귀 메서드가 호출될 때마다 인수 lo, hi의 값을 출력하고 호출 깊이에 맞게 인덴트를 추가하라.
	
	public static int rank(int key, int[] a, int lo, int hi, int depth) {
		if (lo > hi)
			return -1;
		
		String print = "";
		for(int i = 0; i < depth; i++) {
			print += "\t";
		}
		
		StdOut.println(print + "lo : " + lo + " \thi : " + hi);
		int mid = (lo + hi) / 2;
		if (a[mid] < key)
			return rank(key, a, mid + 1, hi, ++depth);
		else if (a[mid] > key)
			return rank(key, a, lo, mid - 1, ++depth);
		else
			return mid;
	}
	
	public static int rank(int key, int[] a) {
		return rank(key, a, 0, a.length - 1, 0);
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		int[] whiteList = in.readAllInts();
		Arrays.sort(whiteList);
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (rank(key, whiteList) == -1)
				StdOut.println("Not Found - " + key);
			else
				StdOut.println("Found - " + key);
		}
		
	}
}
