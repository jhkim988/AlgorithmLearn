import java.io.*;
import java.util.*;

public class BOJ18437 {
  static ArrayList<Queue<Integer>> tree;
  static int[] start, end;
  static int id = 1;
  private static class SegTree {
    int n;
    int[] tree, lazy;
    SegTree(int n) {
      this.n = n;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      lazy = new int[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = 1;
        return;
      }
      int mid = (start+end)>>1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    void updateLazy(int node, int start, int end) {
      if (lazy[node] == 0) return;
      tree[node] = lazy[node] == 1 ? end-start+1 : 0;
      if (start != end) {
        lazy[node<<1] = lazy[node];
        lazy[node<<1|1] = lazy[node];
      }
      lazy[node] = 0;
    }
    void update(int node, int start, int end, int left, int right, int q) {
      updateLazy(node, start, end);
      if (end < left || right < start) return;
      if (left <= start && end <= right) {
        tree[node] = q == 1 ? end-start+1 : 0;
        if (start != end) {
          lazy[node<<1] = q;
          lazy[node<<1|1] = q;
        }
        return;
      }
      int mid = (start+end)>>1;
      update(node<<1, start, mid, left, right, q);
      update(node<<1|1, mid+1, end, left, right, q);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    void update(int left, int right, int q) {
      update(1, 0, n-1, left, right, q);
    }
    int get(int node, int start, int end, int left, int right) {
      updateLazy(node, start, end);
      if (right < start || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start+end)>>1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
    int get(int left, int right) {
      if (left > right) return 0;
      return get(1, 0, n-1, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    tree = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      tree.add(new LinkedList<>());
    }
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      int p = Integer.parseInt(st.nextToken());
      if (i == 1) continue;
      tree.get(i).add(p);
      tree.get(p).add(i);
    }

    start = new int[n+1];
    end = new int[n+1];
    start[1] = id;
    dfs(1);

    SegTree sg = new SegTree(n+1);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      if (type != 3) {
        sg.update(start[i]+1, end[i], type);
      } else {
        bw.write(Integer.toString(sg.get(start[i]+1, end[i])));
        bw.newLine();
      }
    }
    bw.flush();
  }
  static void dfs(int node) {
    for (int adj : tree.get(node)) {
      if (start[adj] != 0) continue;
      start[adj] = ++id;
      dfs(adj);
    }
    end[node] = id;
  }
}
