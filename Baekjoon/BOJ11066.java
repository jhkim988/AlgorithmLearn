import java.io.*;
import java.util.*;

public class BOJ11066 {
  static int greedy(int[] pages) {
    // wrong! order of Chapter...
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < pages.length; i++) {
      pq.add(pages[i]);
    }
    int cost = 0;
    while (!pq.isEmpty()) {
      int ch1 = pq.poll();
      int ch2 = 0;
      System.out.println("ch1: " + ch1);
      if (!pq.isEmpty()) {
        ch2 = pq.poll();
        cost += ch1 + ch2;
        System.out.println("ch2: " + ch2);
      } else {
        pq.add(ch1);
        break;
      }
      pq.add(ch1 + ch2);
    }
    return cost;
  }
  static int minCost(int[] pages) {
    int len = pages.length;
    int[][] dp = new int[len][len]; // dp[i][j]: minimum cost to merge between i ~ j.
    // dp[i][i + 1] = pages[i] + pages[i + 1]
    // dp[i][j + 1] = min(dp[i][j], dp[i][j - 1] + dp[j][j + 1], dp[i][j - 2] + dp[j - 1][j + 1], ..., dp[i][i + 1] + dp[i + 2][j + 1]) + sum[i ~ j + 1]
    int[] sum = new int[len];
    sum[0] = pages[0];
    for (int i = 1; i < len; i++) {
      sum[i] = sum[i - 1] + pages[i];
    }

    for (int i = 1; i < len; i++) {
      for (int j = 0; i + j < len; j++) {
        int partialSum = j > 0 ? sum[j + i] - sum[j - 1] : sum[i];
        dp[j][j + i] = dp[j][j + i - 1] + partialSum;
        for (int k = j + 1; k + 1 < i + j; k++) {
          dp[j][j + i] = Math.min(dp[j][j + i], dp[j][k] + dp[k + 1][i + j] + partialSum);
        }
        dp[j][j + i] = Math.min(dp[j][j + i], dp[j + 1][j + i] + partialSum);
      }
    } 

    for (int i = 0; i < len; i++) {

    }

    return dp[0][len - 1];
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());
    int numCh;
    int[] pages;
    StringTokenizer st;
    for (int i = 0; i < numTest; i++) {
      numCh = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      pages = new int[numCh];
      for (int j = 0; j < numCh; j++) {
        pages[j] = Integer.parseInt(st.nextToken());
      }
      bw.write(minCost(pages) + "\n");
    }
    bw.flush();
  }
}
