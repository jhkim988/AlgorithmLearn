import java.io.*;
import java.util.*;

public class BOJ4574 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numGame = 1;
    int numDomino = 0; 
    while ((numDomino = Integer.parseInt(br.readLine())) != 0) {
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
        if (num2 < num1) {
          int tmp = num1;
          num1 = num2;
          num2 = tmp;
        }
        useDomino[num1][num2] = true;
      }

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= 9; i++) {
        String pos = st.nextToken();
        board[pos.charAt(0) - 'A'][pos.charAt(1) - '1'] = i;
      }

      int[][] sol = solver(board, useDomino);
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
  static int[][] solver(int[][] board, boolean[][] useDomino) {
    int[][] copy = new int[9][9];
    for (int i = 0; i < 9; i++) {
      copy[i] = board[i].clone();
    }
    boolean[] flag = {false};
    recur(copy, useDomino, flag);
    return copy;
  }
  static void recur(int[][] board, boolean[][] useDomino, boolean[] flag) {
    if (flag[0]) return;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != 0) continue;
          boolean[] occupiedOrigin = occupied(i, j, board);
        // up-down
        if (i + 1 < 9 && board[i + 1][j] == 0) {
          boolean[] occupiedDown = occupied(i + 1, j, board);
          for (int k = 1; k < 10; k++) {
            if (occupiedOrigin[k]) continue;
            for (int l = 1; l < 10; l++) {
              if (occupiedDown[l]) continue;
              int idx0, idx1;
              if (k < l) {
                idx0 = k;
                idx1 = l;
              } else {
                idx0 = l;
                idx1 = k;
              }
              if (useDomino[idx0][idx1]) continue;
              board[i][j] = k;
              board[i + 1][j] = l;
              useDomino[idx0][idx1] = true;
              recur(board, useDomino, flag);
              board[i][j] = 0;
              board[i + 1][j] = 0;
              useDomino[idx0][idx1] = false;
            }
          }
        }
        // left-right
        if (j + 1 < 9 && board[i][j + 1] == 0) {
          boolean[] occupiedRight = occupied(i, j + 1, board);
          for (int k = 1; k < 10; k++) {
            if (occupiedOrigin[k]) continue;
            for (int l = 1; l < 10; l++) {
              if (occupiedRight[l]) continue;
              int idx0, idx1;
              if (k < l) {
                idx0 = k;
                idx1 = l;
              } else {
                idx0 = l;
                idx1 = k;
              }
              if (useDomino[idx0][idx1]) continue;
              board[i][j] = k;
              board[i][j + 1] = l;
              useDomino[idx0][idx1] = true;
              recur(board, useDomino, flag);
              board[i][j] = 0;
              board[i][j + 1] = 0;
              useDomino[idx0][idx1] = false;
            }
          }
        }
      }
    }
    flag[0] = true;
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
