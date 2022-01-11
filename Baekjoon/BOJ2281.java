import java.io.*;
import java.util.*;

public class BOJ2281 {
  static int[][] dp;
  static int line;
  static int width;
  static int[] input;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    line = Integer.parseInt(st.nextToken());
    width = Integer.parseInt(st.nextToken());
    input = new int[line];
    for (int i = 0; i < line; i++) input[i] = Integer.parseInt(br.readLine());
    dp = new int[line][width + 1];
    for (int i = 0; i < line; i++) Arrays.fill(dp[i], -1);
    bw.write(Integer.toString(recur(0, input[0])));
    bw.newLine();
    bw.flush();
  }
  static int recur(int name, int colIdx) {
    if (name + 1 >= line) return 0;
    if (dp[name][colIdx] != -1) return dp[name][colIdx];
    int len = width - colIdx;
    dp[name][colIdx] = len * len + recur(name + 1, input[name + 1]); // next line
    if (colIdx + input[name + 1] + 1 <= width) {
      dp[name][colIdx] = Math.min(dp[name][colIdx], recur(name + 1, colIdx + input[name + 1] + 1)); // same line
    }
    return dp[name][colIdx];
  }
}