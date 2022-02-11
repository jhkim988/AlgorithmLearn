import java.io.*;
import java.util.*;

public class BOJ12837 {
  private static class SegTree {
    int n, treeSize;
    long[] tree;
    SegTree(int n) {
      this.n = n;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
    }
    void update(int node, int start, int end, int idx, long add) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] += add;
        return;
      }
      int mid = (start + end) >> 1;
      update(node*2, start, mid, idx, add);
      update(node*2+1, mid+1, end, idx, add);
      tree[node] = tree[node*2] + tree[node*2+1];      
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node*2, start, mid, left, right) + get(node*2+1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
    SegTree sg = new SegTree(n);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int idx = Integer.parseInt(st.nextToken())-1;
        long val = Long.parseLong(st.nextToken());
        sg.update(1, 0, n-1, idx, val);
      } else {
        int left = Integer.parseInt(st.nextToken())-1;
        int right = Integer.parseInt(st.nextToken())-1;
        bw.write(Long.toString(sg.get(1, 0, n-1, left, right)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
