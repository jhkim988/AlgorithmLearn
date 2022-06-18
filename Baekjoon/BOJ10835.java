import java.io.*;
import java.util.*;

public class BOJ10835 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] left = new int[n];
    for (int i = 0; i < n; i++) {
      left[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    int[] right = new int[n];
    for (int i = 0; i < n; i++) {
      right[i] = Integer.parseInt(st.nextToken());
    }
    int[][] dp = new int[n+1][n+1];
    for (int i = n-1; i >= 0; i--) {
      for (int j = n-1; j >= 0; j--) {
        if (right[j] >= left[i]) {
          dp[i][j] = Integer.max(dp[i+1][j], dp[i+1][j+1]);
        } else {
          dp[i][j] = dp[i][j+1] + right[j];
        }
      }
    } 
    bw.write(Integer.toString(dp[0][0]));
    bw.flush();
  }
}
