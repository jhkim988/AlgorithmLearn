import java.io.*;

public class BOJ2302 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] dp = new int[41];
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
      dp[i] = 2*dp[i-2] + dp[i-3];
    }
    int m = Integer.parseInt(br.readLine());
    int answer = 1;
    int prev = 1;
    while (m-- > 0) {
      int fix = Integer.parseInt(br.readLine());
      answer *= dp[fix - prev];
      prev = fix+1;
    }
    answer *= dp[n+1-prev];
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
}
