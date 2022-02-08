import java.io.*;
import java.util.*;

public class BOJ12016 {
  private static class SegmentTree {
    // node: index of minimum value on [i, j]
    int n, treeSize;
    int[] arr, tree;
    SegmentTree(int[] arr) {
      this.n = arr.length+1;
      this.arr = new int[this.n];
      System.arraycopy(arr, 0, arr, 1, arr.length);
      this.arr[0] = Integer.MAX_VALUE;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    int init(int node, int start, int end) {
      if (start == end) return tree[node] = start;
      int mid = (start + end) >> 1;
      int leftChild = init(node*2, start, mid);
      int rightChild = init(node*2+1, mid+1, end);
      return tree[node] = arr[leftChild] < arr[rightChild] ? rightChild : leftChild; 
    }
    int update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return 0;
      if (start == end) {
        arr[idx] = val;
        return start;
      }
      int mid = (start + end) >> 1;
      int leftChild = update(node*2, start, mid, idx, val);
      int rightChild = update(node*2+1, mid+1, end, idx, val);
      return tree[node] = arr[leftChild] < arr[rightChild] ? rightChild : leftChild; 
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      int leftChild = get(node*2, start, mid, left, right);
      int rightChild = get(node*2+1, mid+1, end, left, right);
      return arr[leftChild] < arr[rightChild] ? rightChild : leftChild;
    }
    int removeMin() {
      int idx = tree[1];
      update(1, 0, n-1, idx, Integer.MAX_VALUE);
      return idx;
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

    SegmentTree sg = new SegmentTree(arr);
  }
}