import java.io.*;
import java.util.*;

public class BOJ3745 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = null;
    while ((input = br.readLine()) != null) {
      int n = Integer.parseInt(input);
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] dp = new int[n+1]; // dp[i] = minimum last element of length i LIS
      int ptr = 0;
      for (int i = 0; i < n; i++) {
        int val = Integer.parseInt(st.nextToken());
        if (dp[ptr] < val) {
          dp[++ptr] = val;
        } else {
          int idx = lowerbound(dp, 0, ptr, val);
          dp[idx] = val;
        }
      }
      bw.write(Integer.toString(ptr));
      bw.newLine();
    }
    bw.flush();
  }
  static int lowerbound(int[] dp, int lo, int hi, int key) {
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (dp[mid] < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
