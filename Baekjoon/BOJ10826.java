import java.io.*;

public class BOJ10826 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    if (n < 2) {
      bw.write(Integer.toString(n));
      bw.newLine();
      bw.flush();
      return;
    }
    long[] dp = new long[n+1];
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i-1] + dp[i-2];
    }
    bw.write(Long.toString(dp[n]));
    bw.newLine();
    bw.flush();
  }
}
