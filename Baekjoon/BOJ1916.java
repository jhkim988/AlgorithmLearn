import java.io.*;
import java.util.*;

public class BOJ1916 {
  static int V;
  static int E;
  static final long INF = 10_000_000_001L;
  private static class Pair implements Comparable<Pair> {
    int end;
    long weight;
    Pair(int end, long weight) {
      this.end = end;
      this.weight = weight;
    }
    @Override
    public int compareTo(Pair other) {
      return Long.compare(this.weight, other.weight);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    V = Integer.parseInt(br.readLine());
    E = Integer.parseInt(br.readLine());
    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    for (int i = 0; i <= V; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < E; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
    }
    StringTokenizer st = new StringTokenizer(br.readLine());
    int dest = Integer.parseInt(st.nextToken());
    int arr = Integer.parseInt(st.nextToken());

    long cost = dijkstra(graph, dest, arr);
    bw.write(cost + "\n");
    bw.flush();
  }
  static long dijkstra(ArrayList<Queue<Pair>> graph, int dest, int arr) {
    long[] dist = new long[V + 1];
    Arrays.fill(dist, INF);
    dist[dest] = 0;

    PriorityQueue<Pair> que = new PriorityQueue<>();
    que.add(new Pair(dest, 0));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.end == arr) {
        return crnt.weight;
      }
      for (Pair next : graph.get(crnt.end)) {
        if (dist[next.end] > dist[crnt.end] + next.weight) {
          dist[next.end] = dist[crnt.end] + next.weight;
          que.add(new Pair(next.end, dist[next.end]));
        }
      }
    }
    return -1; // Error
  }
}
