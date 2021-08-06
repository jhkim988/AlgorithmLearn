import java.io.*;
import java.util.*;

public class BOJ1504 {
  static final int INF = 200_000_001;
  static int numV;
  static int numE;
  static ArrayList<Queue<Edge>> graph;
  private static class Edge implements Comparable<Edge> {
    int end;
    int weight;
    Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
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
  
    graph = new ArrayList<>();

    // graph initialize
    for (int i = 0; i <= numV; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < numE; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      // a -> b, cost c
      graph.get(a).add(new Edge(b, c));
      graph.get(b).add(new Edge(a, c));
    }
    st = new StringTokenizer(br.readLine());
    int v1 = Integer.parseInt(st.nextToken());
    int v2 = Integer.parseInt(st.nextToken());

    int common = dijkstra(v1, v2);
    int path1 = dijkstra(1, v1) + common + dijkstra(v2, numV);
    int path2 = dijkstra(1, v2) + common + dijkstra(v1, numV);
    if (Math.min(path1, path2) >= INF) {
      bw.write("-1\n");
    } else {
      bw.write(Math.min(path1, path2) + "\n");
    }    
    bw.flush();
  }
  static int dijkstra(int start, int end) {
    int[] dist = new int[numV + 1]; // dist[j]: start -> j cost
    Arrays.fill(dist, INF);
    dist[start] = 0;
    PriorityQueue<Edge> que = new PriorityQueue<>();
    Edge startNode = new Edge(start, 0);
    que.add(startNode);

    while (!que.isEmpty()) {
      Edge crnt = que.poll();
      if (crnt.end == end) {
        return dist[crnt.end];
      }
      for (Edge next : graph.get(crnt.end)) {
        if (dist[next.end] > dist[crnt.end] + next.weight) {
          dist[next.end] = dist[crnt.end] + next.weight;
          que.add(new Edge(next.end, dist[next.end]));
        }
      }
    }
    return INF;
  }
}
