import java.io.*;
import java.util.*;

public class BOJ14501 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    int[][] schedule = new int[N + 1][2];
    schedule[0][0] = 1;
    for (int i = 1; i <= N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 2; j++) {
        schedule[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    // bw.write(bruteForce(schedule, N) + "\n");
    bw.write(dynamicProgramming(schedule, N) + "\n");
    bw.flush();
  }
  static int bruteForce(int[][] schedule, int N) {
    return recur(0, 0, schedule, N);
  }
  static int recur(int crnt, int sum, int[][] schedule, int N) {
    if (crnt > N) {
      return sum;
    }
    // System.out.println("crnt: " + crnt + " / sum: " + sum);
    int max = 0;
    for (int i = crnt; i <= N; i++) {
      int val;
      if (i + schedule[i][0] <= N + 1) {
        val = recur(i + schedule[i][0], sum + schedule[i][1], schedule, N);
      } else {
        val = sum;
      }
      if (max < val) {
        max = val;
      }
    }
    return max;
  }
  static int dynamicProgramming(int[][] schedule, int N) {
    int[] dp = new int[N + 2];

    // initialize
    int firstIdx = 0;
    for (int i = N; i >= 1; i--) {
      if (schedule[i][0] + i <= N + 1) {
        firstIdx = i;
        break;
      }
    }
    // System.out.println(firstIdx);
    if (firstIdx < 1) {
      return 0;
    }
    dp[firstIdx] = schedule[firstIdx][1];

    for (int i = firstIdx - 1; i >= 1; i--) {
      if (i + schedule[i][0] <= N + 1) {
        dp[i] = Math.max(dp[i + schedule[i][0]] + schedule[i][1], dp[i + 1]);
      } else {
        dp[i] = dp[i + 1];
      }
    }
    // System.out.println(Arrays.toString(dp));
    return dp[1];
  }
}
