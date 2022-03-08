import java.io.*;
import java.util.*;

public class BOJ17135 {
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  private static class Simulation {
    int[][] map;
    int a1, a2, a3, d;
    Simulation(int[][] map, int d) {
      this.map = map;
      this.d = d;
    }
    void set(int a1, int a2, int a3) {
      this.a1 = a1;
      this.a2 = a2;
      this.a3 = a3;
    }
    int simulation() {
      return 0;
    }

  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());
    int[][] map = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int max = 0;
    Simulation simul = new Simulation(map, d);
    for (int i = 0; i < col; i++) {
      for (int j = i + 1; j < col; j++) {
        for (int k = j + 1; k < col; k++) {
          simul.set(i, j, k);
          int val = simul.simulation();
          if (max < val) max = val;
        }
      }
    }
    bw.write(Integer.toString(max));
    bw.newLine();
    bw.flush();
  }
}
