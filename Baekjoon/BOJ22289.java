import java.io.*;
import java.util.*;

public class BOJ22289 {
  static int bundle = 7; // 입력받은 숫자를 bundle개씩 묶어 다항식을 만든다.
  static long digit = 1; // 10^bundle
  static int sumLen;
  static double[] cos;
  static double[] sin;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    char[] first = st.nextToken().toCharArray();
    char[] second = st.nextToken().toCharArray();
    if (first[0] == '0' || second[0] == '0') {
      bw.write("0\n");
      bw.flush();
      return;
    }
    // 다항식의 차수
    int firstlen = first.length / bundle + (first.length % bundle == 0 ? 0 : 1);
    int secondlen = second.length / bundle + (second.length % bundle == 0 ? 0 : 1);
    int len = 1; // FFT 적용 크기
    sumLen = firstlen + secondlen + 2;
    while (len < sumLen) { 
      len <<= 1;
    }
    for (int i = 0; i < bundle; i++) digit *= 10;
    // first/second -> 다항식(계수배열 p, q)
    long[] p = new long[firstlen]; 
    long[] q = new long[secondlen];
    coefficientAllocation(p, firstlen, first); first = null;
    coefficientAllocation(q, secondlen, second); second = null;
    // long[] r = product(p, q);
    long[] r = product_highAccuracy(p, q);
    answer(r);
  }
  static void coefficientAllocation(long[] p, int numTerm, char[] input) {
    for (int i = 0; i < numTerm; i++) {
      long val = 0;
      long base = 1;
      for (int j = 0; j < bundle; j++) {
        if (input.length - bundle * i - j - 1 < 0) break;
        val += base * (input[input.length - bundle * i - j - 1] - '0');
        base *= 10;
      }
      p[i] = val;
    }
  }
  static void answer(long[] p) throws IOException {
    int nonzero = p.length;
    while (p[--nonzero] == 0);
    bw.write(Long.toString(p[nonzero]));
    for (int i = nonzero - 1; i >= 0; i--) {
      long val = p[i];
      long exp = digit/10;
      while (exp != 1 && val < exp) {
        bw.write('0');
        exp /= 10;
      }
      bw.write(Long.toString(val));
    }
    bw.newLine();
    bw.flush();
  }
  static long[] product(long[] _p, long[] _q) {
    int len = 1;
    while (len < _p.length + _q.length) len <<= 1;
    double[][] p = new double[len][2];
    double[][] q = new double[len][2];
    
    cos = new double[len >> 1];
    sin = new double[len >> 1];
    double angle = Math.PI/(len >> 1);
    for (int i = 0; i < (len >> 1); i++) {
      cos[i] = Math.cos(angle*i);
      sin[i] = Math.sin(angle*i);
    }

    for (int i = 0; i < _p.length; i++) {
      p[i][0] = _p[i];
    }
    for (int i = 0; i < _q.length; i++) {
      q[i][0] = _q[i];
    }

    fft(p, false); fft(q, false);
    double[][] r = new double[len][2];
    for (int i = 0; i < len; i++) {
      r[i][0] = p[i][0]*q[i][0] - p[i][1]*q[i][1];
      r[i][1] = p[i][0]*q[i][1] + p[i][1]*q[i][0];
    }
    fft(r, true);
    long[] result = new long[len];
    for (int i = 0; i < len; i++) {
      result[i] += (long) Math.round(r[i][0]);
      if (i < len - 1 && result[i] >= digit) {
        result[i + 1] += result[i] / digit;
        result[i] %= digit;
      }
    }
    return result;
  }
  static long[] product_highAccuracy(long[] _p, long[] _q) {
    int len = 1;
    while (len < _p.length + _q.length) len <<= 1;    

    cos = new double[len >> 1];
    sin = new double[len >> 1];
    double angle = Math.PI/(len >> 1);
    for (int i = 0; i < (len >> 1); i++) {
      cos[i] = Math.cos(angle*i);
      sin[i] = Math.sin(angle*i);
    }

    double[][] p1 = new double[len][2];
    double[][] p2 = new double[len][2];
    double[][] q1 = new double[len][2];
    double[][] q2 = new double[len][2];

    for (int i = 0; i < _p.length; i++) {
      p1[i][0] = _p[i] & ((1L << 15) - 1);
      p2[i][0] = _p[i] >> 15;
    }
    for (int i = 0; i < _q.length; i++) {
      q1[i][0] = _q[i] & ((1L << 15) - 1);
      q2[i][0] = _q[i] >> 15;
    }
    fft(p1, false); fft(p2, false); fft(q1, false); fft(q2, false);

    double[][] r1 = new double[len][2];
    double[][] r2 = new double[len][2];
    double[][] r3 = new double[len][2];

    for (int i = 0; i < len; i++) {
      r1[i][0] = p1[i][0]*q1[i][0] - p1[i][1]*q1[i][1];
      r1[i][1] = p1[i][0]*q1[i][1] + p1[i][1]*q1[i][0];

      r2[i][0] = p1[i][0]*q2[i][0] - p1[i][1]*q2[i][1] + p2[i][0]*q1[i][0] - p2[i][1]*q1[i][1];
      r2[i][1] = p1[i][0]*q2[i][1] + p1[i][1]*q2[i][0] + p2[i][0]*q1[i][1] + p2[i][1]*q1[i][0];

      r3[i][0] = p2[i][0]*q2[i][0] - p2[i][1]*q2[i][1];
      r3[i][1] = p2[i][0]*q2[i][1] + p2[i][1]*q2[i][0];
    }

    fft(r1, true); fft(r2, true); fft(r3, true);

    long[] result = new long[len];
    for (int i = 0; i < len; i++) {
      long a = (long) Math.round(r1[i][0]);
      long b = ((long) Math.round(r2[i][0])) << 15;
      long c = ((long) Math.round(r3[i][0])) << 30;
      result[i] += a + b + c;
      if (i < len - 1 && result[i] >= digit) {
        result[i + 1] += result[i]/digit;
        result[i] %= digit;
      }
    }
    return result;
  }
  static void fft(double[][] p, boolean isInverse) {
    int n = p.length;
    for (int i = 1, rev = 0; i < n; i++) {
      int bit = n >> 1;
      while (rev >= bit) {
        rev -= bit;
        bit >>= 1;
      }
      rev += bit;
      if (i < rev) {
        double[] tmp = p[i];
        p[i] = p[rev];
        p[rev] = tmp;
      }
    }
    for (int k = 1; k < n; k <<= 1) {
      for (int i = 0; i < n; i += k*2) {
        for (int j = 0; j < k; j++) {
          double c = cos[(n >> 1) / k * j];
          double s = sin[(n >> 1) / k * j] * (isInverse ? -1 : 1);
          double[] p1 = p[i + j];
          double[] p2 = p[i + j + k];
          double rex = p1[0];
          double imx = p1[1];
          double rey = p2[0] * c - p2[1] * s;
          double imy = p2[0] * s + p2[1] * c;
          p1[0] = rex + rey;
          p1[1] = imx + imy;
          p2[0] = rex - rey;
          p2[1] = imx - imy;
          // 누적해서 곱하면 실수 오차가 생긴다.
        }
      }
    }
    if (isInverse) {
      for (int i = 0; i < n; i++) {
        p[i][0] /= n;
        p[i][1] /= n;
      }
    }
  }
}