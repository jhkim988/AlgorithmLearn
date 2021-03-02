import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_15 {
	static int[] histogram(int a[], int M) {
		int[] result = new int[M];
		for (int i = 0; i < a.length; i++) {
			if (a[i] < M && a[i] >= 0)
				result[a[i]]++;
		}
		return result;
	}
	
	static int sumOfArr(int a[]) {
		int sum = 0;
		for(int el : a)
			sum += el;
		return sum;
	}
	
	public static void main(String [] args) {
		int arrLen = 10;
		int a[] = new int [arrLen];
		int M = 15;
		for(int i = 0; i < a.length; i++)
			a[i] = StdRandom.uniform(M);
		
		StdOut.println(Arrays.toString(a));
		int [] result = histogram(a, M); 
		StdOut.println(Arrays.toString(result));
		StdOut.println(sumOfArr(result));
	}
}
