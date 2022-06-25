import java.io.*;
import java.util.*;

public class BOJ13309 {
  static ArrayList<ArrayList<Integer>> tree;
  private static class SegTree {
    int n;
    int[] max;
    SegTree(int n) {
      this.n = n;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      max = new int[treeSize];
      Arrays.fill(max, 1);
    }
    void update(int node, int start, int end, int idx) {
      if (idx < start || end < idx) return;
      if (start == end) {
        max[node] = start;
        return;
      }
      int mid = (start+end) >> 1;
      update(node<<1, start, mid, idx);
      update(node<<1|1, mid+1, end, idx);
      max[node] = Integer.max(max[node<<1], max[node<<1|1]);
    }
    int get(int node, int start, int end, int left, int right) {
      if (right < start || end < left) return 1;
      if (left <= start && end <= right) return max[node];
      int mid = (start+end) >> 1;
      return Integer.max(get(node<<1, start, mid, left, right), get(node<<1|1, mid+1, end, left, right));
    }
    void update(int idx) {
      update(1, 0, n-1, idx);
    }
    int get(int left, int right) {
      return get(1, 0, n-1, left, right);
    }
  }
  private static class HLD {
    int id = 0;
    int[] dep, par, sz, in, out, top, vertex;
    SegTree sg;
    HLD(int n) {
      dep = new int[n+1]; par = new int[n+1]; sz = new int[n+1];
      in = new int[n+1]; out = new int[n+1]; top = new int[n+1];
      vertex = new int[n+1];
      sg = new SegTree(n+1);
      dfs1(1); dfs2(1);      
    }
    void dfs1(int v) {
      ArrayList<Integer> e = tree.get(v);
      sz[v] = 1;
      for (int i = 0; i < e.size(); i++) {
        int adj = e.get(i);
        dep[adj] = dep[v] + 1;
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
    void cut(int a) {
      sg.update(in[a]);
    }
    int root(int a) {
      int ret = 1;
      while (top[a] != 0) {
        ret = Integer.max(ret, sg.get(in[top[a]], in[a]));
        a = par[top[a]];
      }
      return vertex[Integer.max(ret, sg.get(1, in[a]))];
    }
    boolean isConnected(int a, int b) {
      return root(a) == root(b);
    }
    String query(int b, int c, int d) {
      boolean isConnected = isConnected(b, c);
      if (d == 1) {
        if (isConnected) cut(b);
        else cut(c);
      }
      return isConnected ? "YES\n" : "NO\n";
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
    tree = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      tree.add(new ArrayList<>());
    }
    for (int i = 2; i <= n; i++) {
      int p = Integer.parseInt(br.readLine());
      tree.get(p).add(i);
    }
    HLD hld = new HLD(n);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      bw.write(hld.query(b, c, d));
    }
    bw.flush();
  }
}
