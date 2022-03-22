import java.io.*;

public class BOJ9507 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    long[] dp = new long[68];
    dp[0] = dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    for (int i = 4; i <= 67; i++) {
      dp[i] = dp[i-1] + dp[i-2] + dp[i-3] + dp[i-4];
    }
    while (numTest-- > 0) {
      int idx = Integer.parseInt(br.readLine());
      bw.write(Long.toString(dp[idx]));
      bw.newLine();
    }
    bw.flush();
  }  
}
