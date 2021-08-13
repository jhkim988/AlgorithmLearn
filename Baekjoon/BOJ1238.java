import java.io.*;
import java.util.*;

public class BOJ1238 {
  static final int INF = 1_000_001;
  private static class Pair implements Comparable<Pair> {
    int end;
    int weight;
    Pair(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    @Override
    public int compareTo(Pair other) {
      return this.weight - other.weight;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int V = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());
    int target = Integer.parseInt(st.nextToken());

    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    ArrayList<Queue<Pair>> reverse = new ArrayList<>();
    for (int i = 0; i <= V; i++) {
      graph.add(new LinkedList<>());
      reverse.add(new LinkedList<>());
    }
    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
      reverse.get(end).add(new Pair(start, weight));
    }

    int maxCost = Dijkstra(V, E, target, graph, reverse);
    // int maxCost = FloydWarshall(V, E, target, graph);
    bw.write(maxCost + "\n");
    bw.flush();
  }
  static int Dijkstra(int V, int E, int target, ArrayList<Queue<Pair>> graph, ArrayList<Queue<Pair>> reverse) {
    int maxCost = 0;
    int[] startToTarget = Dijkstra(V, E, target, reverse);
    int[] targetToStart = Dijkstra(V, E, target, graph);
    for (int i = 1; i <= V; i++) {
      int cost = startToTarget[i] + targetToStart[i];
      if (maxCost < cost) {
        maxCost = cost;
      }
    }
    return maxCost;
  }
  static int[] Dijkstra(int V, int E, int start, ArrayList<Queue<Pair>> graph) {
    int[] dist = new int[V + 1]; // dist[i]: distance between start -> i
    Arrays.fill(dist, INF);
    dist[start] = 0;
    PriorityQueue<Pair> que = new PriorityQueue<>();
    que.add(new Pair(start, 0));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (Pair next : graph.get(crnt.end)) {
        if (dist[next.end] > dist[crnt.end] + next.weight) {
          dist[next.end] = dist[crnt.end] + next.weight;
          que.add(new Pair(next.end, dist[next.end]));
        }
      }
    }
    return dist;
  }
  static int FloydWarshall(int V, int E, int target, ArrayList<Queue<Pair>> graph) {
    int[][] dist = new int[V + 1][V + 1];
    for (int i = 0; i <= V; i++) {
      Arrays.fill(dist[i], INF);
    }
    for (int i = 1; i <= V; i++) {
      dist[i][i] = 0;
    }
    for (int i = 1; i <= V; i++) {
      for (Pair adj : graph.get(i)) {
        dist[i][adj.end] = adj.weight;
      }
    }
    for (int k = 1; k <= V; k++) {
      for (int i = 1; i <= V; i++) {
        for (int j = 1; j <= V; j++) {
          if (dist[i][k] >= INF || dist[k][j] >= INF) continue;
          if (dist[i][j] > dist[i][k] + dist[k][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }

    int maxCost = 0;
    for (int i = 1; i <= V; i++) {
      int cost = dist[i][target] + dist[target][i];
      if (maxCost < cost) {
        maxCost = cost;
      }
    }
    return maxCost;
  }
}
