import java.io.*;

public class BOJ2133 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    if (N % 2 != 0) {
      bw.write("0\n");
      bw.flush();
      return;
    } else if (N == 2) {
      bw.write("3\n");
      bw.flush();
      return;
    }
    int[][] dp = new int[N + 1][3]; // dp[i][j]: length i, last type j
    dp[0][0] = 1;
    dp[1][1] = 1;
    dp[1][2] = 1;
    dp[2][0] = 3;
    for (int i = 3; i <= N; i++) {
      dp[i][0] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 2][0];
      dp[i][1] = dp[i - 1][0] + dp[i - 2][1];
      dp[i][2] = dp[i - 1][0] + dp[i - 2][2];
    }
    bw.write(dp[N][0] + "\n");
    bw.flush(); 
  }
}