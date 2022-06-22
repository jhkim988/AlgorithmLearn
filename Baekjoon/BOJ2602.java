import java.io.*;

public class BOJ2602 {
  static char[] scroll, left, right;
  static int[][][] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    scroll = br.readLine().toCharArray();
    left = br.readLine().toCharArray();
    right = br.readLine().toCharArray();
    dp = new int[scroll.length][left.length][2];
    int num = 0;
    for (int i = 0; i < left.length; i++) {
      if (left[i] != scroll[0]) continue;
      num += recur(1, i+1, false);
    }
    for (int i = 0; i < right.length; i++) {
      if (right[i] != scroll[0]) continue;
      num += recur(1, i+1, true);
    }
    bw.write(Integer.toString(num));
    bw.flush();
  }
  static int recur(int scrollIdx, int startIdx, boolean isLeft) {
    if (scrollIdx == scroll.length) return 1;
    if (startIdx == left.length) return 0;
    if (dp[scrollIdx][startIdx][isLeft ? 0 : 1] != 0) return dp[scrollIdx][startIdx][isLeft ? 0 : 1];
    int ret = 0;
    if (isLeft) {
      for (int i = startIdx; i < left.length; i++) {
        if (left[i] != scroll[scrollIdx]) continue;
        ret += recur(scrollIdx+1, i+1, false);
      }
    } else {
      for (int i = startIdx; i < right.length; i++) {
        if (right[i] != scroll[scrollIdx]) continue;
        ret += recur(scrollIdx+1, i+1, true);
      } 
    }
    return dp[scrollIdx][startIdx][isLeft ? 0 : 1] = ret;
  }
}
