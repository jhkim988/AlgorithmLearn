import java.io.*;

public class BOJ2011 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    char[] input = br.readLine().toCharArray();
    long[] dp = new long[input.length];
    if (input[0] == '0') {
      bw.write("0\n");
      bw.flush();
      return;
    } 

    dp[0] = 1;
    if ((input[0] >= '3' || input[0] == '0') && input[1] == '0') {
      fail();
      return;
    }
    if (input[0] == '1' || (input[0] == '2' && input[1] <= '6')) {
      dp[1] = 2;
    } else {
      dp[1] = 1;
    }
    for (int i = 2; i < input.length; i++) {
      if ((input[i-1] >= '3' || input[i-1] == '0') && input[i] == '0') {
        fail();
        return;
      }
      if (input[i-1] == '1' || (input[i-1] == '2' && input[i] <= '6')) {
        dp[i] = (dp[i-1] + dp[i-2]) % 1_000_000;
      } else {
        dp[i] = dp[i-1];
      }
    }

    bw.write(Long.toString(dp[input.length-1]));
    bw.newLine();
    bw.flush();
  }
  static void fail() throws IOException {
    bw.write("0\n");
    bw.flush();
    return;
  }
}