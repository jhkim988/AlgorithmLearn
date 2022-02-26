import java.io.*;
import java.util.*;

public class BOJ2336 {
  private static class Pair {
    int t1, t2;
    Pair(int t1, int t2) {
      this.t1 = t1;
      this.t2 = t2;
    }
  }
  private static class SegTree {
    int size;
    int[] tree;
    SegTree(int n) {
      size = 1;
      while (size < n) size <<= 1;
      size <<= 1;
      tree = new int[size];
    }
    void update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] = val;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
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
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    boolean[] isGreat = new boolean[n];
    Arrays.fill(isGreat, true);
    int[][] rank = new int[3][n];
    for (int i = 0; i < 3; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) { 
        rank[i][j] = Integer.parseInt(st.nextToken()) - 1;
      }
    }
    for (int test1 = 0; test1 < 3; test1++) {
      for (int test2 = test1+1; test2 < 3; test2++) {
        ArrayList<Pair> relative = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          relative.add(new Pair(rank[test1][i], rank[test2][i]));
        }
        Collections.sort(relative, (a, b) -> a.t1-b.t1);
        for (Pair p : relative) {
          System.out.print(p.t2 + " ");
        }
        System.out.println();
        SegTree sg = new SegTree(n);
        for (int i = 0; i < n; i++) {
          Pair p = relative.get(i);
          int ic = sg.get(1, 0, n-1, 0, p.t2);
          if (ic > 0) isGreat[i] = false;
          sg.update(1, 0, n-1, p.t2, 1);
        }
      }
    }

    int num = 0;
    for (boolean great : isGreat) {
      if (great) num++;
    }
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();

    System.out.println("isGreat: " + Arrays.toString(isGreat));
  }
}
