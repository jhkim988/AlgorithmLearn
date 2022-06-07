import java.io.*;
import java.util.*;

public class BOJ16993 {
  static final int INF = Integer.MAX_VALUE/2;
  private static class Pair {
    int sum, lval, rval, val;
    Pair(int sum, int lval, int rval, int val) {
      this.sum = sum;
      this.lval = lval;
      this.rval = rval;
      this.val = val;
    }
  }
  private static class SegTree {
    int n;
    int[] arr, sum, leftMax, rightMax, tree;
    SegTree(int[] arr) {
      this.n = arr.length;
      this.arr = arr;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      sum = new int[treeSize];
      leftMax = new int[treeSize];
      rightMax = new int[treeSize];
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        sum[node] = leftMax[node] = rightMax[node] = tree[node] = arr[start];
        return;
      }
      int mid = (start+end)>>1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      sum[node] = sum[node<<1] + sum[node<<1|1];
      leftMax[node] = Integer.max(leftMax[node<<1], sum[node<<1] + leftMax[node<<1|1]);
      rightMax[node] = Integer.max(rightMax[node<<1|1], sum[node<<1|1] + rightMax[node<<1]);
      tree[node] = Integer.max(Integer.max(tree[node<<1], tree[node<<1|1]), rightMax[node<<1] + leftMax[node<<1|1]);
    }
    Pair get(int node, int start, int end, int left, int right) {
      if (right < start || end < left) return new Pair(0, -INF, -INF, -INF);
      if (left <= start && end <= right) return new Pair(sum[node], leftMax[node], rightMax[node], tree[node]);
      int mid = (start+end)>>1;
      Pair lnode = get(node<<1, start, mid, left, right);
      Pair rnode = get(node<<1|1, mid+1, end, left, right);
      int sval = lnode.sum + rnode.sum;
      int lval = Integer.max(lnode.lval, lnode.sum + rnode.lval);
      int rval = Integer.max(rnode.rval, rnode.sum + lnode.rval);
      int max = Integer.max(Integer.max(lnode.val, rnode.val), lnode.rval + rnode.lval);
      return new Pair(sval, lval, rval, max);
    }
    int get(int left, int right) {
      return get(1, 0, n-1, left, right).val;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n+1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    SegTree sg = new SegTree(arr);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(sg.get(left, right)));
      bw.newLine();
    }
    bw.flush();
  }
}
