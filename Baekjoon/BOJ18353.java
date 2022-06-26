import java.io.*;
import java.util.*;

public class BOJ18353 {
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
    dp[0] = 10_000_001;
    int ptr = 1;
    for (int i = 0; i < n; i++) {
      int idx = binarySearch(dp, 0, ptr, arr[i]);
      dp[idx] = arr[i];
      if (idx == ptr) ptr++;
    }
    bw.write(Integer.toString(n-ptr+1));
    bw.flush();
  }
  static int binarySearch(int[] arr, int start, int end, int key) {
    int lo = start, hi = end;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr[mid] > key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
