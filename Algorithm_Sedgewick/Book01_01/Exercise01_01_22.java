import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_22 {
	// 1.1.22
	// BinarySearch�� rank() �޼��带 ����Լ��� �ٲپ �����غ���
	// �޼����� ȣ���� ��� �Ǵ��� ���� ����.
	// ��� �޼��尡 ȣ��� ������ �μ� lo, hi�� ���� ����ϰ� ȣ�� ���̿� �°� �ε�Ʈ�� �߰��϶�.
	
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
