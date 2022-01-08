import java.io.*;
import java.util.*;

public class BOJ1695 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] input = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }

    int[][] dp = new int[N][N];
    for (int i = 0; i < N; i++) Arrays.fill(dp[i], N);
    for (int i = 0; i < N - 1; i++) {
      dp[i][i] = 0;
      if (input[i] == input[i + 1]) dp[i][i + 1] = 0;
      else dp[i][i + 1] = 1;
    }
    dp[N - 1][N - 1] = 0;

    for (int diff = 2; diff < N; diff++) {
      for (int i = 0; i + diff < N; i++) {
        if (input[i] == input[i + diff]) {
          dp[i][i + diff] = dp[i + 1][i + diff - 1];
        } else {
          dp[i][i + diff] = Math.min(dp[i][i + diff - 1], dp[i + 1][i + diff]) + 1;
        }
      }
    }

    bw.write(Integer.toString(dp[0][N - 1]));
    bw.newLine();
    bw.flush();
  }
}
