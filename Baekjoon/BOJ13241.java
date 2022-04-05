import java.io.*;
import java.util.*;

public class BOJ13241 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long a = Integer.parseInt(st.nextToken());
    long b = Integer.parseInt(st.nextToken());
    long gcd = gcd(a, b);
    bw.write(Long.toString(a*b/gcd));
    bw.newLine();
    bw.flush();
  }
  static long gcd(long a, long b) {
    if (b == 0) return a;
    return gcd(b, a%b);
  }
}
