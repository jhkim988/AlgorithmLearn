import java.io.*;
import java.util.*;

public class BOJ13977 {
  static final long divisor = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final int len = 4_000_001;
    int ptr = 2;
    long[] _n = new long[len];
    _n[0] = _n[1] = 1;
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      while (_n[n] == 0) {
        _n[ptr] = (ptr * _n[ptr - 1]) % divisor; 
        ptr++;
      }
      long result = (_n[n] * (pow(_n[k], divisor -2))) % divisor;
      result = (result * pow(_n[n-k], divisor - 2)) % divisor; 
      bw.write(Long.toString(result));
      bw.newLine();
    }
    bw.flush();
  }
  static long pow(long base, long exp) {
    long result = 1;
    while (exp > 0) {
      if ((exp & 1) != 0) {
        result = (result * base) % divisor;
      }
      exp /= 2;
      base = (base * base) % divisor;
    }
    return result;
  }
}
