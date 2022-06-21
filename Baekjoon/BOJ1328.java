import java.io.*;
import java.util.*;

public class BOJ1328 {
  public static void main(String[] args) throws IOException {
    final long D = 1_000_000_007;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int l = Integer.parseInt(st.nextToken());
    int r = Integer.parseInt(st.nextToken());
    long[][] comb = new long[n][n];
    for (int i = 0; i < n; i++) comb[i][0] = comb[i][i] = 1;
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < n; j++) {
        comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
        comb[i][j] %= D;
      }
    }
    // dp[x][i]: [0~i]에서 x개 보이는 경우의 수, 최댓값은 i에 위치
    long[][] dp = new long[n+1][n];
    dp[1][0] = 1;
    for (int x = 2; x <= n; x++) {
      for (int i = x-1; i < n; i++) {
        long val = 1;
        for (int j = i-1; j >= x-2; j--) {
          dp[x][i] += (dp[x-1][j]*val)%D;
          dp[x][i] %= D;
          val *= j;
          val %= D;
        }
      }
    }

    long answer = 0;
    for (int i = l-1; i <= n-r; i++) {
      answer += ((dp[l][i]*dp[r][n-i-1]) % D)*comb[n-1][i]%D;
      answer %= D;
    }
    bw.write(Long.toString(answer));
    bw.flush();
  }
}
