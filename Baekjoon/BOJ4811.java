import java.io.*;

public class BOJ4811 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int MAX = 30;
    long[][] dp = new long[2*MAX+1][MAX+1];
    dp[1][1] = 1;
    for (int i = 2; i <= 2*MAX; i++) {
      for (int w = 1; w <= MAX; w++) {
        if (w != 0) dp[i][w] = dp[i-1][w-1];
        if (w*2 >= i) dp[i][w] += dp[i-1][w];
      }
    }
    int input;
    while ((input = Integer.parseInt(br.readLine())) != 0) {
      bw.write(Long.toString(dp[input*2][input]));
      bw.newLine();
    }
    bw.flush();
  }  
}
