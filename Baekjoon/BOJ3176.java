import java.io.*;
import java.util.*;

public class BOJ3176 {
  static int log;
  private static final int INF = 1_000_001;
  private static class Edge {
    int end, weight;
    Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
  }
  private static class TreeDP {
    int end, min, max;
    TreeDP(int end, int min, int max) {
      this.end = end;
      this.min = min;
      this.max = max;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    ArrayList<Queue<Edge>> graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < N - 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(a).add(new Edge(b, weight));
      graph.get(b).add(new Edge(a, weight));
    }
    while (1 << log < N) log++;
    TreeDP[][] dp = new TreeDP[log][N + 1]; // <id, weight>
    int[] level = new int[N + 1];
    boolean[] visit = new boolean[N + 1];
    dp[0][1] = new TreeDP(1, INF, 0);
    level[1] = 1;
    visit[1] = true;
    Queue<Edge> que = new LinkedList<Edge>(); // <id, level>
    que.add(new Edge(1, 1));
    while (!que.isEmpty()) {
      Edge crnt = que.poll();
      for (Edge next : graph.get(crnt.end)) {
        if (visit[next.end]) continue;
        visit[next.end] = true;
        dp[0][next.end] = new TreeDP(crnt.end, next.weight, next.weight);
        level[next.end] = crnt.weight + 1; // level
        que.add(new Edge(next.end, level[next.end]));
      }
    }
    
    for (int k = 1; k < log; k++) {
      for (int x = 1; x <= N; x++) {
        TreeDP halfStart = dp[k - 1][x];
        TreeDP halfMid = dp[k - 1][halfStart.end];
        int end = halfMid.end;
        int min = Integer.min(halfStart.min, halfMid.min);
        int max = Integer.max(halfStart.max, halfMid.max);
        dp[k][x] = new TreeDP(end, min, max);
      } 
    }

    int numQuery = Integer.parseInt(br.readLine());
    while (numQuery-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (a == b) {
        bw.write("0 0\n");
        continue;
      }
      int minB = INF, maxB = 0;
      if (level[a] != level[b]) {
        if (level[a] > level[b]) {
          // make: level[a] < level[b]
          int tmp = a;
          a = b;
          b = tmp;
        } 
        int diff = level[b] - level[a];
        for (int k = 0; k < log; k++) {
          if ((diff & (1 << k)) != 0) {
            minB = Integer.min(minB, dp[k][b].min);
            maxB = Integer.max(maxB, dp[k][b].max);
            b = dp[k][b].end;
          }
        }
        if (a == b) {
          bw.write(Integer.toString(minB));
          bw.write(' ');
          bw.write(Integer.toString(maxB));
          bw.newLine();
          continue;
        }
      }
      int minA = INF, maxA = 0;
      for (int k = log - 1; k >= 0; k--) {
        if (dp[k][a].end != dp[k][b].end) {
          minA = Integer.min(minA, dp[k][a].min);
          minB = Integer.min(minB, dp[k][b].min);
          maxA = Integer.max(maxA, dp[k][a].max);
          maxB = Integer.max(maxB, dp[k][b].max);
          a = dp[k][a].end;
          b = dp[k][b].end;
        }
      }
      minA = Integer.min(minA, dp[0][a].min);
      minB = Integer.min(minB, dp[0][b].min);
      maxA = Integer.max(maxA, dp[0][a].max);
      maxB = Integer.max(maxB, dp[0][b].max);

      bw.write(Integer.toString(Integer.min(minA, minB)));
      bw.write(' ');
      bw.write(Integer.toString(Integer.max(maxA, maxB)));
      bw.newLine();
    }
    bw.flush();
  }
}
