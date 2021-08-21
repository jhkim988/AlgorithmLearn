import java.io.*;
import java.util.*;

public class BOJ1311 {
  static final int INF = Integer.MAX_VALUE / 2;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[][] D = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        D[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] dp = new int[N][1 << N];
    int min = minCost(0, 0, N, D, dp);
    bw.write(min + "\n");
    bw.flush();
  }
  static int minCost(int crnt, int bit, int N, int[][] D, int[][] dp) {
    if (bit == ((1 << N) - 1) || crnt == N) { // All works assigned.
      return 0;
    }
    if (dp[crnt][bit] != 0) {
      return dp[crnt][bit];
    }
    dp[crnt][bit] = INF;
    for (int j = 0; j < N; j++) { // Human crnt, Pick work j
      if ((bit & (1 << j)) == (1 << j)) continue; // work j already picked.
      dp[crnt][bit] = Math.min(dp[crnt][bit], D[crnt][j] + minCost(crnt + 1, bit | (1 << j), N, D, dp));
    }
    return dp[crnt][bit];
  }
}
