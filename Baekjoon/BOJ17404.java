import java.io.*;
import java.util.*;

public class BOJ17404 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    int[][] RGBCost = new int[N][3];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        RGBCost[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int minCost = 1_000_001;
    if (N == 2) {      
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (i == j) continue;
          if (RGBCost[0][i] + RGBCost[1][j] < minCost) {
            minCost = RGBCost[0][i] + RGBCost[1][j];
          } 
        }
      }
    } else {
      // Fix color of first house.
      for (int k = 0; k < 3; k++) {
        int[][] dp = new int[N][3];
        for (int i = 0; i < 3 ; i++) {
          dp[0][i] = RGBCost[0][k];
        }
        for (int i = 1; i < N; i++) {
          for (int j = 0; j < 3; j++) {            
            if (i == 1 && j == k) {
              dp[i][k] = 1_000_001;
              continue;
            }
            dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) %3]) + RGBCost[i][j];
          }
        }
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            if (j == i || j == k) continue;
            if (dp[N - 2][i] + RGBCost[N - 1][j] < minCost) {
              minCost = dp[N - 2][i] + RGBCost[N - 1][j];
            }
          }
        }
      } 
    }

    bw.write(minCost + "\n");
    bw.flush();  
  }
}
