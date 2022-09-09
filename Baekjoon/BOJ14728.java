import java.io.*;
import java.util.*;

public class BOJ14728 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(st.nextToken());
    
    int[] dp = new int[t+1]; // dp[x] = the highest score, use time x
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int time = Integer.parseInt(st.nextToken());
      int score = Integer.parseInt(st.nextToken());
      for (int x = t; x >= time; x--) {
        dp[x] = Integer.max(dp[x], dp[x-time]+score);
      }
    }

    int max = 0;
    for (int i = 0; i <= t; i++) {
      max = Integer.max(dp[i], max);
    }

    bw.write(Integer.toString(max));
    bw.flush();
  }
}
