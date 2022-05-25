import java.io.*;
import java.util.*;

public class BOJ13907 {
  private static class Edge {
    int end, weight, edge;
    Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    Edge(int end, int weight, int edge) {
      this(end, weight);
      this.edge = edge;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    // Graph init:
    ArrayList<Queue<Edge>> graph = new ArrayList<>();
    for (int i = 0; i <= v; i++) graph.add(new LinkedList<>());
    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graph.get(a).add(new Edge(b, w));
      graph.get(b).add(new Edge(a, w));
    }

    // Dijkstra:
    final int INF = Integer.MAX_VALUE/2;
    int[][] dist = new int[v+1][v+1];
    PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
      @Override
      public int compare(Edge e1, Edge e2) {
        return e1.weight-e2.weight;
      }
    });
    for (int i = 0; i <= v; i++) Arrays.fill(dist[i], INF);
    dist[0][start] = 0;
    pq.add(new Edge(start, 0, 0));
    while (!pq.isEmpty()) {
      Edge crnt = pq.poll();
      if (crnt.weight > dist[crnt.edge][crnt.end]) continue;
      nextVertex: for (Edge adj : graph.get(crnt.end)) {
        if (crnt.edge+1 > v) continue;
        if (dist[crnt.edge+1][adj.end] <= dist[crnt.edge][crnt.end] + adj.weight) continue;
        for (int edge = 0; edge <= crnt.edge; edge++) {
          if (dist[edge][adj.end] < crnt.weight) continue nextVertex;
        }
        dist[crnt.edge+1][adj.end] = dist[crnt.edge][crnt.end] + adj.weight;
        pq.add(new Edge(adj.end, dist[crnt.edge+1][adj.end], crnt.edge+1));
      }
    }

    int sumTax = 0;
    int min = INF;
    for (int i = 0; i <= v; i++) {
      if (dist[i][end] < min) min = dist[i][end];
    }
    bw.write(Integer.toString(min));
    bw.newLine();
    for (int i = 0; i < k; i++) {
      sumTax += Integer.parseInt(br.readLine());
      min = INF;
      for (int j = 0; j <= v; j++) {
        if (dist[j][end]+j*sumTax < min) min = dist[j][end]+j*sumTax;
      }
      bw.write(Integer.toString(min));
      bw.newLine();
    }
    bw.flush();
  }
}