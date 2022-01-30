import java.io.*;
import java.util.*;

public class BOJ9345 {
  private static class SegmentTree {
    int n, height, treeSize;
    int[] arr;
    int[] min, max;
    SegmentTree(int n) {
      this.n = n;
      while (1 << height < n) height++;
      treeSize = 1 << (height + 1);
      arr = new int[n];
      for (int i = 0; i < n; i++) arr[i] = i;
      min = new int[treeSize];
      max = new int[treeSize];
      init(1, 0, n - 1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        min[node] = arr[start];
        max[node] = arr[start];
        return;
      }
      int mid = (start + end) / 2;
      init(node*2, start, mid);
      init(node*2+1, mid+1, end);
      min[node] = Integer.min(min[node*2], min[node*2+1]);
      max[node] = Integer.max(max[node*2], max[node*2+1]);
    }
    void update(int a, int b) {
      int valA = arr[a];
      int valB = arr[b];
      arr[a] = valB;
      arr[b] = valA;
      update(1, 0, n - 1, a, valB);
      update(1, 0, n - 1, b, valA);
    }
    void update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return;
      if (start == end) {
        min[node] = val;
        max[node] = val;
        return;
      }
      int mid = (start + end) / 2;
      update(node*2, start, mid, idx, val);
      update(node*2+1, mid+1, end, idx, val);
      min[node] = Integer.min(min[node*2], min[node*2+1]);
      max[node] = Integer.max(max[node*2], max[node*2+1]);
    }
    boolean get(int left, int right) {
      return getMin(1, 0, n - 1, left, right) == left && getMax(1, 0, n - 1, left, right) == right;
    }
    int getMin(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return Integer.MAX_VALUE;
      if (left <= start && end <= right) return min[node];
      int mid = (start + end) / 2;
      int leftChild = getMin(node*2, start, mid, left, right);
      int rightChild = getMin(node*2+1, mid+1, end, left, right);
      return Integer.min(leftChild, rightChild);
    }
    int getMax(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return Integer.MIN_VALUE;
      if (left <= start && end <= right) return max[node];
      int mid = (start + end) / 2;
      int leftChild = getMax(node*2, start, mid, left, right);
      int rightChild = getMax(node*2+1, mid+1, end, left, right);
      return Integer.max(leftChild, rightChild);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      SegmentTree tree = new SegmentTree(n);
      while (k-- > 0) {
        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        if (q == 0) {
          tree.update(a, b);
        } else {
          bw.write(tree.get(a, b) ? "YES\n" : "NO\n");
        }
      }
    }
    bw.flush();
  }
}
