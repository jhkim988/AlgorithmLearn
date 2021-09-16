import java.io.*;
import java.util.*;

public class BOJ2225 {
  public static void main(String[] args) throws IOException {
    final int remainder = 1_000_000_000;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[][] dp = new int[N + 1][K + 1];

    for (int i = 0; i <= N; i++) {
      dp[i][1] = 1;
    }

    for (int i = 0; i <= N; i++) {
      for (int j = 1; j <= K; j++) {
        for (int k = 0; k <= i; k++) {
          dp[i][j] += dp[i - k][j - 1];
          dp[i][j] %= remainder;
        }
      }
    }
    bw.write(dp[N][K] + "\n");
    bw.flush();
  }
}
