import java.io.*;
import java.util.*;

public class BOJ1937 {
  static int n;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  static int[][] map, dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    map = new int[n][n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int max = 0;
    dp = new int[n][n];
    for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (dp[i][j] >= 0) continue;
        max = Integer.max(max, recur(i, j));
      }
    }
    bw.write(Integer.toString(max+1));
    bw.newLine();
    bw.flush();
  }
  static int recur(int r, int c) {
    if (dp[r][c] >= 0) return dp[r][c];
    boolean flag = false;
    int max = 0;
    for (int k = 0; k < 4; k++) {
      int adjRow = r + rowDi[k];
      int adjCol = c + colDi[k];
      if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
      if (map[r][c] >= map[adjRow][adjCol]) continue;
      max = Integer.max(max, recur(adjRow, adjCol));
      flag = true;
    }
    return dp[r][c] = max + (flag ? 1 : 0);
  }
}
