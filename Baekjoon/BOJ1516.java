import java.io.*;
import java.util.*;

public class BOJ1516 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] time = new int[n+1];
    int[] indegree = new int[n+1];
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) graph.add(new LinkedList<>());
    for (int v = 1; v <= n; v++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      time[v] = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      while (w != -1) {
        indegree[v]++;
        graph.get(w).add(v);
        w = Integer.parseInt(st.nextToken());
      }
    }

    int[] dp = new int[n+1];
    for (int v = 1; v <= n; v++) {
      if (indegree[v] == 0) bfs(v, graph, time, dp);
    }

    for (int v = 1; v <= n; v++) {
      bw.write(Integer.toString(dp[v]));
      bw.newLine();
    }
    bw.flush();
  }
  static void bfs(int v, ArrayList<Queue<Integer>> graph, int[] time, int[] dp) {
    Queue<Integer> que = new LinkedList<>();
    que.add(v);
    dp[v] = time[v];
    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (int adj : graph.get(crnt)) {
        if (dp[crnt] + time[adj] <= dp[adj]) continue;
        dp[adj] = dp[crnt] + time[adj]; 
        que.add(adj);
      }
    }
  }
}