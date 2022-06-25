import java.io.*;
import java.util.*;

public class BOJ13512 {
  static int n;
  static ArrayList<ArrayList<Integer>> graph, tree;
  static class SegTree {
    int n, INF;
    int[] tree;
    SegTree(int n) {
      this.n = n;
      INF = n+1;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = INF;
        return;
      }
      int mid = (start+end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = Integer.min(tree[node<<1], tree[node<<1|1]);
    }
    void update(int node, int start, int end, int idx) {
      if (idx < start || end < idx) return;
      if (start == end) {
        tree[node] = tree[node] == INF ? start : INF;
        return;
      }
      int mid = (start+end) >> 1;
      update(node<<1, start, mid, idx);
      update(node<<1|1, mid+1, end, idx);
      tree[node] = Integer.min(tree[node<<1], tree[node<<1|1]);
    }
    int get(int node, int start, int end, int left, int right) {
      if (end < left || right < start) return INF;
      if (left <= start && end <= right) return tree[node];
      int mid = (start+end) >> 1;
      return Integer.min(get(node<<1, start, mid, left, right), get(node<<1|1, mid+1, end, left, right));
    }
    void update(int idx) {
      update(1, 0, n-1, idx);
    }
    int get(int left, int right) {
      return get(1, 0, n-1, left, right);
    }
  }
  static class HLD {
    int id, INF;
    int[] dep, par, sz, in, out, top, vertex;
    SegTree sg;
    HLD() {
      INF = n+1;
      dep = new int[n+1]; par = new int[n+1]; sz = new int[n+1];
      in = new int[n+1]; out = new int[n+1]; top = new int[n+1];
      vertex = new int[n+1]; // vertex[a] = b, in[b] = a
      sg = new SegTree(n+1);
      dfs1(1); dfs2(1);
    }
    void dfs1(int v) {
      sz[v] = 1;
      ArrayList<Integer> e = tree.get(v);
      for (int i = 0; i < e.size(); i++) {
        int adj = e.get(i);
        dep[adj] = dep[v]+1;
        par[adj] = v;
        dfs1(adj);
        sz[v] += sz[adj];
        if (sz[adj] > sz[e.get(0)]) e.set(0, e.set(i, e.get(0))); 
      }
    }
    void dfs2(int v) {
      ArrayList<Integer> e = tree.get(v);
      in[v] = ++id;
      vertex[id] = v;
      for (int i = 0; i < e.size(); i++) {
        int adj = e.get(i);
        top[adj] = i == 0 ? top[v] : adj;
        dfs2(adj);
      }
      out[v] = id;
    }
    void update(int a) {
      sg.update(in[a]);
    }
    int get(int a) {
      int ret = INF;
      while (top[a] != top[1]) {
        ret = Integer.min(ret, sg.get(in[top[a]], in[a]));
        a = par[top[a]];
      }
      ret = Integer.min(ret, sg.get(1, in[a]));
      return ret == INF ? -1 : vertex[ret];
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    graph = new ArrayList<>();
    tree = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
      tree.add(new ArrayList<>());
    }
    for (int i = 1; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      graph.get(u).add(v);
      graph.get(v).add(u);
    }
    graphToTree();
    HLD hld = new HLD();
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int u = Integer.parseInt(st.nextToken());
      if (type == 1) {
        hld.update(u);
      } else {
        bw.write(Integer.toString(hld.get(u)));
        bw.newLine();
      }
    }
    bw.flush();
  }
  static void graphToTree() {
    boolean[] visit = new boolean[graph.size()];
    visit[1] = true;
    dfs(1, visit);
  }
  static void dfs(int v, boolean[] visit) {
    for (int adj : graph.get(v)) {
      if (visit[adj]) continue;
      visit[adj] = true;
      tree.get(v).add(adj);
      dfs(adj, visit);
    }
  }
  static int swap(int a, int b) {
    return a;
  }
}
