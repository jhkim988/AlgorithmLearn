import java.io.*;
import java.util.*;

public class BOJ2169 {
  static int row, col;
  static int[][] map;
  static int[][] dp;
  static int[] rowDi = {0, 1, 0};
  static int[] colDi = {-1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    map = new int[row][col];
    dp = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
      Arrays.fill(dp[i], Integer.MIN_VALUE);
    }
    dp[0][0] = map[0][0];
    recur(0, 0, -1, -1);
    bw.write(Integer.toString(dp[0][0]));
    bw.newLine();
    bw.flush();

    for (int i = 0; i < row; i++) {
      System.out.println(Arrays.toString(dp[i]));
    }
  }
  static int recur(int r, int c, int prevR, int prevC) {
    System.out.println("call: " + r + "," + c);
    if (r == row-1 && c == col-1) return dp[r][c] = map[r][c];
    for (int i = 0; i < 3; i++) {
      int nextRow = r + rowDi[i];
      int nextCol = c + colDi[i];
      if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
      if (prevR == nextRow && prevC == nextCol) continue;
      dp[r][c] = Integer.max(dp[r][c], recur(nextRow, nextCol, r, c));
    }
    return map[r][c] + dp[r][c];
  }
}
