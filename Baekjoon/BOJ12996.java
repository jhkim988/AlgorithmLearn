import java.io.*;
import java.util.*;

public class BOJ12996 {
  public static void main(String[] args) throws IOException {
    final int REMAINDER = 1_000_000_007;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    int singer1 = Integer.parseInt(st.nextToken());
    int singer2 = Integer.parseInt(st.nextToken());
    int singer3 = Integer.parseInt(st.nextToken());

    int[][] idx = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {1, 1, 0}, {1, 0, 1}, {0, 1, 1}, {1, 1, 1}};
    int[][][][] dp = new int[len][singer1 + 1][singer2 + 1][singer3 + 1];
    for (int i = 0; i < 7; i++) dp[0][idx[i][0]][idx[i][1]][idx[i][2]] = 1;
    for (int i = 1; i < len; i++) {
      for (int s1 = 0; s1 <= singer1; s1++) {
        for (int s2 = 0; s2 <= singer2; s2++) {
          for (int s3 = 0; s3 <= singer3; s3++) {
            for (int j = 0; j < 7; j++) {
              if (s1 < idx[j][0] || s2 < idx[j][1] || s3 < idx[j][2]) continue;
              dp[i][s1][s2][s3] += dp[i - 1][s1 - idx[j][0]][s2 - idx[j][1]][s3 - idx[j][2]];
              dp[i][s1][s2][s3] %= REMAINDER;
            }
          }
        }
      }
    }

    bw.write(Integer.toString(dp[len - 1][singer1][singer2][singer3]));
    bw.newLine();
    bw.flush();
  }
}
