import java.io.*;
import java.util.*;

public class BOJ2042 {
  static long init(long[] arr, long[] tree, int node, int start, int end) {
    if (start == end) {
      return tree[node] = arr[start];
    }
    else {
      return tree[node] = init(arr, tree, node*2, start, (start + end)/2) + init(arr, tree, node*2+1, (start + end)/2+1, end);
    }
  }
  static long sum(long[] tree, int node, int start, int end, int left, int right) {
    if (start > right || end < left) return 0;
    if (left <= start && end <= right) return tree[node];
    return sum(tree, node*2, start, (start + end)/2, left, right) + sum(tree, node*2+1, (start + end)/2+1, end, left, right);
  }
  static void update(long[] tree, int node, int start, int end, int index, long diff) {
    if (start > index || end < index) return;
    tree[node] += diff;
    if (start != end) {
      update(tree, node*2, start, (start + end)/2, index, diff);
      update(tree, node*2+1, (start + end)/2+1, end, index, diff);
    }
  }
  public static void main(String[] args) throws IOException {
    useFenwickTree();
  } 
  static void useSegTree() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    int numChange = Integer.parseInt(st.nextToken());
    int numSum = Integer.parseInt(st.nextToken());
    int numQuery = numChange + numSum;
    
    long[] arr = new long[len];
    for (int i = 0; i < len; i++) arr[i] = Long.parseLong(br.readLine());
    
    int height = 0;
    while ((1 << height) < len) height++;
    int treeSize = 1 << (height + 1);
    long[] tree = new long[treeSize];
    init(arr, tree, 1, 0, len - 1);

    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) { // array change
        int idx = Integer.parseInt(st.nextToken()) - 1;
        long value = Long.parseLong(st.nextToken());
        long diff = value - arr[idx];
        arr[idx] = value;
        update(tree, 1, 0, len - 1, idx, diff);
      } else { // get partial sum
        int lo = Integer.parseInt(st.nextToken()) - 1;
        int hi = Integer.parseInt(st.nextToken()) - 1;
        bw.write(Long.toString(sum(tree, 1, 0, len - 1, lo, hi)));
        bw.newLine();
      }
    }
    bw.flush();
  }
  static void useFenwickTree() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    int numChange = Integer.parseInt(st.nextToken());
    int numSum = Integer.parseInt(st.nextToken());
    int numQuery = numChange + numSum;
    
    long[] arr = new long[len + 1];
    long[] tree = new long[len + 1];
    for (int i = 1; i <= len; i++) {
      arr[i] = Long.parseLong(br.readLine());
      update(tree, i, arr[i]);
    }
    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) { // array change
        int idx = Integer.parseInt(st.nextToken());
        long value = Long.parseLong(st.nextToken());
        long diff = value - arr[idx];
        arr[idx] = value;
        update(tree, idx, diff);
      } else { // get partial sum
        int lo = Integer.parseInt(st.nextToken());
        int hi = Integer.parseInt(st.nextToken());
        long pSum = sum(tree, hi) - (lo == 1 ? 0 : sum(tree, lo - 1));
        bw.write(Long.toString(pSum));
        bw.newLine();
      }
    }
    bw.flush();
  }
  static void update(long[] tree, int i, long diff) {
    while (i < tree.length) {
      tree[i] += diff;
      i += i & -i;
    }
  }
  static long sum(long[] tree, int i) {
    long value = 0;
    while (i > 0) {
      value += tree[i];
      i -= i & -i;
    }
    return value;
  }
}
