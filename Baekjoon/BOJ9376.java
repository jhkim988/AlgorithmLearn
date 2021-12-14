import java.io.*;
import java.util.*;

public class BOJ9376 {
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
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    final int INF = 10_000;
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int numRow = Integer.parseInt(st.nextToken());
      int numCol = Integer.parseInt(st.nextToken());
      char[][] map = new char[numRow][];
      ArrayList<Pair> prisoner = new ArrayList<>();
      ArrayList<Pair> door = new ArrayList<>();
      for (int i = 0; i < numRow; i++) {
        map[i] = br.readLine().toCharArray();
        for (int j = 0; j < numCol; j++) {
          if (map[i][j] == '$') prisoner.add(new Pair(i, j, INF));
          else if (map[i][j] == '#') door.add(new Pair(i, j, INF));
        }
      }

      // Use Dijkstra:
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      int[][] dist = new int[numRow][numCol];

      // initialize:
      for (int i = 0; i < numRow; i++) Arrays.fill(dist, INF);
      for (int i = 0; i < 2; i++) pq.add(prisoner.get(i));

      while (!pq.isEmpty()) {
        Pair crnt = pq.poll();
        for (int i = 0; i < 4; i++) {
          int adjRow = crnt.row + rowDi[i];
          int adjCol = crnt.col + colDi[i];
          if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
          if (map[adjRow][adjCol] == '#') {}
        }
      }
    }
  }
}
