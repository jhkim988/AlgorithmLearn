import java.io.*;
import java.util.*;
public class BOJ2240 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int t = Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());
    int[] arr = new int[t];
    for (int i = 0; i < t; i++) {
      arr[i] = Integer.parseInt(br.readLine())-1;
    }    
    int[][][] dp = new int[t][w+1][2];
    for (int j = 0; j <= w; j++) {
      dp[t-1][j][0] = (arr[t-1] == 0 ? 1 : 0);
      dp[t-1][j][1] = (arr[t-1] == 1 ? 1 : 0);
    }
    for (int i = t-2; i >= 0; i--) {
      for (int j = 0; j < w; j++) {
        dp[i][j][0] = Integer.max(dp[i+1][j][0], dp[i+1][j+1][1]) + (arr[i] == 0 ? 1 : 0);
        dp[i][j][1] = Integer.max(dp[i+1][j][1], dp[i+1][j+1][0]) + (arr[i] == 1 ? 1 : 0);
      }
      dp[i][w][0] = dp[i+1][w][0] + (arr[i] == 0 ? 1 : 0);
      dp[i][w][1] = dp[i+1][w][1] + (arr[i] == 1 ? 1 : 0);
    }
    bw.write(Integer.toString(Integer.max(dp[0][0][0], dp[0][1][1])));
    bw.flush();
  }
}
