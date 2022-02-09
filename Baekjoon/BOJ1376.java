import java.io.*;
import java.util.*;

public class BOJ1376 {
  private static class Edge {
    ArrayList<Integer> arr = new ArrayList<>();
    HashSet<Integer> hs = new HashSet<>();
    int n, treeSize;
    int[] counttree, mintree;
    void init() {
      n = hs.size();
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      counttree = new int[treeSize];
      mintree = new int[treeSize];
      initCount(1, 0, n-1);
      initMin(1, 0, n-1);
      Collections.sort(arr);
    }
    int initCount(int node, int start, int end) {
      if (start == end) return counttree[node] = 1;
      int mid = (start + end) >> 1;
      int leftChild = initCount(node*2, start, mid);
      int rightChild = initCount(node*2+1, mid+1, end);
      return counttree[node] = leftChild + rightChild;
    }
    int initMin(int node, int start, int end) {
      if (start == end) return mintree[node] = start;
      int mid = (start + end) >> 1;
      int leftChild = initMin(node*2, start, mid);
      int rightChild = initMin(node*2+1, mid+1, end);
      return mintree[node] = arr.get(leftChild) < arr.get(rightChild) ? leftChild : rightChild;
    }
    int updateCount(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return counttree[node];
      if (start == end) return counttree[node] = val;
      int mid = (start + end) >> 1;
      int leftChild = updateCount(node*2, start, mid, idx, val);
      int rightChild = updateCount(node*2+1, mid+1, end, idx, val);
      return counttree[node] = leftChild + rightChild;
    }
    int updateMin(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return mintree[node];
      if (start == end) {
        arr.set(idx, val);
        return start;
      }
      int mid = (start + end) >> 1;
      int leftChild = updateMin(node*2, start, mid, idx, val);
      int rightChild = updateMin(node*2+1, mid+1, end, idx, val);
      if (arr.get(leftChild) == -1) return rightChild;
      if (arr.get(rightChild) == -1) return leftChild;
      return mintree[node] = arr.get(leftChild) < arr.get(rightChild) ? leftChild : rightChild;
    }
    boolean hasNext() {
      return n > 0;
    }
    int next() {
      if ((counttree[1] & 1) == 0) { // even
        return min();
      } else { // odd
        return median(); 
      }
    }
    int min() {
      int idx = mintree[1];
      int r = arr.get(idx);
      delete(r);
      return r;
    }
    int median() {
      int idx = median(1, 0, n-1, (n+1)/2);
      int r = arr.get(idx);
      delete(r);
      return r;
    }
    int median(int node, int start, int end, int val) {
      if (start == end) return start;
      int mid = (start + end) >> 1;
      if (counttree[node*2] >= val) {
        return median(node*2, start, mid, val);
      } else {
        return median(node*2+1, mid+1, end, val - counttree[node*2]);
      }
    }
    void add(int v) {
      if (hs.contains(v)) return;
      hs.add(v);
      arr.add(v);
    }
    void delete(int v) { // this -> v
      int idx = find(1, 0, n-1, v);
      if (arr.get(idx) != v) return;
      updateCount(1, 0, n-1, idx, 0);
      updateMin(1, 0, n-1, idx, Integer.MAX_VALUE);
    }
    int find(int node, int start, int end, int v) {
      if (start == end) return start;
      int mid = (start + end) >> 1;
      if (mintree[node*2] >= v) {
        return find(node*2, start, mid, v);
      } else {
        return find(node*2+1, mid+1, end, v);
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    ArrayList<Edge> graph = new ArrayList<>();
    for (int i = 0; i <= v; i++) {
      graph.add(new Edge());
    }
    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (a == b) continue;
      graph.get(a).add(b);
      graph.get(b).add(a);
    }
    for (int i = 1; i <= v; i++) {
      graph.get(i).init();
    }

    int[] edgeTo = new int[v+1];
    dfs(1, graph, edgeTo);

    int p = 1;
    bw.write('1');
    while (edgeTo[p] > 0) {
      bw.write(' ');
      bw.write(edgeTo[p]);
      p = edgeTo[p];
    }
    bw.newLine();
    bw.flush();
  }
  static void dfs(int v, ArrayList<Edge> graph, int[] edgeTo) {
    System.out.println("v: " + v);
    Edge edge = graph.get(v);
    System.out.print("arr: ");
    for (int p : edge.arr) System.out.print(p + " ");
    System.out.println();
    while (true) {
      if (!edge.hasNext()) return;
      int next = edge.next();
      System.out.println("next: " + next);
      edgeTo[v] = next;
      for (int other : edge.arr) {
        if (other > graph.size()) continue;
        graph.get(other).delete(v);
      }
      dfs(next, graph, edgeTo);
    }
  }
}
