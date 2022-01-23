import java.io.*;
import java.util.*;

public class BOJ11658 {
  // My try: SegmentTree * Row
  // Time Complexity:
  // N: length of row(=col), M: num Query
  // Struct SegmentTree = O(N^2 log N)
  // Get Partial Sum = O(N log N)
  // Change value = O(log N)
  // -> All query = O(MN log N)
  // Time Limit Exceed
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int size = Integer.parseInt(st.nextToken());
    int numQuery = Integer.parseInt(st.nextToken());
    int[][] data = new int[size + 1][size + 1];
    int[][] tree = new int[size + 1][size + 1];

    for (int i = 1; i <= size; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= size; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
        update(tree, i, j, data[i][j]);
      }
    }
    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 0) { // change
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int value = Integer.parseInt(st.nextToken());
        int diff = value - data[row][col];
        data[row][col] = value;
        update(tree, row, col, diff);
      } else { // get Partial Sum
        int firstRow = Integer.parseInt(st.nextToken());
        int firstCol = Integer.parseInt(st.nextToken());
        int secondRow = Integer.parseInt(st.nextToken());
        int secondCol = Integer.parseInt(st.nextToken());        
        bw.write(Integer.toString(pSum(tree, firstRow - 1, firstCol - 1, secondRow, secondCol)));
        bw.newLine();
      }
    }
    bw.flush();
  }
  static int getSum(int[][] tree, int row, int col) {
    int ret = 0;
    for (int i = row; i > 0; i -= i & -i) {
      for (int j = col; j > 0; j -= j & -j) {
        ret += tree[i][j];
      }
    }
    return ret;
  }
  static int pSum(int[][] tree, int row1, int col1, int row2, int col2) {
    return getSum(tree, row2, col2) - getSum(tree, row2, col1) - getSum(tree, row1, col2) + getSum(tree, row1, col1);
  }
  static void update(int[][] tree, int row, int col, int diff) {
    for (int i = row; i < tree.length; i += i & -i) {
      for (int j = col; j < tree[i].length; j += j & -j) {
        tree[i][j] += diff;
      }
    }
  }
}
