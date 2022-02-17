import java.io.*;
import java.util.*;

public class BOJ6218 {
  private static class SegTree {
    int n, treeSize;
    int[] arr, min, max;
    SegTree(int[] arr) {
      this.n = arr.length;
      this.arr = arr;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      min = new int[treeSize];
      max = new int[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        min[node] = max[node] = arr[start];
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      max[node] = Integer.max(max[node<<1], max[node<<1|1]);
      min[node] = Integer.min(min[node<<1], min[node<<1|1]);
    }
    int getMin(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return Integer.MAX_VALUE;
      if (left <= start && end <= right) return min[node];
      int mid = (start + end) >> 1;
      return Integer.min(getMin(node<<1, start, mid, left, right), getMin(node<<1|1, mid+1, end, left, right));
    }
    int getMax(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return Integer.MIN_VALUE;
      if (left <= start && end <= right) return max[node];
      int mid = (start + end) >> 1;
      return Integer.max(getMax(node<<1, start, mid, left, right), getMax(node<<1|1, mid+1, end, left, right));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    SegTree sg = new SegTree(arr);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken()) - 1;
      int right = Integer.parseInt(st.nextToken()) - 1;
      int diff = sg.getMax(1, 0, n-1, left, right) - sg.getMin(1, 0, n-1, left, right);
      bw.write(Integer.toString(diff));
      bw.newLine();
    }
    bw.flush();
  }
}
