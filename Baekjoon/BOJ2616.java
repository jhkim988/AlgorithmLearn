import java.io.*;
import java.util.*;

public class BOJ2616 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len  = Integer.parseInt(br.readLine());
    int[] input = new int[len];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) input[i] = Integer.parseInt(st.nextToken());
    int maxGet = Integer.parseInt(br.readLine());

    int[] pSum = new int[len];
    pSum[0] = input[0];
    for (int i = 1; i < len; i++) pSum[i] = pSum[i - 1] + input[i];

    int[][] dp = new int[len][3];
    // pick 1:
    dp[maxGet-1][0] = pSum[maxGet-1];
    for (int i = maxGet; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], pSum[i] - pSum[i - maxGet]);
    }
    // pick 2:
    dp[maxGet*2-1][1] = pSum[maxGet*2-1];
    for (int i = maxGet*2; i < len; i++) {
      dp[i][1] = Math.max(dp[i - 1][1], dp[i - maxGet][0] + pSum[i] - pSum[i - maxGet]);
    }
    // pick 3:
    dp[maxGet*3-1][2] = pSum[maxGet*3 - 1];
    for (int i = maxGet*3; i < len; i++) {
      dp[i][2] = Math.max(dp[i - 1][2], dp[i - maxGet][1] + pSum[i] - pSum[i - maxGet]);
    }

    bw.write(Integer.toString(dp[len - 1][2]));
    bw.newLine();
    bw.flush();
  }
}