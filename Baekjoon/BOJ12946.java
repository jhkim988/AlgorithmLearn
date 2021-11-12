import java.io.*;
import java.util.*;

public class BOJ12946 {
  static int N;
  static int[] rowDi = {-1, -1, 0, 0, 1, 1};
  static int[] colDi = {0, 1, -1, 1, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    char[][] board = new char[N][];
    for (int i = 0; i < N; i++) {
      board[i] = br.readLine().toCharArray();
    }

    int[][] color = new int[N][N];
    for (int i = 0; i < N; i++) {
      Arrays.fill(color[i], -1);
    }

    int max = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (board[i][j] == 'X' && color[i][j] == -1) { // find new componenet
          // System.out.println("first call");
          max = Math.max(max, recur(i, j, color, board, 1));
        }
      }
    }

    bw.write(max + "\n");
    bw.flush();

    // print(color);
  }
  static int recur(int row, int col, int[][] color, char[][] board, int clr) {

  }
}
