import java.io.*;
import java.util.*;

public class BOJ22289 {
  static int bundle = 5; // 입력받은 숫자를 bundle개씩 묶어 다항식을 만든다.
  static long digit = 1; // 10^bundle
  static int sumLen;
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
    int len = 1;
    sumLen = firstlen + secondlen + 2;
    while (len < sumLen) { // FFT 적용 크기
      len <<= 1;
    }
    for (int i = 0; i < bundle; i++) digit *= 10;
    
    // first/second -> 다항식(계수배열 p, q)
    double[][] p = new double[len][2]; 
    double[][] q = new double[len][2];
    coefficientAllocation(p, firstlen, first);
    coefficientAllocation(q, secondlen, second);
    productPolynomial(p, q);
    answer(p);
  }
  static void scaling(double[][] p, double multi) {
    for (int i = 0; i < p.length; i++) {
      p[i][0] *= multi;
      p[i][1] *= multi;
    }
  }
  static void coefficientAllocation(double[][] p, int numTerm, char[] input) {
    for (int i = 0; i < numTerm; i++) {
      long val = 0;
      long base = 1;
      for (int j = 0; j < bundle; j++) {
        if (input.length - bundle * i - j - 1 < 0) break;
        val += base * (input[input.length - bundle * i - j - 1] - '0');
        base *= 10;
      }
      p[i][0] = val;
    }
  }
  static void answer(double[][] p) throws IOException {
    for (int i = 0; i < sumLen - 1; i++) p[i][0] = Math.round(p[i][0]);
    for (int i = 0; i < sumLen - 1; i++) {
      p[i + 1][0] += Math.floor(p[i][0] / digit);
      p[i][0] %= digit;
    }
    int nonzero = sumLen;
    while (p[--nonzero][0] < 0.5);
    bw.write(Long.toString((long) p[nonzero][0]));
    for (int i = nonzero - 1; i >= 0; i--) {
      long val = (long) p[i][0];
      long exp = digit/10;
      while (exp != 1 && val < exp) {
        bw.write(Long.toString(0L));
        exp /= 10;
      }
      bw.write(Long.toString(val));
    }
    bw.newLine();
    bw.flush();
  }
  static void productPolynomial(double[][] p, double[][] q) {
    fft(p, false);
    fft(q, false);
    for (int i = 0; i < p.length; i++) {
      double re = p[i][0]*q[i][0] - p[i][1]*q[i][1];
      double im = p[i][0]*q[i][1] + p[i][1]*q[i][0];
      p[i][0] = re;
      p[i][1] = im;
    }
    fft(p, true);
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
      double a = isInverse ? Math.PI/k : -Math.PI/k;
      for (int i = 0; i < n; i += k*2) {
        double rewp = 1.0;
        double imwp = 0.0;
        for (int j = 0; j < k; j++) {
          double[] p1 = p[i + j];
          double[] p2 = p[i + j + k];
          double rex = p1[0];
          double imx = p1[1];
          double rey = p2[0] * rewp - p2[1] * imwp;
          double imy = p2[0] * imwp + p2[1] * rewp;
          p1[0] = rex + rey;
          p1[1] = imx + imy;
          p2[0] = rex - rey;
          p2[1] = imx - imy;
          // 누적해서 곱하면 실수 오차가 생긴다.
          rewp = Math.cos(a*(j+1));
          imwp = Math.sin(a*(j+1));
        }
      }
    }
    if (isInverse) {
      scaling(p, 1 / (double) n);
    }
  }
}