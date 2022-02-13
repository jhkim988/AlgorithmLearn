import java.io.*;
import java.util.*;

public class BOJ12844 {
  private static class SegTreeLazy {
    int n, treeSize;
    int[] arr, tree, lazy;
    SegTreeLazy(int[] arr) {
      this.n = arr.length;
      this.arr = arr;
      treeSize = 1;
      while (treeSize < arr.length) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      lazy = new int[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = arr[start];
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = tree[node<<1] ^ tree[node<<1|1];
    }
    void updateLazy(int node, int start, int end) {
      if (lazy[node] == 0) return;
      tree[node] ^= ((end-start+1) & 1) == 0 ? 0 : lazy[node];
      if (start != end) {
        lazy[node<<1] ^= lazy[node];
        lazy[node<<1|1] ^= lazy[node];
      }
      lazy[node] = 0;
    }
    void update(int node, int start, int end, int left, int right, int k) {
      updateLazy(node, start, end);
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        tree[node] ^= ((end-start+1) & 1) == 0 ? 0 : k;
        if (start != end) {
          lazy[node<<1] ^= k;
          lazy[node<<1|1] ^= k;
        }
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, left, right, k);
      update(node<<1|1, mid+1, end, left, right, k);
      tree[node] = tree[node<<1] ^ tree[node<<1|1];
    }
    int get(int node, int start, int end, int left, int right) {
      updateLazy(node, start, end);
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) ^ get(node<<1|1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    SegTreeLazy sg = new SegTreeLazy(arr);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      if (right < left) {
        int tmp = left;
        left = right;
        right = tmp;
      }
      if (type == 1) {
        int k = Integer.parseInt(st.nextToken());
        sg.update(1, 0, n-1, left, right, k);
      } else {
        bw.write(Integer.toString(sg.get(1, 0, n-1, left, right)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
