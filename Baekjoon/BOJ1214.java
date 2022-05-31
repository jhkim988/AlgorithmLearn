import java.io.*;
import java.util.*;

public class BOJ1214 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long d = Long.parseLong(st.nextToken());
    long p = Long.parseLong(st.nextToken());
    long q = Long.parseLong(st.nextToken());

    if (q < p) {
      long tmp = p;
      p = q;
      q = tmp;
    }

    long answer = solution(d, p, q);
    bw.write(Long.toString(answer));
    bw.flush();
  }
  static long solution(long d, long p, long q) {
    if (d % p == 0 || d % q == 0 || p == 1 || q == 1) return d;
    long min = Long.MAX_VALUE/2;
    long dp = d/p;
    for (long i = 0; i <= dp; i++) {
      long y = ((d-p*i)%q == 0 ? 0 : 1) + (d-p*i)/q;
      if (p*i+q*y < min) min = p*i+q*y;
    }
    return min;
  }
}
