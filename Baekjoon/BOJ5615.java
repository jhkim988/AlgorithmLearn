import java.io.*;
import java.math.BigInteger;

public class BOJ5615 {
  static int[] smallPrimes = {2, 3, 5, 7, 11};
  static long[] lim = {2_047L, 1_373_653L, 25_326_001L, 3_215_031_751L, Long.MAX_VALUE};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int numPrime = 0;
    while (N-- > 0) {
      long num = Long.parseLong(br.readLine());
      if (isPrime(2 * num + 1)) { // 2 * num + 1 >= 3, odd 
        numPrime++;
      }
    }
    bw.write(numPrime + "\n");
    bw.flush();
  }
  static long mulMod(long a, long b, long mod) {
    return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
  }
  static boolean millerRabin(long num, long a, long d) {
    long det = pow(a, d, num);
    if (det == 1 || det == num - 1) {
      return true;
    }
    while (d != num - 1) {
      det = mulMod(det, det, num);
      d *= 2;
      if (det == 1) {
        return false;
      } 
      if (det == num - 1) {
        return true;
      }
    }
    return false;
  }
  static long pow(long base, long exp, long mod) {
    long res = 1;
    base = base % mod;
    while (exp > 0) {
      if ((exp & 1) == 1) { // exp is odd
        res = mulMod(res, base, mod);
      }
      exp >>= 1;
      base = mulMod(base, base, mod);
    }
    return res;
  }
  static boolean isPrime(long num) {
    if (num <= 1 || num == 4) return false;
    if (num <= 3) return true;
    // n - 1 = 2 ^ s * d
    long d = num - 1;
    while (d % 2 == 0) {
      d >>= 1;
    }
    for (int j = 0; j < lim.length; j++) {
      if (num < lim[j]) {
        for (int i = 0; i < j + 1; i++) {
          long a = smallPrimes[i];
          if (num <= a) continue;
          if (!millerRabin(num, a, d)) return false;
        }
        return true;
      }
    }
    return true;
  }
}
