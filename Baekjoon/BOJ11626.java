import java.io.*;
import java.util.*;

public class BOJ11626 {
  private static class SegTree {
    int treeSize;
    int[] tree;
    SegTree(int n) {
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = 1;
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    void update(int node, int start, int end, int idx) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] = 0;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx);
      update(node<<1|1, mid+1, end, idx);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int n = Integer.parseInt(br.readLine());
      SegTree sg = new SegTree(n);
      ArrayList<Integer> answer = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        int input = Integer.parseInt(br.readLine());
        if (sg.get(1, 0, n-1, 0, input-2) > 0) answer.add(input);
        sg.update(1, 0, n-1, input-1);
      }
      Collections.sort(answer);
      bw.write(Integer.toString(answer.size()));
      bw.newLine();
      for (int a : answer) {
        bw.write(Integer.toString(a));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
