import java.io.*;

public class BOJ14852 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final long D = 1_000_000_007;
    int n = Integer.parseInt(br.readLine());
    long[][] dp = new long[n+1][3];
    dp[0][0] = 1;
    dp[1][0] = 2;
    dp[1][1] = 1;
    dp[1][2] = 1;
    for (int i = 2; i <= n; i++) {
      dp[i][0] = (((2*dp[i-1][0]%D + dp[i-1][1])%D + dp[i-1][2])%D + dp[i-2][0])%D;
      dp[i][1] = (dp[i-1][0] + dp[i-1][2])%D;
      dp[i][2] = (dp[i-1][0] + dp[i-1][1])%D;
    }
    bw.write(Long.toString(dp[n][0]%D));
    bw.flush();
  }  
}
