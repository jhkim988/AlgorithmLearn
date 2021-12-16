import java.io.*;
import java.util.*;

public class BOJ22289 {
  static int bundle = 3;
  static int digit = 1;
  static int sumLen;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    char[] first = st.nextToken().toCharArray();
    char[] second = st.nextToken().toCharArray();
    if (first[0] == '0' || second[0] == '0') {
      bw.write("0\n");
      bw.flush();
      return;
    }
    int len = 1;
    sumLen = first.length + second.length;
    while (len <= (sumLen/bundle)) len <<= 1;
    len <<= 1;
    for (int i = 0; i < bundle; i++) digit *= 10;
    double[][] p = new double[len][2];
    double[][] q = new double[len][2];
    for (int i = 0; i <= first.length/bundle; i++) {
      int val = 0;
      int base = 1;
      for (int j = 0; j < bundle; j++) {
        if (first.length - bundle * i - j - 1 < 0) break;
        val += base * (first[first.length - bundle * i - j - 1] - '0');
        base *= 10;
      }
      p[i][0] = val;
    }
    for (int i = 0; i <= second.length/bundle; i++) {
      int val = 0;
      int base = 1;
      for (int j = 0; j < bundle; j++) {
        if (second.length - bundle * i - j - 1 < 0) break;
        val += base * (second[second.length - bundle * i - j - 1] - '0');
        base *= 10;
      }
      q[i][0] = val;
    }
    productPolynomial(p, q);
    bw.write(answer(p));
    bw.flush();
  }
  static void print(double[][] p) {
    for (int i = 0; i < p.length; i++) {
      System.out.print(p[i][0] + " ");
    }
    System.out.println();
  }
  static String answer(double[][] p) {
    int len = p.length;
    long[] result = new long[len];
    for (int i = 0; i < len-1; i++) {
      p[i + 1][0] += p[i][0] / digit;
      result[i] = ((long) Math.round(p[i][0])) % digit;
    }
    int nonzero = len;
    while (result[--nonzero] == 0);
    StringBuilder sb = new StringBuilder();
    sb.append(result[nonzero]);
    for (int i = nonzero - 1; i >= 0; i--) {
      int exp = digit/10;
      while (exp != 1 && result[i] < exp) {
        sb.append(0);
        exp /= 10;
      }
      sb.append(result[i]);
    }
    sb.append('\n');
    return sb.toString();
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
