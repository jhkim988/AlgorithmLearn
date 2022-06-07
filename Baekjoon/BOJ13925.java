import java.io.*;
import java.util.*;

public class BOJ13925 {
  private static final long D = 1_000_000_007;
  private static class SegTree {
    int n;
    long[] arr, tree;
    long[][] lazy;
    SegTree(long[] arr) {
      this.n = arr.length;
      this.arr = arr;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
      lazy = new long[treeSize][2];
      init(1, 0, n-1);
    }
    long init(int node, int start, int end) {
      lazy[node][0] = 1;
      if (start == end) return tree[node] = arr[start] % D;
      int mid = (start+end)>>1;
      return tree[node] = (init(node<<1, start, mid) + init(node<<1|1, mid+1, end))%D;
    }
    void updateLazy(int node, int start, int end) {
      if (lazy[node][0] == 1 && lazy[node][1] == 0) return;
      tree[node] = (lazy[node][0]*tree[node]%D + lazy[node][1]*(end-start+1)%D)%D;
      if (start != end) {
        lazy[node<<1][0] = lazy[node<<1][0]*lazy[node][0]%D;
        lazy[node<<1][1] = (lazy[node<<1][1]*lazy[node][0]%D + lazy[node][1])%D; 
        lazy[node<<1|1][0] = lazy[node<<1|1][0]*lazy[node][0]%D;
        lazy[node<<1|1][1] = (lazy[node<<1|1][1]*lazy[node][0]%D + lazy[node][1])%D; 
      }
      lazy[node][0] = 1;
      lazy[node][1] = 0;
    }
    void update(int node, int start, int end, int left, int right, long a, long b) {
      updateLazy(node, start, end);
      if (end < left || right < start) return;
      if (left <= start && end <= right) {
        lazy[node][0] = a;
        lazy[node][1] = b;
        updateLazy(node, start, end);
        return;
      }
      int mid = (start+end)>>1;
      update(node<<1, start, mid, left, right, a, b);
      update(node<<1|1, mid+1, end, left, right, a, b);
      tree[node] = (tree[node<<1] + tree[node<<1|1])%D;
    }
    void add(int left, int right, long val) {
      update(1, 0, n-1, left, right, 1, val);
    }
    void product(int left, int right, long val) {
      update(1, 0, n-1, left, right, val, 0);
    }
    void replace(int left, int right, long val) {
      update(1, 0, n-1, left, right, 0, val);
    }
    long get(int node, int start, int end, int left, int right) {
      updateLazy(node, start, end);
      if (end < left || right < start) return 0;
      if (left <= start && end <= right) return tree[node] % D;
      int mid = (start+end)>>1;
      return (get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right)) % D;
    }
    long get(int left, int right) {
      return get(1, 0, n-1, left, right) % D;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    long[] arr = new long[n+1];
    for (int i = 1; i <= n; i++) {
      arr[i] = Long.parseLong(st.nextToken()) % D;
    }
    SegTree sg = new SegTree(arr);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      if (type == 1) {
        long val = Long.parseLong(st.nextToken());
        sg.add(left, right, val);
      } else if (type == 2) {
        long val = Long.parseLong(st.nextToken());
        sg.product(left, right, val);
      } else if (type == 3) {
        long val = Long.parseLong(st.nextToken());
        sg.replace(left, right, val);
      } else {
        bw.write(Long.toString(sg.get(left, right)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
