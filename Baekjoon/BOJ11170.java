import java.io.*;
import java.util.*;

public class BOJ11170 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    int[] dp = new int[1_000_001];
    dp[0] = 1;
    for (int i = 1; i <= 1_000_000; i++) {
      int val = 0;
      int copy = i;
      while (copy != 0) {
        if (copy % 10 == 0) val++;
        copy /= 10;
      }
      dp[i] = dp[i-1] + val;
    }
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(dp[hi] - (lo == 0 ? 0 : dp[lo-1])));
      bw.newLine();
    }
    bw.flush();
  }
}
