import java.io.*;

public class BOJ1562 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    
    long result = dynamic(N);
    bw.write(result + "\n");
    bw.flush();
  }
  static long dynamic(int N) {
    long dp[][][] = new long[N + 1][10][1 << 10];
    // dp[i][j][k]: length i, last number j, bit-masking k, the number of step-number
    final long QUOTIENT = 1_000_000_000L;
    for (int i = 1; i < 10; i++) {
      dp[1][i][1 << i] = 1;
    }
    for (int i = 2; i <= N; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 1; k < (1<<10); k++) {
          if (j == 0) {
            dp[i][j][k | (1 << 0)] += dp[i - 1][1][k];
          } else if (j == 9) {
            dp[i][j][k | (1 << 9)] += dp[i - 1][8][k];
          } else {
            dp[i][j][k | (1 << j)] += (dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % QUOTIENT;
          } 
        }
      }
    }

    long result = 0;
    for (int i = 0; i < 10; i++) {
      result = (result + dp[N][i][0b11_1111_1111]) % QUOTIENT;
    }
    return result;
  }
}
