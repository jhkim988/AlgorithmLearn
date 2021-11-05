import java.io.*;
import java.util.*;

public class BOJ1981 {
  static int N;
  static int[][] data;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  static int answer = 200;
  private static class Pair {  
    int row;
    int col;
    int max;
    int min;
    Pair (int row, int col, int max, int min) {
      this.row = row;
      this.col = col;
      this.max = max;
      this.min = min;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    data = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dps();
    bw.write(answer + "\n");
    bw.flush();
  }
  static void dps() {
    boolean[][] marked = new boolean[N][N];
    marked[0][0] = true;
    Pair start = new Pair(0, 0, data[0][0], data[0][0]);
    recur(start, marked);
  }
  static void recur(Pair crnt, boolean[][] marked) {
    if (crnt.row == N - 1 && crnt.col == N - 1) {
      int val = crnt.max - crnt.min;
      if (val < answer) answer = val;
      return; 
    } 
    for (int i = 0; i < 4; i++) {
      int nextRow = crnt.row + rowDi[i];
      int nextCol = crnt.col + colDi[i];
      if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
      if (marked[nextRow][nextCol]) continue;
      int max = crnt.max < data[nextRow][nextCol] ? data[nextRow][nextCol] : crnt.max;
      int min = data[nextRow][nextCol] < crnt.min ? data[nextRow][nextCol] : crnt.min;
      marked[nextRow][nextCol] = true;
      recur(new Pair(nextRow, nextCol, max, min), marked);
      marked[nextRow][nextCol] = false;
    }
  }
}