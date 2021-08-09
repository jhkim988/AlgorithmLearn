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

    int[][] dp = new int[len][len];
    dp[0][0] = data[0];
    int LISlen = 1;
    int LISrow = 0;
    for (int i = 1; i < len; i++) {
      dp[i] = dp[LISrow].clone();
      int idx = Arrays.binarySearch(dp[i - 1], 0, LISlen, data[i]);
      if (idx > -1) {
        continue;
      } else {
        idx = -(idx + 1);
        if (idx == LISlen) {
          LISrow = i;          
          dp[i][LISlen] = data[i];
          LISlen++;
        } else {
          dp[i][idx] = data[i];
          if (idx == LISlen - 1) {
            LISrow = i;
          }
        }
      }
    }
    bw.write(LISlen + "\n");
    bw.write(dp[LISrow][0] + "");
    for (int i = 1; i < LISlen; i++) {
      bw.write(" " + dp[LISrow][i]);
    }
    bw.write("\n");
    bw.flush();

    // for (int i = 0; i < len; i++) {
    //   for (int j = 0; j < len; j++) {
    //     System.out.print(dp[i][j] + " ");
    //   }
    //   System.out.println();
    // }
  }
}
