import java.io.*;
import java.util.*;

public class BOJ14002 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int len = Integer.parseInt(br.readLine());
    int[] data = new int[len];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    int[][] dp = new int[len][len + 1];
    dp[0][0] = 0;
    dp[0][1] = data[0];
    int LISlen = 1;
    for (int i = 1; i < len; i++) {
      int idx = Arrays.binarySearch(dp[i - 1], 1, LISlen, data[i]);
      if (idx > -1) {
        dp[i] = dp[i - 1].clone();
        continue;
      } else {
        idx = -(idx + 1);
        if (idx == LISlen) {
          if (dp[i - 1][0] == LISlen - 1) {
            dp[i] = dp[i - 1].clone();
            dp[i][0] = 0;
            dp[i][LISlen] = data[i];
            LISlen++;
          }
        } else {
          dp[i].clone();
          dp[i][0] = idx;
          dp[i][idx] = data[i];
        }
      }
    }
    bw.write(dp[]) 
  }
}
