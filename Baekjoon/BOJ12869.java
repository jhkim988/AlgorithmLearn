import java.io.*;
import java.util.*;

public class BOJ12869 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] input = new int[3];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
    final int max = 61;
    int[][][] dp = new int[max][max][max];
    int[][] minus = {{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};
    for (int i = 0; i < max; i++) for (int j = 0; j < max; j++) Arrays.fill(dp[i][j], max);
    for (int i = 0; i < 6; i++) {
      for (int val1 = minus[i][0]; val1 >= 0; val1--) {
        for (int val2 = minus[i][1]; val2 >= 0; val2--) {
          for (int val3 = minus[i][2]; val3 >= 0; val3--) {
            dp[val1][val2][val3] = 1;
          }
        }
      } 
    }
    dp[0][0][0] = 0;
  
    for (int i = 0; i < max; i++) {
      for (int j = 0; j < max; j++) {
        for (int k = 0; k < max; k++) {
          for (int l = 0; l < 6; l++) {
            dp[i][j][k] = Math.min(dp[i][j][k], dp[Math.max(i - minus[l][0], 0)][Math.max(j - minus[l][1], 0)][Math.max(k - minus[l][2], 0)] + 1);
          }
        }
      }
    }

    bw.write(Integer.toString(dp[input[0]][input[1]][input[2]]));
    bw.newLine();
    bw.flush();
  }
}
