import java.io.*;
import java.util.*;

public class BOJ20293 {
  private static class GasStation {
    int row, col, gas;
    GasStation(int row, int col, int gas) {
      this.row = row;
      this.col = col;
      this.gas = gas;
    }
    int dist(GasStation other) {
      return Math.abs(row - other.row) + Math.abs(col - other.col);
    }
  }  
  static int R, C;
  static int[][] map, dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(br.readLine());

    GasStation[] station = new GasStation[n + 2];
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int f = Integer.parseInt(st.nextToken());
      station[i] = new GasStation(r, c, f);
    }
    station[0] = new GasStation(1, 1, 0);
    station[n + 1] = new GasStation(R, C, 0);
    Arrays.sort(station, (a, b) -> (a.row + a.col) - (b.row + b.col));
    int lo = 0;
    int hi = R + C;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!check(mid, station)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int init, GasStation[] station) {
    int[] dp = new int[station.length];
    Arrays.fill(dp, -1);
    dp[0] = init;
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp.length; j++) {
        if (i == j) continue;
        if (station[i].row < station[j].row || station[i].col < station[j].col) continue;
        if (station[i].dist(station[j]) > dp[j]) continue;
        dp[i] = Integer.max(dp[j] - station[i].dist(station[j]) + station[i].gas, dp[i]);
      }
    }
    return dp[station.length - 1] >= 0;
  }
}
