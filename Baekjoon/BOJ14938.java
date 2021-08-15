import java.io.*;
import java.util.*;

public class BOJ14938 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int V;
  static int range;
  static int E;
  static int[] item;
  static ArrayList<Queue<Pair>> graph;
  static final int INF = 1501;

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
    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    range = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());
    graph = new ArrayList<>();
    for (int i = 0; i <= V; i++) {
      graph.add(new LinkedList<>());
    }
    st = new StringTokenizer(br.readLine());
    item = new int[V + 1];
    for (int i = 1; i <= V; i++) {
      item[i] = Integer.parseInt(st.nextToken());
    }
    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
      graph.get(end).add(new Pair(start, weight));
    }
    Dijkstra();
  }
  static void FolydWarshall() throws IOException {    
    int[][] dist = new int[V + 1][V + 1];
    for (int i = 0; i <= V; i++) {
      for (int j = 0; j <= V; j++) {
        dist[i][j] = INF;
      }
    }
    for (int i = 1; i <= V; i++) {
      for (Pair next : graph.get(i)) {
        if (dist[i][next.end] > next.weight) {
          dist[i][next.end] = next.weight;
        }
      }
    }
    for (int i = 0; i <= V; i++) {
      dist[i][i] = 0;
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

    int maxItem = 0;
    for (int i = 1; i <= V; i++) {
      int itemCount = 0;
      for (int j = 1; j <= V; j++) {
        if (dist[i][j] <= range) {
          itemCount += item[j];
        }
      }
      if (maxItem < itemCount) {
        maxItem = itemCount;
      }
    }

    bw.write(maxItem + "\n");
    bw.flush();
  }
  static void Dijkstra() throws IOException {
    int maxItem = 0;
    for (int node = 1; node <= V; node++) {
      PriorityQueue<Pair> que = new PriorityQueue<>();
      Pair start = new Pair(node, 0);
      int[] dist = new int[V + 1];
      for (int i = 0; i <= V; i++) {
        dist[i] = INF;
      }
      dist[node] = 0;
      que.add(start);
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        if (crnt.weight > range) {
          break;
        }
        for (Pair next : graph.get(crnt.end)) {
          if (dist[next.end] > dist[crnt.end] + next.weight) {
            dist[next.end] = dist[crnt.end] + next.weight;
            que.add(new Pair(next.end, dist[next.end]));
          }
        }
      }
      int itemCount = 0;
      for (int i = 1; i <= V; i++) {
        if (dist[i] <= range) {
          itemCount += item[i];
        }
      }
      if (maxItem < itemCount) {
        maxItem = itemCount;
      }
    }
    bw.write(maxItem + "\n");
    bw.flush();
  }
}
