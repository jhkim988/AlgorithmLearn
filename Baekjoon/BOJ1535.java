import java.io.*;
import java.util.*;

public class BOJ1535 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] life = new int[n];
    int[] joy = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      life[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      joy[i] = Integer.parseInt(st.nextToken());
    }
    int max = 0;
    int[] dp = new int[100];
    for (int i = 0; i < n; i++) {
      for (int j = 99; j >= 0; j--) {
        if (j < life[i]) continue;
        dp[j] = Integer.max(dp[j], dp[j-life[i]] + joy[i]);
        if (max < dp[j]) max = dp[j];
      }
    }
    bw.write(Integer.toString(max));
    bw.flush();
  }
}
