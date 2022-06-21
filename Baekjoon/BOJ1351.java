import java.io.*;
import java.util.*;

public class BOJ1351 {
  static int p, q;
  static long[] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long n = Long.parseLong(st.nextToken());
    p = Integer.parseInt(st.nextToken());
    q = Integer.parseInt(st.nextToken());
    int len = (int) Long.min(1_000_001, n+1);
    dp = new long[len];
    dp[0] = 1;
    for (int i = 1; i < len; i++) {
      dp[i] = dp[i/p] + dp[i/q];
    }
    long answer = recur(n);
    bw.write(Long.toString(answer));
    bw.flush();
  }
  static long recur(long n) {
    if (n < dp.length && dp[(int) n] != 0) return dp[(int) n];
    return recur(n/p) + recur(n/q);
  }
}
