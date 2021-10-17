import java.io.*;

public class BOJ1309 {
  public static void main(String[] args) throws IOException {
    final int remainder = 9901;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[][] dp = new int[N + 1][3]; // 0: No, 1: left, 2: right
    dp[1][0] = dp[1][1] = dp[1][2] = 1;
    for (int i = 2; i <= N; i++) {
      for (int j = 0; j < 3; j++) {
        dp[i][0] = (dp[i][0] + dp[i - 1][j]) % remainder;
      }
      dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % remainder;
      dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % remainder;
    }
    int sum = 0;
    for (int i = 0; i < 3; i++) {
      sum = (sum + dp[N][i]) % remainder;
    }
    bw.write(sum + "\n");
    bw.flush();
  }
}
