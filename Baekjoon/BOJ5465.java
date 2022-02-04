import java.io.*;
import java.util.*;

public class BOJ5465 {
  static int n, speed;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  private static class Pair {
    int row, col, level;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
    Pair(int row, int col, int level) {
      this(row, col);
      this.level = level;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    speed = Integer.parseInt(st.nextToken());

    char[][] map = new char[n][];
    for (int i = 0; i < n; i++) {
      map[i] = br.readLine().toCharArray();
    }
    int[][] beeTime = beeTime(map);
    Pair start = null;
    findStart: for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (map[i][j] == 'M') {
          start = new Pair(i, j);
          break findStart;
        }
      }
    }

    int lo = -1;
    int hi = beeTime[start.row][start.col];
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (simulation(map, beeTime, mid, start)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
  
    bw.write(Integer.toString(lo));
    bw.newLine();
    bw.flush();
  }
  static int[][] beeTime(char[][] map) {
    int[][] dist = new int[n][n];
    for (int i = 0; i < n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
    Queue<Pair> bee = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (map[i][j] == 'H') {
          dist[i][j] = 0;
          bee.add(new Pair(i, j));
        }
      }
    }

    while (!bee.isEmpty()) {
      Pair crnt = bee.poll();
      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.row + rowDi[i];
        int nextCol = crnt.col + colDi[i];
        if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
        if (map[nextRow][nextCol] != 'G' && map[nextRow][nextCol] != 'M') continue;
        if (dist[nextRow][nextCol] != Integer.MAX_VALUE) continue;
        dist[nextRow][nextCol] = dist[crnt.row][crnt.col] + 1;
        bee.add(new Pair(nextRow, nextCol, crnt.level + 1));
      }
    }

    return dist;
  }
  static boolean simulation(char[][] map, int[][] beeTime, int time, Pair start) {
    if (beeTime[start.row][start.col] <= time) return false;
    boolean[][] visit = new boolean[n][n];
    Queue<Pair> que = new LinkedList<>();
    visit[start.row][start.col] = true;
    que.add(start);
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int i = 0; i < 4; i++) {
        int nr = crnt.row + rowDi[i];
        int nc = crnt.col + colDi[i];
        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
        if (visit[nr][nc]) continue;
        if (map[nr][nc] == 'D') return true;
        if (map[nr][nc] != 'G') continue;
        if (beeTime[nr][nc] <= time + (crnt.level + 1) / speed) continue;
        visit[nr][nc] = true;
        que.add(new Pair(nr, nc, crnt.level + 1));
      }
    }
    return false;
  }
}
