import java.io.*;
import java.util.*;

public class BOJ3653 {
  private static class SegmentTree {
    int n, treeSize, ptr;
    int[] arr, index, tree;
    SegmentTree(int[] arr, int m) {
      this.arr = arr;
      this.n = arr.length;
      this.ptr = m - 1;
      index = new int[n + 1];
      for (int i = 1; i <= n; i++) {
        index[i] = m - 1 + i;
      }
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    int init(int node, int start, int end) {
      if (start == end) return tree[node] = arr[start];
      int mid = (start + end) >> 1;
      int leftChild = init(node*2, start, mid);
      int rightChild = init(node*2+1, mid+1, end);
      return tree[node] = leftChild + rightChild;
    }
    int update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) return tree[node] = arr[idx] = val;
      int mid = (start + end) >> 1;
      int leftChild = update(node*2, start, mid, idx, val);
      int rightChild = update(node*2+1, mid+1, end, idx, val);
      return tree[node] = leftChild + rightChild;
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      int leftChild = get(node*2, start, mid, left, right);
      int rightChild = get(node*2+1, mid+1, end, left, right);
      return leftChild + rightChild;
    }
    int query(int v) {
      int val = get(1, 0, n-1, 0, index[v] - 1);
      update(1, 0, n-1, index[v], 0);
      index[v] = ptr;
      update(1, 0, n-1, ptr--, 1);
      return val;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int[] arr = new int[n + m];
      for (int i = m; i < arr.length; i++) {
        arr[i] = 1;
      }
      SegmentTree sg = new SegmentTree(arr, m);
      st = new StringTokenizer(br.readLine());
      while (m-- > 0) {
        int q = Integer.parseInt(st.nextToken());
        bw.write(Integer.toString(sg.query(q)));
        if (m > 0) bw.write(' ');
      }
      bw.newLine();
    }
    bw.flush();
  }
}