import java.io.*;
import java.util.*;

public class BOJ11437 {
  private static class Pair {
    int end, level;
    Pair(int end, int level) {
      this.end = end;
      this.level = level;
    }
  }
  private static class Graph {
    ArrayList<Queue<Integer>> graph;
    Graph(int n) {
      graph = new ArrayList<>();
      for (int i = 0; i <= n; i++) {
        graph.add(new LinkedList<>());
      }
    }
    void addEdge(int a, int b) {
      graph.get(a).add(b);
      graph.get(b).add(a);
    }
    int size() { return graph.size(); }
    Queue<Integer> get(int i) { return graph.get(i); }
  }
  private static class SparseTable {
    int height;
    int[][] tree;
    int[] level;
    SparseTable(Graph graph) {
      height = 1;
      while (1 << height < graph.size()) height++;
      tree = new int[height][graph.size()];
      tree[0][1] = 1;
      boolean[] visit = new boolean[graph.size()];
      level = new int[graph.size()];
      Queue<Pair> que = new LinkedList<>();
      que.add(new Pair(1, 1));
      visit[1] = true;
      level[1] = 1;
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        for (int next : graph.get(crnt.end)) {
          if (visit[next]) continue;
          visit[next] = true;
          level[next] = crnt.level + 1;
          tree[0][next] = crnt.end;
          que.add(new Pair(next, level[next]));
        }
      }
      for (int h = 1; h < height; h++) {
        for (int i = 1; i < graph.size(); i++) {
          tree[h][i] = tree[h - 1][tree[h - 1][i]];
        }
      }
    }
    int lca(int a, int b) {
      if (level[a] != level[b]) {
        if (level[a] > level[b]) {
          int tmp = a;
          a = b;
          b = tmp;
        } // level[a] < level[b]
        int diff = level[b] - level[a];
        for (int h = 0; h < height; h++) {
          if ((diff & (1 << h)) == 0) continue;
          b = tree[h][b];
        }
      }
      if (a == b) return a;
      for (int h = height - 1; h >= 0; h--) {
        if (tree[h][a] != tree[h][b]) {
          a = tree[h][a];
          b = tree[h][b];
        }
      }
      return tree[0][a];
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
      graph.addEdge(a, b);
    }
    SparseTable sp = new SparseTable(graph);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(sp.lca(a, b)));
      bw.newLine();
    }
    bw.flush();
  }
}
