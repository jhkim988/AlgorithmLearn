import java.io.*;
import java.util.*;

public class BOJ16194 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int N = Integer.parseInt(br.readLine());
    int[] prices = new int[N + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      prices[i] = Integer.parseInt(st.nextToken());
    }

    int[] dp = new int[N + 1];
    dp[1] = prices[1];
    for (int i = 2; i <= N; i++) {
      int lim = (i + 1) / 2;
      dp[i] = prices[i];
      for (int j = 0; j <= lim; j++) {
        dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
      }
    }

    bw.write(dp[N] + "\n");
    bw.flush();
  }
}
