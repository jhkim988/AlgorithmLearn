import java.io.*;
import java.util.*;

public class BOJ11505 {
  static final long DIVISOR = 1_000_000_007;
  private static class FenwickTree {
    int n;
    long[] arr;
    long[] tree;
    long[] numzero;
    FenwickTree(int n, long[] arr) {
      this.n = n;
      this.arr = arr;
      this.tree = new long[n + 1];
      this.numzero = new long[n + 1];
      Arrays.fill(tree, 1L);
      updateInit();
    }
    void updateInit() {
      for (int k = 1; k <= n; k++) {
        int bit = k;
        if (arr[k - 1] == 0) {
          while (bit <= n) {
            numzero[bit]++;
            bit += bit & -bit;
          }
        } else {
          while (bit <= n) {
            tree[bit] = (tree[bit] * arr[k - 1]) % DIVISOR;
            bit += bit & -bit;
          }
        } 
      }
    }
    void update(int i, long num) {
      long multi = (num * inverse(arr[i - 1])) % DIVISOR;
      if (num != 0) {
        updateTree(i, multi);
        if (getNumZero(i, i) > 0) updateNumZero(i, -1);
      } else {
        updateNumZero(i, 1);
      }
      if (num != 0) arr[i - 1] = num;
    }
    void updateTree(int i, long num) {
      while (i <= n) {
        tree[i] = (tree[i] * num) % DIVISOR;
        i += i & -i;
      }
    }
    void updateNumZero(int i, int num) {
      while (i <= n) {
        numzero[i] += num;
        i += i & -i;
      }
    }
    long get(int left, int right) {
      if (getNumZero(left, right) > 0) return 0;
      return (get(right) * inverse(get(left - 1))) % DIVISOR;
    }
    long get(int range) {
      long val = 1;
      while (range > 0) {
        val = (val * tree[range]) % DIVISOR;
        range -= range & -range;
      }
      return val;
    }
    int getNumZero(int range) {
      int num0 = 0;
      while (range > 0) {
        num0 += numzero[range];
        range -= range & -range;
      }
      return num0;
    }
    int getNumZero(int left, int right) {
      return getNumZero(right) - getNumZero(left - 1);
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
    void update(int idx, long change) {
      arr[idx - 1] = change;
      update(1, 0, n - 1, idx - 1, change);
    }
    long update(int node, int start, int end, int idx, long change) {
      if (start > idx || end < idx) return tree[node];
      if (start != end) {
        int mid = (start + end) / 2;
        long leftChild = update(node*2, start, mid, idx, change);
        long rightChild = update(node*2+1, mid+1, end, idx, change);
        return tree[node] = (leftChild * rightChild) % DIVISOR;
      } else {
        return tree[node] = change;
      }
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

    // FenwickTree tree = new FenwickTree(n, arr);
    SegmentTree tree = new SegmentTree(n, arr);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int idx = Integer.parseInt(st.nextToken());
        int change = Integer.parseInt(st.nextToken());
        tree.update(idx, change);
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
