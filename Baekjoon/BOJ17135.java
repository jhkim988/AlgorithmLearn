import java.io.*;
import java.util.*;

public class BOJ17135 {
  static int row, col, d;
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  private static class Pair {
    int row, col, dist;
    Pair(int row, int col, int dist) {
      this.row = row;
      this.col = col;
      this.dist = dist;
    }
  }
  private static class Simulation {
    int[][] map;
    int a1, a2, a3;
    Simulation(int[][] map) {
      this.map = map.clone();
    }
    void set(int a1, int a2, int a3) {
      this.a1 = a1;
      this.a2 = a2;
      this.a3 = a3;
    }
    int simulation() {
      
      return 0;
    }
    void bfs(int c) {
      boolean[][] visit = new boolean[row][col];
      Queue<Pair> que = new LinkedList<>();
      que.add(new Pair(row, c, 0));
      int findDist = row + col;
      int target = -1;
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        for (int k = 0; k < 4; k++) {
          int adjRow = crnt.row + rowDi[k];
          int adjCol = crnt.col + colDi[k];
          if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
          if (visit[adjRow][adjCol]) continue;
          if (crnt.dist + 1 > d) break;
          if (map[adjRow][adjCol] == 1) {
            if (crnt.dist + 1 < findDist) {
              findDist = crnt.dist + 1;

            }
          }
          visit[adjRow][adjCol] = true;
          que.add(new Pair(adjRow, adjCol, crnt.dist + 1));
        }
      }
    }
    void down() {
      for (int i = row-1; i > 0; i--) {
        for (int j = 0; j < col; j++) {
          map[i][j] = map[i-1][j];
        }
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());
    int[][] map = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int max = 0;
    Simulation simul = new Simulation(map);
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
