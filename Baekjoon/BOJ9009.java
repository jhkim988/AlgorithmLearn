import java.io.*;
import java.util.*;

public class BOJ9009 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long[] fibo = new long[45];
    fibo[0] = 0; fibo[1] = 1;
    for (int i = 2; i < 45; i++) {
      fibo[i] = fibo[i-1] + fibo[i-2];
    } 
    Stack<Long> stk = new Stack<>();
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      long n = Long.parseLong(br.readLine());
      while (n != 0) {
        int idx = binarySearch(fibo, n);
        n -= fibo[idx];
        stk.push(fibo[idx]);
      }
      while (!stk.isEmpty()) {
        bw.write(Long.toString(stk.pop()));
        bw.write(' ');
      }
      bw.newLine();
    }
    bw.flush();
  }
  static int binarySearch(long[] arr, long key) {
    int lo = -1, hi = arr.length;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr[mid] <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return lo;
  }
}
