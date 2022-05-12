import java.io.*;
import java.util.*;

public class BOJ20057 {
  static int n, out;
  static int[] rowDi = {0, 1, 1, 1, 0, -1, -1, -1};
  static int[] colDi = {1, 1, 0, -1, -1, -1, 0, 1};
  static int[][] map;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    map = new int[n][n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    boolean[][] visit = new boolean[n][n];
    int row = n/2, col = n/2, direction = 6;
    for (int k = 0; k < n*n; k++) {
      visit[row][col] = true;
      int nextDirection = (direction+6)%8;
      int nextRow = row + rowDi[nextDirection];
      int nextCol = col + colDi[nextDirection];
      if (visit[nextRow][nextCol]) {
        nextRow = row + rowDi[direction];
        nextCol = col + colDi[direction];
        nextDirection = direction;
      }
      direction = nextDirection;
      move(row, col, direction);
      row = nextRow;
      col = nextCol;
    }
    bw.write(Integer.toString(out));
    bw.flush();
  }
  static void move(int row, int col, int direction) {
    int nextRow = row + rowDi[direction];
    int nextCol = col + colDi[direction];
    if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) return;
    int val = map[nextRow][nextCol];
    int add = 0;
    add += move(nextRow, nextCol, direction, 2, val, 0.05);
    add += move(nextRow, nextCol, (direction+1)%8, 1, val, 0.1);
    add += move(nextRow, nextCol, (direction+7)%8, 1, val, 0.1);
    add += move(nextRow, nextCol, (direction+2)%8, 1, val, 0.07);
    add += move(nextRow, nextCol, (direction+2)%8, 2, val, 0.02);
    add += move(nextRow, nextCol, (direction+6)%8, 1, val, 0.07);
    add += move(nextRow, nextCol, (direction+6)%8, 2, val, 0.02);
    add += move(nextRow, nextCol, (direction+3)%8, 1, val, 0.01);
    add += move(nextRow, nextCol, (direction+5)%8, 1, val, 0.01);
    move(nextRow, nextCol, direction, 1, val-add);
    map[nextRow][nextCol] = 0;
  }
  static int move(int row, int col, int direction, int dist, int val, double rate) {
    int tmpVal = (int) (val*rate);
    move(row, col, direction, dist, tmpVal);
    return tmpVal;
  }
  static void move(int row, int col, int direction, int dist, int add) {
    int tmpRow = row + dist*rowDi[direction];
    int tmpCol = col + dist*colDi[direction];
    if (tmpRow < 0 || tmpRow >= n || tmpCol < 0 || tmpCol >= n) out += add;
    else map[tmpRow][tmpCol] += add;
  }
}
