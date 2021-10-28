import java.io.*;
import java.util.*;

public class BOJ1790 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int lo = 1;
    int hi = 10;
    int digit = 1;
    int accum = 0;
    while (lo <= N) {
      int cmp = (k - accum - 1) / digit + lo;
      System.out.println("lo: " + lo + ", hi: " + hi + ", digit: " + digit + ", accum: " + accum + ", k - accum: " + (k - accum));
      System.out.println("cmp: " + cmp);
      if (cmp <= N && lo <= cmp && cmp < hi) {
        int charAt = (k - accum) % digit;
        bw.write((cmp + "").charAt(digit - charAt - 1) + "\n");
        bw.flush();
        return;
      }
      accum += (hi - lo) * digit;
      lo = hi;
      hi *= 10;
      digit++;
    }
    bw.write("-1\n");
    bw.flush();
  } 
}
