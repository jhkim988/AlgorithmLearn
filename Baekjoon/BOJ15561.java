import java.io.*;
import java.util.*;

public class BOJ15561 {
  private static final long INF = -10_000_000;
  private static class Node {
    long maxL, maxR, max, sum;
    Node() { maxL = maxR = max = sum = 0; }
    Node(long maxL, long maxR, long max, long sum) {
      this.maxL = maxL;
      this.maxR = maxR;
      this.max = max;
      this.sum = sum;
    }    
    void set(long v) { maxL = maxR = max = sum = v; }
    Node merge(Node other) {
      Node ret = new Node();
      ret.maxL = Long.max(this.maxL, this.sum + other.maxL);
      ret.maxR = Long.max(other.maxR, other.sum + this.maxR);
      ret.max = Long.max(Long.max(this.max, other.max), this.maxR + other.maxL);
      ret.sum = this.sum + other.sum;
      return ret;
    }
  }
  private static class SegTree {
    int n, treeSize;
    int[] arr;
    Node[] tree;
    SegTree(int[] arr) {
      n = arr.length;
      this.arr = arr;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new Node[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = new Node(arr[start], arr[start], arr[start], arr[start]);
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = tree[node<<1].merge(tree[node<<1|1]);
    }
    void update(int node, int start, int end, int index, long val) {
      if (start > index || end < index) return;
      if (start == end) {
        tree[node].set(val);
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, index, val);
      update(node<<1|1, mid+1, end, index, val);
      tree[node] = tree[node<<1].merge(tree[node<<1|1]);
    }
    Node get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return new Node(INF, INF, INF, 0);
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      Node leftChild = get(node<<1, start, mid, left, right);
      Node rightChild = get(node<<1|1, mid+1, end, left, right);
      return leftChild.merge(rightChild);
    }
    long get(int left, int right) {
      return get(1, 0, n-1, left, right).max;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
    int u = Integer.parseInt(st.nextToken());
    int v = Integer.parseInt(st.nextToken());
    int[] arr = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken())*u+v;
    }
    SegTree sg = new SegTree(arr);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (type == 0) {
        // get max
        long get = sg.get(a-1, b-1);
        bw.write(Long.toString(get-v));
        bw.newLine();
      } else {
        // update
        sg.update(1, 0, n-1, a-1, u*b+v);
      }
    }
    bw.flush();
  }
}
