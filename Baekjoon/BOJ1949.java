import java.io.*;
import java.util.*;

public class BOJ1949 {
  static int N;
  static int[] population;
  static ArrayList<Queue<Integer>> graph;
  static int[][] dp;
  static boolean[] marked;
  static int root = 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());
    population = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      population[i] = Integer.parseInt(st.nextToken());
    }
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      graph.get(end).add(start);
    }
    // dp[i][0] = max population of subtree with root i, contain root
    // dp[i][1] = max population of subtree with root i, not contain root
    dp = new int[N + 1][2];
    marked = new boolean[N + 1];
    bw.write(solve() + "\n");
    bw.flush();

    // for (int i = 1; i <= N; i++) {
    //   System.out.println(dp[i][0] + " " + dp[i][1]);
    // }
  }
  static int solve() {
    if (N == 1) {
      return population[root];
    }
    return recur(root, -1);
  }
  static int recur(int crnt, int prev) {
    if (marked[crnt]) { // memo
      return Math.max(dp[crnt][0], dp[crnt][1]);
    }
    marked[crnt] = true;
    dp[crnt][0] = population[crnt];    
    if (graph.get(crnt).size() == 1 && crnt != root) {
      // leaf node
      return dp[crnt][0];
    }
    
    for (int next : graph.get(crnt)) {
      if (next == prev) continue;
      recur(next, crnt);
      dp[crnt][0] += dp[next][1];
      dp[crnt][1] += Math.max(dp[next][0], dp[next][1]);
    }
    return Math.max(dp[crnt][0], dp[crnt][1]);
  }
}
