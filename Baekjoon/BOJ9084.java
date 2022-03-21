import java.io.*;
import java.util.*;

public class BOJ9084 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int numType = Integer.parseInt(br.readLine());
      int[] types = new int[numType];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < numType; i++) {
        types[i] = Integer.parseInt(st.nextToken());
      }
      int target = Integer.parseInt(br.readLine());
      int[][] dp = new int[target + 1][numType];
      for (int i = 0; i <= target; i++) Arrays.fill(dp[i], -1);
      int answer = 0;
      for (int i = 0; i < numType; i++) {
        answer += recur(target - types[i], i, types, dp);
      }
      bw.write(Integer.toString(answer));
      bw.newLine();
      bw.flush();
    }
  }
  static int recur(int money, int use, int[] types, int[][] dp) {
    if (money == 0) return 1;
    if (money < 0) return 0;
    if (dp[money][use] != -1) return dp[money][use];
    dp[money][use] = 0;
    for (int i = 0; i < types.length; i++) {
      if (use <= i)
        dp[money][use] += recur(money - types[i], i, types, dp);
    }
    return dp[money][use];
  }
}
