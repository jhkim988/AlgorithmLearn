import java.io.*;

public class BOJ2401 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] str = br.readLine().toCharArray();
    int n = Integer.parseInt(br.readLine());
    char[][] pats = new char[n][];
    for (int i = 0; i < n; i++) {
      pats[i] = br.readLine().toCharArray();
    }
    int[][] pi = new int[n][];
    for (int i = 0; i < n; i++) {
      pi[i] = pi(pats[i]);
    }
    int[] j = new int[n];
    int[] dp = new int[str.length];
    for (int i = 0; i < str.length; i++) {
      if (i > 0) dp[i] = Integer.max(dp[i], dp[i-1]);
      for (int x = 0; x < n; x++) {
        while (j[x] > 0 && str[i] != pats[x][j[x]]) j[x] = pi[x][j[x]-1];
        if (str[i] == pats[x][j[x]]) {
          if (j[x] == pats[x].length-1) {
            j[x] = pi[x][j[x]];
            dp[i] = Integer.max(dp[i], (i >= pats[x].length ? dp[i-pats[x].length] : 0) + pats[x].length);
          } else {
            j[x]++;
          }
        }
      }
    }
    bw.write(Integer.toString(dp[str.length-1]));
    bw.flush();
  }
  static int[] pi(char[] pat) {
    int[] pi = new int[pat.length];
    int j = 0;
    for (int i = 1; i < pat.length; i++) {
      while (j > 0 && pat[i] != pat[j]) j = pi[j-1];
      if (pat[i] == pat[j]) pi[i] = ++j;
    }
    return pi;
  }
}
