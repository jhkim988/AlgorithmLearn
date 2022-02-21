import java.io.*;
import java.util.*;

public class BOJ14419 {
  private static class SegTree {
    int n, treeSize;
    long[] arr;
    long[] positive, negative;
    SegTree(long[] arr) {
      this.n = arr.length;
      this.arr = arr;
      treeSize = 1;
      while (treeSize < arr.length) treeSize <<= 1;
      treeSize <<= 1;
      positive = new long[treeSize];
      negative = new long[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        if (arr[start] > 0) positive[node] = arr[start];
        if (arr[start] < 0) negative[node] = arr[start];
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      positive[node] = positive[node<<1] + positive[node<<1|1];
      negative[node] = negative[node<<1] + negative[node<<1|1];
    }
    void update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return;
      if (start == end) {
        arr[idx] += val;
        if (arr[idx] > 0) positive[node] = arr[start];
        else positive[node] = 0;
        if (arr[idx] < 0) negative[node] = arr[start];
        else negative[node] = 0;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
      positive[node] = positive[node<<1] + positive[node<<1|1];
      negative[node] = negative[node<<1] + negative[node<<1|1];
    }
    long get(int s, int t) {
      return -negative[1] * t - positive[1] * s;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
    int s = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(st.nextToken());
    long[] altitude = new long[n];
    int prev = 0;
    br.readLine();
    for (int i = 0; i < n; i++) {
      int input = Integer.parseInt(br.readLine());
      altitude[i] = input - prev;
      prev = input;
    }
    SegTree sg = new SegTree(altitude);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());
      sg.update(1, 0, n-1, left-1, value);
      sg.update(1, 0, n-1, right, -value);
      bw.write(Long.toString(sg.get(s, t)));
      bw.newLine();
    }
    bw.flush();
  }
}
