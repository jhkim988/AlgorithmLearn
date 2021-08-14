import java.io.*;
import java.util.*;

public class BOJ9252 {
  private static class Pair {
    int i;
    int j;
    Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String str1 = br.readLine();
    String str2 = br.readLine();
    int len1 = str1.length();
    int len2 = str2.length();
    
    int[][] dp = new int[len1 + 1][len2 + 1];
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    // trace back
    Pair start = new Pair(len1, len2);
    Stack<Character> stk = new Stack<>();
    while (start.i != 0 && start.j != 0) {
      if (str1.charAt(start.i - 1) == str2.charAt(start.j - 1)) {
        stk.push(str1.charAt(start.i - 1));
        start.i--;
        start.j--;
      } else {
        if (dp[start.i - 1][start.j] > dp[start.i][start.j - 1]) {
          start.i--;
        } else {
          start.j--;
        }
      }
    }

    bw.write(dp[len1][len2] + "\n");
    while (!stk.isEmpty()) {
      bw.write(stk.pop());
    }
    bw.write("\n");
    bw.flush();
  }  
}
