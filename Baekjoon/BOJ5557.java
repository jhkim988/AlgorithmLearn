import java.io.*;
import java.util.*;

public class BOJ5557 {
  static final int RANGE = 20;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] data = new int[len];
    for (int i = 0 ; i < len; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    long[][] dp = new long[len][RANGE + 1];
    dp[0][data[0]] = 1;
    for (int i = 1; i < len - 1; i++) {
      for (int j = 0; j <= RANGE; j++) {
        if (j + data[i] <= RANGE) dp[i][j + data[i]] += dp[i - 1][j];
        if (j - data[i] >= 0) dp[i][j - data[i]] += dp[i - 1][j];
      }
    }

    bw.write(dp[len - 2][data[len - 1]] + "\n");
    bw.flush();
  }
}
