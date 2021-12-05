import java.io.*;
import java.util.*;

public class BOJ17069 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[][] house = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        house[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    // direction code
    // horizon: 0
    // diagonal: 1
    // vertical: 2

    long[][][] dp = new long[N][N][3]; // dp[i][j][k] = # of case (0, 0) -> (i, j), where last direction is k.
    dp[0][1][0] = 1;
    
    for (int j = 2; j < N; j++) {
      if (house[0][j] == 1) break;
      dp[0][j][0] = dp[0][j - 1][0] + dp[0][j - 1][1];
    }
    for (int i = 1; i < N; i++) {
      for (int j = 1; j < N; j++) {
        if (house[i][j] == 1) continue;
        dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
        dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
        if (house[i - 1][j] == 1 || house[i][j - 1] == 1) continue;
        dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
      }
    }
    
    long sum = 0;
    for (int k = 0; k < 3; k++) {
      sum += dp[N - 1][N - 1][k];
    }

    bw.write(sum + "\n");
    bw.flush();

    // for (int k = 0; k < 3; k++) {
    //   System.out.println("direction: " + k);      
    //   print(dp, k);
    // }
  }
  static void print(int[][][] dp, int direction) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[i].length; j++) {
        sb.append(dp[i][j][direction]).append(' ');
      }
      sb.append('\n');
    }
    System.out.println(sb.toString());
  }
}
