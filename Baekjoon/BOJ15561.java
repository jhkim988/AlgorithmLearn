import java.io.*;
import java.util.*;

public class BOJ15561 {
  private static class SegTree {
    int n, treeSize;
    int[] arr;
    long[] maxL, maxR, max, sum;
    SegTree(int[] arr) {
      n = arr.length;
      this.arr = arr;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      maxL = new long[treeSize];
      maxR = new long[treeSize];
      max = new long[treeSize];
      sum = new long[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        maxL[node] = maxR[node] = max[node] = sum[node] = arr[start];
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      maxL[node] = Long.max(maxL[node<<1], sum[node<<1] + maxL[node<<1|1]);
      maxR[node] = Long.max(maxR[node<<1|1], sum[node<<1|1] + maxR[node<<1]);
      max[node] = Long.max(Long.max(maxL[node], maxR[node]), maxR[node<<1] + maxL[node<<1|1]);
      sum[node] = sum[node<<1] + sum[node<<1|1];
    }
    void update(int node, int start, int end, int index, int val) {
      if (start > index || end < index) return;
      if (start == end) {
        maxL[node] = maxR[node] = max[node] = sum[node] = arr[start] = val;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, index, val);
      update(node<<1|1, mid+1, end, index, val);
      maxL[node] = Long.max(maxL[node<<1], sum[node<<1] + maxL[node<<1|1]);
      maxR[node] = Long.max(maxR[node<<1|1], sum[node<<1|1] + maxR[node<<1]);
      max[node] = Long.max(Long.max(maxL[node], maxR[node]), maxR[node<<1] + maxL[node<<1|1]);
      sum[node] = sum[node<<1] + sum[node<<1|1];
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return Long.MIN_VALUE;
      if (left <= start && end <= end) return max[node];
      int mid = (start + end) >> 1;
      return Long.max(get(node<<1, start, mid, left, right), get(node<<1|1, mid+1, end, left, right));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
    int u = Integer.parseInt(st.nextToken());
    int v = Integer.parseInt(st.nextToken());
    int[] arr = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    SegTree sg = new SegTree(arr);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (type == 0) {
        // get max
        
      } else {
        // update
      }
    }
  }
}
