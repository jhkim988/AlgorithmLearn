import java.io.*;
import java.util.*;

public class BOJ7469 {
  private static class MergeSortTree {
    int n, treeSize;
    int[] arr;
    int[][] tree;
    MergeSortTree(int n, int[] arr) {
      this.n = n;
      this.arr = arr;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize][];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        int[] leaf = { arr[start] };
        tree[node] = leaf;
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      int[] left = tree[node<<1];
      int[] right = tree[node<<1|1];
      int[] merge = new int[end-start+1];
      int ptr1 = 0;
      int ptr2 = 0;
      int ptr = 0;
      while (ptr1 < left.length && ptr2 < right.length) {
        if (left[ptr1] < right[ptr2]) merge[ptr++] = left[ptr1++];
        else merge[ptr++] = right[ptr2++];
      }
      while (ptr1 < left.length) merge[ptr++] = left[ptr1++];
      while (ptr2 < right.length) merge[ptr++] = right[ptr2++];
      tree[node] = merge;
    }
    int num(int node, int start, int end, int left, int right, int val) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return lowerbound(val, tree[node]);
      int mid = (start + end) >> 1;
      int leftChild = num(node<<1, start, mid, left, right, val);
      int rightChild = num(node<<1|1, mid+1, end, left, right, val);
      return leftChild + rightChild;
    }
    int query(int left, int right, int k) {
      if (left == right) return arr[left]; 
      int lo = -1_000_000_000;
      int hi = 1_000_000_000;
      while (lo + 1 < hi) {
        int mid = (lo + hi) >> 1;
        if (num(1, 0, n-1, left, right, mid) < k) {
          lo = mid;
        } else {
          hi = mid;
        }
      }
      return hi;
    }
  }
  static int lowerbound(int key, int[] arr) {
    int lo = -1;
    int hi = arr.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
  
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    MergeSortTree mt = new MergeSortTree(n, arr);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken())-1;
      int right = Integer.parseInt(st.nextToken())-1;
      int k = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(mt.query(left, right, k)));
      bw.newLine();
    }
    bw.flush();
  }
}
