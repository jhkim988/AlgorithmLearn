import java.io.*;

public class BOJ14517 {
  public static void main(String[] args) throws IOException {
    final int DIVISOR = 10_007;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int n = input.length;
    int[][] dp = new int[n][n];
    for (int lo = 0; lo < n; lo++) {
      dp[lo][lo] = 1;
    }
    for (int lo = 0; lo+1 < n; lo++) {
      dp[lo][lo+1] = 2 + (input[lo] == input[lo+1] ? 1 : 0);
    }
    for (int gap = 2; gap < n; gap++) {
      for (int lo = 0; lo + gap < n; lo++) {
        dp[lo][lo+gap] = ((dp[lo+1][lo+gap] + dp[lo][lo+gap-1])%DIVISOR + (DIVISOR-dp[lo+1][lo+gap-1])%DIVISOR)%DIVISOR;
        if (input[lo] == input[lo+gap]) {
          dp[lo][lo+gap] = ((dp[lo][lo+gap] + dp[lo+1][lo+gap-1])%DIVISOR + 1)%DIVISOR;
        }
      }
    }
    bw.write(Integer.toString(dp[0][n-1]));
    bw.flush();
  }
}
