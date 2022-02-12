import java.io.*;

public class BOJ1280 {
  static final int sz = 200_000;
  static final long divisor = 1_000_000_007;
  private static class SegTree {
    int treeSize;
    long[] tree;
    SegTree(int n) {
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
    }
    void update(int node, int start, int end, int idx, long add) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] += add;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, add);
      update(node<<1|1, mid+1, end, idx, add);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    long get(int left, int right) {
      if (left > right) return 0;
      return get(1, 0, sz, left, right);
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    SegTree sum = new SegTree(sz+1);
    SegTree count = new SegTree(sz+1);
    long cost = 1;
    int pos = Integer.parseInt(br.readLine());
    sum.update(1, 0, sz, pos, pos);
    count.update(1, 0, sz, pos, 1);
    for (int i = 1; i < n; i++) {
      pos = Integer.parseInt(br.readLine());
      long left = count.get(0, pos-1) * pos - sum.get(0, pos-1);
      long right = sum.get(pos+1, sz) - count.get(pos+1, sz) * pos;
      cost *= (left + right) % divisor;
      cost %= divisor;
      sum.update(1, 0, sz, pos, pos);
      count.update(1, 0, sz, pos, 1);
    }
    bw.write(Long.toString(cost));
    bw.newLine();
    bw.flush();
  }
}
