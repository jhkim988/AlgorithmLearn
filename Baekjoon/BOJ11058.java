import java.io.*;

public class BOJ11058 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    if (N <= 6) {
      bw.write(N + "\n");
      bw.flush();
      return;
    }
    long[] dp = new long[N + 1];
    for (int i = 1; i <= 6; i++) dp[i] = i;
    for (int i = 7; i <= N; i++) {
      for (int j = 3; j < i; j++) {
        dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
      }
    }
    bw.write(dp[N] + "\n");
    bw.flush();
  }
}
