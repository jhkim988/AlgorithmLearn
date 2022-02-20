import java.io.*;
import java.util.*;

public class BOJ9798 {
  private static class Pair {
    int id, k, a;
    Pair(int id, int k, int a) {
      this.id = id;
      this.k = k;
      this.a = a;
    }
  }
  private static class Tree {
    Tree p, l, r;
    int id;
  }
  private static class Node {
    int id, p, l, r;
  }
  private static class SegTree {
    int n, treeSize;
    int[] tree;
    ArrayList<Pair> arr;
    SegTree(ArrayList<Pair> arr) {
      n = arr.size();
      this.arr = arr;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = start;
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = arr.get(tree[node<<1]).a < arr.get(tree[node<<1|1]).a ? tree[node<<1] : tree[node<<1|1]; 
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return -1;
      if (left <= start && end <= right) {
        return tree[node];
      }
      int mid = (start + end) >> 1;
      int leftChild = get(node<<1, start, mid, left, right);
      int rightChild = get(node<<1|1, mid+1, end, left, right);
      if (leftChild == -1) return rightChild;
      if (rightChild == -1) return leftChild;
      return arr.get(leftChild).a < arr.get(rightChild).a ? leftChild : rightChild; 
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Pair> arr = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int k = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      arr.add(new Pair(i+1, k, a));
    }
    Collections.sort(arr, (a, b) -> a.k==b.k ? a.a-b.a:a.k-b.k);
    SegTree sg = new SegTree(arr);
    Tree root = new Tree();
    ArrayList<Node> print = new ArrayList<>();
    recur(0, n-1, n, root, print, sg);
    bw.write("YES\n");
    Collections.sort(print, (a, b) -> a.id-b.id);
    for (int i = 0; i < n; i++) {
      Node crnt = print.get(i);
      bw.write(Integer.toString(crnt.p));
      bw.write(' ');
      bw.write(Integer.toString(crnt.l));
      bw.write(' ');
      bw.write(Integer.toString(crnt.r));
      bw.newLine();
    }
    bw.flush();
  }
  static void recur(int start, int end, int n, Tree tree, ArrayList<Node> print, SegTree sg) {
    int idx = sg.get(1, 0, n-1, start, end);
    int id = sg.arr.get(idx).id;
    Node crnt = new Node();
    tree.id = crnt.id = id;
    crnt.p = tree.p == null ? 0 : tree.p.id;
    if (start < idx) {
      tree.l = new Tree();
      tree.l.p = tree;
      recur(start, idx-1, n, tree.l, print, sg);
      crnt.l = tree.l.id;
    }
    if (idx < end) {
      tree.r = new Tree();
      tree.r.p = tree;
      recur(idx+1, end, n, tree.r, print, sg);
      crnt.r = tree.r.id;
    }
    print.add(crnt);
  }
}
