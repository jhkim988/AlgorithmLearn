import java.io.*;
import java.util.*;

public class BOJ2468 {
  static int n;
  static int[][] map;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    map = new int[n][n];
    int max = 0;
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (max < map[i][j]) max = map[i][j];
      }
    }

    int answer = 0;
    for (int height = 0; height < max; height++) {
      int bfs = bfs(height);
      if (answer < bfs) answer = bfs;
    }

    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static int bfs(int height) {
    int count = 0;
    boolean[][] visit = new boolean[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (map[i][j] <= height) continue;
        if (visit[i][j]) continue;
        Queue<Pair> que = new LinkedList<>();
        visit[i][j] = true;
        que.add(new Pair(i, j));
        count++;
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int adjRow = crnt.row + rowDi[k];
            int adjCol = crnt.col + colDi[k];
            if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
            if (visit[adjRow][adjCol]) continue;
            if (map[adjRow][adjCol] <= height) continue;
            visit[adjRow][adjCol] = true;
            que.add(new Pair(adjRow, adjCol));
          }
        }
      }
    }
    return count;
  }
}