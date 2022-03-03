import java.io.*;
import java.util.*;

public class BOJ4485 {
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  private static class Pair {
    int row, col, dist;
    Pair(int row, int col, int dist) {
      this.row = row;
      this.col = col;
      this.dist = dist;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int testId = 1;
    while (n != 0) {
      int[][] map = new int[n][n];
      for (int i = 0; i < n; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < n; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      } 
      int min = dijkstra(map, n);
      bw.write("Problem ");
      bw.write(Integer.toString(testId++));
      bw.write(": ");
      bw.write(Integer.toString(min));
      bw.newLine();
      n = Integer.parseInt(br.readLine());
    }
    bw.flush();
  }  
  static int dijkstra(int[][] map, int n) {
    final int INF = 10*n*n;
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist-b.dist);
    int[][] dist = new int[n][n];
    for (int i = 0; i < n; i++) Arrays.fill(dist[i], INF);
    pq.add(new Pair(0, 0, map[0][0]));
    dist[0][0] = map[0][0];
    while (!pq.isEmpty()) {
      Pair crnt = pq.poll();
      if (crnt.dist > dist[crnt.row][crnt.col]) continue;
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt.row + rowDi[k];
        int adjCol = crnt.col + colDi[k];
        if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
        if (crnt.dist + map[adjRow][adjCol] < dist[adjRow][adjCol]) {
          pq.add(new Pair(adjRow, adjCol, dist[adjRow][adjCol] = crnt.dist + map[adjRow][adjCol]));
        } 
      }
    }
    return dist[n-1][n-1];
  }
}
