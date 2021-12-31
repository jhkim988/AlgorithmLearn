import java.io.*;

public class BOJ15989 {
  static final int len = 10_001;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    long[][] dp = new long[len][3];
    init(dp);
    while (numTest-- > 0 ) {
      int input = Integer.parseInt(br.readLine());
      int sum = 0;
      for (int i = 0; i < 3; i++) sum += dp[input][i];
      bw.write(sum + "\n");
    }
    bw.flush();
  }
  static void init(long[][] dp) {
    dp[1][0] = 1;
    dp[2][0] = dp[2][1] = 1;
    dp[3][0] = dp[3][1] = dp[3][2] = 1;
    for (int i = 4; i < len; i++) {
      dp[i][0] = dp[i - 1][0];
      dp[i][1] = dp[i - 2][0] + dp[i - 2][1];
      dp[i][2] = dp[i - 3][0] + dp[i - 3][1] + dp[i - 3][2];
    }
  }
}
