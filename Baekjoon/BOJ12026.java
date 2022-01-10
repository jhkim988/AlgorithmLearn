import java.io.*;
import java.util.*;

public class BOJ12026 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    char[] input = br.readLine().toCharArray();
    int[] dp = new int[N];
    final int INF = Integer.MAX_VALUE/2;
    Arrays.fill(dp, INF);
    dp[0] = 0;
    for (int i = 0; i < N; i++) {
      char crnt = input[i];
      char next = crnt == 'B' ? 'O' : (crnt == 'O' ? 'J' : 'B');
      for (int dist = 1; i + dist < N; dist++) {
        if (input[i + dist] == next) dp[i + dist] = Math.min(dp[i + dist], dp[i] + dist * dist);
      }
    }
    String answer = dp[N - 1] == INF ? "-1" : Integer.toString(dp[N-1]);
    bw.write(answer);
    bw.newLine();
    bw.flush();
  }
}
