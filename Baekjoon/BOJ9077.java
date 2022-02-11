import java.io.*;
import java.util.*;

public class BOJ9077 {
  static final int sz = 10_001;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private static class UseBruteForce {
    int[][] pSum = new int[sz][sz];
    void clear() {
      for (int i = 0; i < sz; i++) Arrays.fill(pSum[i], 0);
    }
    void init() {
      for (int i = 1; i < sz; i++) {
        pSum[i][0] = pSum[i-1][0] + (pSum[i][0]==1 ? 1 : 0);
      }
      for (int j = 1; j < sz; j++) {
        pSum[0][j] = pSum[0][j-1] + (pSum[0][j]==1 ? 1 : 0);
      }
      for (int i = 1; i < sz; i++) {
        for (int j = 1; j < sz; j++) {
          pSum[i][j] = pSum[i][j-1] + pSum[i-1][j] - pSum[i-1][j-1] + (pSum[i][j] == 1 ? 1 : 0);
        }
      }
    }
    int run() throws IOException {
      int numMine = Integer.parseInt(br.readLine());
      for (int i = 0; i < numMine; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        pSum[x][y] = 1;
      }
      init();  
      int max = pSum[10][10];
      for (int i = 1; i + 10 < sz; i++) {
        int val = pSum[i+10][10] - pSum[i-1][10];
        if (max < val) max = val;
      }
      for (int j = 1; j + 10 < sz; j++) {
        int val = pSum[10][j+10] - pSum[10][j-1];
        if (max < val) max = val;
      }
      for (int i = 1; i + 10 < sz; i++) {
        for (int j = 1; j + 10 < sz; j++) {
          int val = pSum[i+10][j+10] - pSum[i-1][j+10] - pSum[i+10][j-1] + pSum[i-1][j-1];
          if (max < val) max = val;
        }
      }
      clear();
      return max;
    }
  }
  private static class SegTreeLazy {
    int treeSize = 1 << 15;
    int[] tree, lazy;
    SegTreeLazy() {
      tree = new int[treeSize];
      lazy = new int[treeSize];
    }
    void updateLazy(int node, int start, int end) {
      if (lazy[node] == 0) return;
      tree[node] += lazy[node];
      if (start != end) {
        lazy[node << 1] += lazy[node];
        lazy[node << 1 | 1] += lazy[node];
      }
      lazy[node] = 0;
    }
    void update(int node, int start, int end, int left, int right, int diff) {
      updateLazy(node, start, end);
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        tree[node] += diff;
        if (start != end) {
          lazy[node << 1] += diff;
          lazy[node << 1 | 1] += diff;
        }
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, left, right, diff);
      update(node<<1|1, mid+1, end, left, right, diff);
      tree[node] = Integer.max(tree[node<<1], tree[node<<1|1]);
    }
    int get(int node, int start, int end, int left, int right) {
      updateLazy(node, start, end);
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return Integer.max(get(node<<1, start, mid, left, right), get(node<<1|1, mid+1, end, left, right));
    }
    int max() {
      updateLazy(1, 0, 10000);
      return tree[1];
    }
    void clear() {
      Arrays.fill(tree, 0);
      Arrays.fill(lazy, 0);
    }
  }
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  private static class UseSegTreeLazy {
    SegTreeLazy sg = new SegTreeLazy();
    int run() throws IOException {
      int numMine = Integer.parseInt(br.readLine());
      Pair[] mine = new Pair[numMine];
      for (int i = 0; i < numMine; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        mine[i] = new Pair(row, col);
      }
      Arrays.sort(mine, (a, b) -> a.row == b.row ? a.col - b.col : a.row - b.row);
      int max = 0;
      for (int i = 0, j = 0; i < numMine; i++) {
        while (mine[j].row < mine[i].row - 10) {
          sg.update(1, 0, 10000, Integer.max(0, mine[j].col - 10), mine[j].col, -1);
          j++;
        }
        sg.update(1, 0, 10000, Integer.max(0, mine[i].col - 10), mine[i].col, 1);
        // max = Integer.max(max, sg.get(1, 0, 10000, 0, 10000));
        max = Integer.max(max, sg.max());
      }
      sg.clear();
      return max;
    }
  }
  public static void main(String[] args) throws IOException {
    int numTest = Integer.parseInt(br.readLine());
    // UseBruteForce sol = new UseBruteForce();
    UseSegTreeLazy sol = new UseSegTreeLazy();
    while (numTest-- > 0) {
      int answer = sol.run();
      bw.write(Integer.toString(answer));
      bw.newLine();
    }
    bw.flush();
  }

}
