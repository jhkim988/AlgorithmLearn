import java.io.*;

public class BOJ1787 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    br.readLine();
    char[] str = br.readLine().toCharArray();
    int[] pi = new int[str.length];
    int[] dp = new int[str.length];
    int j = 0;
    long sum = 0;
    for (int i = 1; i < str.length; i++) {
      while (j > 0 && str[i] != str[j]) j = pi[j-1];
      if (str[i] == str[j]) pi[i] = ++j;
      dp[i] = pi[i];
      if (pi[i] > 0 && dp[pi[i]-1] > 0) dp[i] = dp[pi[i]-1];
      if (dp[i] != 0 && (i+1)-dp[i] >= dp[i]) {
        sum += i+1-dp[i];
      }
    }
    bw.write(Long.toString(sum));
    bw.flush();
  }
}
