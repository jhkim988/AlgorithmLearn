import java.io.*;
import java.util.*;

public class BOJ2163 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    // dp:
    int[][] dp = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
    dp[1][1] = 0;
    for (int j = 2; j <= M; j++) {
      for (int k = 1; k <= j/2; k++) {
        dp[1][j] = Math.min(dp[1][j], dp[1][k] + dp[1][j - k] + 1);
      }
    }
    for (int i = 2; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        for (int k = 1; k <= i/2; k++) {
          dp[i][j] = Math.min(dp[i][j], dp[i - k][j] + dp[k][j] + 1);
        }
      }
    }

    bw.write(Integer.toString(dp[N][M]));
    bw.newLine();
    bw.flush();
  
    // direct:
    // bw.write(Integer.toString(N*M - 1));
    // bw.newLine();
    // bw.flush();
  }
}
