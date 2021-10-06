import java.io.*;
import java.util.*;

public class BOJ16197 {
  static int row;
  static int col;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  private static class Pair {
    int row;
    int col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());

    char[][] map = new char[row][col];
    ArrayList<Pair> coins = new ArrayList<>();
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
      for (int j = 0; j < col; j++) {
        if (map[i][j] == 'o') {
          coins.add(new Pair(i, j));
          map[i][j] = '.';
        }
      }
    }

    bw.write(answer(map, coins) + "\n");
    bw.flush();
  }
  static int answer(char[][] map, ArrayList<Pair> coins) {
    return recur(0, map, coins);
  }
  static int recur(int depth, char[][] map, ArrayList<Pair> coins) {
    if (depth >= 10) {
      return -1;
    }
    int min = 11;
    for (int i = 0; i < 4; i++) {
      Pair coin0 = coins.get(0);
      Pair coin1 = coins.get(1);

      int nextRow0 = coin0.row + rowDi[i];
      int nextCol0 = coin0.col + colDi[i];
      int nextRow1 = coin1.row + rowDi[i];
      int nextCol1 = coin1.col + colDi[i];
      
      int numOut = 0;
      if (nextRow0 < 0 || nextRow0 >= row || nextCol0 < 0 || nextCol0 >= col) numOut++;
      if (nextRow1 < 0 || nextRow1 >= row || nextCol1 < 0 || nextCol1 >= col) numOut++;

      switch (numOut) {
        case 0:
          if (map[nextRow0][nextCol0] != '#') {
            coins.set(0, new Pair(nextRow0, nextCol0));
          }
          if (map[nextRow1][nextCol1] != '#') {
            coins.set(1, new Pair(nextRow1, nextCol1));
          }
          int result = recur(depth + 1, map, coins);
          min = result < 0 ? min : Math.min(min, result);
          coins.set(0, coin0);
          coins.set(1, coin1);
          break;
        case 1:
          return depth + 1;
        case 2:
          continue;
      }
    }
    return min == 11 ? -1 : min;
  }
}
