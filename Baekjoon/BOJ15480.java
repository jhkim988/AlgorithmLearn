import java.io.*;
import java.util.*;

public class BOJ15480 {
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
      for (int i = 0; i < n; i++) {
        graph.add(new LinkedList<>());
      }
    }
    void addEdge(int start, int end) {
      graph.get(start).add(end);
      graph.get(end).add(start);
    }
    int size() {
      return graph.size();
    }
    Queue<Integer> get(int i) {
      return graph.get(i);
    }
  }
  private static class SparseTable {
    int n, height;
    Graph graph;
    int[] level;
    int[][] tree;
    SparseTable(Graph graph) {
      this.graph = graph;
      n = graph.size();
      level = new int[n];
      init();
    }
    void init() {
      height = 0;
      while (1 << height < n) height++;
      tree = new int[height][n];
      tree[0][1] = 1;
      boolean[] visit = new boolean[n];
      Queue<Pair> que = new LinkedList<>();
      visit[1] = true;
      level[1] = 1;
      que.add(new Pair(1, 1));
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        for (int next : graph.get(crnt.end)) {
          if (visit[next]) continue;
          tree[0][next] = crnt.end;
          visit[next] = true;
          level[next] = crnt.level + 1;
          que.add(new Pair(next, level[next]));
        }
      }    
      for (int h = 1; h < height; h++) {
        for (int i = 1; i < n; i++) {
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
        }
        // level[a] < level[b]
        int diff = level[b] - level[a];
        for (int h = 0; h < height; h++) {
          if ((diff & (1 << h)) == 0) continue;
          b = tree[h][b];
        }
      }
      if (a == b) return a;
      for (int h = tree.length - 1; h >= 0; h--) {
        if (tree[h][a] != tree[h][b]) {
          a = tree[h][a];
          b = tree[h][b];
        }
      }
      return tree[0][a];
    }
    int query(int r, int a, int b) {
      int ab = lca(a, b);
      int ra = lca(a, r);
      int rb = lca(b, r);
      int answer = ab;
      if (level[ra] >= level[answer]) answer = ra;
      if (level[rb] >= level[answer]) answer = rb;
      return answer;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Graph graph = new Graph(n + 1);
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
      int r = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(sp.query(r, a, b)));
      bw.newLine();
    }
    bw.flush();
  }
}
