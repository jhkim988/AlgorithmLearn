import java.io.*;
import java.util.*;

public class BOJ12899 {
  private static class SegmentTree {
    int n, height, treeSize;
    int[] count, tree;
    SegmentTree(int n) {
      this.n = n + 1;
      count = new int[this.n];
      while (1 << height < this.n) height++;
      treeSize = 1 << (height + 1);
      tree = new int[treeSize];
    }
    void update(int x, int add) {
      count[x] += add;
      update(1, 0, n - 1, x, add);
    }
    void update(int node, int start, int end, int x, int add) {
      if (start > x || end < x) return;
      if (start != end) {
        int mid = (start + end) / 2;
        update(node*2, start, mid, x, add);
        update(node*2+1, mid+1, end, x, add);  
      }
      tree[node] += add;
    }
    int get(int k) {
      return get(1, 0, n - 1, k);
    }
    int get(int node, int start, int end, int k) {
      if (start == end) {
        tree[node]--;
        return start;
      }
      int result = 0;
      int mid = (start + end) / 2;
      if (tree[node*2] >= k) {
        result =  get(node*2, start, mid, k);
      } else {
        result = get(node*2+1, mid + 1, end, k - tree[node*2]);
      }
      tree[node]--;
      return result;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    final int LIM = 2_000_000;
    SegmentTree tree = new SegmentTree(LIM);
    while (n-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      if (type == 1) {
        tree.update(x, 1);
      } else {
        bw.write(Integer.toString(tree.get(x)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
