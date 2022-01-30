import java.io.*;
import java.util.*;

public class BOJ1168 {
  private static class SegmentTree {
    int n, height, treeSize;
    int[] count, tree;
    SegmentTree(int n) {
      this.n = n;
      while (1 << height < n) height++;
      treeSize = 1 << (height + 1);
      count = new int[n];
      Arrays.fill(count, 1);
      tree = new int[treeSize];
      init(1, 0, n - 1);
    }
    int init(int node, int start, int end) {
      if (start == end) return tree[node] = 1;
      int mid = (start + end) / 2;
      return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
    }
    int get(int node, int start, int end, int k) {
      if (start == end) {
        tree[node]--;
        return start;
      }
      int result = 0;
      int mid = (start + end) / 2;
      if (tree[node*2] >= k) {
        result = get(node*2, start, mid, k);
      } else {
        result = get(node*2+1, mid+1, end, k - tree[node*2]);
      }
      tree[node]--;
      return result;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    if (n == 1) {
      bw.write("<1>\n");
      bw.flush();
      return;
    }
    SegmentTree tree = new SegmentTree(n);
    bw.write('<');
    bw.write(Integer.toString(tree.get(1, 0, n - 1, k) + 1));
    int num = n - 1;
    int q = k + k - 1;
    if (q > num) q = (q - 1) % num + 1;
    // q %= num;
    while (num > 0) {
      bw.write(", ");
      bw.write(Integer.toString(tree.get(1, 0, n - 1, q) + 1));
      num--;
      if (num == 0) break;
      q += k - 1;
      if (q > num) q = (q - 1) % num + 1;
    }
    bw.write(">\n");
    bw.flush();
  }
}