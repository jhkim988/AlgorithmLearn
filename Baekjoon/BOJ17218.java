import java.io.*;

public class BOJ17218 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    char[] str1 = br.readLine().toCharArray();
    char[] str2 = br.readLine().toCharArray();

    int len1 = str1.length;
    int len2 = str2.length;

    StringBuilder[][] dp = new StringBuilder[len1 + 1][len2 + 1];
    for (int i = 0; i <= len1; i++) {
      dp[i][0] = new StringBuilder();
    }
    for (int j = 0; j <= len2; j++) {
      dp[0][j] = new StringBuilder();
    }
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        int ilen = dp[i - 1][j].length();
        int jlen = dp[i][j - 1].length();
        if (ilen > jlen) {
          dp[i][j] = new StringBuilder(dp[i - 1][j]);
        } else if (ilen < jlen) {
          dp[i][j] = new StringBuilder(dp[i][j - 1]);
        } else {
          if (str1[i - 1] == str2[j - 1]) {
            dp[i][j] = new StringBuilder(dp[i][j - 1].toString() + str1[i - 1]);
          } else {
            dp[i][j] = new StringBuilder(dp[i - 1][j]);
          }
        }
      }
    }
    bw.write(dp[len1][len2].toString()+ "\n");
    bw.flush();
  }
}
