import java.io.*;
import java.util.*;

public class BOJ13141 {
  private static class Edge {
    int end; double weight;
    Edge(int end, double weight) {
      this.end = end;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Edge>> graph = new ArrayList<>();
    double[][] max = new double[v+1][v+1];
    for (int i = 0 ; i <= v; i++) {
      graph.add(new LinkedList<>());
    }
    while (e-- > 0) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graph.get(a).add(new Edge(b, w));
      graph.get(b).add(new Edge(a, w));
      max[a][b] = max[b][a] = Double.max(max[b][a], w);
    }

    final int INF = 3_000_000;
    double[][] dist = new double[v+1][v+1];
    for (int i = 0; i <= v; i++) Arrays.fill(dist[i], INF);
    for (int i = 1; i <= v; i++) dist[i][i] = 0;
    for (int i = 1; i <= v; i++) {
      for (Edge edge : graph.get(i)) {
        dist[i][edge.end] = Double.min(dist[i][edge.end], edge.weight);
      }
    }
    for (int k = 1; k <= v; k++) {
      for (int i = 1; i <= v; i++) {
        for (int j = 1; j <= v; j++) {
          dist[i][j] = Double.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }

    double min = Double.MAX_VALUE;
    for (int start = 1; start <= v; start++) {
      double val = 0;
      for (int end = 1; end <= v; end++) {
        for (int adj = 1; adj <= v; adj++) {
          val = Double.max(val, (max[end][adj]+dist[start][adj]+dist[start][end])/2);
        }
      }
      min = Double.min(min, val);
    }
    bw.write(Double.toString(min));
    bw.flush();
  } 
}
