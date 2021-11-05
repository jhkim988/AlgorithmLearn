import java.io.*;
import java.util.*;

public class BOJ2228 {
  static final int minusINF = Integer.MIN_VALUE / 2;
  static int N;
  static int M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int[] data = new int[N];

    for (int i = 0 ; i < N; i++) {
      data[i] = Integer.parseInt(br.readLine());
    }

    int[][][] dp = new int[N][M + 1][2]; // dp[data index][num choose interval][last element check]
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <= M; j++) {
        for (int k = 0; k < 2; k++) {
          dp[i][j][k] = minusINF;
        }
      }
    }
    for (int i = 0; i < N; i++) {
        dp[i][0][0] = 0;
    }
    dp[0][1][1] = data[0];
  
    for (int i = 0; i < N - 1; i++) {
      for (int j = 1; j <= M; j++) {
        dp[i + 1][j][0] = Math.max(dp[i][j][0], dp[i][j][1]);
        dp[i + 1][j][1] = Math.max(dp[i][j][1], dp[i][j - 1][0]) + data[i + 1];
      }
    }
    int max = Math.max(dp[N - 1][M][0], dp[N - 1][M][1]);
    bw.write(max + "\n");
    bw.flush();
  }
  static void print(int[][][] dp) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <= M; j++) {
        if (minusINF / 2 > dp[i][j][0]) {
          System.out.print("N");
        } else {
          System.out.print(dp[i][j][0]);
        }
        System.out.print("/");
        if (minusINF / 2 > dp[i][j][1]) {
          System.out.print("N");
        } else {
          System.out.print(dp[i][j][1]);
        }
        System.out.print(" ");
      }
      System.out.println();
    }
  }
}