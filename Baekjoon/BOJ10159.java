import java.io.*;
import java.util.*;

public class BOJ10159 {
  public static void main(String[] args) throws IOException {
    final int INF = 1_000;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int heavy = Integer.parseInt(st.nextToken());
      int light = Integer.parseInt(st.nextToken());
      graph.get(heavy).add(light);
    }
    // use Floyd-Warshall Algorithm:
    int[][] dist = new int[n+1][n+1];
    int[][] rdist = new int[n+1][n+1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(dist[i], INF);
      Arrays.fill(rdist[i], INF);
    } 
    for (int i = 1; i <= n; i++) {
      for (int j : graph.get(i)) {
        dist[i][j] = 1;
        rdist[j][i] = 1;
      }
    }
    for (int i = 1; i <= n; i++) {
      dist[i][i] = 0;
      rdist[i][i] = 0;
    }
    for (int k = 1; k <= n; k++) {
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);
          rdist[i][j] = Integer.min(rdist[i][j], rdist[i][k] + rdist[k][j]);
        } 
      }  
    }
    for (int i = 1; i <= n; i++) {
      int sum = 0;
      for (int j = 1; j <= n; j++) {
        if (dist[i][j] == INF && rdist[i][j] == INF) sum++;
      }
      bw.write(Integer.toString(sum));
      bw.newLine();
    }
    bw.flush();
  }
}
