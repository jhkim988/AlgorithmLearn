import java.io.*;
import java.util.*;

public class BOJ1395 {
  private static class SegTreeLazy {
    int treeSize;
    int[] tree, lazy;
    SegTreeLazy(int n) {
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      lazy = new int[treeSize];
    }
    void updateLazy(int node, int start, int end) {
      if (lazy[node] == 0) return;
      tree[node] = (end-start+1) - tree[node];
      lazy[node] = 0;
      if (start == end) return;
      lazy[node*2] = ~lazy[node*2];
      lazy[node*2+1] = ~lazy[node*2+1];
    }
    void update(int node, int start, int end, int left, int right) {
      updateLazy(node, start, end);
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        tree[node] = end-start+1 - tree[node];
        if (start == end) return;
        lazy[node*2] = ~lazy[node*2];
        lazy[node*2+1] = ~lazy[node*2+1];
        return;
      }
      int mid = (start + end) >> 1;
      update(node*2, start, mid, left, right);
      update(node*2+1, mid+1, end, left, right);
      tree[node] = tree[node*2] + tree[node*2+1];
    }
    int get(int node, int start, int end, int left, int right) {
      updateLazy(node, start, end);
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node*2, start, mid, left, right) + get(node*2+1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    SegTreeLazy sg = new SegTreeLazy(n);
    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken())-1;
      int right = Integer.parseInt(st.nextToken())-1;
      if (type == 0) {
        sg.update(1, 0, n-1, left, right);
      } else {
        bw.write(Integer.toString(sg.get(1, 0, n-1, left, right)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}