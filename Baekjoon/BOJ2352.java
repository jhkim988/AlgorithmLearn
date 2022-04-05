import java.io.*;
import java.util.*;

public class BOJ2352 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int[] dp = new int[n+1];
    int ptr = 1;
    dp[0] = -1;
    dp[1] = arr[0];
    for (int i = 0; i < n; i++) {
      int idx = upperbound(dp, 0, ptr, arr[i]);
      dp[idx] = arr[i];
      if (idx > ptr) ptr++;
    }
    bw.write(Integer.toString(ptr));
    bw.newLine();
    bw.flush();
  }
  static int upperbound(int[] arr, int lo, int hi, int key) {
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return arr[hi] < key ? hi+1 : hi;
  }
}
