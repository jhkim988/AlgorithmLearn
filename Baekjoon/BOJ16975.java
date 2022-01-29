import java.io.*;
import java.util.*;

public class BOJ16975 {
  private static class SegmentTree {
    int n, height, treeSize;
    long[] tree;
    SegmentTree(int n) {
      this.n = n;
      while (1 << height < n) height++;
      treeSize = 1 << (height + 1);
      tree = new long[treeSize];
    }
    void update(int left, int right, int k) {
      update(1, 0, n - 1, left - 1, right - 1, k);
    }
    void update(int node, int start, int end, int left, int right, int k) {
      if (start == left && end == right) {
        tree[node] += k;
        return;
      }
      int mid = (start + end) / 2;
      if (mid < left) {
        update(node*2+1, mid+1, end, left, right, k);
      } else if (left <= mid && mid + 1 <= right) {
        update(node*2, start, mid, left, mid, k);
        update(node*2+1, mid+1, end, mid+1, right, k);
      } else if (right < mid + 1) {
        update(node*2, start, mid, left, right, k);
      }
    }
    long get(int idx) {
      return get(1, 0, n - 1, idx - 1);
    }
    long get(int node, int start, int end, int idx) {
      if (end < idx || start > idx) return 0;
      if (start == end) return tree[node];
      int mid = (start + end) / 2;
      long val = 0;
      if (idx <= mid) {
        val = get(node*2, start, mid, idx);
      } else {
        val = get(node*2+1, mid+1, end, idx);
      }
      return tree[node] + val;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] input = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }
    SegmentTree tree = new SegmentTree(n);
    int q = Integer.parseInt(br.readLine());
    while(q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        tree.update(left, right, k);
      } else {
        int idx = Integer.parseInt(st.nextToken());
        bw.write(Long.toString(input[idx - 1] + tree.get(idx)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
