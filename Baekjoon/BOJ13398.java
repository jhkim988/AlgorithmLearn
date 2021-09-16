import java.io.*;
import java.util.*;

public class BOJ13398 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = Integer.parseInt(br.readLine());
    int[] seq = new int[len];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }

    bw.write(doubleDP(seq) + "\n");
    bw.flush();
  }
  static int memExceedSol(int[] seq) {
    int len = seq.length;
    int[][] dp = new int[len][];
    // dp[i][j]: Max continuous sum of subseq with last element seq[i], except seq[j] (j < i)
    // dp[i][i + 1]: No Exception element

    for (int i = 0; i < len; i++) {
      dp[i] = new int[i + 1];
    }

    for (int i = 0; i < len; i++) {
      for (int j = 0; j <= i; j++) {
        dp[i][j] = Integer.MIN_VALUE;
      }
    }

    dp[0][0] = seq[0];
    for (int i = 1; i < len; i++) {
      dp[i][i] = Math.max(dp[i - 1][i - 1] + seq[i], seq[i]);
    }
    int max = Integer.MIN_VALUE;
    for (int i = 2; i < len; i++) {
      for (int j = 0; j < i; j++) {
        if (j == i - 1) {
          dp[i][j] = Math.max(dp[i - 2][i - 2] + seq[i], seq[i]);
        } else {
          dp[i][j] = Math.max(dp[i - 1][j] + seq[i], seq[i]);          
        }
        if (max < dp[i][j]) {
          max = dp[i][j];
        }
      }
    }
    return max;
  }
  static int doubleDP(int[] seq) {
    int len = seq.length;
    if (len == 1) {
      return seq[0];
    }
    int[] dpFirst = new int[len]; // dpFirst[i]: max sum of conti. sum of subseq of seq[i ... len - 1]
    int[] dpLast = new int[len]; // dpLast[i]: Max sum of conti. sum of subseq of seq[0 ... i]

    dpLast[0] = seq[0];
    dpFirst[len - 1] = seq[len - 1];
    
    for (int i = 1; i < len; i++) {
      dpLast[i] = Math.max(dpLast[i - 1] + seq[i], seq[i]);
    }
    
    for (int i = len - 2; i >= 0; i--) {
      dpFirst[i] = Math.max(dpFirst[i + 1] + seq[i], seq[i]);
    }

    int max = dpFirst[0];
    for (int i = 1; i < len - 1; i++) {
      max = Math.max(max, dpLast[i - 1] + dpFirst[i + 1]);
    }
    return max;
  }
}