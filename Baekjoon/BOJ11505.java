import java.io.*;
import java.util.*;

public class BOJ11505 {
  static final long DIVISOR = 1_000_000_007;
  private static class FenwickTree {
    int n;
    long[] arr;
    long[] tree;
    long[] nonzero;
    FenwickTree(int n, long[] arr) {
      this.n = n;
      this.arr = arr;
      this.tree = new long[n + 1];
      this.nonzero = new long[n + 1];
      Arrays.fill(tree, 1L);
      for (int i = 1; i <= n; i++) {
        update(i, this.arr[i - 1]);
      }
    }
    void update(int i, long num) {
      int j = i;
      if (num == 0) {
        while (i <= n) {
          tree[i] = (tree[i] * num) % DIVISOR;
          i += i & -i;
        } 
        return;
      }

      if (arr[i - 1] == 0) {
        System.arraycopy(nonzero, 0, tree, 0, n);
      }
      i = j;
      while (i <= n) {
        nonzero[i] = (nonzero[i] * num) % DIVISOR;
        i += i & -i;
      }
      i = j;
      while (i <= n) {
        tree[i] = (tree[i] * num) % DIVISOR;
        i += i & -i;
      }
    }
    long get(int i) {
      long val = 1L;
      while (i > 0) {
        val = (tree[i] * val) % DIVISOR;
        i -= i & -i;
      }
      return val;
    }
    long get(int left, int right) {
      return (get(right) * inverse(get(left - 1))) % DIVISOR;
    }

  }
  private static class SegmentTree {
    int n;
    int height;
    long[] arr;
    long[] tree;
    SegmentTree(int n, long[] arr) {
      this.n = n;
      this.arr = arr;
      while(1 << height < n) height++;
      int treeSize = 1 << (height + 1);
      this.tree = new long[treeSize];
      init(1, 0, n - 1);
    }
    long init(int node, int start, int end) {
      if (start == end) return tree[node] = arr[start];
      int mid = (start + end) / 2;
      long leftChild = init(node*2, start, mid);
      long rightChild = init(node*2+1, mid+1, end);
      return tree[node] = (leftChild * rightChild) % DIVISOR;
    }
    void update(int idx, long multi) {
      update(1, 0, n - 1, idx - 1, multi);
    }
    void update(int node, int start, int end, int idx, long multi) {
      if (start > idx || end < idx) return;
      if (start != end) {
        int mid = (start + end) / 2;
        update(node*2, start, mid, idx, multi);
        update(node*2+1, mid+1, end, idx, multi);
      } else {
        if (tree[node] == 0) {
          tree[node] = multi;
          return;
        }
      }
      tree[node] = (tree[node] * multi) % DIVISOR;
    }
    long get(int left, int right) {
      return get(1, 0, n - 1, left - 1, right - 1);
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 1;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) / 2;
      long leftChild = get(node*2, start, mid, left, right);
      long rightChild = get(node*2+1, mid+1, end, left, right);
      return (leftChild * rightChild) % DIVISOR;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
    long[] arr = new long[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Long.parseLong(br.readLine());
    }

    FenwickTree tree = new FenwickTree(n, arr);
    // SegmentTree tree = new SegmentTree(n, arr);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int idx = Integer.parseInt(st.nextToken());
        int change = Integer.parseInt(st.nextToken());
        long multi = arr[idx - 1] != 0 ? (change * inverse(arr[idx - 1])) % DIVISOR : change;
        arr[idx - 1] = change;
        tree.update(idx, multi);
        System.out.println("update: " + Arrays.toString(tree.tree));
        System.out.println("nonzero: " + Arrays.toString(tree.tree));
      } else {
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        bw.write(Long.toString(tree.get(left, right)));
        bw.newLine();
      }
    }
    bw.flush();
  } 
  static long inverse(long a) {
    return power(a, DIVISOR - 2);
  }
  static long power(long a, long p) {
    if (p == 0) return 1L;
    if (p == 1) return a;
    if (a == 0) return 0;
    if (a == 1) return 1;
    long half = power(a, p/2);
    long twoHalf = (half * half) % DIVISOR;
    if (p % 2 == 0) return twoHalf;
    else return (twoHalf * a) % DIVISOR;
  }
}
