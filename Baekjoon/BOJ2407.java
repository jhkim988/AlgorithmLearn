import java.io.*;
import java.math.*;
import java.util.*;
public class BOJ2407 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    BigInteger[][] dp = new BigInteger[n + 1][n + 1];

    dp[1][0] = dp[1][1] = new BigInteger("1");
    for (int i = 2; i <= n; i++) {
      dp[i][0] = new BigInteger("1");
      dp[i][i] = new BigInteger("1");
      for (int j = 1; j < i; j++) {
        dp[i][j] = (dp[i - 1][j - 1]).add(dp[i - 1][j]); 
      }
    }
    bw.write(dp[n][m] + "\n");
    bw.flush();
  }
}
