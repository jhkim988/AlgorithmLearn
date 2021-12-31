import java.io.*;

public class BOJ10422 {
  static final int MAX_LEN = 5_000/2;
  static final int REMAINDER = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    long[] dp = new long[MAX_LEN + 1];
    dp[0] = dp[1] = 1L;
    for (int i = 2; i <= MAX_LEN; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] += (dp[j]*dp[i - j - 1]) % REMAINDER;
        dp[i] %= REMAINDER;
      }  
    }
    while (numTest-- > 0) {
      int input = Integer.parseInt(br.readLine());
      if (input % 2 == 1) bw.write("0\n");
      else bw.write(dp[input/2] + "\n");
    }
    bw.flush();
  }  
}
