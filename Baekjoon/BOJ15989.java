import java.io.*;

public class BOJ15989 {
  static final int len = 10_001;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    long[][] dp = new long[len][2];
    init(dp);
    while (numTest-- > 0 ) {
      int input = Integer.parseInt(br.readLine());
      bw.write(dp[input] + "\n");
    }
    bw.flush();
  }
  static void init(long[][] dp) {
    dp[1][0] = 1;
    dp[2][0] = dp[2][1] = 1;
    dp[3][0] = 3;
    for (int i = 4; i < len; i++) {
      dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
    }
  }
}
