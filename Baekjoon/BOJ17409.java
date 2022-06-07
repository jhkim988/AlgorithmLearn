import java.io.*;
import java.util.*;

public class BOJ17409 {
  static final long D = 1_000_000_009;
  private static class SegTree {
    int n;
    int[] sum;
    SegTree(int n) {
      this.n = n;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      sum = new int[treeSize];
    }
    void update(int node, int start, int end, int idx) {
      if (idx < start || end < idx) return;
      if (start == end) {
        sum[node]++;
        return;
      }
      int mid = (start+end)>>1;
      update(node<<1, start, mid, idx);
      update(node<<1|1, mid+1, end, idx);
      sum[node] = sum[node<<1] + sum[node<<1|1];
    }
    int get(int node, int start, int end, int left, int right) {
      if (start == end) return sum[node];
      if (end < left || right < start) return 0;
      int mid = (start+end)>>1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
    void update(int idx) {
      update(1, 0, n-1, idx);
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
    int k = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n+1];
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    SegTree sg = new SegTree(n+1);
    int[] nbig = new int[n+1];
    for (int i = n; i >= 1; i--) {
      nbig[i] = sg.get(i+1, n);
      sg.update(arr[i]);
    }

  }
}
