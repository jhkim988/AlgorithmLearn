import java.io.*;
import java.util.*;

public class BOJ10217 {
  static final int INF = 10_000_001;  
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N;
  static int M;
  static int K;
  static ArrayList<Queue<Pair>> graph;
  static int minTime;
  private static class Pair implements Comparable<Pair> {
    int end;
    int cost;
    int time;
    Pair(int end, int cost, int time) {
      this.end = end;
      this.cost = cost;
      this.time = time;
    }
    @Override
    public int compareTo(Pair other) {
      return this.time - other.time;
    }
  }
  public static void main(String[] args) throws IOException {
    int numTest = Integer.parseInt(br.readLine());
    for (int i = 0; i < numTest; i++) {
      input();
      minTime();
      if (minTime >= INF) {
        bw.write("Poor KCM\n");
      } else {
        bw.write(minTime + "\n");
      }
    }
    bw.flush();
  }
  static void input() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      int time = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, cost, time));
    }
  }
  static void minTime() {
    minTime = INF;
    int[][] dp = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      for (int j = 0; j <= M; j++) {
        dp[i][j] = INF;
      }
    }
    dp[1][0] = 0;
    PriorityQueue<Pair> que = new PriorityQueue<>();
    Pair start = new Pair(1, 0, 0);
    que.add(start);
    
    while (!que.isEmpty()) {
      Pair crnt = que.poll();

      if (crnt.end == N) {
        minTime = crnt.time;
        break;
      }
      if (crnt.cost > M) {
        continue;
      }
      if (dp[crnt.end][crnt.cost] < crnt.time) {
        continue;
      }

      for (Pair next : graph.get(crnt.end)) {
        int cost = crnt.cost + next.cost;
        int time = crnt.time + next.time;
        if (cost > M) {
          continue;
        }
        if (dp[next.end][cost] > time) {
          for (int j = cost + 1; j <= M; j++) {
            if (dp[next.end][j] <= time) break;
            dp[next.end][j] = time;
          }
          dp[next.end][cost] = time;
          que.add(new Pair(next.end, cost, time));          
        }        
      }
    }
  }
}
