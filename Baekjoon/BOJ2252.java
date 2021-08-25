import java.io.*;
import java.util.*;

public class BOJ2252 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int small = Integer.parseInt(st.nextToken());
      int tall = Integer.parseInt(st.nextToken());
      graph.get(small).add(tall);
    }
    Queue<Integer> sort = useInorder(graph);
    // Stack<Integer> sort = useDFS(graph);
    while (!sort.isEmpty()) {
      // bw.write(sort.pop() + " ");
      bw.write(sort.poll() + " ");
    }
    bw.write("\n");
    bw.flush();
  }
  static Queue<Integer> useInorder(ArrayList<Queue<Integer>> graph) {
    int V = graph.size() - 1;
    int[] indegree = new int[V + 1];
    for (int i = 1; i <= V; i++) {
      for (int v : graph.get(i)) {
        indegree[v]++;
      }
    }
    Queue<Integer> que = new LinkedList<>();
    Queue<Integer> result = new LinkedList<>();
    for (int i = 1; i <= V; i++) {
      if (indegree[i] != 0) continue;
      que.add(i);
    }
    while (!que.isEmpty()) {
      int v = que.poll();
      result.add(v);
      for (int w : graph.get(v)) {
        indegree[w]--;
        if (indegree[w] == 0) {
          que.add(w);
        }
      }
    }
    return result;
  }
  static Stack<Integer> useDFS(ArrayList<Queue<Integer>> graph) {
    int V = graph.size() - 1;
    Stack<Integer> stk = new Stack<>();
    boolean[] marked = new boolean[V + 1];
    for (int i = 1; i <= V; i++) {
      if (marked[i]) continue;
      dfs(graph, i, stk, marked);
    }
    return stk;
  }
  static void dfs(ArrayList<Queue<Integer>> graph, int vertex, Stack<Integer> stk, boolean[] marked) {
    marked[vertex] = true;
    for (int adj : graph.get(vertex)) {
      if (marked[adj]) continue;
      dfs(graph, adj, stk, marked);
    }
    stk.push(vertex);
  }
}
