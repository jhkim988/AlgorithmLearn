import java.io.*;
import java.util.*;

public class BOJ1102 {
  static final int INF = Integer.MAX_VALUE/2;
  static int n, p, min;
  static int[] dp;
  static int[][] cost;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    cost = new int[n][n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        cost[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    dp = new int[1 << n];
    int num = 0;
    int bitStat = 0;
    char[] stat = br.readLine().toCharArray();
    p = Integer.parseInt(br.readLine());
    for (int i = 0; i < n; i++) {
      if (stat[i] == 'N') continue;
      num++;
      bitStat |= (1 << i);
    }
    Arrays.fill(dp, INF);
    min = INF;
    int answer = recur(num, bitStat, 0);
    bw.write(Integer.toString(answer == INF ? -1 : answer));
    bw.flush();
  }
  static int recur(int num, int bitStat, int consume) {
    if (dp[bitStat] != INF) return dp[bitStat];
    if (num >= p) {
      min = Integer.min(min, consume);
      return dp[bitStat] = 0;
    }
    if (consume >= min) return INF;
    int ret = INF;
    for (int i = 0; i < n; i++) {
      if ((bitStat & (1<<i)) != 0) continue;
      int minCost = INF;
      for (int j = 0; j < n; j++) {
        if ((bitStat & (1<<j)) == 0) continue;
        if (cost[j][i] < minCost) minCost = cost[j][i];
      }
      if (minCost == INF) continue;
      int recur = recur(num+1, bitStat | (1<<i), consume+minCost);
      ret = Integer.min(ret,  recur + minCost);
    }
    return dp[bitStat] = ret;
  }
}
