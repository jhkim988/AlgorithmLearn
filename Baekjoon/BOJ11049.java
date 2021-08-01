import java.io.*;
import java.util.StringTokenizer;

public class BOJ11049 {
  static long minCost(long[][] data) {
    int numMat = data.length;
    long[][] dp = new long[numMat][numMat];
    // dp[i][j]: cost to product data[i] ~ data[j]
    for (int i = 1; i < numMat; i++) {
      for (int j = 0; i + j < numMat; j++) {
        // [j, i + j - 1] + numProduct
        dp[j][j + i] = dp[j][j + i - 1] + data[j][0] * data[j + i][0] * data[j + i][1];
        for (int k = j + 1; k + 1 < j + i; k++) {
          // [j, k] + [k + 1, i + j] + numProduct
          dp[j][j + i] = Math.min(dp[j][j + i], dp[j][k] + dp[k + 1][j + i] + data[j][0] * data[k + 1][0] * data[j + i][1]);
        }
        // [j + 1, j + i] + numProduct
        dp[j][j + i] = Math.min(dp[j][j + i], dp[j + 1][j + i] + data[j][0] * data[j + 1][0] * data[j + i][1]);
      }
    }
    return dp[0][numMat - 1];
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numMat = Integer.parseInt(br.readLine());
    long[][] data = new long[numMat][2];
    StringTokenizer st;
    for (int i = 0; i < numMat; i++) {
      st = new StringTokenizer(br.readLine());
      data[i][0] = Long.parseLong(st.nextToken());
      data[i][1] = Long.parseLong(st.nextToken());
    }
    bw.write(minCost(data) + "\n");
    bw.flush();
  }
}
