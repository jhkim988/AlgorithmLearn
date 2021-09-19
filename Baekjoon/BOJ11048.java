import java.io.*;
import java.util.*;

public class BOJ11048 {
  public static void main(String[] args) throws IOException {
    int[] rowDi = {0, -1, -1};
    int[] colDi = {-1, 0, -1};
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[][] map = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] dp = new int[N + 1][M + 1];
    dp[1][1] = map[1][1];

    for (int idxSum = 3; idxSum <= N + M; idxSum++) {
      for (int row = 1; row < idxSum; row++) {
        if (row > N || idxSum - row > M) continue;
        for (int tmp = 0; tmp < 3; tmp++) {
          int prevRow = row + rowDi[tmp];
          int prevCol = idxSum - row + colDi[tmp];
          if (prevRow <= 0 || prevCol <= 0) continue;
          dp[row][idxSum - row] = Math.max(dp[row][idxSum - row], dp[prevRow][prevCol] + map[row][idxSum - row]);
        }
      }
    }

    bw.write(dp[N][M] + "\n");
    bw.flush();
  }
}
