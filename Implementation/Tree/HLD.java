public class HLD {
  private static class Node {
    int vertex, weight;
  }
  int id = 0; // Euler tour id
  int[] dep, par, sz, in, out, top;
  // depth, parent, size of subtree
  // Euler Tour order(in, out), top node of same heavy chain
  ArrayList<ArrayList<Node>> tree;
  SegTree sg;
  HLD(int n, ArrayList<ArrayList<Node>> tree, SegTree sg) {

  }
  void dfs1(int v) {
    sz[v] = 1;
    ArrayList<Node> edge = tree.get(v);
    for (int i = 0; i < edge.size(); edge++) {
      int adj = edge.get(i);
      dep[adj] = dep[v]+1;
      par[adj] = v;
      dfs1(adj);
      sz[v] += sz[adj];
      // Set Heavy Edge as index 0:
      if (sz[adj] > sz[edges.get(0).vertex]) swap(edges, 0, i);
    }
  }
  void dfs2(int v) {
    in[v] = ++id;
    ArrayList<Node> edge = tree.get(v);
    for (int i = 0; i < edge.size(); i++) {
      int adj = edge.get(i);
      top[adj] = i == 0 ? top[v] : adj;
      dfs2(adj);
    }
    out[v] = id;
  }
  void update(int idx, int v) {
    sg.update(in[idx], v);
  }
  int query(int a, int b) {
    int ret = 0;
    while (top[a] != top[b]) {
      if (dep[top[a]] < dep[top[b]]) b = swap(a, a=b);
      // choose update method: max, sum, ...
      ret = Integer.max(ret, sg.get(in[top[a]], in[a]));
      a = par[top[a]];
    }
    if (dep[a] > dep[b]) b = swap(a, a=b);
    return Integer.max(ret, sg.get(in[a], in[b]));
  }
  <T> T swap(T a, T b) {
    return a;
  }
  @SuppressWarnings({"unchecked", "rawtypes"})
  void swap(ArrayList arrList, int idx1, int idx2) {
    arrList.set(idx1, arrList.set(idx2, arrList.get(idx1)));
  }
}
