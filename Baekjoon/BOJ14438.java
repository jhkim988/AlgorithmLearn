import java.io.*;
import java.util.*;

public class BOJ14438 {
  static final int INF = 1_000_000_001;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] seq = new int[len];
    for (int i = 0; i < len; i++) seq[i] = Integer.parseInt(st.nextToken());
    
    int height = 0;
    while ((1 << height) < len) height++;
    int treeSize = 1 << (height + 1);
    int[] segTree = new int[treeSize];
    init(seq, segTree, 1, 0, len - 1);
    
    int numQuery = Integer.parseInt(br.readLine());
    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) { // change
        int index = Integer.parseInt(st.nextToken()) - 1;
        int value = Integer.parseInt(st.nextToken());
        seq[index] = value;
        update(segTree, 1, 0, len - 1, index, value);
      } else { // getMin
        int lo = Integer.parseInt(st.nextToken()) - 1;
        int hi = Integer.parseInt(st.nextToken()) - 1;
        bw.write(Integer.toString(getMin(segTree, 1, 0, len - 1, lo, hi)));
        bw.newLine();
      }
    }
    bw.flush();
  }
  static int init(int[] arr, int[] tree, int node, int start, int end) {
    if (start == end) return tree[node] = arr[start];
    int mid = (start + end) / 2;
    return tree[node] = Integer.min(init(arr, tree, 2*node, start, mid), init(arr, tree, 2*node+1, mid + 1, end));
  }
  static int getMin(int[] tree, int node, int start, int end, int left, int right) {
    // node: [start, end], query: [left, right]
    if (start > right || end < left) return INF;
    if (left <= start && end <= right) return tree[node];
    int mid = (start + end) / 2;
    return Integer.min(getMin(tree, 2*node, start, mid, left, right), getMin(tree, 2*node+1, mid+1, end, left, right));
  }
  static int update(int[] tree, int node, int start, int end, int index, int value) {
    if (start > index || end < index) return tree[node];
    if (start != end) {
      int mid = (start + end) / 2;
      return tree[node] = Integer.min(update(tree, 2*node, start, mid, index, value), update(tree, 2*node+1, mid+1, end, index, value));
    }
    return tree[node] = value;
  }
}