import java.io.*;
import java.util.*;

public class BOJ1275 {
  private static class SegmentTree {
    int n, treeSize;
    long[] arr;
    long[] tree;
    SegmentTree(long[] arr) {
      this.n = arr.length;
      this.arr = arr;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
      init(1, 0, n-1);
    }
    long init(int node, int start, int end) {
      if (start == end) return tree[node] = arr[start];
      int mid = (start + end) >> 1;
      return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
    }
    long update(int node, int start, int end, int idx, long val) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) return tree[node] = arr[idx] = val;
      int mid = (start + end) >> 1;
      return tree[node] = update(node*2, start, mid, idx, val) + update(node*2+1, mid+1, end, idx, val);
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
    long[] arr = new long[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n ; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    } 
    SegmentTree sg = new SegmentTree(arr);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken()) - 1;
      int right = Integer.parseInt(st.nextToken()) - 1;
      if (right < left) {
        int tmp = right;
        right = left;
        left = tmp;
      }
      int idx = Integer.parseInt(st.nextToken()) - 1;
      long val = Long.parseLong(st.nextToken());
      bw.write(Long.toString(sg.get(1, 0, n-1, left, right)));
      bw.newLine();
      sg.update(1, 0, n-1, idx, val);
    }
    bw.flush();
  }
}