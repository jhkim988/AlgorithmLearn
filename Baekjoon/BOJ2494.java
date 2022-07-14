import java.io.*;
import java.util.*;

public class BOJ2494 {
  static int n;
  static char[] start, end;
  static int[][] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    start = br.readLine().toCharArray();
    end = br.readLine().toCharArray();
    dp = new int[n][10]; for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
    bw.write(Integer.toString(recur(0, 0)));
    bw.newLine();
    int left = 0;
    for (int i = 0; i < n; i++) {
      int diff = (end[i]-start[i]-left%10+20)%10;
      int hist = 0;
      if (i+1 < n) {
        if (dp[i+1][(left+diff)%10]+diff < dp[i+1][left]+(10-diff)%10) {
          hist = diff;
          left += diff; left %= 10;
        } else {
          hist = -((10-diff)%10);
        }
      } else {
        hist = diff < (10-diff)%10 ? diff : -((10-diff)%10);
      }
      bw.write(Integer.toString(i+1));
      bw.write(' ');
      bw.write(Integer.toString(hist));
      bw.newLine();
    }

    bw.flush();
  }
  static int recur(int depth, int left) {
    if (depth >= n) return 0;
    if (dp[depth][left%10] != -1) return dp[depth][left%10];
    int diff = (end[depth]-start[depth]-left%10 + 20)%10;
    int leftPick = recur(depth+1, left+diff) + diff;
    int rightPick = recur(depth+1, left) + (10-diff)%10;
    return dp[depth][left%10] = Integer.min(leftPick, rightPick);
  }
}