import java.io.*;
import java.util.*;

public class BOJ14268 {
  static int id = 1;
  static ArrayList<Queue<Integer>> tree;
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
    void update(int node, int start, int end, int left, int right, int val) {
      if (end < left || right < start) return;
      if (left <= start && end <= right) {
        tree[node] += val;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, left, right, val);
      update(node<<1|1, mid+1, end, left, right, val);
    }
    void update(int left, int right, int val) {
      update(1, 0, n-1, left, right, val);
    }
    int get(int node, int start, int end, int idx) {
      if (idx < start || end < idx) return 0;
      if (start == end) return tree[node];
      int mid = (start+end)>>1;
      if (idx <= mid) return tree[node] + get(node<<1, start, mid, idx);
      else return tree[node] + get(node<<1|1, mid+1, end, idx);
    }
    int get(int idx) {
      return get(1, 0, n-1, idx);
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
    start[1] = 1;
    dfs(1);
    SegTree sg = new SegTree(n+1);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int w = Integer.parseInt(st.nextToken());
        sg.update(start[i], end[i], w);
      } else {
        bw.write(Integer.toString(sg.get(start[i])));
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
