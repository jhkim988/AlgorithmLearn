import java.io.*;
import java.util.*;

public class BOJ1753 {
  static int numV;
  static int numE;
  static ArrayList<List<Edge>> graph;
  static int[] dist;
  static int INF = 100_000_000;

  private static class Edge implements Comparable<Edge> {
    int weight;
    int vertex;
    Edge(int weight, int vertex) {
      this.weight = weight;
      this.vertex = vertex;
    }
    @Override
    public int compareTo(Edge other) {
      return this.weight - other.weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    numV = Integer.parseInt(st.nextToken());
    numE = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(br.readLine());

    graph = new ArrayList<>();
    for (int i = 0; i <= numV; i++) {
      graph.add(new ArrayList<Edge>());
    }
    for (int i = 0; i < numE; i++) {
      st = new StringTokenizer(br.readLine());
      // u -> v, weight
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
    
      graph.get(u).add(new Edge(w, v));
    }
    dist = new int[numV + 1];
    Arrays.fill(dist, INF);
    dijkstra(start);
    StringBuilder sb = new StringBuilder();
    for (int target = 1; target <= numV; target++) {
      if (dist[target] == INF) {
        sb.append("INF\n");
      } else {
        sb.append(dist[target] + "\n");
      }
    }
    bw.write(sb.toString());
    bw.flush();
  }
  static void dijkstra(int start) {
    PriorityQueue<Edge> minpq = new PriorityQueue<>();
    boolean[] marked = new boolean[numV + 1];

    Edge startNode = new Edge(0, start); 
    minpq.add(startNode);
    dist[start] = 0;

    while (!minpq.isEmpty()) {
      Edge crnt = minpq.poll();
      if (marked[crnt.vertex]) {
        continue;
      }
      marked[crnt.vertex] = true;
      for (Edge next : graph.get(crnt.vertex)) {
        if (dist[next.vertex] > dist[crnt.vertex] + next.weight) {          
          dist[next.vertex] = dist[crnt.vertex] + next.weight; 
          minpq.add(new Edge(dist[next.vertex], next.vertex));
        }
      }
    }
  }
}
