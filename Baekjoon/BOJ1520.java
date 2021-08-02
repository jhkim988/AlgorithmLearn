import java.io.*;
import java.util.*;

public class BOJ1520 {
  static int numPath(int[][] map) {
    int N = map.length;
    int M = map[0].length;

    if (N == 1 || M == 1) {
      return 1;
    } 

    int[][] dp = new int[N][M]; // Goal: dp[N - 1][M - 1]
    // dp[i][j]: (i, j) to Goal
    // dp[i][j] = dp[i - 1][j] + dp[i + 1][j] + dp[i][j - 1] + dp[i][j + 1]

    // 1. down and right move
    for (int i = N - 2; i >= 0; i--) {
      if (map[i][M - 1] > map[i + 1][M - 1]) {
        dp[i][M - 1]++;
      } else {
        break;
      }
    }

    for (int i = M - 2; i >= 0; i--) {
      if (map[N - 1][i] > map[N - 1][i + 1]) {
        dp[N - 1][i]++;
      } else {
        break;
      }
    }

    for (int j = M - 2; j >= 0; j--) {
      for (int i = N - 2; i >= 0; i--) {
        if (map[i][j + 1] > 0 && map[i][j] > map[i][j + 1]) {
          dp[i][j]++;
        }
        if (map[i + 1][j] > 0 && map[i][j] > map[i + 1][j]) {
          dp[i][j]++;
        }
      }
    }

    // 2. up and left move
    for (int i = N - 1; i > 0; i--) {
      for (int j = M - 2; j > 0; j--) {
        if (map[i - 1][j] > 0 && map[i][j] > map[i - 1][j]) {
          dp[i][j]++;
        }
      }
    }

    for (int i = N - 2; i > 0; i--) {
      for (int j = M - 1; j > 0; j--) {
        if (map[i][j - 1] > 0 && map[i][j] > map[i][j - 1]) {
          dp[i][j]++;
        }
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(dp[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();

    return dp[0][0];
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
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
