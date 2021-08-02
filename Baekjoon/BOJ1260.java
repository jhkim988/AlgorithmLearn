import java.io.*;
import java.util.*;

public class BOJ1260 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numV = Integer.parseInt(st.nextToken());
    int numE = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());

    ArrayList<TreeSet<Integer>> graph = new ArrayList<>(numV + 1);
    boolean[] markedDFS = new boolean[numV + 1];
    boolean[] markedBFS = new boolean[numV + 1];

    // initialize
    for (int i = 0; i <= numV; i++) {
      graph.add(i, new TreeSet<Integer>());
    }
    for (int i = 0; i < numE; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      graph.get(node1).add(node2);
      graph.get(node2).add(node1);
    }
    dfs(graph, start, markedDFS);
    bw.write("\n");
    bfs(graph, start, markedBFS);
    bw.write("\n");
    bw.flush();
  }
  static void bfs(ArrayList<TreeSet<Integer>> graph, int start, boolean[] marked) throws IOException {
    // initialize queue
    Queue<Integer> que = new LinkedList<>();
    que.add(start);
    marked[start] = true;
    bw.write(start + " ");

    while (!que.isEmpty()) {
      int v = que.poll();
      for (int w : graph.get(v)) {
        if (!marked[w]) {
          marked[w] = true;;
          que.add(w);
          bw.write(w + " ");
        }
      }
    }
  }
  static void dfs(ArrayList<TreeSet<Integer>> graph, int start, boolean[] marked) throws IOException {
    bw.write(start + " ");
    marked[start] = true;
    for (int w : graph.get(start)) {
      if (!marked[w]) {
        dfs(graph, w, marked);
      }
    }
  }
}
