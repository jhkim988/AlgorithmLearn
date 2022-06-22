import java.io.*;
import java.util.*;

public class BOJ11062 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }
      // int answer = bottomUp(arr);
      int answer = topDown(arr);
      bw.write(Integer.toString(answer));
      bw.newLine();
    }
    bw.flush();
  }
  static int bottomUp(int[] arr) {
    int n = arr.length;
    int[][][] dp = new int[n][n][2];
    for (int i = 0; i < n; i++) {
      dp[i][i][0] = dp[i][i][1] =  arr[i];
    }
    for (int i = 0; i < n-1; i++) {
      dp[i][i+1][0] = arr[i];
      dp[i][i+1][1] = arr[i+1];
    }
    for (int len = 3; len <= n; len++) {
      for (int i = 0; i+len-1 < n; i++) {
        dp[i][i+len-1][0] += arr[i];
        if (dp[i+1][i+len-1][0] < dp[i+1][i+len-1][1]) {
          dp[i][i+len-1][0] += Integer.max(dp[i+1][i+len-2][0], dp[i+1][i+len-2][1]);
        } else {
          dp[i][i+len-1][0] += Integer.max(dp[i+2][i+len-1][0], dp[i+2][i+len-1][1]);
        }
        dp[i][i+len-1][1] += arr[i+len-1];
        if (dp[i][i+len-2][0] < dp[i][i+len-2][1]) {
          dp[i][i+len-1][1] += Integer.max(dp[i][i+len-3][0], dp[i][i+len-3][1]);
        } else {
          dp[i][i+len-1][1] += Integer.max(dp[i+1][i+len-2][0], dp[i+1][i+len-2][1]);
        }
      }
    }
    return Integer.max(dp[0][n-1][0], dp[0][n-1][1]);
  }
  static int topDown(int[] arr) {
    int n = arr.length;
    int[][] dp = new int[n][n];
    return recur(arr, dp, 0, 0, n-1);
  }
  static int recur(int[] arr, int[][] dp, int depth, int left, int right) {
    if (left > right) return 0;
    if (dp[left][right] != 0) return dp[left][right];
    int ret = 0;
    if (depth % 2 == 0) {
      ret = Integer.max(arr[left] + recur(arr, dp, depth+1, left+1, right), arr[right] + recur(arr, dp, depth+1, left, right-1));
    } else {
      ret = Integer.min(recur(arr, dp, depth+1, left+1, right), recur(arr, dp, depth+1, left, right-1));
    }
    return dp[left][right] = ret;
  }
}