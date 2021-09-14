import java.io.*;

public class BOJ15990 {
  public static void main(String[] args) throws IOException {
    final int remainder = 1_000_000_009;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[][] dp = new int[100_001][4]; // dp[i][j]: number of the way, last = j
    dp[1][1] = 1;
    dp[2][2] = 1;
    dp[3][1] = 1;
    dp[3][2] = 1;
    dp[3][3] = 1;
    int cursor = 4;
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int input = Integer.parseInt(br.readLine());
      if (input < cursor) {
        int sum = (((dp[input][1] + dp[input][2]) % remainder) + dp[input][3]) % remainder;
        bw.write(sum + "\n");
      } else {
        for (; cursor <= input; cursor++) {
          for (int i = 1; i < 4; i++) {
            dp[cursor][i] = (dp[cursor - i][(i % 3) + 1] + dp[cursor - i][((i + 1) % 3) + 1]) % remainder;
          }
        }
        int sum = (((dp[input][1] + dp[input][2]) % remainder) + dp[input][3]) % remainder;
        bw.write(sum + "\n");
      }
    }
    bw.flush();
  }
}
