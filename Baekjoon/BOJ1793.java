import java.io.*;
import java.math.*;

public class BOJ1793 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BigInteger[] dp = new BigInteger[251];
    dp[0] = BigInteger.ONE; dp[1] = BigInteger.ONE;
    for (int i = 2; i <= 250; i++) {
      dp[i] = dp[i-1].add(dp[i-2].multiply(BigInteger.TWO));
    }
    String input;
    while ((input = br.readLine()) != null) {
      int n = Integer.parseInt(input);
      bw.write(dp[n].toString());
      bw.newLine();
    }
    bw.flush();
  }
}
