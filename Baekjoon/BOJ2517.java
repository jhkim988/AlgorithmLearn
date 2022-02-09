import java.io.*;
import java.util.*;

public class BOJ2517 {
  private static class SegmentTree {
    int n, treeSize;
    int[] arr, tree;
    SegmentTree(int n) {
      arr = new int[n];
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
    }
    int update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) return tree[node] = arr[idx] += val;
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
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    // int[] compression = coordinate_compression_myTry(n, arr);
    int[] compression = coordinate_compression(n, arr);
    SegmentTree sg = new SegmentTree(n);
    for (int i = 0; i < n; i++) {
      bw.write(Integer.toString(sg.get(1, 0, n-1, compression[i] + 1, n-1) + 1));
      bw.newLine();
      sg.update(1, 0, n-1, compression[i], 1);
    }
    bw.flush();
  }
  static int[] coordinate_compression_myTry(int n, int[] arr) {
    int[] copy = new int[n];
    System.arraycopy(arr, 0, copy, 0, n);
    Arrays.sort(copy);
    HashMap<Integer, Integer> hm = new HashMap<>();
    for (int i = 0; i < n; i++) {
      hm.put(copy[i], i);
    }
    int[] compression = new int[n];
    for (int i = 0; i < n; i++) {
      compression[i] = hm.get(arr[i]);
    }
    return compression;
  }
  static int[] coordinate_compression(int n, int[] arr) {
    // sort
    // remove duplicate element(gurantee in this problem)
    // use binary search
    int[] copy = new int[n];
    System.arraycopy(arr, 0, copy, 0, n);
    Arrays.sort(copy);
    int[] compression = new int[n];
    for (int i = 0; i < n; i++) {
      compression[i] = lowerbound(arr[i], copy);
    }
    return compression;
  }
  static int lowerbound(int key, int[] arr) {
    int lo = -1;
    int hi = arr.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
