import java.io.*;
import java.util.*;

public class BOJ1509 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int len = input.length;
    boolean[][] isPelindrome = new boolean[len][len];
    for (int i = 0; i < len; i++) isPelindrome[i][i] = true;
    for (int i = 0; i < len - 1; i++) {
      if (input[i] == input[i + 1]) isPelindrome[i][i + 1] = true;
    }
    for (int diff = 2; diff < len; diff++) {
      for (int i = 0; i + diff < len; i++) {
        if (isPelindrome[i + 1][i + diff - 1] && input[i] == input[i + diff])
          isPelindrome[i][i + diff] = true;
      }
    }

    int[] dp = new int[len];
    Arrays.fill(dp, len);
    dp[len - 1] = 1;
    for (int i = len - 2; i >= 0; i--) {
      for (int j = i; j < len - 1; j++) {
        if (isPelindrome[i][len - 1]) dp[i] = 1;
        if (isPelindrome[i][j]) dp[i] = Math.min(dp[i], dp[j + 1] + 1);
      }
    }

    bw.write(dp[0] + "\n");
    bw.flush();
  }
}
