import java.io.*;
import java.util.*;

public class BOJ1006 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int[][] input = new int[2][n];
      for (int i = 0; i < 2; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < n; j++) {
          input[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      if (n == 1) {
        bw.write(input[0][0]+input[1][0] <= w ? "1\n" : "2\n");
        continue;
      }

      // the number of 2*n tile.. (maximize)
      int[][][] dp = new int[4][3][n]; // [init stat][i'th stat][i]
      dp[0][0][0] = (input[0][0]+input[1][0] <= w) ? 1 : 0;
      dp[1][1][0] = (input[0][n-1]+input[0][0] <= w) ? 1 : 0;
      dp[2][2][0] = (input[1][n-1]+input[1][0] <= w) ? 1 : 0;
      dp[3][0][0] = dp[1][1][0]+dp[2][2][0];

      for (int k = 0; k < 4; k++) {
        for (int i = 1; i < n; i++) {
          for (int j = 0; j < 3; j++) {
            dp[k][j][i] = dp[k][j][i-1];
          }
          if (input[0][i]+input[1][i] <= w) {
            for (int j = 0; j < 3; j++) {
              dp[k][0][i] = Integer.max(dp[k][0][i], dp[k][j][i-1]+1);
            }
          }
          if (input[0][i-1]+input[0][i] <= w && input[1][i-1]+input[1][i] <= w) {
            if (i >= 2) {
              for (int j = 0; j < 3; j++) {
                dp[k][0][i] = Integer.max(dp[k][0][i], dp[k][j][i-2]+2);
              }
            } else {
              for (int j = 0; j < 3; j++) {
                dp[k][0][i] = Integer.max(dp[k][0][i], 2);
              }
            }
          }
          if (input[0][i-1]+input[0][i] <= w) {
            if (i >= 2) {
              for (int j = 0; j < 3; j++) {
                dp[k][1][i] = Integer.max(dp[k][1][i], dp[k][j][i-2]+1);
              }
            } else {
              for (int j = 0; j < 3; j++) {
                dp[k][1][i] = Integer.max(dp[k][1][i], 1);
              }
            }
            dp[k][1][i] = Integer.max(dp[k][1][i], dp[k][2][i-1]+1);
          }
          if (input[1][i-1]+input[1][i] <= w) {
            if (i >= 2) {
              for (int j = 0; j < 3; j++) {
                dp[k][2][i] = Integer.max(dp[k][2][i], dp[k][j][i-2]+1);
              }
            } else {
              for (int j = 0; j < 3; j++) {
                dp[k][2][i] = Integer.max(dp[k][2][i], 1);
              }
            }
            dp[k][2][i] = Integer.max(dp[k][2][i], dp[k][1][i-1]+1);
          }
        }
      }
      
      int max = 0;
      for (int j = 0; j < 3; j++) {
        max = Integer.max(max, dp[0][j][n-1]);
      }
      for (int j = 0; j < 3; j++) {
        max = Integer.max(max, dp[1][j][n-2]);
      }
      max = Integer.max(max, dp[1][2][n-1]);
      for (int j = 0; j < 3; j++) {
        max = Integer.max(max, dp[2][j][n-2]);
      }
      max = Integer.max(max, dp[2][1][n-1]);
      for (int j = 0; j < 3; j++) {
        max = Integer.max(max, dp[3][j][n-2]);
      }
      bw.write(Integer.toString(2*n-max));
      bw.newLine();

      // for (int k = 0; k < 4; k++) {
      //   System.out.println("init: " + k);
      //   for (int j = 0; j < 3; j++) {
      //     System.out.print("[");
      //     for (int i = 0; i < n; i++) {
      //       System.out.print(dp[k][j][i] + " ");
      //     }
      //     System.out.println("]");
      //   }
      // }
    }
    bw.flush();
  }
}
