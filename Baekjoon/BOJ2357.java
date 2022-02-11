import java.io.*;
import java.util.*;
// import java.util.function.*;

public class BOJ2357 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    int numQuery = Integer.parseInt(st.nextToken());
    int[] arr = new int[len];
    for (int i = 0; i < len; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    int height = 1;
    while ((1 << height) < len) height++;
    int treeSize = 1 << (height + 1);
    int[] mintree = new int[treeSize];
    int[] maxtree = new int[treeSize];
    // init(arr, mintree, 1, 0, len - 1, Integer::min);
    // init(arr, maxtree, 1, 0, len - 1, Integer::max);
    initMin(arr, mintree, 1, 0, len-1);
    initMax(arr, maxtree, 1, 0, len-1);

    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken()) - 1;
      int right = Integer.parseInt(st.nextToken()) - 1;
      // int min = get(mintree, 1, 0, len-1, left, right, Integer::min);
      // int max = get(maxtree, 1, 0, len-1, left, right, Integer::max);
      int min = getMin(mintree, 1, 0, len-1, left, right);
      int max = getMax(maxtree, 1, 0, len-1, left, right);
      bw.write(Integer.toString(min));
      bw.write(' ');
      bw.write(Integer.toString(max));
      bw.newLine();
    }
    bw.flush();
  }
  // static int init(int[] arr, int[] tree, int node, int start, int end, BinaryOperator<Integer> f) {
  //   if (start == end) return tree[node] = arr[start];
  //   int mid = (start + end) / 2;
  //   int leftChild = init(arr, tree, node*2, start, mid, f);
  //   int rightChild = init(arr, tree, node*2+1, mid+1, end, f);
  //   return tree[node] = f.apply(leftChild, rightChild);
  // }
  // static int get(int[] tree, int node, int start, int end, int left, int right, BinaryOperator<Integer> f) {
  //   if (start > right || end < left) return f.apply(1, -1) > 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
  //   if (left <= start && end <= right) return tree[node];
  //   int mid = (start + end) / 2;
  //   int leftChild = get(tree, node*2, start, mid, left, right, f);
  //   int rightChild = get(tree, node*2+1, mid+1, end, left, right, f);
  //   return f.apply(leftChild, rightChild);
  // }
  static int initMin(int[] arr, int[] tree, int node, int start, int end) {
    if (start == end) return tree[node] = arr[start];
    int mid = (start + end) / 2;
    int leftChild = initMin(arr, tree, node*2, start, mid);
    int rightChild = initMin(arr, tree, node*2+1, mid+1, end);
    return tree[node] = Integer.min(leftChild, rightChild);
  }
  static int getMin(int[] tree, int node, int start, int end, int left, int right) {
    if (start > right || end < left) return Integer.MAX_VALUE;
    if (left <= start && end <= right) return tree[node];
    int mid = (start + end) / 2;
    int leftChild = getMin(tree, node*2, start, mid, left, right);
    int rightChild = getMin(tree, node*2+1, mid+1, end, left, right);
    return Integer.min(leftChild, rightChild);
  }
  static int initMax(int[] arr, int[] tree, int node, int start, int end) {
    if (start == end) return tree[node] = arr[start];
    int mid = (start + end) / 2;
    int leftChild = initMax(arr, tree, node*2, start, mid);
    int rightChild = initMax(arr, tree, node*2+1, mid+1, end);
    return tree[node] = Integer.max(leftChild, rightChild);
  }
  static int getMax(int[] tree, int node, int start, int end, int left, int right) {
    if (start > right || end < left) return Integer.MIN_VALUE;
    if (left <= start && end <= right) return tree[node];
    int mid = (start + end) / 2;
    int leftChild = getMax(tree, node*2, start, mid, left, right);
    int rightChild = getMax(tree, node*2+1, mid+1, end, left, right);
    return Integer.max(leftChild, rightChild);
  }
}
