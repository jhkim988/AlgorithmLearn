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
    }

    for (int i = 1; i <= v; i++) {
      boolean[] visit = new boolean[v+1];
      Queue<Edge> que = new LinkedList<>();
      visit[i] = true;
      que.add(new Edge(i, 0));
      while (!que.isEmpty()) {
        Edge crnt = que.poll();
        for (Edge edge : graph.get(i)) {
          if (!visit[edge.end])que.add(new Edge(edge.end, crnt.weight+edge.weight));
          max[i][edge.end] = Double.max(max[i][edge.end], crnt.weight+edge.weight);
          visit[edge.end] = true;
        }
      }
    }

    final int INF = 3_000_000;
    double[][] floyd = new double[v+1][v+1];
    for (int i = 0; i <= v; i++) Arrays.fill(floyd[i], INF);
    for (int i = 1; i <= v; i++) floyd[i][i] = 0;
    for (int i = 1; i <= v; i++) {
      for (Edge edge : graph.get(i)) {
        floyd[i][edge.end] = edge.weight;
      }
    }
    for (int k = 1; k <= v; k++) {
      for (int i = 1; i <= v; i++) {
        for (int j = 1; j <= v; j++) {
          floyd[i][j] = Double.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
        }
      }
    }
    double time = 0;
    for (int i = 1; i <= v; i++) {
      for (int j = 1; j <= v; j++) {
        time = Double.max(time, (max[i][j]+floyd[i][j])/2);
      }
    }
    bw.write(Double.toString(time));
    bw.flush();
  } 
}
