import java.io.*;

public class BOJ16565 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());

    bw.write(Long.toString(useComb(n) % 10_007));
    bw.newLine();
    bw.flush();
  }
  static long useDP(int n) {
    long[][] dp = new long[53][53];
    dp[0][0] = 1;
    for (int i = 1; i < 53; i++) {
      dp[i][0] = 1;
      dp[i][1] = i;
      dp[i][i] = 1;
    }
    for (int i = 3; i < 53; i++) {
      for (int j = 1; j <= i; j++) {
        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
      }
    }
    long incExc = 0;
    boolean positive = true;
    for (int count = 1; count <= 13; count++) {
      if (4 * count > n) break;;
      long add = dp[52 - 4 * count][n - 4 * count] * dp[13][count];
      incExc += positive ? add : -add;
      positive = !positive;
    }
    return incExc;
  }
  static long useComb(int n) {
    long incExc = 0;
    boolean positive = true;
    for (int count = 1; count <= 13; count++) {
      long add = C(52 - 4 * count, n - 4 * count) * C(13, count);
      incExc += positive ? add : -add;
      positive = !positive;
    }
    return incExc;
  }
  static long C(long n, long k) {
    if (k < 0 || k > n) return 0;
    if (k > n/2) return C(n, n - k);
    long answer = 1;
    long ptr1 = n;
    long ptr2 = 2;
    for (int i = 0; i < k; i++) {
      answer *= ptr1--;
      while (ptr2 <= k && answer % ptr2 == 0) {
        answer /= ptr2++;
      }
    }
    while (ptr2 <= k) {
      answer /= ptr2++;
    }
    return answer;
  }
}
