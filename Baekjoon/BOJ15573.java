import java.io.*;
import java.util.*;

public class BOJ15573 {
  static int n, m, k;
  static int[][] mine;
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
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    
    int max = 0;
    mine = new int[n][m];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        mine[i][j] = Integer.parseInt(st.nextToken());
        max = Integer.max(max, mine[i][j]);
      }
    }

    int lo = 0;
    int hi = max + 1;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!check(mid)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }

  static boolean check(int ability) {
    boolean[][] visit = new boolean[n][m];
    Queue<Pair> que = new LinkedList<>();
    // init:
    int count = 0;
    for (int i = 0; i < n; i++) {
      count += init(i, 0, ability, visit, que);
      count += init(i, m - 1, ability, visit, que);
    }
    for (int j = 1; j < m - 1; j++) {
      count += init(0, j, ability, visit, que);
    }
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int i = 0; i < 4; i++) {
        int adjR = crnt.row + rowDi[i];
        int adjC = crnt.col + colDi[i];
        if (adjR < 0 || adjR >= n || adjC < 0 || adjC >= m) continue;
        if (visit[adjR][adjC]) continue;
        if (mine[adjR][adjC] > ability) continue;
        count++;
        if (count >= k) return true;
        visit[adjR][adjC] = true;
        que.add(new Pair(adjR, adjC));
      }
    }
    if (count >= k) return true;
    return false;
  }
  static int init(int r, int c, int ability, boolean[][] visit, Queue<Pair> que) {
    if (mine[r][c] <= ability) {
      visit[r][c] = true;
      que.add(new Pair(r, c));
      return 1;
    }
    return 0;
  }
}
