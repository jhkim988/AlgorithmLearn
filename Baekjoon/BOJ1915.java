import java.io.*;
import java.util.*;

public class BOJ1915 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    char[][] input = new char[n][];
    for (int i = 0; i < n; i++) {
      input[i] = br.readLine().toCharArray();
    }
    int max = 0;
    int[][] dp = new int[n][m];
    for (int i = 0; i < n; i++) {
      dp[i][0] = input[i][0] -'0';
      max = Integer.max(max, dp[i][0]);
    }
    for (int j = 0; j < m; j++) {
      dp[0][j] = input[0][j] -'0';
      max = Integer.max(max, dp[0][j]);
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (input[i][j] == '0') continue;
        int min = Integer.min(dp[i-1][j-1], Integer.min(dp[i-1][j], dp[i][j-1]));
        dp[i][j] = min + 1;
        max = Integer.max(max, dp[i][j]);
      }
    }
    bw.write(Integer.toString(max*max));
    bw.newLine();
    bw.flush();
  }
}