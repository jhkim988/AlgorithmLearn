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
      int[] dp = new int[target+1];
      for (int i = 0; i < numType; i++) {
        if (types[i] <= target) dp[types[i]] = 1; 
      }
      for (int i = 1; i <= target; i++) {
        for (int j = 0; j < numType; j++) {
          if (i >= types[j]) dp[i] += dp[i - types[j]];
        }
      }
      bw.write(Integer.toString(dp[target]));
      bw.newLine();
      bw.flush();
      System.out.println(Arrays.toString(dp));
    }
  }
}
