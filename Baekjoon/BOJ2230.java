import java.io.*;
import java.util.*;

public class BOJ2230 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    long m = Long.parseLong(st.nextToken());
    long[] arr = new long[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Long.parseLong(br.readLine());
    }
    Arrays.sort(arr);
    int lo = 0, hi = 0;
    long min = Long.MAX_VALUE;
    while (hi < n) {
      if (arr[hi]-arr[lo] >= m) {
        if (arr[hi]-arr[lo] < min) min = arr[hi]-arr[lo];
        lo++; 
        if (lo > hi) hi = lo;
      } else {
        hi++;
      }
    }
    bw.write(Long.toString(min));
    bw.flush();
  }
}