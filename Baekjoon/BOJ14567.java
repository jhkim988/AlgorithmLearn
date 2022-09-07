import java.io.*;
import java.util.*;

public class BOJ14567 {
  static ArrayList<Queue<Integer>> graph;
  static int[] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(end).add(start);
    }

    dp = new int[n+1];
    for (int node = n; node >= 1; node--) {
      if (dp[node] != 0) continue;
      dfs(node);
    }

    for (int i = 1; i <= n; i++) {
      bw.write(Integer.toString(dp[i]));
      bw.write(' ');
    }
    bw.flush();
  }
  static int dfs(int node) {
    int max = 0;
    for (int adj: graph.get(node)) {
      if (dp[adj] == 0) dfs(adj);
      max = Integer.max(max, dp[adj]);
    }
    return dp[node] = max+1;
  }
}
