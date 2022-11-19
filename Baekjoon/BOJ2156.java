import java.io.*;
import java.util.*;

public class BOJ2156 {
  private static int n;
  private static int[] arr;
  private static int[][] dp;
  private static final int INF = Integer.MAX_VALUE >> 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    dp = new int[3][n];
    for (int i = 0; i < 3; i++) {
      Arrays.fill(dp[i], -1);
    }
    int max = 0;
    for (int i = 0; i < 3; i++) {
      max = Integer.max(max, recur(0, i));
    }
    bw.write(Integer.toString(max));
    bw.flush();
  }
  private static int recur(int pos, int numConti) {
    if (pos >= n) return 0;
    if (numConti < 0) return -INF;
    if (dp[numConti][pos] != -1) return dp[numConti][pos];
    if (numConti == 0) {
      return dp[numConti][pos] = Integer.max(recur(pos+1, 0), Integer.max(recur(pos+1, 1), recur(pos+1, 2)));
    }
    int sum = 0;
    for (int i = 0; i < numConti && pos+i < n; i++) {
      sum += arr[pos+i];
    }
    return dp[numConti][pos] = recur(pos + numConti, 0) + sum;
  }
}