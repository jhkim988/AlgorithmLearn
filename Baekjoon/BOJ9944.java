import java.io.*;
import java.util.*;

public class BOJ9944 {
  final static int MAX = 30 * 30;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = null;
    int numCase = 1;
    while ((input = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(input);
      int numRow = Integer.parseInt(st.nextToken());
      int numCol = Integer.parseInt(st.nextToken());
      char[][] board = new char[numRow][];
      int numEmpty = 0;
      for (int i = 0; i < numRow; i++) {
        board[i] = br.readLine().toCharArray();
        for (int j = 0; j < numCol; j++) {
          if (board[i][j] == '.') numEmpty++;
        }
      }
      boolean[][] visit = new boolean[numRow][numCol];
      int min = MAX;
      for (int i = 0; i < numRow; i++) {
        for (int j = 0; j < numCol; j++) {
          if (board[i][j] == '*') continue;
          visit[i][j] = true;
          int val = getMinCost(numEmpty - 1, 0, i, j, board, visit);
          if (val < min) min = val;
          visit[i][j] = false;
        }
      }
      if (min == MAX) min = -1;
      StringBuilder sb = new StringBuilder();
      sb.append("Case ").append(numCase++).append(": ").append(min).append('\n');
      bw.write(sb.toString());
      bw.flush();
    }
  }
  static int getMinCost(int numEmpty, int move, int rowIdx, int colIdx, char[][] board, boolean[][] visit) {
    if (numEmpty == 0) {
      return move;
    }

    int numRow = board.length;
    int numCol = board[0].length;
    int val = MAX;

    // Up
    if (!(rowIdx == 0 || board[rowIdx - 1][colIdx] == '*' || visit[rowIdx - 1][colIdx])) {
      int ptr = rowIdx;
      while (ptr > 0 && board[ptr - 1][colIdx] == '.' && !visit[ptr - 1][colIdx]) {
        numEmpty--;
        visit[ptr--][colIdx] = true;
      }
      visit[ptr][colIdx] = true;
      val = Math.min(val, getMinCost(numEmpty, move + 1, ptr, colIdx, board, visit));
      while (ptr < rowIdx) {
        numEmpty++;
        visit[ptr++][colIdx] = false;
      }
    }

    // Down
    if (!(rowIdx == numRow - 1 || board[rowIdx + 1][colIdx] == '*' || visit[rowIdx + 1][colIdx])) {
      int ptr = rowIdx;
      while (ptr < numRow - 1  && board[ptr + 1][colIdx] == '.' && !visit[ptr + 1][colIdx]) {
        numEmpty--;
        visit[ptr++][colIdx] = true;
      }
      visit[ptr][colIdx] = true;
      val = Math.min(val, getMinCost(numEmpty, move + 1, ptr, colIdx, board, visit));
      while (ptr > rowIdx) {
        numEmpty++;
        visit[ptr--][colIdx] = false;
      }
    }

    // Left
    if (!(colIdx == 0 || board[rowIdx][colIdx - 1] == '*' || visit[rowIdx][colIdx - 1])) {
      int ptr = colIdx;
      while (ptr > 0 && board[rowIdx][ptr - 1] == '.' && !visit[rowIdx][ptr - 1]) {
        numEmpty--;
        visit[rowIdx][ptr--] = true;
      }
      visit[rowIdx][ptr] = true;
      val = Math.min(val, getMinCost(numEmpty, move + 1, rowIdx, ptr, board, visit));
      while (ptr < colIdx) {
        numEmpty++;
        visit[rowIdx][ptr++] = false;
      }
    }

    // Right
    if (!(colIdx == numCol - 1 || board[rowIdx][colIdx + 1] == '*' || visit[rowIdx][colIdx + 1])) {
      int ptr = colIdx;
      while (ptr < numCol - 1 && board[rowIdx][ptr + 1] == '.' && !visit[rowIdx][ptr + 1]) {
        numEmpty--;
        visit[rowIdx][ptr++] = true;
      }
      visit[rowIdx][ptr] = true;
      val = Math.min(val, getMinCost(numEmpty, move + 1, rowIdx, ptr, board, visit));
      while (ptr > colIdx) {
        numEmpty++;
        visit[rowIdx][ptr--] = false;
      }
    }
    return val;
  }
  
}
