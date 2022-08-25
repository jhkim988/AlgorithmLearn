import java.io.*;
import java.util.*;

public class BOJ13459 {
  static int nRow, nCol;
  static char[][] board;
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  private static class Ball implements Cloneable {
    int row, col;
    char color;
    boolean isInHall = false;;
    Ball(int row, int col, char color) {
      this.row = row;
      this.col = col;
      this.color = color;
    }
    @Override
    public Ball clone() {
      return new Ball(row, col, color);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    nRow = Integer.parseInt(st.nextToken());
    nCol = Integer.parseInt(st.nextToken());
    board = new char[nRow][nCol];
    Queue<Ball> balls = new LinkedList<>();
    for (int i = 0; i < nRow; i++) {
      board[i] = br.readLine().toCharArray();
      for (int j = 0; j < nCol; j++) {
        if (board[i][j] == 'R' || board[i][j] == 'B') {
          balls.add(new Ball(i, j, board[i][j]));
        }
      }
    }
    bw.write(Integer.toString(dfs(0, balls) ? 1 : 0));
    bw.flush();
  }
  static boolean dfs(int depth, Queue<Ball> balls) {
    if (depth >= 10) return false;
    for (int k = 0; k < 4; k++) {
      Queue<Ball> nextBallPosition = new LinkedList<>();
      for (Ball ball : balls) {
        nextBallPosition.add(ball.clone());
      }
      int moveCode = move(nextBallPosition, k);
      if (moveCode == 1) return true;
      else if (moveCode == 0) {
        if (dfs(depth+1, nextBallPosition)) return true;
      }
      for (Ball ball : nextBallPosition) {
        board[ball.row][ball.col] = '.';
      }
      for (Ball ball : balls) {
        board[ball.row][ball.col] = ball.color;
      }
    }
    return false;
  }
  static int move(Queue<Ball> que, int direction) {
    boolean flagRed = false;
    while (true) {
      int numMove = 0;
      for (Ball ball : que) {
        if (ball.isInHall) continue;
        int nextRow = ball.row + rowDi[direction];
        int nextCol = ball.col + colDi[direction];
        while (board[nextRow][nextCol] != '#') {
          if (board[nextRow][nextCol] == 'O') {
            numMove++;
            board[ball.row][ball.col] = '.';
            ball.isInHall = true;
            if (ball.color == 'B') return -1;
            else {
              flagRed = true;
              break;
            }
          } 
          if (board[nextRow][nextCol] != '.') break;
          board[ball.row][ball.col] = '.';
          board[nextRow][nextCol] = ball.color;
          ball.row += rowDi[direction];
          ball.col += colDi[direction];
          nextRow += rowDi[direction];
          nextCol += colDi[direction];
          numMove++;          
        }
      }
      if (numMove == 0) break;
    }
    if (flagRed) return 1;
    return 0;
  }
  static void print() {
    for (int i = 0; i < nRow; i++) {
      System.out.println(board[i]);
    }
    System.out.println();
  }
}