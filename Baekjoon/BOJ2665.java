import java.io.*;
import java.util.*;

public class BOJ2665 {
  private static class Pair implements Comparable<Pair> {
    int row, col, dist;
    Pair(int row, int col, int dist) {
      this.row = row;
      this.col = col;
      this.dist = dist;
    }
    @Override
    public int compareTo(Pair other) {
      return this.dist - other.dist;
    }
  }
  public static void main(String[] args) throws IOException {
    final int INF = 1_000_000;
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    char[][] map = new char[n][];
    for (int i = 0; i < n; i++) {
      map[i] = br.readLine().toCharArray();
    }
    int[][] dist = new int[n][n];
    for (int i = 0; i < n; i++) Arrays.fill(dist[i], INF);
    dist[0][0] = 0;
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.add(new Pair(0, 0, 0));
    while (!pq.isEmpty()) {
      Pair crnt = pq.poll();
      if (crnt.dist > dist[crnt.row][crnt.col]) continue;
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt.row + rowDi[k];
        int adjCol = crnt.col + colDi[k];
        if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
        if (adjRow == n-1 && adjCol == n-1) {
          bw.write(Integer.toString(dist[crnt.row][crnt.col]));
          bw.newLine();
          bw.flush();
          return;
        }
        int add = map[adjRow][adjCol] == '0' ? 1 : 0;
        if (dist[adjRow][adjCol] <= dist[crnt.row][crnt.col] + add) continue;
        pq.add(new Pair(adjRow, adjCol, dist[adjRow][adjCol] = dist[crnt.row][crnt.col] + add));
      }
    }
  }
}
