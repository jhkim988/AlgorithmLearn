import java.io.*;
import java.util.*;

public class BOJ2056 {
  public static void useKahnAlgorithm() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int V = Integer.parseInt(br.readLine());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    int[] cost = new int[V];
    for (int i = 0; i < V; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < V; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int time = Integer.parseInt(st.nextToken());
      int num = Integer.parseInt(st.nextToken());
      cost[i] = time;
      for (int j = 0; j < num; j++) {
        int end = Integer.parseInt(st.nextToken()) - 1;
        graph.get(end).add(i);
      }
    }

    int[] incomming = new int[V];
    for (int i = 0; i < V; i++) {
      for (int node : graph.get(i)) {
        incomming[node]++;
      }
    }

    Queue<Integer> zeroIncomming = new LinkedList<>();
    for (int i = 0; i < V; i++) {
      if (incomming[i] == 0) zeroIncomming.add(i);
    }
    boolean[] selected = new boolean[V];
    int[] dp = new int[V];
    for (int i = 0; i < V; i++) dp[i] = cost[i];
    while (!zeroIncomming.isEmpty()) {
      int node = zeroIncomming.poll();
      selected[node] = true;
      
      for (int next : graph.get(node)) {
        dp[next] = Integer.max(dp[next], dp[node] + cost[next]);
        incomming[next]--;
        if (incomming[next] == 0 && !selected[next]) zeroIncomming.add(next);
      }
    }

    int max = 0;
    for (int i = 0; i < V; i++) max = Integer.max(max, dp[i]);

    bw.write(Integer.toString(max));
    bw.newLine();
    bw.flush();
  }
  public static void useDFS() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    int[] time = new int[n+1];
    int[] cost = new int[n+1];
    boolean[] visit = new boolean[n+1];
    Stack<Integer> stk = new Stack<>();
    for (int v = 1; v <= n; v++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      time[v] = Integer.parseInt(st.nextToken());
      int numAdj = Integer.parseInt(st.nextToken());
      for (int j = 0; j < numAdj; j++) {
        int u = Integer.parseInt(st.nextToken());
        graph.get(u).add(v);
      }
    }
    for (int v = 1; v <= n; v++) {
      if (time[v] != 0 && visit[v]) continue;
      visit[v] = true;
      cost[v] = time[v];
      dfs(graph, v, visit, stk);
    }
    int max = 0;
    while (!stk.isEmpty()) {
      int crnt = stk.pop();
      for (int next : graph.get(crnt)) {
        cost[next] = Integer.max(cost[next], cost[crnt] + time[next]);
      }
    }
    for (int c : cost) max = Integer.max(max, c);
    bw.write(Integer.toString(max));
    bw.flush();
  }
  static void dfs(ArrayList<Queue<Integer>> graph, int node, boolean[] visit, Stack<Integer> stk) {
    for (int adj : graph.get(node)) {
      if (visit[adj]) continue;
      dfs(graph, adj, visit, stk);
      visit[adj] = true;
    }
    stk.push(node);
  }
  public static void main(String[] args) throws IOException {
    useDFS();
  }
}
