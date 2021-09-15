import java.io.*;

public class BOJ11057 {
  public static void main(String[] args) throws IOException {
    final int remainder = 10_007;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    int[][] dp = new int[N + 1][10];

    for (int i = 0; i < 10; i++) {
      dp[1][i] = 1;
    }

    for (int i = 2; i <= N; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k <= j; k++) {
          dp[i][j] += dp[i - 1][k];
          dp[i][j] %= remainder;
        }
      }
    }

    int sum = 0;
    for (int i = 0; i < 10; i++) {
      sum += dp[N][i];
      sum %= remainder;
    }
    bw.write(sum + "\n");
    bw.flush();
  }  
}
