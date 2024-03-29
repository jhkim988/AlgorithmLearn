import java.io.*;
import java.util.*;

public class BOJ17409 {
  static final long D = 1_000_000_007;
  private static class SegTree {
    int n;
    long[] sum;
    SegTree(int n) {
      this.n = n;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      sum = new long[treeSize];
    }
    void update(int node, int start, int end, int idx, long val) {
      if (idx < start || end < idx) return;
      if (start == end) {
        sum[node] = val % D;
        return;
      }
      int mid = (start+end)>>1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
      sum[node] = (sum[node<<1] + sum[node<<1|1]) % D;
    }
    long get(int node, int start, int end, int left, int right) {
      if (end < left || right < start) return 0;
      if (left <= start && end <= right) return sum[node] % D;
      int mid = (start+end)>>1;
      return (get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right)) % D;
    }
    void update(int idx, long val) {
      update(1, 0, n-1, idx, val % D);
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
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n+1];
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    SegTree[] sgs = new SegTree[k+1];
    for (int l = 1; l <= k; l++) {
      sgs[l] = new SegTree(n+1);
    }
    for (int i = 1; i <= n; i++) {
      for (int l = 1; l <= k; l++) {
        if (l == 1) {
          sgs[l].update(arr[i], 1);
        } else {
          sgs[l].update(arr[i], sgs[l-1].get(0, arr[i]-1));
        }
      }
    }
    bw.write(Long.toString(sgs[k].get(1, n)));
    bw.newLine();
    bw.flush();
  }
}
