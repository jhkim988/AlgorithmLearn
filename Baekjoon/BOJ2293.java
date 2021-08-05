import java.io.*;

public class BOJ2293 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int numCoin;
  static int sumCoin;
  static int[] coins;
  public static void main(String[] args) throws IOException {
    String[] tmp = br.readLine().split(" ");
    numCoin = Integer.parseInt(tmp[0]);
    sumCoin = Integer.parseInt(tmp[1]);
    coins = new int[numCoin];
    for (int i = 0; i < numCoin; i++) {
      coins[i] = Integer.parseInt(br.readLine());
    }
    long[] dp = new long[sumCoin + 1];
    sol(dp);
    // long[][] ddp = new long[sumCoin + 1][numCoin];
    // mySol(ddp);
    // bw.write(dp[sumCoin] + ", " + ddp[sumCoin][numCoin - 1] + "\n");
    bw.write(dp[sumCoin] + "\n");
    bw.flush();
  }
  static void sol(long[] dp) throws IOException {
    // dp[j]: number of Case with sum = j
    dp[0] = 1;
    for (int i = 0; i < numCoin; i++) {
      for (int j = coins[i]; j <= sumCoin; j++) {
        if (j >= coins[i]) {
          dp[j] += dp[j - coins[i]];
        }
      }
    }
  }
  static void mySol(long[][] dp) throws IOException {
    // dp[i][j]: number of Case with sum = i, and j coins.
    for (int i = 0; i <= sumCoin; i++) {
      if (i % coins[0] == 0) {
        dp[i][0] = 1L;
      }
    }
    for (int j = 1; j < numCoin; j++) {
      for (int i = 0; i <= sumCoin; i++) {
        if (i >= coins[j]) {
          dp[i][j] = dp[i][j - 1] + dp[i - coins[j]][j]; // 1차원으로 합친다
        } else {
          dp[i][j] = dp[i][j - 1];
        }
      }
    }
  }
}
