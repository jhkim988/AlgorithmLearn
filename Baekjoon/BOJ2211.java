import java.io.*;
import java.util.*;

public class BOJ2211 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static final int INF = Integer.MAX_VALUE/2;
  private static class Pair {
    int node, weight;
    Pair(int node, int weight) {
      this.node = node;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
      graph.get(end).add(new Pair(start, weight));
    }

    int[] dist = dijkstra(graph);
    int[] tree = new int[n+1]; // tree[x] = parent of x

    Queue<Pair> que = new LinkedList<>();
    que.add(new Pair(1, 0));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (Pair adj : graph.get(crnt.node)) {
        if (dist[adj.node] != crnt.weight + adj.weight) continue;
        tree[adj.node] = crnt.node;
        que.add(new Pair(adj.node, dist[adj.node]));
      }
    }

    print(tree);
  }
  static int[] dijkstra(ArrayList<Queue<Pair>> graph) {
    int[] dist = new int[graph.size()];
    Arrays.fill(dist, INF);
    dist[1] = 0;
    Queue<Pair> que = new LinkedList<>();
    que.add(new Pair(1, 0));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.weight > dist[crnt.node]) continue;
      for (Pair adj : graph.get(crnt.node)) {
        if (dist[crnt.node] + adj.weight >= dist[adj.node]) continue;
        dist[adj.node] = dist[crnt.node] + adj.weight;
        que.add(new Pair(adj.node, dist[adj.node]));
      }
    }

    return dist;
  }
  static void print(int[] tree) throws IOException {
    bw.write(Integer.toString(tree.length-2));
    bw.newLine();
    for (int i = 2; i < tree.length; i++) {
      bw.write(Integer.toString(i));
      bw.write(' ');
      bw.write(Integer.toString(tree[i]));
      bw.newLine();
    }
    bw.flush();
  }
}
