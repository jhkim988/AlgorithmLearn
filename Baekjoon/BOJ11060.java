import java.io.*;
import java.util.*;

public class BOJ11060 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = Integer.parseInt(br.readLine());
    int[] data = new int[len];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) data[i] = Integer.parseInt(st.nextToken());
    int[] dp = new int[len];
    Arrays.fill(dp, len);
    dp[0] = 0;
    for (int i = 0; i < len; i++) {
      for (int j = 1; j <= data[i] && i + j < len; j++) {
        dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
      }
    }
    bw.write(dp[len - 1] == len ? "-1\n" : dp[len - 1] + "\n");
    bw.flush();
  }
}
