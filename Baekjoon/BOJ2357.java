import java.io.*;
import java.util.*;
import java.util.function.*;

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
    init(arr, mintree, 1, 0, len - 1, Integer::min);
    init(arr, maxtree, 1, 0, len - 1, Integer::max);

    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken()) - 1;
      int right = Integer.parseInt(st.nextToken()) - 1;
      bw.write(Integer.toString(get(mintree, 1, 0, len - 1, left, right, Integer::min)));
      bw.write(' ');
      bw.write(Integer.toString(get(maxtree, 1, 0, len - 1, left, right, Integer::max)));
      bw.newLine();
    }
    bw.flush();
  }
  static int init(int[] arr, int[] tree, int node, int start, int end, BinaryOperator<Integer> f) {
    if (start == end) return tree[node] = arr[start];
    int mid = (start + end) / 2;
    int leftChild = init(arr, tree, node*2, start, mid, f);
    int rightChild = init(arr, tree, node*2+1, mid+1, end, f);
    return tree[node] = f.apply(leftChild, rightChild);
  }
  static int get(int[] tree, int node, int start, int end, int left, int right, BinaryOperator<Integer> f) {
    if (start > right || end < left) return f.apply(1, -1) > 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    if (left <= start && end <= right) return tree[node];
    int mid = (start + end) / 2;
    int leftChild = get(tree, node*2, start, mid, left, right, f);
    int rightChild = get(tree, node*2+1, mid+1, end, left, right, f);
    return f.apply(leftChild, rightChild);
  }
}
