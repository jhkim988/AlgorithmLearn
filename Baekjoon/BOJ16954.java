import java.io.*;
import java.util.*;

public class BOJ16954 {
  private static class Pair {
    int row;
    int col;
    int move;
    Pair(int row, int col, int move) {
      this.row = row;
      this.col = col;
      this.move = move;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int row = 8;
    int col = 8;
    char[][] board = new char[row][col];
    for (int i = 0; i < row; i++) {
      board[i] = br.readLine().toCharArray();
    }

    // BFS:
    int[] rowDi = {1, 0, -1, 1, -1, 1, 0, -1};
    int[] colDi = {-1, -1, -1, 0, 0, 1, 1, 1};
    Pair start = new Pair(row - 1, 0, 0);
    Queue<Pair> que = new LinkedList<>();
    boolean[][] marked = new boolean[row][col];
    que.add(start);
    marked[row - 1][0] = true;
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      // System.out.println(crnt.row + ", " + crnt.col + ", " + crnt.move);
      if (crnt.row <= 0) {
        bw.write("1\n");
        bw.flush();
        return;
      }

      // Move:
      for (int i = 0; i < 8; i++) {
        int nextRow = crnt.row + rowDi[i];
        int nextCol = crnt.col + colDi[i];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        if (nextRow > 0 && marked[nextRow - 1][nextCol]) continue;
        if (board[nextRow][nextCol] == '#' || (nextRow > 0 && board[nextRow - 1][nextCol] == '#')) continue;
        que.add(new Pair(nextRow - 1, nextCol, crnt.move + 1));
        if (nextRow > 0) marked[nextRow - 1][nextCol] = true;
      }
      // Stay:
      if (crnt.row > 0 &&  board[crnt.row - 1][crnt.col] != '#') {
        que.add(new Pair(crnt.row - 1, crnt.col, crnt.move + 1));
      }
    }

    bw.write("0\n");
    bw.flush();
  }
}
