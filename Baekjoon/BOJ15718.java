import java.io.*;
import java.util.*;

public class BOJ15718 {
  static final long D = 100_007; // 97 * 1031
  static long[][] comb97;
  static long[][] comb1031;
  static long ss = 1, tt = 0; // ss*97 + tt*1031 = 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    init();
    extEuclid();
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      long answer = answer(n, m);
      bw.write(Long.toString(answer));
      bw.newLine();
    }
    bw.flush();
  }
  static long answer(long n, long m) {
    if (n == 0 && m == 1) return 1;
    if (n == 0 || m == 1) return 0;
    long val = (combLucas(n-1, m-2, 1031)*ss*97 + combLucas(n-1, m-2, 97)*tt*1031)%D;
    while (val < 0) val += D;
    val %= D;
    return val;
  }
  static void init() {
    comb97 = new long[98][98];
    comb1031 = new long[1032][1032];
    for (int i = 0; i < 98; i++) {
      comb97[i][0] = comb97[i][i] = 1;
    }
    for (int i = 0; i < 1032; i++) {
      comb1031[i][0] = comb1031[i][i] = 1;
    }
    for (int i = 1; i < 98; i++) {
      for (int j = 1; j < i; j++) {
        comb97[i][j] = (comb97[i-1][j-1] + comb97[i-1][j]) % 97;
      }
    }
    for (int i = 1; i < 1032; i++) {
      for (int j = 1; j < i; j++) {
        comb1031[i][j] = (comb1031[i-1][j-1] + comb1031[i-1][j]) % 1031;
      }
    }
  }
  static long combLucas(long n, long m, int mod) {
    long[][] comb = mod == 97 ? comb97 : comb1031;
    long val = 1;
    while (n != 0 || m != 0) {
      val *= comb[(int)(n%mod)][(int)(m%mod)];
      val %= mod;
      n /= mod;
      m /= mod;
    }
    return val;
  }
  static long pow(long base, int exp, int mod) {
    if (exp == 0) return 1;
    if (exp == 1) return base % mod;
    long half = pow(base, exp/2, mod);
    long half2 = half*half%mod;
    if (exp % 2 == 0) return half2;
    return half2*base%mod;
  }
  static void extEuclid() {
    long oldr = 97, olda = 1, oldb = 0;
    long r = 1031, a = 0, b = 1;
    long tmp = 0;
    while (r != 0) {
      long q = oldr/r;
      tmp = r;
      r = oldr - q*r;
      oldr = tmp;
      
      tmp = a;
      a = olda - q*a;
      olda = tmp;

      tmp = b;
      b = oldb - q*b;
      oldb = tmp;
    }
    ss = olda;
    tt = oldb;
  }
}
