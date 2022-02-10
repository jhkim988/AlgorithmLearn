import java.io.*;
import java.util.*;

public class BOJ10999 {
  private static class SegTreeLazy {
    int n, treeSize;
    long[] arr, tree, lazy;
    SegTreeLazy(long[] arr) {
      n = arr.length;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      this.arr = arr;
      tree = new long[treeSize];
      lazy = new long[treeSize];
      init(1, 0, n-1);
    }
    long init(int node, int start, int end) {
      if (start == end) return tree[node] = arr[start];
      int mid = (start + end) >> 1;
      return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
    }
    long get(int node, int start, int end, int left, int right) {
      updateLazy(node, start, end);
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node*2, start, mid, left, right) + get(node*2+1, mid+1, end, left, right);
    }
    void update(int node, int start, int end, int left, int right, long diff) {
      updateLazy(node, start, end);
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        tree[node] += diff*(end-start+1);
        if (start != end) {
          lazy[node*2] += diff;
          lazy[node*2+1] += diff;
        }
        return;
      }
      int mid = (start + end) >> 1;
      update(node*2, start, mid, left, right, diff);
      update(node*2+1, mid+1, end, left, right, diff);
      tree[node] = tree[node*2] + tree[node*2+1];
    }
    void updateLazy(int node, int start, int end) {
      if (lazy[node] != 0) {
        tree[node] += (end-start+1)*lazy[node];
        if (start != end) {
          lazy[node*2] += lazy[node];
          lazy[node*2+1] += lazy[node];
        }
        lazy[node] = 0;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
    long[] arr = new long[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Long.parseLong(br.readLine());
    }
    SegTreeLazy stz = new SegTreeLazy(arr);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken()) - 1;
      int right = Integer.parseInt(st.nextToken()) - 1;
      if (type == 1) { // update range
        long add = Long.parseLong(st.nextToken());
        stz.update(1, 0, n-1, left, right, add);
      } else { // get
        bw.write(Long.toString(stz.get(1, 0, n-1, left, right)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
