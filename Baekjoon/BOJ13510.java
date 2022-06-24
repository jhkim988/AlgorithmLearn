import java.io.*;
import java.util.*;

public class BOJ13510 {
  static boolean[] visit;
  private static class Pair {
    int vertex, weight;
    Pair(int vertex, int weight) {
      this.vertex = vertex;
      this.weight = weight;
    }
  }
  private static class Edge {
    int start, end, weight;
    Edge(int start, int end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }
  }
  private static class SegTree {
    int n;
    int[] max;
    SegTree(int n) {
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      max = new int[treeSize];
    }
    void update(int node, int start, int end, int idx, int val) {
      if (idx < start || end < idx) return;
      if (start == end) {
        max[node] = val;
        return;
      }
      int mid = (start+end) >> 1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
      max[node] = Integer.max(max[node<<1], max[node<<1|1]);
    }
    int get(int node, int start, int end, int left, int right) {
      if (end < left || right < start) return 0;
      if (left <= start && end <= right) return max[node];
      int mid = (start+end) >> 1;
      return Integer.max(get(node<<1, start, mid, left, right), get(node<<1|1, mid+1, end, left, right));
    }
    void update(int idx, int val) {
      update(1, 0, n-1, idx, val);
    }
    int get(int left, int right) {
      return get(1, 0, n-1, left, right);
    }
  }
  private static class HLD {
    int id = 0;
    int[] dep, par, sz, in, out, top;
    Edge[] edges;
    ArrayList<ArrayList<Pair>> tree;
    SegTree sg;
    HLD(int n, ArrayList<ArrayList<Pair>> tree, Edge[] edges) {
      n++;
      this.tree = tree;
      this.edges = edges;
      sg = new SegTree(n);
      dep = new int[n]; par = new int[n]; sz = new int[n];
      in = new int[n]; out = new int[n]; top = new int[n];
      dfs1(1); dfs2(1); edgesSort();
      for (int i = 1; i < edges.length; i++) {
        sg.update(in[edges[i].start], edges[i].weight);
        System.out.println("sg init: " + in[edges[i].start] + " -> " + edges[i].weight);
      }
      System.out.println("sg.get():" + sg.get(2, 3));
    }
    void dfs1(int v) {
      sz[v] = 1;
      for (int i = 0; i < tree.get(v).size(); i++) {
        int adj = tree.get(v).get(i).vertex;
        dep[adj] = dep[v]+1;
        par[adj] = v;
        dfs1(adj);
        sz[v] += sz[adj];
        if (sz[adj] > sz[tree.get(v).get(0).vertex]) {
          Pair tmp = tree.get(v).get(0);
          tree.get(v).set(0, tree.get(v).get(i));
          tree.get(v).set(i, tmp);
        }
      }
    }
    void dfs2(int v) {
      in[v] = ++id;
      for (int i = 0; i < tree.get(v).size(); i++) {
        int adj = tree.get(v).get(i).vertex;
        top[adj] = adj == tree.get(v).get(0).vertex ? top[v] : adj;
        dfs2(adj);
      }
      out[v] = id;
    }
    void edgesSort() {
      for (int i = 1; i < edges.length; i++) {
        if (dep[edges[i].start] > dep[edges[i].end]) continue;
        int tmp = edges[i].start;
        edges[i].start = edges[i].end;
        edges[i].end = tmp;
      }
    }
    void update(int idx, int w) {
      sg.update(in[edges[idx].start], w);
    }
    int query(int a, int b) {
      System.out.println("Query: " + a + ", " + b);
      int ret = 0;
      while (top[a] != top[b]) {
        if (dep[top[a]] < dep[top[b]]) {
          int tmp = a;
          a = b;
          b = tmp;
        }
        int st = top[a];
        ret = Integer.max(ret, sg.get(in[st]+1, in[a]));
        a = par[st];
      }
      if (dep[a] > dep[b]) {
        int tmp = a;
        a = b;
        b = tmp;
      }
      return Integer.max(ret, sg.get(in[a]+1, in[b]));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
    ArrayList<ArrayList<Pair>> tree = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
      tree.add(new ArrayList<>());
    }
    Edge[] edges = new Edge[n];
    for (int i = 1; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
      graph.get(end).add(new Pair(start, weight));
      edges[i] = new Edge(start, end, weight);
    }
    visit = new boolean[n+1];
    visit[1] = true;
    dfs(graph, tree, 1);

    HLD hld = new HLD(n, tree, edges);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int idx = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());
        hld.update(idx, cost);
      } else {
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        bw.write(Integer.toString(hld.query(u, v)));
        bw.newLine();
      }
      bw.flush();
    }
  }
  static void dfs(ArrayList<ArrayList<Pair>> graph, ArrayList<ArrayList<Pair>> tree, int v) {
    for (Pair adj : graph.get(v)) {
      if (visit[adj.vertex]) continue;
      tree.get(v).add(new Pair(adj.vertex, adj.weight));
      visit[adj.vertex] = true;
      dfs(graph, tree, adj.vertex);
    }
  }
}