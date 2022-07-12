import java.io.*;
import java.util.*;

public class BOJ13392 {
  static final int INF = Integer.MAX_VALUE/2;
  static int n;
  static char[] start, end;
  static int[][] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    start = br.readLine().toCharArray();
    end = br.readLine().toCharArray();
    dp = new int[n][10];
    for (int i = 0; i < n; i++) Arrays.fill(dp[i], INF);
    int answer = recur(0, 0);
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static int recur(int depth, int leftRot) {
    if (depth == n) return 0;
    if (dp[depth][leftRot%10] != INF) return dp[depth][leftRot%10];
    int diff = (end[depth]-start[depth]-leftRot%10+20)%10;
    int min = Integer.min(recur(depth+1, leftRot+diff)+diff, recur(depth+1, leftRot)+(10-diff)%10);
    return dp[depth][leftRot%10] = Integer.min(dp[depth][leftRot%10], min);
  }
}
