import java.io.*;
import java.util.*;

public class BOJ14287 {
  static ArrayList<Queue<Integer>> tree;
  static int id = 1;
  static int[] start, end;
  private static class SegTree {
    int n;
    int[] tree;
    SegTree(int n) {
      this.n = n;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
    }
    void update(int node, int start, int end, int idx, int val) {
      if (idx < start || end < idx) return;
      if (start == end) {
        tree[node] += val;
        return;
      }
      int mid = (start+end)>>1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    void update(int idx, int val) {
      update(1, 0, n-1, idx, val);
    }
    int get(int node, int start, int end, int left, int right) {
      if (right < start || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start+end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
    int get(int left, int right) {
      return get(1, 0, n-1, left, right);
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
      tree.add(new LinkedList<>());
    }
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      int p = Integer.parseInt(st.nextToken());
      if (i == 1) continue;
      tree.get(p).add(i);
      tree.get(i).add(p);
    }

    start = new int[n+1];
    end = new int[n+1];
    start[1] = id;
    dfs(1);

    SegTree sg = new SegTree(n+1);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int w = Integer.parseInt(st.nextToken());
        sg.update(start[i], w);
      } else {
        bw.write(Integer.toString(sg.get(start[i], end[i])));
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
