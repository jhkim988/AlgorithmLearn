import java.io.*;
import java.util.*;

public class BOJ17136 {
  static int[][] board;
  static int[] numRemaind = {5, 5, 5, 5, 5};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    board = new int[10][10];
    for (int i = 0; i < 10; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 10; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

  }
  static int recur(int row, int col) {
    if (board[row][col] != 1) return -1;
    int min = 25;
    for (int add = 0; add <= 4; add++) {
      if (numRemaind[add] <= 0) continue;
      boolean possible = true;
      for (int i = 0; i < add; i++) {
        possible = possible && board[row + i][col + add] == 1;
        possible = possible && board[row + add][col + 1] == 1;
      }
      if (!possible) break;
      numRemaind[add]--;
      
      min = Integer.min(min, recur(row, col + add));
      numRemaind[add]++;
    }
    return min;
  }
}
