import java.io.*;
import java.util.*;

public class BOJ18428 {
  static int n;
  static char[][] board;
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  static HashSet<Integer> teacher;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    board = new char[n][n];

    teacher = new HashSet<>();
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        board[i][j] = st.nextToken().charAt(0);
        if (board[i][j] == 'T') teacher.add(i*n+j);
      }
    }

    bw.write(recur(0, 0) ? "YES\n" : "NO\n");
    bw.flush();
  }
  static boolean recur(int depth, int ptr) {
    if (depth >= 3) {
      for (int teacherPtr: teacher) {
        int tRow = teacherPtr/n;
        int tCol = teacherPtr%n;
        for (int k = 0; k < 4; k++) {
          int nextRow = tRow + rowDi[k];
          int nextCol = tCol + colDi[k];
          while (0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n) {
            if (board[nextRow][nextCol] == 'S') return false;
            if (board[nextRow][nextCol] == 'O') break;
            nextRow += rowDi[k];
            nextCol += colDi[k];              
          }
        }
      }
      return true;
    }

    for (int x = ptr; x < n*n; x++) {
      int xRow = x/n;
      int xCol = x%n;
      if (board[xRow][xCol] != 'X') continue;
      board[xRow][xCol] = 'O';
      if (recur(depth+1, x+1)) return true;
      board[xRow][xCol] = 'X';
    }

    return false;
  }
}