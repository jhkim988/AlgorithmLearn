import java.io.*;
import java.util.*;

public class BOJ4574 {
  static int[] rowDi = {0, 1};
  static int[] colDi = {1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numGame = 1;
    int numDomino = 0; 
    while ((numDomino = Integer.parseInt(br.readLine())) != 0) {
      int numEmpty = 81 - numDomino * 2 - 9;
      boolean[][] useDomino = new boolean[10][10];
      int[][] board = new int[9][9];

      for (int i = 0; i < numDomino; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        String pos1 = st.nextToken();
        board[pos1.charAt(0) - 'A'][pos1.charAt(1) - '1'] = num1;
        int num2 = Integer.parseInt(st.nextToken());
        String pos2 = st.nextToken();
        board[pos2.charAt(0) - 'A'][pos2.charAt(1) - '1'] = num2;
        int small = Math.min(num1, num2);
        int larger = Math.max(num1, num2);
        useDomino[small][larger] = true;
      }

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= 9; i++) {
        String pos = st.nextToken();
        board[pos.charAt(0) - 'A'][pos.charAt(1) - '1'] = i;
      }

      int[][] sol = solver(board, useDomino, numEmpty);
      StringBuilder sb = new StringBuilder();
      sb.append("Puzzle ").append(numGame++).append('\n');
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          sb.append(sol[i][j]);
        }
        sb.append('\n');
      }
      bw.write(sb.toString());
    }
    bw.flush();
  }
  static int[][] solver(int[][] board, boolean[][] useDomino, int numEmpty) {
    int[][] copy = new int[9][9];
    for (int i = 0; i < 9; i++) {
      copy[i] = board[i].clone();
    }
    recur(0, 0, copy, useDomino, numEmpty);
    return copy;
  }
  static boolean recur(int i, int j, int[][] board, boolean[][] useDomino, int numEmpty) {
    if (numEmpty <= 0) {
      return true;
    }
    if (j >= 9) {
      return recur(i + 1, 0, board, useDomino, numEmpty);
    }

    if (board[i][j] != 0) {
      if (j + 1 < 9) {
        return recur(i, j + 1, board, useDomino, numEmpty);
      } else if (i + 1 < 9) {
        return recur(i + 1, 0, board, useDomino, numEmpty);
      }
      // No case.
      return false;
    }
    boolean[] impossibleOrigin = occupied(i, j, board);
    for (int k = 0; k < 2; k++) {
      int nextRow = i + rowDi[k];
      int nextCol = j + colDi[k];
      if (nextRow >= 9 || nextCol >= 9) continue;
      if (board[nextRow][nextCol] != 0) continue;
      for (int candidate = 1; candidate < 10; candidate++) {
        if (impossibleOrigin[candidate]) continue;
        board[i][j] = candidate;
        boolean[] impossibleNext = occupied(nextRow, nextCol, board);
        for (int nextCandidate = 1; nextCandidate < 10; nextCandidate++) {
          if (impossibleNext[nextCandidate]) continue;
          int small = Math.min(candidate, nextCandidate);
          int larger = Math.max(candidate, nextCandidate);
          if (useDomino[small][larger]) continue;
          board[nextRow][nextCol] = nextCandidate;
          useDomino[small][larger] = true;
          if (recur(i, j + 1, board, useDomino, numEmpty - 2)) return true;
          board[nextRow][nextCol] = 0;
          useDomino[small][larger] = false;
        }
        board[i][j] = 0;
      }
    }
    return false;
  }
  static boolean[] occupied(int i, int j, int[][] board) {
    boolean[] occupied = new boolean[10];
    for (int k = 0; k < 9; k++) {
      if (board[i][k] != 0) occupied[board[i][k]] = true; 
      if (board[k][j] != 0) occupied[board[k][j]] = true;
    }
    int startI = (i / 3) * 3;
    int startJ = (j / 3) * 3;
    for (int k = 0; k < 3; k++) {
      for (int l = 0; l < 3; l++) {
        if (board[startI + k][startJ + l] != 0) {
          occupied[board[startI + k][startJ + l]] = true;
        }
      }
    }
    return occupied;
  }
}
