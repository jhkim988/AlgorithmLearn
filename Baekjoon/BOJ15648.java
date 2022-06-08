import java.io.*;
import java.util.*;

public class BOJ15648 {
  private static class SegTree {
    int n;
    long[] tree;
    SegTree(int n) {
      this.n = n;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
    }
    void update(int node, int start, int end, int idx, long val) {
      if (idx < start || end < idx) return;
      if (start == end) {
        tree[node] = val;
        return;
      }
      int mid = (start+end) >> 1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
      tree[node] = Long.max(tree[node<<1], tree[node<<1|1]);
    }
    long get(int node, int start, int end, int left, int right) {
      if (end < left || right < start) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start+end) >> 1;
      return Long.max(get(node<<1, start, mid, left, right), get(node<<1|1, mid+1, end, left, right));
    }
    void update(int idx, long val) {
      update(1, 0, n-1, idx, val);
    }
    long get(int left, int right) {
      return get(1, 0, n-1, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());
    int[] arr = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    long max = 0;
    long[] remainder = new long[k];
    SegTree sg = new SegTree(500_001);
    for (int i = 0; i < n; i++) {
      long val = Long.max(sg.get(arr[i]-d, arr[i]+d), remainder[arr[i]%k]) + 1;
      if (max < val) max = val;
      sg.update(arr[i], val);
      remainder[arr[i]%k] = val;
    }
    bw.write(Long.toString(max));
    bw.flush();
  }
}
