import java.io.*;
import java.util.*;

public class BOJ1005 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      int[] time = new int[N + 1];
      st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= N; i++) {
        time[i] = Integer.parseInt(st.nextToken());
      }
      ArrayList<Queue<Integer>> needToBuild = new ArrayList<>();
      ArrayList<Queue<Integer>> reverse = new ArrayList<>();
      for (int i = 0; i <= N; i++) {
        needToBuild.add(new LinkedList<>());
        reverse.add(new LinkedList<>());
      }
      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        int next = Integer.parseInt(st.nextToken());
        needToBuild.get(prev).add(next);
        reverse.get(next).add(prev);
      }
      int target = Integer.parseInt(br.readLine());
      int[] dp = new int[N + 1];
      Stack<Integer> stk = TopologicalSort(needToBuild);
      while (!stk.isEmpty()) {
        int node = stk.pop();
        for (int adj : reverse.get(node)) {
          dp[node] = Math.max(dp[node], dp[adj]);
        }
        dp[node] += time[node];
      }
      bw.write(dp[target] + "\n");
      bw.flush();
    }
  }
  static Stack<Integer> TopologicalSort(ArrayList<Queue<Integer>> graph) {
    int V = graph.size() - 1;
    Stack<Integer> reversePost = new Stack<>();
    boolean[] marked = new boolean[V + 1];
    for (int v = 1; v <= V; v++) {
      if (marked[v]) continue;
      dfs(graph, v, reversePost, marked);     
    }
    return reversePost;
  }
  static void dfs(ArrayList<Queue<Integer>> graph, int vertex, Stack<Integer> stack, boolean[] marked) {
    marked[vertex] = true;
    for (int w : graph.get(vertex)) {
      if (!marked[w]) dfs(graph, w, stack, marked);
    }
    stack.push(vertex);
  }


  static private class newSolution {
    static ArrayList<Queue<Integer>> rev;
    static Queue<Integer> que;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      int t = Integer.parseInt(br.readLine());
      while (t-- > 0) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        rev = new ArrayList<>();
        que = new LinkedList<>();
        visit = new boolean[n+1];
        for (int i = 0; i <= n; i++) {
          rev.add(new LinkedList<>());
        }
        st = new StringTokenizer(br.readLine());
        int[] cost = new int[n+1];
        for (int i = 1; i <= n; i++) {
          cost[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < k; i++) {
          st = new StringTokenizer(br.readLine());
          int prev = Integer.parseInt(st.nextToken());
          int next = Integer.parseInt(st.nextToken());
          rev.get(next).add(prev);
        }
        int start = Integer.parseInt(br.readLine());
        visit[start] = true;
        revdfs(start);
        
        int[] dp = new int[n+1];
        while (!que.isEmpty()) {
          int node = que.poll();
          for (int adj : rev.get(node)) {
            if (!visit[adj]) continue;
            dp[node] = Integer.max(dp[node], dp[adj]);
          }
          dp[node] += cost[node];
        }
        bw.write(Integer.toString(dp[start]));
        bw.newLine();
      }
      bw.flush();
    }
    static void revdfs(int v) {
      for (int adj : rev.get(v)) {
        if (visit[adj]) continue;
        visit[adj] = true;
        revdfs(adj);
      }
      que.add(v);
    }
  }
}


