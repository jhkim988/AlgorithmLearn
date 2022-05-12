import java.io.*;
import java.util.*;

public class BOJ2615 {
  static int[] rowDi = {0, 1, 1, -1};
  static int[] colDi = {1, 1, 0, 1};
  static int[][] board;
  static boolean[][] visit;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    board = new int[19][19];
    visit = new boolean[19][19];
    for (int i = 0; i < 19; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 19; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    for (int i = 0; i < 19; i++) {
      for (int j = 0; j < 19; j++) {
        if (board[i][j] == 0) continue;
        for (int d = 0; d < 4; d++) {
          int color = board[i][j];
          int leftRow = i, leftCol = j, rightRow = i, rightCol = j;
          while (true) {
            int nextRow = leftRow - rowDi[d];
            int nextCol = leftCol - colDi[d];
            if (nextRow < 0 || nextRow >= 19 || nextCol < 0 || nextCol >= 19) break;
            if (board[nextRow][nextCol] != color) break;
            leftRow = nextRow;
            leftCol = nextCol;
          }
          while (true) {
            int nextRow = rightRow + rowDi[d];
            int nextCol = rightCol + colDi[d];
            if (nextRow < 0 || nextRow >= 19 || nextCol < 0 || nextCol >= 19) break;
            if (board[nextRow][nextCol] != color) break;
            rightRow = nextRow;
            rightCol = nextCol;
          }
          if (rightRow-leftRow == 4 || rightCol-leftCol == 4) {
            bw.write(Integer.toString(color));
            bw.newLine();
            bw.write(Integer.toString(leftRow+1));
            bw.write(' ');
            bw.write(Integer.toString(leftCol+1));
            bw.flush();
            return;
          }
        }
      }
    }
    bw.write("0");
    bw.flush();
  }
}
