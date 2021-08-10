import java.io.*;
import java.util.*;

public class BOJ11660 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[][] data = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] dp = new int[N + 1][N + 1];
    dp[1][1] = data[0][0];
    for (int i = 1; i <= N; i++) {
      dp[1][i] = dp[1][i - 1] + data[0][i - 1];
      dp[i][1] = dp[i - 1][1] + data[i - 1][0];
    }

    for (int i = 2; i <= N; i++) {
      for (int j = 2; j <= N; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + data[i - 1][j - 1];
      }
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      int result = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
      bw.write(result + "\n");
    }
    bw.flush();
  }
}
