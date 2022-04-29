import java.io.*;
import java.util.*;

public class BOJ16566 {
  private static class SegTree {
    int size, treeSize;
    int[] arr, tree;
    SegTree(int[] arr) {
      this.arr = arr;
      size = arr.length;
      treeSize = 1;
      while (treeSize < size) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, size-1);
    }
    void init(int node, int lo, int hi) {
      if (lo == hi) {
        tree[node] = lo;
        return;
      }
      int mid = (lo + hi) >> 1;
      init(node<<1, lo, mid);
      init(node<<1|1, mid+1, hi);
      if (arr[tree[node<<1]] < arr[tree[node<<1|1]]) {
        tree[node] = tree[node<<1|1];
      } else if (arr[tree[node<<1]] > arr[tree[node<<1|1]]) {
        tree[node] = tree[node<<1];
      } else {
        tree[node] = Integer.min(tree[node<<1], tree[node<<1|1]);
      }
    }
    int find(int node, int lo, int hi, int q) {
      if (arr[tree[node]] == 0) return -1;
      if (lo == hi) return lo;
      int mid = (lo + hi) >> 1;
      if (mid < q || arr[tree[node<<1]] == 0) return find(node<<1|1, mid+1, hi, q);
      if (q <= tree[node<<1]) return find(node<<1, lo, mid, q);
      int left = find(node<<1, lo, mid, q);
      int right = find(node<<1|1, mid+1, hi, q);
      if (left == -1) return right;
      return left;              
    }
    int find(int q) {
      return find(1, 0, size-1, q);
    }
    void update(int node, int lo, int hi, int idx) {
      if (idx < lo || hi < idx) return;
      if (lo == hi) {
        arr[idx]--;
        return;
      }
      int mid = (lo + hi) >> 1;
      update(node<<1, lo, mid, idx);
      update(node<<1|1, mid+1, hi, idx);
      if (arr[tree[node<<1]] < arr[tree[node<<1|1]]) {
        tree[node] = tree[node<<1|1];
      } else if (arr[tree[node<<1]] > arr[tree[node<<1|1]]) {
        tree[node] = tree[node<<1];
      } else {
        tree[node] = Integer.min(tree[node<<1], tree[node<<1|1]);
      }
    }
    void update(int q) {
      update(1, 0, size-1, q);
    }
  }
  private static class UnionFind {
    int[] id, sz, max;
    UnionFind(int n) {
      id = new int[n];
      sz = new int[n];
      max = new int[n];
      for (int i = 0; i < n; i++) {
        id[i] = i;
        sz[i] = 1;
        max[i] = i;
      }
    }
    int root(int i) {
      while (i != id[i]) {
        i = id[i] = id[id[i]];
      }
      return i;
    }
    void union(int p, int q) {
      int prt = root(p);
      int qrt = root(q);
      if (prt == qrt) return;
      if (sz[prt] < sz[qrt]) {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
        max[qrt] = Integer.max(max[prt], max[qrt]);
      } else {
        id[qrt] = prt;
        sz[prt] += qrt;
        max[prt] = Integer.max(max[prt], max[qrt]);
      }
    }
  }
  public static void main(String[] args) throws IOException {
    // useSegTree();
    useUnionFind();
  }
  static void useUnionFind() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[m];
    for (int i = 0; i < m; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);
    UnionFind uf = new UnionFind(m);
    boolean[] check = new boolean[m];
    st = new StringTokenizer(br.readLine());
    while (k-- > 0) {
      int req = Integer.parseInt(st.nextToken());
      int idx = upperbound(arr, req);
      if (check[idx]) {
        idx = uf.max[uf.root(idx)]+1;
      } 
      check[idx] = true;
      if (idx > 0 && check[idx-1]) uf.union(idx, idx-1);
      if (idx < m-1 && check[idx+1]) uf.union(idx, idx+1);
      bw.write(Integer.toString(arr[idx]));
      bw.newLine();
    }
    bw.flush();
  }
  static int upperbound(int[] arr, int key) {
    int lo = -1, hi = arr.length;
    while (lo+1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
  static void useSegTree() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n+1];
    while (m-- > 0) {
      int input = Integer.parseInt(st.nextToken());
      arr[input]++;
    }
    st = new StringTokenizer(br.readLine());
    SegTree sg = new SegTree(arr);
    while (k-- > 0) {
      int req = Integer.parseInt(st.nextToken());
      int res = sg.find(req+1);
      sg.update(res);
      bw.write(Integer.toString(res));
      bw.newLine();
    }
    bw.flush();
  }
}
