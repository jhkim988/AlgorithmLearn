import java.io.*;
import java.util.*;

public class BOJ14245 {
  private static class SegTree {
    int treeSize;
    int[] arr, tree;
    SegTree(int[] arr) {
      this.arr = arr;
      treeSize = 1;
      while (treeSize < arr.length) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
    }
    void update(int node, int start, int end, int left, int right, int c) {
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        tree[node] ^= c;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, left, right, c);
      update(node<<1|1, mid+1, end, left, right, c);
    }
    int get(int node, int start, int end, int k) {
      if (start == end) return arr[start] ^ tree[node];
      int mid = (start + end) >> 1;
      if (mid >= k) {
        return get(node<<1, start, mid, k) ^ tree[node]; 
      } else {
        return get(node<<1|1, mid+1, end, k) ^ tree[node];
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    SegTree sg = new SegTree(arr);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        sg.update(1, 0, n-1, a, b, c);
      } else {
        int a = Integer.parseInt(st.nextToken());
        bw.write(Integer.toString(sg.get(1, 0, n-1, a)));
        bw.newLine();
      }
      bw.flush();
    }
  }
}
