import java.io.*;

public class BOJ15988 {
  public static void main(String[] args) throws IOException {
    final int remainder = 1_000_000_009;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    int[] dp = new int[1_000_001];
    int cursor = 4;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    while (numTest-- > 0) {
      int input = Integer.parseInt(br.readLine());
      if (input < cursor) {
        bw.write(dp[input] + "\n");
      } else {
        for (; cursor <= input; cursor++) {
          dp[cursor] = ((dp[cursor - 3] + dp[cursor - 2]) % remainder + dp[cursor - 1]) % remainder;
        }
        bw.write(dp[input] + "\n");
      }
    }
    bw.flush();
  }
}