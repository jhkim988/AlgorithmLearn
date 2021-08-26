import java.io.*;
import java.util.*;

public class BOJ2623 {
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
      int num = Integer.parseInt(st.nextToken());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      for (int j = 1; j < num - 1; j++) {
        start = end;
        end = Integer.parseInt(st.nextToken());
        graph.get(start).add(end);
      }
    }

    // topological sort
    // Stack<Integer> sort = useDFS(graph);
    Queue<Integer> sort = useInorder(graph);
    if (sort.isEmpty()) {
      bw.write("0\n");
      bw.flush();
      return;
    }
    while (!sort.isEmpty()) {
      // bw.write(sort.pop() + "\n");
      bw.write(sort.poll() + "\n");
    }
    bw.flush();
  }
  static Stack<Integer> useDFS(ArrayList<Queue<Integer>> graph) {
    int V = graph.size() - 1;
    Stack<Integer> stk = new Stack<>();
    boolean[] marked = new boolean[V + 1];
    boolean flag = true;
    for (int i = 1; i <= V; i++) {
      if (marked[i]) continue;
      flag = dfs(graph, i, stk, marked, true);
      if (!flag) {
        return new Stack<Integer>(); // empty
      }
    }
    return stk;
  }
  static boolean dfs(ArrayList<Queue<Integer>> graph, int vertex, Stack<Integer> stk, boolean[] marked, boolean flag) {
    marked[vertex] = true;
    for (int adj : graph.get(vertex)) {
      if (marked[adj]) {
        if (stk.contains(adj)) {
          continue;
        } else {
          // System.out.println("vertex " + vertex + ", adj: " + adj);
          return false;
        }
      } else {
        flag = dfs(graph, adj, stk, marked, flag);
      }
    }
    stk.push(vertex);
    return flag;
  }
  static Queue<Integer> useInorder(ArrayList<Queue<Integer>> graph) {
    int V = graph.size() - 1;
    int[] inorder = new int[V + 1];
    for (int i = 1; i <= V; i++) {
      for (int v : graph.get(i)) {
        inorder[v]++;
      }
    } 

    Queue<Integer> que = new LinkedList<>();
    Queue<Integer> result = new LinkedList<>();
    for (int i = 1; i <= V; i++) {
      if (inorder[i] == 0) {
        que.add(i);
      }
    }

    int count = 0;
    while (!que.isEmpty()) {
      int crnt = que.poll();
      result.add(crnt);
      for (int adj : graph.get(crnt)) {
        inorder[adj]--;
        if (inorder[adj] == 0) {
          que.add(adj);
        }
      }
      count++;
    }
    if (count != V) {
      return new LinkedList<>();
    }
    return result;
  }
}
