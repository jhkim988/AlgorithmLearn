import java.io.*;

public class BOJ5582 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] str0 = br.readLine().toCharArray();
    char[] str1 = br.readLine().toCharArray();
    int[][] dp = new int[str0.length][str1.length];
    int maxLen = 0;
    // int endPointOfStr0 = -1;
    for (int i = 0; i < str0.length; i++) {
      for (int j = 0; j < str1.length; j++) {
        if (str0[i] == str1[j]) {
          if (i == 0 || j == 0) {
            dp[i][j] = 1;
          } else {
            dp[i][j] = dp[i - 1][j - 1] + 1;
          }
        } else {
          dp[i][j] = 0;
        }
        if (dp[i][j] > maxLen) {
          maxLen = dp[i][j];
          // endPointOfStr0 = i;
        }
      }
    }

    // for (int i = endPointOfStr0 - maxLen + 1; i <= endPointOfStr0; i++) {
    //   bw.write(str0[i]);
    // }
    // bw.newLine();
    // bw.flush();
    bw.write(Integer.toString(maxLen));
    bw.newLine();
    bw.flush();
  }
}
