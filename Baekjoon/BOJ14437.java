import java.io.*;

public class BOJ14437 {
  static final int DIVISOR = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int s = Integer.parseInt(br.readLine());
    int len = br.readLine().length();
    // int len = Integer.parseInt(br.readLine());
    long incExc = 0;
    boolean positive = true;
    for (int num = 1; num <= len; num++) {
      long add = (H(len, s - num * 26) * C(len, num)) % DIVISOR;
      if (positive) {
        incExc = (incExc + add) % DIVISOR;
      } else {
        incExc = (incExc > add ? incExc - add : (DIVISOR - (add - incExc))) % DIVISOR;
      }
      positive = !positive;
    }
    long total = H(len, s);
    long answer = total > incExc ? total - incExc : (DIVISOR - (incExc - total));  
    bw.write(Long.toString(answer % DIVISOR));
    bw.newLine();
    bw.flush();
  }
  static long C(int n, int k) {
    if (k < 0) return 0;
    long a = n;
    long product = 1;
    for (int i = 1; i <= k; i++) {
      product = (product * a) % DIVISOR;
      a--;
    }
    long factorial = 1;
    for (int i = 1; i <= k; i++) {
      factorial = (factorial * i) % DIVISOR;
    }
    return (product * power(factorial, DIVISOR - 2)) % DIVISOR;
  }
  static long H(int n, int k) {
    if (k < 0) return 0;
    return C(n+k-1, k);
  }
  private static long power(long base, int exp) {
    if (exp == 0) return 1;
    if (exp == 1) return base;
    long half = power(base, exp/2);
    long result = (half * half) % DIVISOR;
    if (exp % 2 == 0) return result;
    else return (result * base) % DIVISOR;
  } 
}
