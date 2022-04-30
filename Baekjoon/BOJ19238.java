import java.io.*;
import java.util.*;

public class BOJ19238 {
  static final int INF = Integer.MAX_VALUE/2;
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  private static class Passenger {
    Pair start, end;
    Passenger(Pair start, Pair end) {
      this.start = start;
      this.end = end;
    }
  }
  private static class FloydWarshall {
    int n;
    int[][] dist;
    private int[] rowDi = {-1, 0, 1, 0};
    private int[] colDi = {0, -1, 0, 1};
    FloydWarshall(int n, int[][] map) {
      this.n = n;
      dist = new int[(n+1)*(n+1)][(n+1)*(n+1)]; // pointer base
      for (int i = 0; i < (n+1)*(n+1); i++) Arrays.fill(dist[i], INF);
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          if (map[i][j] == 1) continue;
          dist[i*(n+1)+j][i*(n+1)+j] = 0;
          for (int k = 0; k < 4; k++) {
            int adjRow = i + rowDi[k];
            int adjCol = j + colDi[k];
            if (adjRow < 1 || adjRow >= (n+1) || adjCol < 1 || adjCol >= (n+1)) continue;
            if (map[adjRow][adjCol] == 1) continue;
            dist[i*(n+1)+j][adjRow*(n+1)+adjCol] = 1;
          }
        }
      }

      for (int k = 0; k < (n+1)*(n+1); k++) {
        for (int i = 0; i < (n+1)*(n+1); i++) {
          for (int j = 0; j < (n+1)*(n+1); j++) {
            if (dist[i][k] + dist[k][j] < dist[i][j])
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }
    int dist(Pair p1, Pair p2) {
      return dist[p1.row*(n+1) + p1.col][p2.row*(n+1) + p2.col];
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int f = Integer.parseInt(st.nextToken());
    int[][] map = new int[n+1][n+1];
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    st = new StringTokenizer(br.readLine());
    Pair start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    Passenger[] parr = new Passenger[m];
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int sr = Integer.parseInt(st.nextToken());
      int sc = Integer.parseInt(st.nextToken());
      int er = Integer.parseInt(st.nextToken());
      int ec = Integer.parseInt(st.nextToken());
      parr[i] = new Passenger(new Pair(sr, sc), new Pair(er, ec));
    }
    Arrays.sort(parr, new Comparator<>(){
      @Override
      public int compare(Passenger p1, Passenger p2) {
        if (p1.start.row != p2.start.row) return p1.start.row-p2.start.row;
        return p1.start.col-p2.start.col;
      }
    });
    FloydWarshall fw = new FloydWarshall(n, map);
    boolean[] check = new boolean[m];
    for (int i = 0; i < m; i++) {
      int idx = 0;
      Passenger closest = new Passenger(new Pair(0, 0), new Pair(0, 0));
      for (int j = 0; j < m; j++) {
        if (check[j]) continue;
        if (fw.dist(start, parr[j].start) < fw.dist(start, closest.start)) {
          closest = parr[j];
          idx = j;
        }
      }
      check[idx] = true;
      f -= fw.dist(start, closest.start);
      int cost = fw.dist(closest.start, closest.end);
      f -= cost;
      if (f < 0) {
        bw.write("-1\n");
        bw.flush();
        return;
      }
      f += 2*cost;
      start = closest.end;
    }
    bw.write(Integer.toString(f));
    bw.flush();
  }
}
