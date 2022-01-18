import java.io.*;
import java.util.*;

public class BOJ22289 {
  static int bundle = 3; // 입력받은 숫자를 bundle개씩 묶어 다항식을 만든다.
  static long digit = 1; // 10^bundle
  static int sumLen;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    br.close();
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
    while (len < sumLen) len <<= 1; // FFT 적용 크기
    for (int i = 0; i < bundle; i++) digit *= 10;
    // first/second -> 다항식(계수배열 p, q)
    double[][] p = new double[len][2]; 
    double[][] q = new double[len][2]; 
    for (int i = 0; i < firstlen; i++) {
      long val = 0;
      long base = 1;
      for (int j = 0; j < bundle; j++) {
        if (first.length - bundle * i - j - 1 < 0) break;
        val += base * (first[first.length - bundle * i - j - 1] - '0');
        base *= 10;
      }
      p[i][0] = val;
    }
    for (int i = 0; i < secondlen; i++) {
      long val = 0;
      long base = 1;
      for (int j = 0; j < bundle; j++) {
        if (second.length - bundle * i - j - 1 < 0) break;
        val += base * (second[second.length - bundle * i - j - 1] - '0');
        base *= 10;
      }
      q[i][0] = val;
    }
    productPolynomial(p, q);
    answer(p);
  }
  static void answer(double[][] p) throws IOException {
    long[] result = new long[sumLen];
    for (int i = 0; i < sumLen; i++) result[i] = (long) Math.round(p[i][0]);
    for (int i = 0; i < sumLen; i++) {
      assert result[i + 1] > Long.MAX_VALUE - result[i] / digit;
      if (i < sumLen - 1) result[i + 1] += result[i] / digit;
      result[i] %= digit;
    }
    int nonzero = sumLen;
    while (result[--nonzero] == 0);
    bw.write(Long.toString(result[nonzero]));
    for (int i = nonzero - 1; i >= 0; i--) {
      long exp = digit/10;
      while (exp != 1 && result[i] < exp) {
        bw.write(Long.toString(0L));
        exp /= 10;
      }
      bw.write(Long.toString(result[i]));
      bw.flush();
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
      double cos = Math.cos(a);
      double sin = Math.sin(a);
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
          double re = rewp*cos - imwp*sin;
          double im = rewp*sin + imwp*cos;
          rewp = re;
          imwp = im;
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