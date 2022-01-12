import java.io.*;
import java.util.*;

public class BOJ10564 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int numPushUp = Integer.parseInt(st.nextToken());
      int numKind = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      int[] kind = new int[numKind];
      for (int i = 0; i < numKind; i++) kind[i] = Integer.parseInt(st.nextToken());
      boolean[][] dp = new boolean[numPushUp + 1][numPushUp + 1];
      for (int i = 0; i < numKind; i++) {
        if (kind[i] <= numPushUp) dp[kind[i]][kind[i]] = true;
      }
      for (int i = 0; i <= numPushUp; i++) {
        for (int j = 0; i + j <= numPushUp; j++) {
          if (dp[i][j]) {
            for (int k = 0; k < numKind; k++) {
              if (i + j + kind[k] <= numPushUp)
              dp[i + j + kind[k]][j + kind[k]] = true;
            }
          }
        }
      }
      int max = numPushUp;
      for (; max >= 0; max--) if (dp[numPushUp][max]) break;
      bw.write(Integer.toString(max));
      bw.newLine();
    }
    bw.flush();
  }
}
