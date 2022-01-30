import java.io.*;
import java.util.*;

public class BOJ1761 {
  private static class Edge {
    int end, weight;
    Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
  }
  private static class Pair {
    int end, level;
    Pair(int end, int level) {
      this.end = end;
      this.level = level;
    }
  }
  private static class Graph {
    ArrayList<Queue<Edge>> graph;
    Graph(int n) {
      graph = new ArrayList<>();
      for (int i = 0; i <= n; i++) {
        graph.add(new LinkedList<>());
      }
    }
    void addEdge(int a, int b, int weight) {
      graph.get(a).add(new Edge(b, weight));
      graph.get(b).add(new Edge(a, weight));
    }
    int size() { return graph.size(); }
    Queue<Edge> get(int i) { return graph.get(i); }
  }
  private static class SparseTable {
    int height;
    int[] level;
    int[][] tree, dist;
    SparseTable(Graph graph) {
      int size = graph.size();
      while (1 << height < size) height++;
      level = new int[size];
      tree = new int[height][size];
      dist = new int[height][size];
      // BFS:
      boolean[] visit = new boolean[size];
      Queue<Pair> que = new LinkedList<>();
      visit[1] = true;
      level[1] = 1;
      tree[0][1] = 1;
      que.add(new Pair(1, 1));
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        for (Edge next : graph.get(crnt.end)) {
          if (visit[next.end]) continue;
          visit[next.end] = true;
          tree[0][next.end] = crnt.end;
          dist[0][next.end] = next.weight;
          level[next.end] = crnt.level + 1;
          que.add(new Pair(next.end, level[next.end]));
        }
      }    
      for (int h = 1; h < height; h++) {
        for (int i = 1; i < size; i++) {
          tree[h][i] = tree[h - 1][tree[h - 1][i]];
          dist[h][i] = dist[h - 1][i] + dist[h - 1][tree[h - 1][i]];
        }
      }
    }
    int dist(int a, int b) {
      int ret = 0;
      if (level[a] != level[b]) {
        if (level[a] > level[b]) {
          int tmp = a;
          a = b;
          b = tmp;
        } // level[a] < level[b]
        int diff = level[b] - level[a];
        for (int h = 0; h < height; h++) {
          if ((diff & 1 << h) == 0) continue;
          ret += dist[h][b];
          b = tree[h][b];
        }
      }
      if (a == b) return ret;
      for (int h = height - 1; h >= 0; h--) {
        if (tree[h][a] != tree[h][b]) {
          ret += dist[h][a] + dist[h][b];
          a = tree[h][a];
          b = tree[h][b];
        }
      }
      return ret + dist[0][a] + dist[0][b];
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Graph graph = new Graph(n);
    for (int i = 1; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graph.addEdge(a, b, w);
    }
    SparseTable sp = new SparseTable(graph);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(sp.dist(a, b)));
      bw.newLine();
    }
    bw.flush();
  }
}
