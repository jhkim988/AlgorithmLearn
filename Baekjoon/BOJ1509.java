import java.io.*;
import java.util.*;

public class BOJ1509 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int len = input.length;
    boolean[][] isPelindrome = new boolean[len][len];
    for (int i = 0; i < len - 1; i++) {
      isPelindrome[i][i] = true;
      if (input[i] == input[i + 1]) isPelindrome[i][i + 1] = true;
    }
    isPelindrome[len - 1][len - 1] = true;
    for (int diff = 2; diff < len; diff++) {
      for (int i = 0; i + diff < len; i++) {
        if (isPelindrome[i + 1][i + diff - 1] && input[i] == input[i + diff])
          isPelindrome[i][i + diff] = true;
      }
    }

    int[] dp = new int[len];
    Arrays.fill(dp, len);
    for (int i = len - 1; i >= 0; i--) {
      if (isPelindrome[i][len - 1]) {
        dp[i] = 1;
      } else {
        for (int j = i; j < len - 1; j++) {
          if (isPelindrome[i][j]){
            int val = dp[j + 1] + 1;
            if (val < dp[i]) dp[i] = val;
          }
        }
      }
    }

    bw.write(Integer.toString(dp[0]));
    bw.newLine();
    bw.flush();
  }
}
