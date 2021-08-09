import java.io.*;
import java.util.*;

public class BOJ1991 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N;
  static int[] tree;
  static ArrayList<ArrayList<Integer>> graph;
  public static void main(String[] args) throws IOException {

    N = Integer.parseInt(br.readLine());
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }
    tree = new int[N + 1]; // root: tree[1]
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int parent = st.nextToken().charAt(0);
      int child1 = st.nextToken().charAt(0);
      int child2 = st.nextToken().charAt(0);
      if (child1 != '.') {
        graph.get(parent - 'A' + 1).add(child1 - 'A' + 1);
      } else {
        graph.get(parent - 'A' + 1).add(-1);
      }
      if (child2 != '.') {
        graph.get(parent - 'A' + 1).add(child2 - 'A' + 1);
      } else {
        graph.get(parent - 'A' + 1).add(-1);
      }
    }
    
    preorder(1);
    bw.write("\n");
    inorder(1);
    bw.write("\n");
    postorder(1);
    bw.write("\n");
    bw.flush();
  }
  static void preorder(int x) throws IOException {
    bw.write(('A' + x - 1));
    for (int child : graph.get(x)) {
      if (child != -1) {
        preorder(child);
      }
    }
  }
  static void inorder(int x) throws IOException {
    if (graph.get(x).get(0) != -1) {
      inorder(graph.get(x).get(0));
    }
    bw.write(('A' + x - 1));
    if (graph.get(x).get(1) != -1) {
      inorder(graph.get(x).get(1));
    }
  }
  static void postorder(int x) throws IOException {
    for (int child : graph.get(x)) {
      if (child != -1) {
        postorder(child);
      }      
    }
    bw.write(('A' + x - 1));
  }
}
