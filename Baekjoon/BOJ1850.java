import java.io.*;
import java.util.*;

public class BOJ1850 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long a = Long.parseLong(st.nextToken());
    long b = Long.parseLong(st.nextToken());
    long g = gcd(a, b);

    for (long i = 0; i < g; i++) {
      bw.write('1');
    }
    bw.newLine();
    bw.flush();
  }
  static long gcd(long a, long b) {
    if (b == 0) return a;
    return gcd(b, a % b);
  }
}
