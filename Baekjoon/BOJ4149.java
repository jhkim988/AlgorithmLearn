import java.io.*;
import java.util.*;
import java.math.*;

public class BOJ4149 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long n = Long.parseLong(br.readLine());
    ArrayList<Long> divisors = new ArrayList<>();
    rho(n, divisors);
    Collections.sort(divisors);
    for (long d : divisors) {
      bw.write(Long.toString(d));
      bw.newLine();
    }
    bw.flush();
  }
  static void rho(long n, ArrayList<Long> ret) {
    if (n == 1) return;
    if (n % 2 == 0) {
      ret.add(2L);
      rho(n/2, ret);
      return;
    }
    if (BigInteger.valueOf(n).isProbablePrime(10)) {
      ret.add(n);
      return;
    }
    long x=0, y=0, c=0, v=n;
    do {
      if (v == n) {
        x = y = (long) (Math.random()*(n-2)) + 2;
        c = (long) (Math.random()*20) + 1;
      }
      x = g(x, n, c);
      y = g(g(y, n, c), n, c);
      v = gcd(Math.abs(x-y), n);
    } while (v == 1);
    rho(v, ret);
    rho(n/v, ret);
  }
  static long g(long x, long n, long c) {
    return BigInteger.valueOf(x).multiply(BigInteger.valueOf(x)).add(BigInteger.valueOf(c)).remainder(BigInteger.valueOf(n)).longValue();
  }
  static long gcd(long a, long b) {
    long r = b;
    while (b != 0) {
      r = a%b;
      a = b;
      b = r;
    }
    return a;
  }
}
