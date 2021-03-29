import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class ShuffleTest {
	// wrong shuffle method
	private static void shuffle01(int[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int rand = StdRandom.uniform(N);
			swap(a, i, rand);
		}
	}

	// Knuth shuffle method
	private static void shuffle02(int[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int r = StdRandom.uniform(i + 1);
			swap(a, i, r);
		}
	}

	private static LinkedStack<String> stk1 = new LinkedStack<String>();
	private static LinkedStack<String> stk2 = new LinkedStack<String>();

	private static void shuffle01Test(int[] a, int idx) {
		// initial idx = 0;
		// terminate idx = a.lenght - 1;
		if (idx == a.length) {
			stk1.push(Arrays.toString(a));
			return;
		}
		for (int i = 0; i < a.length; i++) {
			swap(a, i, idx); // a[i]와 a[idx]를 바꾼다.
			shuffle01Test(a, ++idx); // 재귀 depth를 하나 내려가고, 마지막에 도달하면 스택에 넣는다.
			--idx;
			swap(a, i, idx); // 원상태로 바꾼다.
		}
	}

	private static void shuffle01Test(int[] a) {
		shuffle01Test(a, 0);
	}

	private static void shuffle02Test(int[] a, int idx) {
		// start idx : 0
		// terminate idx : a.length - 1
		// swap (a[idx], a[i]), i: 0 ~ idx
		if (idx == a.length) {
			stk2.push(Arrays.toString(a));
			return;
		}
		for (int i = 0; i <= idx; i++) {
			swap(a, i, idx);
			shuffle02Test(a, ++idx);
			--idx;
			swap(a, i, idx);
		}
	}

	private static void shuffle02Test(int[]a) {
		shuffle02Test(a, 0);
	}
	
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static void countStk(LinkedStack stk) {
		int size = stk.size();
		String[] mainString = new String[size];
		int[] count = new int[size];

		int ptr = 0;
		while (!stk.isEmpty()) {
			boolean flag = false;
			String item = (String) stk.pop();
			for (int i = 0; i <= ptr; i++) {
				if (item.equals(mainString[i])) {
					count[i]++;
					flag = true;
					break;
				}
			}
			if (!flag) {
				mainString[ptr] = item;
				count[ptr]++;
				ptr++;
			}
		}

		for (int i = 0; i < ptr; i++) {
			System.out.println(mainString[i] + " : " + count[i]);
		}
	}

	public static void main(String[] args) {
		int[] example = { 0, 1, 2};
		System.out.println("----- Wrong Shuffle -----");
		shuffle01Test(example);
		countStk(stk1);
		System.out.println("----- Knuth Shuffle -----");
		shuffle02Test(example);
		countStk(stk2);
	}
}
