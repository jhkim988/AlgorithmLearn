import java.io.*;
import java.util.*;

public class BOJ3006 {
  private static class SegTree {
    int treeSize;
    int[] tree;
    SegTree(int n) {
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
    }
    void update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] = val;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
  }
  private static class SegTree_ {
    int treeSize;
    int[] tree;
    SegTree_(int n) {
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
    }
    void update(int node, int start, int end, int left, int right, int val) {
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        tree[node] += val;
        return;
      }
      int mid = (start + end) >> 1;
      update(node*2, start, mid, left, right, val);
      update(node*2+1, mid+1, end, left, right, val);
    }
    int get(int node, int start, int end, int idx) {
      if (start > idx || end < idx) return 0;
      if (start == end) return tree[node];
      int mid = (start + end) >> 1;
      return tree[node] + get(node*2, start, mid, idx) + get(node*2+1, mid+1, end, idx);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    HashMap<Integer, Integer> hm = new HashMap<>(); // <value, index>
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
      hm.put(arr[i], i);
    }
    SegTree sgLeft = new SegTree(n);
    SegTree sgRight = new SegTree(n);
    int[] numLargeLeft = new int[n];
    int[] numSmallRight = new int[n];
    for (int i = 0; i < n; i++) {
      numLargeLeft[i] = sgLeft.get(1, 0, n-1, arr[i]-1, n-1);
      sgLeft.update(1, 0, n-1, arr[i]-1, 1);
    }
    for (int i = n-1; i >= 0; i--) {
      numSmallRight[i] = sgRight.get(1, 0, n-1, 0, arr[i]-1);
      sgRight.update(1, 0, n-1, arr[i]-1, 1);
    }

    int ptr1 = 1;
    int ptr2 = n;
    SegTree_ sgLeft_ = new SegTree_(n);
    SegTree_ sgRight_ = new SegTree_(n);
    for (int i = 1; i <= n; i++) {
      if ((i & 1) == 1) {
        int idx = hm.get(ptr1++);
        int minus = sgRight_.get(1, 0, n-1, idx);
        sgLeft_.update(1, 0, n-1, 0, idx, -1);
        bw.write(Integer.toString(numLargeLeft[idx] + minus));
      } else {
        int idx = hm.get(ptr2--);
        int minus = sgLeft_.get(1, 0, n-1, idx);
        sgRight_.update(1, 0, n-1, idx, n-1, -1);
        bw.write(Integer.toString(numSmallRight[idx] + minus));
      }
      bw.newLine();
    }
    bw.flush();
  }  
}
