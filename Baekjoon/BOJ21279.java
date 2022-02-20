import java.io.*;
import java.util.*;

public class BOJ21279 {
  private static class Pair {
    int x, y;
    long v;
    Pair(int x, int y, long v) {
      this.x = x;
      this.y = y;
      this.v = v;
    }
  }
  private static class SegTree {
    int n, treeSize;
    long[] tree;
    SegTree(int n) {
      this.n = n;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
    }
    void update(int node, int start, int end, int idx, long val) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] += val;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
    int find(long k) {
      if (tree[1] <= k) return n-1;
      int lo = -1, hi = n;
      while (lo + 1 < hi) {
        int mid = (lo + hi) >> 1;
        if (get(1, 0, n-1, 0, mid) <= k) {
          lo = mid;
        } else {
          hi = mid;
        }
      }
      return lo;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int len = 500_000;
    ArrayList<Pair> arr = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      long v = Long.parseLong(st.nextToken());
      arr.add(new Pair(x, y, v));
    }
    Collections.sort(arr, (a, b) -> a.y!=b.y ? a.y-b.y : a.x-b.x);
    SegTree count = new SegTree(len);
    SegTree value = new SegTree(len);
    long max = 0;
    for (int ptr = 0; ptr < n;) {
      int y = arr.get(ptr).y;
      while (ptr < n && arr.get(ptr).y == y) {
        Pair p = arr.get(ptr++);
        count.update(1, 0, len-1, p.x, 1);
        value.update(1, 0, len-1, p.x, p.v);
      }
      int idx = count.find(c);
      if (idx < 0) break;
      max = Long.max(max, value.get(1, 0, len-1, 0, idx));
    }
    bw.write(Long.toString(max));
    bw.newLine();
    bw.flush();
  }
}
