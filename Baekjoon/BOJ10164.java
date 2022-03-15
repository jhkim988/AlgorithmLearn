import java.io.*;
import java.util.*;

public class BOJ10164 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int circle = Integer.parseInt(st.nextToken());
    int[][] dp = new int[row][col];
      for (int i = 1; i < row; i++) {
        dp[i][0] = 1;
      }
      for (int j = 1; j < col; j++) {
        dp[0][j] = 1;
      }
      for (int i = 1; i < row; i++) {
        for (int j = 1; j < col; j++) {
          dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
      }
    if (circle == 0) {
      bw.write(Integer.toString(dp[row-1][col-1]));
    } else {
      int cRow = (circle - 1) / col;
      int cCol = (circle - 1) % col;
      bw.write(Integer.toString(dp[cRow][cCol] * dp[row-cRow-1][col-cCol-1]));
    }
    bw.newLine();
    bw.flush();
  }  
}
