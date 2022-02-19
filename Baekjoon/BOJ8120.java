import java.io.*;

public class BOJ8120 {
  private static class SegTree {
    int[] tree;
    SegTree(int n) {
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = 1;
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    void update(int node, int start, int end, int idx) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] = 0;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx);
      update(node<<1|1, mid+1, end, idx);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    int get(int node, int start, int end, int k) {
      if (start == end) {
        if (k == 1) return start;
        else return -1;
      }
      int mid = (start + end) >> 1;
      if (tree[node<<1|1] < k) {
        return get(node<<1, start, mid, k - tree[node<<1|1]);
      } else {
        return get(node<<1|1, mid+1, end, k);
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] ic = new int[n]; // inversion count;
    for (int i = 0; i < n; i++) {
      ic[i] = Integer.parseInt(br.readLine());
    }
    int[] answer = new int[n];
    SegTree sg = new SegTree(n);
    for (int i = n-1; i >= 0; i--) {
      int idx = sg.get(1, 0, n-1, ic[i]+1);
      if (idx < 0) {
        bw.write("NIE\n");
        bw.flush();
        return;
      }
      answer[i] = idx+1;
      sg.update(1, 0, n-1, idx);
    }
    for (int a : answer) {
      bw.write(Integer.toString(a));
      bw.newLine();
    }
    bw.flush();
  }
}