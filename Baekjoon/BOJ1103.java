import java.io.*;
import java.util.*;

public class BOJ1103 {
  static int row, col;
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  static char[][] board;
  static boolean[][] visit;
  static int[][] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    board = new char[row][col];
    for (int i = 0; i < row; i++) {
      board[i] = br.readLine().toCharArray();
    }
    visit = new boolean[row][col];
    dp = new int[row][col];
    int max = recur(0, false, 0, 0);
    bw.write(Integer.toString(max));
    bw.flush();
  }
  static int recur(int depth, boolean isInfinite, int r, int c) {
    if (isInfinite) return -1;
    if (dp[r][c] != 0) return dp[r][c];
    visit[r][c] = true;
    int val = board[r][c]-'0';
    for (int k = 0; k < 4; k++) {
      int adjRow = r + rowDi[k]*val;
      int adjCol = c + colDi[k]*val;
      if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col || board[adjRow][adjCol] == 'H') {
        dp[r][c] = Integer.max(dp[r][c], 1);
        continue;
      }
      int recur = recur(depth+1, visit[adjRow][adjCol], adjRow, adjCol);
      if (recur == -1) return -1;
      dp[r][c] = Integer.max(dp[r][c], recur+1);
    }
    visit[r][c] = false;
    return dp[r][c];
  }
}
