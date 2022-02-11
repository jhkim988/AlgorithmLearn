import java.io.*;

public class BOJ2482 {
  static int N;
  static int K;
  static long[][][] dp;
  static final long REMAINDER = 1_000_000_003;
    public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());

    long sum = 0;
    // dp[num color][num pick][hasFirst] = num case
    dp = new long[N + 1][K + 1][2];
    for (int flag = 0; flag < 2; flag++) {
      for (int i = 1; i <= N; i++) {
        if (flag == 0 && i != 1) {
          dp[i][1][flag] = i - 1;
        }
        if (flag == 1) {
          dp[i][1][flag] = 1;
        }
      }
      for (int i = 2; i < N; i++) {
        for (int j = 2; j <= K; j++) {
          dp[i][j][flag] = (dp[i - 1][j][flag] + dp[i - 2][j - 1][flag]) % REMAINDER;
        }
      }
      for (int j = 2; j <= K; j++) {
        if (flag == 0) {
          dp[N][j][flag] = (dp[N - 1][j][flag] + dp[N - 2][j - 1][flag]) % REMAINDER;
        } else {
          dp[N][j][flag] = dp[N - 1][j][flag];
        }
      }
      sum = (sum + dp[N][K][flag]) % REMAINDER;
    }
    bw.write(sum + "\n");
    bw.flush();
  }
}
