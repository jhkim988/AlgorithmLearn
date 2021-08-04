import java.io.*;
import java.util.*;

public class BOJ2629 {
  static int numWeight;
  static int numBead;
  static int[] weight;
  static int[] bead;
  static int[][] dp;
  static final int rangeBead = 40000;
  // dp[i][w] = weight[i] if weight[i] = w
  // dp[i][w] = One of { dp[i-1][w], dp[i-1][w-weight[i]], dp[i-1][w+weight[i]] }
  // such that dp[i][w] maximalized w - { ... }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    numWeight = Integer.parseInt(br.readLine());
    weight = new int[numWeight];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numWeight; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    numBead = Integer.parseInt(br.readLine());
    bead = new int[numBead];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numBead; i++) {
      bead[i] = Integer.parseInt(st.nextToken());
    }

    dp = new int[numWeight][rangeBead + 1];
    init();

    for (int beadIdx = 0; beadIdx < numBead; beadIdx++) {
      if (dp[numWeight - 1][bead[beadIdx]] == bead[beadIdx]) {
        bw.write("Y ");
      } else {
        bw.write("N ");
      }
    }
    bw.write("\n");
    bw.flush();
  }
  static void init() {
    for (int weightTarget = 1; weightTarget <= rangeBead; weightTarget++) {
      if (weight[0] <= weightTarget) { 
        dp[0][weightTarget] = weight[0]; 
      } 
    }
    for (int i = 1; i < numWeight; i++) {
      for (int weightTarget = 1; weightTarget <= rangeBead - 500; weightTarget++) {
        if (weight[i] == weightTarget) {
          dp[i][weightTarget] = weightTarget;
        } else {
          int nearest = dp[i - 1][weightTarget];
          int candidate = Math.abs(dp[i - 1][weightTarget + weight[i]] - weight[i]);
          if (candidate <= weightTarget && nearest < candidate) {
            nearest = candidate;
          }
          if (weightTarget > weight[i]) {
            candidate = dp[i - 1][weightTarget - weight[i]] + weight[i];
            if (candidate <= weightTarget && nearest < candidate ) {
              nearest = candidate;
            }
          } else {
            candidate = weight[i] - dp[i - 1][weight[i] - weightTarget];
            if (candidate <= weightTarget && nearest < candidate ) {
              nearest = candidate;
            }
          }          
          dp[i][weightTarget] = nearest;
        }
      }
    }
  }
}
