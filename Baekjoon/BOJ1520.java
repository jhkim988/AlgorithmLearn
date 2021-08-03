import java.io.*;
import java.util.*;

public class BOJ1520 {
  static int N;
  static int M;

  static int[] xDi = {1, 0, -1, 0};
  static int[] yDi = {0, 1, 0, -1};

  static int numPath(int[][] map) {
    if (N == 1 || M == 1) {
      return 1;
    } 
    int[][] dp = new int[N][M]; // dp[i][j]: (i, j) to Goal, Goal: dp[N - 1][M - 1]
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        dp[i][j] = -1;
      }
    }    
    return dps(map, dp, 0, 0);
  }
  static int dps(int[][] map, int[][] dp, int xIdx, int yIdx) {
    if (xIdx == N - 1 && yIdx == M - 1) {
      return 1;
    }
    dp[xIdx][yIdx] = 0;
    for (int i = 0; i < 4; i++) {
      int nextX = xIdx + xDi[i];
      int nextY = yIdx + yDi[i];
      if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
        if (map[nextX][nextY] < map[xIdx][yIdx]) {
          if (dp[nextX][nextY] != -1) {
            dp[xIdx][yIdx] += dp[nextX][nextY];
            continue; 
          }
          dp[xIdx][yIdx] += dps(map, dp, nextX, nextY);
        }
      }
    }
    return dp[xIdx][yIdx];
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int[][] map = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    bw.write(numPath(map) + "\n");
    bw.flush();
  }
}
