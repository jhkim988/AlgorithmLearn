import java.io.*;
import java.util.*;

public class BOJ2624 {
  static int[] sum;
  static int[][] dp;
  private static class Pair {
    int kind, num;
    Pair(int kind, int num) {
      this.kind = kind;
      this.num = num;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    int k = Integer.parseInt(br.readLine());
    Pair[] arr = new Pair[k];
    for (int i = 0; i < k; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int kind = Integer.parseInt(st.nextToken());
      int num = Integer.parseInt(st.nextToken());
      arr[i] = new Pair(kind, num);
    }

    bw.write(Integer.toString(answer(t, k, arr)));
    bw.flush();
  }
  static int answer(int t, int k, Pair[] arr) {
    // sum = new int[k];
    // dp = new int[t+1][k+1];
    // sum[k-1] = arr[k-1].kind*arr[k-1].num;
    // for (int i = k-2; i >= 0; i--) {
    //   sum[i] = sum[i+1] + arr[i].kind*arr[i].num;
    // }
    // return recur(t, 0, 0, arr);

    return nonRecur(t, k, arr);
  }
  static int recur(int n, int acc, int depth, Pair[] arr) {
    if (dp[acc][depth] != 0) return dp[acc][depth];
    if (n == acc) return 1;
    if (depth == arr.length) return 0;
    int ret = 0;
    int val = acc;
    int copy = arr[depth].num;
    if (val + (depth+1 < arr.length ? sum[depth+1] : 0) >= n) ret += recur(n, val, depth+1, arr);
    while (arr[depth].num > 0 && val + arr[depth].kind <= n) {
      val += arr[depth].kind;
      arr[depth].num--;
      if (val + (depth+1 < arr.length ? sum[depth+1] : 0) < n) continue;
      ret += recur(n, val, depth+1, arr);
    }
    arr[depth].num = copy;
    return dp[acc][depth] = ret;
  }
  static int nonRecur(int t, int k, Pair[] arr) {
    Arrays.sort(arr, (a, b) -> {
      return a.kind != b.kind ? a.kind-b.kind : a.num-b.num;
    });
    dp = new int[t+1][k];
    for (int j = 0; j <= arr[0].num && arr[0].kind*j <= t; j++) {
      dp[arr[0].kind*j][0]++;
    }
    for (int i = 1; i < k; i++) {
      for (int acc = 0; acc <= t; acc++) {
        for (int j = 0; j <= arr[i].num && acc+arr[i].kind*j <= t; j++)
        dp[acc+arr[i].kind*j][i] += dp[acc][i-1];
      }
    }
    return dp[t][k-1];
  }
}