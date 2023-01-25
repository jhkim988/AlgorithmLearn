public class FenwickTree {
	public static void main(String[] args) {
		int len = 10;
		int[] arr = new int[len];

		// arr value allocation...

		int[] tree = new int[len + 1];
		// tree[i]: A[i] + ... (the number of sum: i & -i)

	}

	static int sum(int[] tree, int i) {
		int value = 0;
		while (i > 0) {
			value += tree[i];
			i -= i & -i;
		}
		return value;
	}

	static void update(int[] tree, int i, int diff) {
		while (i < tree.length) {
			tree[i] += diff;
			i += i & -i;
		}
	}
}
