import java.io.*;
import java.util.*;

public class BOJ12872 {
  static int N;
  static int M;
  static int P;
  static long[][] dp;
  static  final long REMAINDER = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());
    dp = new long[P + 1][N + 1];
    for (int i = 0; i <= P; i++) Arrays.fill(dp[i], -1);
    bw.write(Long.toString(recur(0, 0)));
    bw.newLine();
    bw.flush();
  }
  static long recur(int len, int numPick) {
    if (len >= P) return dp[len][numPick] = numPick == N ? 1 : 0;
    if (dp[len][numPick] != -1) return dp[len][numPick];
    long val1 = numPick < N ? (recur(len + 1, numPick + 1) * (N - numPick)) % REMAINDER : 0;
    long val2 = numPick > M ? (recur(len + 1, numPick) * (numPick - M)) % REMAINDER : 0;
    return dp[len][numPick] = (val1 + val2) % REMAINDER;
  }
}
