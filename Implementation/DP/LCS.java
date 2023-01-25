public class LCS {
	// LCS: Longest Common Substring
	public static void main(String[] args) {
		// Example string
		String str1 = "twoloverssatontheparkbenchilovealgorithmdoyouloveme";
		String str2 = "withoutyouihavenoreasonforbeingilovejavadoyou";

		// use dynamic programing
		// complexity: O(N^2)
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		int maxRow = -1;
		int maxCol = -1;
		int maxLen = 0;
		int[][] dp = new int[arr1.length][arr2.length]; // dp[i][j]: 마지막 글자가 arr1[i] == arr2[j] 인 substring의 길이
		for (int i = 0; i < arr1.length; i++) {
			dp[i][0] = arr1[i] == arr2[0] ? 1 : 0;
			if (maxLen < dp[i][0]) {
				maxRow = i;
				maxCol = 0;
				maxLen = dp[i][0];
			}
		}
		for (int j = 0; j < arr2.length; j++) {
			dp[0][j] = arr1[0] == arr2[j] ? 1 : 0;
			if (maxLen < dp[0][j]) {
				maxRow = 0;
				maxCol = j;
				maxLen = dp[0][j];
			}
		}
		for (int i = 1; i < arr1.length; i++) {
			for (int j = 1; j < arr2.length; j++) {
				dp[i][j] = arr1[i] == arr2[j] ? dp[i - 1][j - 1] + 1 : 0;
				if (maxLen < dp[i][j]) {
					maxRow = i;
					maxCol = j;
					maxLen = dp[i][j];
				}
			}
		}

		System.out.println("maxLen: " + maxLen);
		System.out.print("str1: ");
		for (int i = 0; i < maxLen; i++) {
			System.out.print(arr1[maxRow - i]);
		}
		System.out.println();
		System.out.print("str2: ");
		for (int i = 0; i < maxLen; i++) {
			System.out.print(arr2[maxCol - i]);
		}
		System.out.println();
	}
}
