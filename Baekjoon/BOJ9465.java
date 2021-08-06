import java.io.*;
import java.util.*;

public class BOJ9465 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());

    while (numTest-- > 0) {
      int row = Integer.parseInt(br.readLine());
      int[][] data = new int[row][2];

      for (int j = 0; j < 2; j++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < row; i++) {
          data[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int[][] dp = new int[row][2];
      // dp[i][0]: max score until i, last = up
      // dp[i][1]: max score util i, last = down
      dp[0][0] = data[0][0];
      dp[0][1] = data[0][1];

      if (row == 1) {
        bw.write(Math.max(dp[0][0], dp[0][1]) + "\n");
        bw.flush();
        continue;
      }

      dp[1][0] = dp[0][1] + data[1][0];
      dp[1][1] = dp[0][0] + data[1][1];

      for (int i = 2; i < row; i++) {
        dp[i][0] = Math.max(dp[i - 1][1] + data[i][0], Math.max(dp[i - 2][0] + data[i][0], dp[i - 2][1] + data[i][0]));
        dp[i][1] = Math.max(dp[i - 1][0] + data[i][1], Math.max(dp[i - 2][1] + data[i][1], dp[i - 2][0] + data[i][1]));
      }
      bw.write(Math.max(dp[row - 1][0], dp[row - 1][1]) + "\n");
    } 
    bw.flush();
  }
}
