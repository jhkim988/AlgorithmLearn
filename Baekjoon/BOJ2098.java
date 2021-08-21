import java.io.*;
import java.util.*;

public class BOJ2098 {
  // Integer.MAX_VALUE: W + dfs() OVERFLOW
  static int INF = Integer.MAX_VALUE / 2;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    int[][] W = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        W[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int minCost = tsp(W, N);
    bw.write(minCost + "\n");
    bw.flush();
  }
  static int tsp(int[][] W, int N) {
    int [][] cost = new int[N][1 << N];
    // cost[i][j]: "REMAINING" cost while at current node i, path j(bit)
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < (1 << N); j++) {
        cost[i][j] = -1; // not visit
      }
    }
    return dfs(0, 1, N, cost, W);
  }
  static int dfs(int node, int bit, int N, int[][] cost, int[][] W) {
    if (bit == ((1 << N) - 1)) { // All city visit
      return W[node][0] == 0 ? INF : W[node][0]; // last city != first city, or not
    }
    if (cost[node][bit] != -1) { // visit city by same way.
      return cost[node][bit];
    }
    cost[node][bit] = INF;
    for (int i = 0; i < N; i++) { // node -> i
      if (W[node][i] == 0) continue; // not connected
      if ((bit & (1 << i)) == (1 << i)) continue; // i is visited city
      cost[node][bit] = Math.min(cost[node][bit], W[node][i] + dfs(i, bit | (1 << i), N, cost, W));
      // REMAINING cost: (node -> i) + (i ~ end)
    } 
    return cost[node][bit];
  }
}
