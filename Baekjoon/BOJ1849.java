import java.io.*;
import java.util.*;

public class BOJ1849 {
  private static class SegmentTree {
    int n, treeSize;
    int[] arr, tree;
    SegmentTree(int n) {
      this.n = n;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      arr = new int[n];
      Arrays.fill(arr, 1);
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
    // int query_TLE(int val) {
    //   // O(N (logN)^2): TLE
    //   int lo = -1;
    //   int hi = n;
    //   while (lo + 1 < hi) {
    //     int mid = (lo + hi) >> 1;
    //     if (get(1, 0, n-1, 0, mid) <= val) {
    //       lo = mid;
    //     } else {
    //       hi = mid;
    //     }
    //   }
    //   update(1, 0, n-1, hi, 0);
    //   return hi;
    // }
    int query(int val) {
      int idx = query(1, 0, n-1, val+1);
      update(1, 0, n-1, idx, 0);
      return idx;
    }
    int query(int node, int start, int end, int val) {
      if (start == end) return start;
      int result;
      int mid = (start + end) >> 1;
      if (tree[node*2] >= val) {
        result = query(node*2, start, mid, val);
      } else {
        result = query(node*2+1, mid+1, end, val - tree[node*2]);
      }
      return result;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] answer = new int[n];
    SegmentTree sg = new SegmentTree(n);
    for (int i = 1; i <= n; i++) {
      int q = Integer.parseInt(br.readLine());
      answer[sg.query(q)] = i;
    }
    for (int seq : answer) {
      bw.write(Integer.toString(seq));
      bw.newLine();
    }
    bw.flush();
  }
}
