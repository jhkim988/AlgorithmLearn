import java.io.*;
import java.util.*;

public class BOJ1890 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[][] map = new int[n][n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    long[][] dp = new long[n][n];
    dp[n-1][n-1] = 1;
    for (int i = n-1; i >= 0; i--) {
      if (i + map[i][n-1] < n) dp[i][n-1] = dp[i + map[i][n-1]][n-1];
      if (i + map[n-1][i] < n) dp[n-1][i] = dp[n-1][i + map[n-1][i]];
    }
    for (int k = 2*n-4; k >= 0; k--) {
      for (int i = n-2; i > k-n+1; i--) {
        if (i < 0 || k-i < 0 || k-i >= n) continue;
        if (0 <= i + map[i][k-i] && i + map[i][k-i] < n) dp[i][k-i] += dp[i + map[i][k-i]][k-i];
        if (0 <= k-i+map[i][k-i] && k-i+map[i][k-i] < n) dp[i][k-i] += dp[i][k-i+map[i][k-i]];
      }
    }

    bw.write(Long.toString(dp[0][0]));
    bw.newLine();
    bw.flush();
  }
}