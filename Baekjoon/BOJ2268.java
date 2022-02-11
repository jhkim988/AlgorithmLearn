import java.io.*;
import java.util.*;

public class BOJ2268 {
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
    long get(int left, int right) {
      if (right < left) {
        int tmp = left;
        left = right;
        right = tmp;
      }
      return get(1, 0, n-1, left, right);
    }
    void update(int node, int start, int end, int idx, long val) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] = val;
        return;
      }
      int mid = (start + end) >> 1;
      update(node*2, start, mid, idx, val);
      update(node*2+1, mid+1, end, idx, val);
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
      if (type == 0) {
        int left = Integer.parseInt(st.nextToken()) - 1;
        int right = Integer.parseInt(st.nextToken()) - 1;
        bw.write(Long.toString(sg.get(left, right)));
        bw.newLine();
      } else {
        int idx = Integer.parseInt(st.nextToken()) - 1;
        long val = Integer.parseInt(st.nextToken());
        sg.update(1, 0, n-1, idx, val);
      }
    }
    bw.flush();
  }
}
