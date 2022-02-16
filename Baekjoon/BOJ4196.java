import java.io.*;
import java.util.*;

public class BOJ4196 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      ArrayList<Queue<Integer>> graph = new ArrayList<>();
      // ArrayList<Queue<Integer>> reverse = new ArrayList<>();
      for (int i = 0; i <= n; i++) {
        graph.add(new LinkedList<>());
        // reverse.add(new LinkedList<>());
      }
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        graph.get(a).add(b);
        // reverse.get(b).add(a);
      }
      Stack<Integer> stk = new Stack<>();
      boolean[] visit = new boolean[n+1];
      for (int i = 1; i < visit.length; i++) {
        if (visit[i]) continue;
        dfs(i, visit, graph, stk);
      }
      int sccId = 0;
      // ArrayList<ArrayList<Integer>> scc = new ArrayList<>();
      Arrays.fill(visit, false);
      while (!stk.isEmpty()) {
        int start = stk.pop();
        if (visit[start]) continue;
        dfs(start, visit, graph, sccId++);
      } 
      bw.write(Integer.toString(sccId));
      bw.newLine();
    }
    bw.flush();
  }
  static void dfs(int start, boolean[] visit, ArrayList<Queue<Integer>> graph, Stack<Integer> stk) {
    if (visit[start]) return;
    visit[start] = true;
    for (int adj : graph.get(start)) {
      if (visit[adj]) continue;
      dfs(adj, visit, graph, stk);
    }
    stk.push(start);
  }
  static void dfs(int start, boolean[] visit, ArrayList<Queue<Integer>> graph, int sccId) {
    if (visit[start]) return;
    visit[start] = true;
    for (int adj : graph.get(start)) {
      if (visit[adj]) continue;
      dfs(adj, visit, graph, sccId);
    }
  }
}
