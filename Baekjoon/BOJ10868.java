import java.io.*;
import java.util.*;

public class BOJ10868 {
  static final int INF = 1_000_000_001;
  static int init(int[] arr, int[] tree, int node, int start, int end) {
    if (start == end) return tree[node] = arr[start];
    int mid = (start + end) / 2;
    return tree[node] = Integer.min(init(arr, tree, node*2, start, mid), init(arr, tree, node*2+1, mid+1, end));
  }
  static int getMin(int[] tree, int node, int start, int end, int left, int right) {
    if (start > right || left > end) return INF;
    if (left <= start && end <= right) return tree[node];
    int mid = (start + end) / 2;
    return Integer.min(getMin(tree, node*2, start, mid, left, right), getMin(tree, node*2+1, mid+1, end, left, right));
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    int numQuery = Integer.parseInt(st.nextToken());
    int[] arr = new int[len];
    for (int i = 0; i < len; i++) arr[i] = Integer.parseInt(br.readLine());
    
    int height = 0;
    while ((1 << height) < len) height++;
    int treeSize = 1 << (height + 1);
    int[] tree = new int[treeSize];
    init(arr, tree, 1, 0, len - 1);

    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken()) - 1;
      int hi = Integer.parseInt(st.nextToken()) - 1;
      bw.write(Integer.toString(getMin(tree, 1, 0, len - 1, lo, hi)));
      bw.newLine();
    }
    bw.flush();
  }
}
