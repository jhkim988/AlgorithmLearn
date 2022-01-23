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
    int[][] data = new int[size][size];
    for (int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int height = 0;
    while ((1 << height) < size) height++;
    int treeSize = 1 << (height + 1);
    long[][] forest = new long[size][treeSize];
    for (int i = 0; i < size; i++) {
      init(data[i], forest[i], 1, 0, size - 1);
    }

    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 0) { // change
        int row = Integer.parseInt(st.nextToken()) - 1;
        int col = Integer.parseInt(st.nextToken()) - 1;
        int value = Integer.parseInt(st.nextToken());
        int diff = value - data[row][col];
        data[row][col] = value;
        update(forest[row], 1, 0, size - 1, col, diff);
      } else { // get Partial Sum
        int firstRow = Integer.parseInt(st.nextToken()) - 1;
        int firstCol = Integer.parseInt(st.nextToken()) - 1;
        int secondRow = Integer.parseInt(st.nextToken()) - 1;
        int secondCol = Integer.parseInt(st.nextToken()) - 1;
        long value = 0;
        for (int rowIdx = firstRow; rowIdx <= secondRow; rowIdx++) {
          value += sum(forest[rowIdx], 1, 0, size - 1, firstCol, secondCol);
        }
        bw.write(Long.toString(value));
        bw.newLine();
      }
    }
    bw.flush();
  }
  static long init(int[] arr, long[] tree, int node, int start, int end) {
    if (start  == end) return tree[node] = arr[start];
    int mid = (start + end) / 2;
    long leftChild = init(arr, tree, node*2, start, mid);
    long rightChild = init(arr, tree, node*2+1, mid+1, end);
    return tree[node] = leftChild + rightChild;
  }
  static void update(long[] tree, int node, int start, int end, int index, int diff) {
    if (start > index || end < index) return;
    tree[node] += diff;
    if (start != end) {
      int mid = (start + end) / 2;
      update(tree, node*2, start, mid, index, diff);
      update(tree, node*2+1, mid+1, end, index, diff);
    }
  }
  static long sum(long[] tree, int node, int start, int end, int left, int right) {
    if (start > right || end < left) return 0L;
    if (left <= start && end <= right) return tree[node];
    int mid = (start + end) / 2;
    long leftChild = sum(tree, node*2, start, mid, left, right);
    long rightChild = sum(tree, node*2+1, mid+1, end, left, right);
    return leftChild + rightChild;
  }
}
