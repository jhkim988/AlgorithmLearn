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
    long lim = Long.min(p-1, d/q);
    long min = q*(d/q+1);
    for (long i = 0; i <= lim; i++) {
      if ((d-q*i)%p == 0) return d;
      min = Long.min(min, p*((d-q*i)/p+1)+q*i);
    }
    return min;
  }
}
