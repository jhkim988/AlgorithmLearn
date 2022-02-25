import java.io.*;
import java.util.*;

public class BOJ2169 {
  static final int MIN = -100_001;
  static int row, col;
  static int[][] map;
  static int[][][] dp;
  static int[] rowDi = {1, 0, 0};
  static int[] colDi = {0, 1, -1};
  static boolean[][] visit;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    map = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int answer = topdown();
    // int answer = bottomup();
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static int topdown() {
    dp = new int[3][row][col];
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < row; i++) {
        Arrays.fill(dp[k][i], MIN);
      }
    }
    visit = new boolean[row][col];
    visit[0][0] = true;
    recur(0, 0, 0);
    recur(1, 0, 0);
    return Integer.max(dp[0][0][0], dp[1][0][0]);
  }
  static int recur(int d, int r, int c) {
    if (dp[d][r][c] > MIN) return dp[d][r][c];
    if (r == row-1 && c == col-1) return dp[d][r][c] = map[r][c];
    int max = MIN;
    for (int i = 0; i < 3; i++) {
      int nextRow = r + rowDi[i];
      int nextCol = c + colDi[i];
      if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
      if (visit[nextRow][nextCol]) continue;
      visit[nextRow][nextCol] = true;
      max = Integer.max(max, recur(i, nextRow, nextCol));
      visit[nextRow][nextCol] = false;
    }
    return dp[d][r][c] = Integer.max(dp[d][r][c], map[r][c] + max);
  }
  static int bottomup() {
    int[][] memo = new int[row][col];
    for (int i = 0; i < row; i++) Arrays.fill(memo[i], MIN);
    memo[0][0] = map[0][0];
    for (int j = 1; j < col; j++) {
      memo[0][j] = memo[0][j-1] + map[0][j];
    }
    for (int i = 1; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int sum = memo[i-1][j];
        for (int k = j; k >= 0; k--) {
          sum += map[i][k];
          if (memo[i][k] < sum) memo[i][k] = sum;
        }
        sum = memo[i-1][j];
        for (int k = j; k < col; k++) {
          sum += map[i][k];
          if (memo[i][k] < sum) memo[i][k] = sum;
        }
      }
    }
    return memo[row-1][col-1];
  }
}
