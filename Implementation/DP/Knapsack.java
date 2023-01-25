public class Knapsack {
	public static void main(String[] args) {
		final int LIM_WEIGHT = 10;
		final int NUM_ITEM = 5;
		final int MAX_WEIGHT = 4;
		final int MAX_VALUE = 4;
		int[] weight = new int[NUM_ITEM];
		int[] value = new int[NUM_ITEM + 1];

		for (int i = 0; i < NUM_ITEM; i++) {
			weight[i] = ((int) (Math.random() * MAX_WEIGHT)) + 1;
			value[i] = ((int) (Math.random() * MAX_VALUE)) + 1;
		}

		System.out.println("weight: " + print(weight));
		System.out.println("value: " + print(value));

		int[][] dp = new int[NUM_ITEM][LIM_WEIGHT + 1];

		// Use 2D Array
		for (int i = 1; i < NUM_ITEM; i++) {
			for (int j = 1; j <= LIM_WEIGHT; j++) {
				if (j >= weight[i]) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		System.out.println("dp: ");
		for (int i = 0; i < NUM_ITEM; i++) {
			System.out.println(print(dp[i]));
		}

		// Use 1D Array
		int[] oneDimDp = new int[LIM_WEIGHT + 1];
		for (int i = 1; i < NUM_ITEM; i++) {
			for (int j = LIM_WEIGHT; j >= 1; j--) {
				if (j >= weight[i]) {
					oneDimDp[j] = Math.max(oneDimDp[j], oneDimDp[j - weight[i]] + value[i]);
				}
			}
		}

		System.out.println("oneDimDp: " + print(oneDimDp));
	}

	static String print(int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			sb.append(' ').append(arr[i]);
		}
		return sb.toString();
	}
}