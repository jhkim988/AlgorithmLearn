import java.io.*;
import java.util.*;

public class BOJ2243 {
  private static class SegmentTree {
    final int n = 1_000_001;
    int treeSize = 1;
    int[] count, tree;
    SegmentTree() {
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      count = new int[n];
      tree = new int[treeSize];
    }
    int update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) return tree[node] = (count[start] += val);
      int mid = (start + end) >> 1;
      int leftChild = update(node*2, start, mid, idx, val);
      int rightChild = update(node*2+1, mid+1, end, idx, val);
      return tree[node] = leftChild + rightChild;
    }
    int get(int node, int start, int end, int k) {
      if (start == end) return start;
      int rank = 0;
      int mid = (start + end) >> 1;
      if (tree[node*2] >= k) {
        rank = get(node*2, start, mid, k);
      } else {
        rank = get(node*2+1, mid+1, end, k-tree[node*2]);
      }
      return rank;
    }
    void addQuery(int rank, int num) {
      update(1, 0, n-1, rank+1, num);
    }
    int takeQuery(int k) {
      int rank = get(1, 0, n-1, k);
      update(1, 0, n-1, rank, -1);
      return rank-1;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numQuery = Integer.parseInt(br.readLine());
    SegmentTree sg = new SegmentTree();
    while (numQuery-- > 0 ) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int k = Integer.parseInt(st.nextToken());
        bw.write(Integer.toString(sg.takeQuery(k)));
        bw.newLine();
      } else {
        int rank = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());
        sg.addQuery(rank, num);
      }
    }
    bw.flush();
  }
}
