import java.io.*;
import java.util.*;

public class BOJ2589 {
  static int row, col;
  static char[][] map;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  private static class Pair {
    int r, c, d;
    Pair(int r, int c, int d) {
      this.r = r;
      this.c = c;
      this.d = d;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    map = new char[row][];
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
    }
    int max = 0;
    boolean[][] visit = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (visit[i][j]) continue;
        if (map[i][j] == 'W') continue;
        max = Integer.max(max, bfs(i, j));
      }
    }
    bw.write(Integer.toString(max));
    bw.newLine();
    bw.flush();
  }
  static int bfs(int r, int c) {
    int max = 0;
    boolean[][] tmp = new boolean[row][col];
    Queue<Pair> que = new LinkedList<>();
    tmp[r][c] = true;
    que.add(new Pair(r, c, 0));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt.r + rowDi[k];
        int adjCol = crnt.c + colDi[k];
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
        if (tmp[adjRow][adjCol]) continue;
        if (map[adjRow][adjCol] == 'W') continue; 
        tmp[adjRow][adjCol] = true;
        que.add(new Pair(adjRow, adjCol, crnt.d + 1));
        if (max < crnt.d + 1) max = crnt.d + 1;
      }
    }
    return max;
  }
}