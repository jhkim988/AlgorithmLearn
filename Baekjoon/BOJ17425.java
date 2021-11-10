import java.io.*;

public class BOJ17425 {
  static final int len = 1_000_001;
  static long[] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();
    init();
    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      sb.append(dp[N]).append('\n');
    }

    bw.write(sb.toString() + "\n");
    bw.flush();
  }  
  static void init() {
    dp = new long[len];
    for (int i = 1; i < len; i++) {
      for (int j = i; j < len; j += i) {
        dp[j] += i;
      }
    }
    for (int i = 2; i < len; i++) {
      dp[i] += dp[i - 1];
    }
  }
}
