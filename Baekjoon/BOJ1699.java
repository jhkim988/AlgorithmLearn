import java.io.*;
// CAN NOT SOLVE GREEDLY.
public class BOJ1699 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] dp = new int[N + 1];
    dp[1] = 1;
    for (int i = 2; i <= N; i++) {
      dp[i] = i;
      for (int j = 1; j * j <= i; j++) {
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
      }
    }
    bw.write(dp[N] + "\n");
    bw.flush();
  }
}
