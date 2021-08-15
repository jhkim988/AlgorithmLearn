import java.io.*;
import java.util.*;

public class BOJ2618 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N;
  static int W;
  static Pair[] cases;
  static int[][] dp;
  private static class Pair {
    int i;
    int j;
    Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
    int dist(Pair other) {
      return Math.abs(this.i - other.i) + Math.abs(this.j - other.j);
    }
  }
  public static void main(String[] args) throws IOException {
    N = Integer.parseInt(br.readLine());
    W = Integer.parseInt(br.readLine());
    cases = new Pair[W + 1];
    for (int i = 1; i <= W; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      cases[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    dp = new int[W + 1][W + 1];
    for (int i = 0; i <= W; i++) {
      for (int j = 0; j <= W; j++) {
        dp[i][j] = -1;
      }
    }
    bw.write(recur(0, 0) + "\n");
    traceBack(0, 0);
    bw.flush();
  }
  static int recur(int x, int y) {
    if (x == W || y == W) {
      return 0;
    }
    if (dp[x][y] != -1) {
      return dp[x][y];
    }

    int next = Math.max(x, y) + 1;
    int dist1;
    int dist2;

    if (x == 0) {
      dist1 = cases[next].dist(new Pair(1, 1));
    } else {
      dist1 = cases[next].dist(cases[x]);
    }

    if (y == 0) {
      dist2 = cases[next].dist(new Pair(N, N));
    } else {
      dist2 = cases[next].dist(cases[y]);
    }

    return dp[x][y] =  Math.min(recur(next, y) + dist1, recur(x, next) + dist2);
  }
  static void traceBack(int x, int y) throws IOException {
    if (x == W || y == W) {
      return;
    }
    int next = Math.max(x, y) + 1;
    int dist1;
    int dist2;

    if (x == 0) {
      dist1 = cases[next].dist(new Pair(1, 1));
    } else {
      dist1 = cases[next].dist(cases[x]);
    }

    if (y == 0) {
      dist2 = cases[next].dist(new Pair(N, N));
    } else {
      dist2 = cases[next].dist(cases[y]);
    }

    if (dp[next][y] + dist1 > dp[x][next] + dist2) {
      bw.write("2\n");
      traceBack(x, next);
    } else {
      bw.write("1\n");
      traceBack(next, y);
    }
  }
}