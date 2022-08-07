import java.io.*;
import java.util.*;

public class BOJ1365 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int lis = lis(arr);
    bw.write(Integer.toString(n-lis));
    bw.flush();
  }
  static int lis(int[] arr) {
    int n = arr.length;
    int[] dp = new int[n+1]; // dp[i]: length i, last value

    int ptr = 1;
    dp[ptr] = arr[0];
    for (int i = 1; i < n; i++) {
      int idx = binarySearch(dp, 1, ptr, arr[i]);
      if (idx > ptr) {
        dp[++ptr] = arr[i];
      } else {
        dp[idx] = arr[i];
      }
    }
    return ptr;
  }
  static int binarySearch(int[] arr, int start, int end, int key) {
    int lo = start-1, hi = end+1;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr[mid] < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}