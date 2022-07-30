import java.io.*;
import java.util.*;

public class BOJ5347 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long a = Integer.parseInt(st.nextToken());
      long b = Integer.parseInt(st.nextToken());
      long gcd = gcd(a, b);
      bw.write(Long.toString(a/gcd*b));
      bw.newLine();
    }
    bw.flush();
  }
  static long gcd(long a, long b) {
    long r = a%b;
    while (r > 0) {
      a = b;
      b = r;
      r = a%b;
    }
    return b;
  }
}