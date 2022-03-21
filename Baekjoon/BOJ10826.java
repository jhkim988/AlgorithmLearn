import java.io.*;
import java.math.*;

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
    BigInteger[] dp = new BigInteger[n+1];
    dp[0] = BigInteger.ZERO;
    dp[1] = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i-1].add(dp[i-2]);
    }
    bw.write(dp[n].toString());
    bw.newLine();
    bw.flush();
  }
}
