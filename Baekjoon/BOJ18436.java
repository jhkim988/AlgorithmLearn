import java.io.*;
import java.util.*;

public class BOJ18436 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private static class SegmentTree {
    // segment tree node: number of odd element
    int n, height, treeSize;
    int[] arr, tree;
    SegmentTree(int n, int[] input) {
      this.n = n;
      this.arr = input;
      while (1 << height < n) height++;
      treeSize = 1 << (height + 1);
      tree = new int[treeSize];
      init(1, 0, n - 1);
    }
    int init(int node, int start, int end) {
      if (start == end) return tree[node] = arr[start] % 2 == 1 ? 1 : 0;
      int mid = (start + end) / 2;
      int leftChild = init(node*2, start, mid);
      int rightChild = init(node*2+1, mid+1, end);
      return tree[node] = leftChild + rightChild;
    }
    void update(int idx, int val) {
      if (arr[idx - 1] % 2 != val % 2) {
        update(1, 0, n - 1, idx - 1, val % 2 == 0 ? -1 : 1);
      }
      arr[idx - 1] = val;
    }
    void update(int node, int start, int end, int idx, int diff) {
      if (start > idx || end < idx) return;
      if (start != end) {
        int mid = (start + end) / 2;
        update(node*2, start, mid, idx, diff);
        update(node*2+1, mid+1, end, idx, diff);
      }
      tree[node] += diff;
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) / 2;
      int leftChild = get(node*2, start, mid, left, right);
      int rightChild = get(node*2+1, mid+1, end, left, right);
      return leftChild + rightChild;
    }
    void query(String q) throws IOException {
      StringTokenizer st = new StringTokenizer(q);
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int idx = Integer.parseInt(st.nextToken());
        int val = Integer.parseInt(st.nextToken());
        update(idx, val);
      } else {
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        int getOdd = get(1, 0, n - 1, left - 1, right - 1);
        bw.write(Integer.toString(type == 2 ? right - left + 1 - getOdd : getOdd));
        bw.newLine();
      }
    }
  }
  public static void main(String[] args) throws IOException {
    int n = Integer.parseInt(br.readLine());
    int[] input = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }
    SegmentTree tree = new SegmentTree(n, input);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      tree.query(br.readLine());
    }
    bw.flush();
  }
}
