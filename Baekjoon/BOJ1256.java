import java.io.*;
import java.util.*;
import java.math.*;

public class BOJ1256 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); // a
    int m = Integer.parseInt(st.nextToken()); // z
    BigInteger k = new BigInteger(st.nextToken());
    BigInteger[][] dp = new BigInteger[n+m+1][n+m+1];
    dp[1][0] = dp[1][1] = BigInteger.ONE;
    for (int i = 2; i <= n+m; i++) {
      dp[i][0] = dp[i][i] = BigInteger.ONE;
      for (int j = 1; j < i; j++) {
        dp[i][j] = dp[i-1][j-1].add(dp[i-1][j]);
      }
    }
    if (dp[n+m][n].compareTo(k) < 0) {
      bw.write("-1\n");
      bw.flush();
      return;
    }
    int useA = 0;
    int useZ = 0;
    BigInteger rank = BigInteger.ZERO;
    char[] answer = new char[n+m];
    for (int i = 0; i < n+m; i++) {
      if (useZ >= m) {
        answer[i] = 'a';
        continue;
      }
      if (useA >= n) {
        answer[i] = 'z';
        continue;
      }
      if (rank.add(dp[n+m-useZ-useA-1][n-useA-1]).subtract(k).compareTo(BigInteger.ZERO) >= 0) {
        answer[i] = 'a';
        useA++;
      } else {
        answer[i] = 'z';
        rank = rank.add(dp[n+m-useZ-useA-1][n-useA-1]);
        useZ++;
      }
    }
    bw.write(answer);
    bw.newLine();
    bw.flush();
  }
}
