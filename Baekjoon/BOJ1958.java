import java.io.*;

public class BOJ1958 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] str1 = br.readLine().toCharArray();
    char[] str2 = br.readLine().toCharArray();
    char[] str3 = br.readLine().toCharArray();
    int answer = lcs(str1, str2, str3);
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static int lcs(char[] str1, char[] str2, char[] str3) {
    int[][][] dp = new int[str1.length+1][str2.length+1][str3.length+1];
    for (int i = 1; i <= str1.length; i++) {
      for (int j = 1; j <= str2.length; j++) {
        for (int k = 1; k <= str3.length; k++) {
          if (str1[i-1] == str2[j-1] && str2[j-1] == str3[k-1]) dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
          else dp[i][j][k] = Integer.max(Integer.max(dp[i-1][j][k], dp[i][j-1][k]), dp[i][j][k-1]);
        }
      }
    }
    return dp[str1.length][str2.length][str3.length];
  }
}
