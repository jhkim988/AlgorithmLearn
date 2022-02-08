import java.io.*;
import java.util.*;

public class BOJ9426 {
  private static class SegmentTreeSum{
    final int MAX = 65536;
    int treeSize;
    int[] arr, tree;
    SegmentTreeSum() {
      arr = new int[MAX];
      treeSize = 1 << 17;
      tree = new int[treeSize];
    }
    void update(int c, int val) {
      update(1, 0, MAX-1, c, val);
    }
    int update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) return tree[node] = (arr[idx] += val);
      int mid = (start + end) >> 1;
      int leftChild = update(node*2, start, mid, idx, val);
      int rightChild = update(node*2+1, mid+1, end, idx, val);
      return tree[node] = leftChild + rightChild;
    }
    int median(int node, int start, int end, int k) {
      if (start == end) return start;
      int mid = (start + end) >> 1;
      if (tree[node*2] >= k) {
        return median(node*2, start, mid, k);
      } else {
        return median(node*2+1, mid+1, end, k - tree[node*2]);
      }
    }
    int median(int k) {
      return median(1, 0, MAX-1, k);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    bw.write(Long.toString(useSegmentTree(n, k, arr)));
    bw.newLine();
    bw.flush();
  }
  static long useSegmentTree(int n, int k, int[] arr) {
    SegmentTreeSum sgm = new SegmentTreeSum();
    int remove = 0;
    long sum = 0;
    for (int i = 0; i < k; i++) {
      sgm.update(arr[i], 1);
    }
    sum += sgm.median((k+1)/2);
    sgm.update(arr[remove++], -1);
    for (int i = k; i < n; i++) {
      sgm.update(arr[i], 1);
      sum += sgm.median((k+1)/2);
      sgm.update(arr[remove++], -1);
    }
    return sum;
  }
}
