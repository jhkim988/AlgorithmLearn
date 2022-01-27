import java.io.*;
import java.util.*;

public class BOJ2056 {
  public static void main(String[] args) throws IOException {
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
}
