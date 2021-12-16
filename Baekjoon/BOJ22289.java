import java.io.*;
import java.util.*;

public class BOJ22289 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    String fs = st.nextToken();
    String ss = st.nextToken();
    char[] first = fs.toCharArray();
    char[] second = ss.toCharArray();
    int len = 1;
    int sumLen = first.length + second.length;
    // if (sumLen < 18) {
    //   long f = Long.parseLong(fs);
    //   long s = Long.parseLong(ss);
    //   bw.write(f*s + "\n");
    //   bw.flush();
    //   return;
    // } else {
    //   fs = ss = null;
    // }

    while (len < sumLen) len <<= 1;
    double[][] p = new double[len][2];
    double[][] q = new double[len][2];
    for (int i = 0; i < first.length; i++) p[first.length - i - 1][0] = first[i] - '0';
    for (int i = 0; i < second.length; i++) q[second.length - i - 1][0] = second[i] - '0';
    first = second = null;
    productPolynomial(p, q);
    for (int i = 0; i < len; i++) {
      System.out.print((int) Math.round(p[i][0]) + " ");
    }
    System.out.println();
    int[] result = new int[len];
    int nonzero = 0;
    for (int i = 0; i < len; i++) {
      int coeff = (int) Math.round(p[i][0]);
      if (coeff != 0) nonzero = i;
      if (coeff >= 10) {
         p[i][0] += coeff / 10;
      } 
      int val = coeff % 10;
      result[i] = val;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = nonzero; i >= 0; i--) {
      sb.append(result[i]);
    }
    sb.append('\n');
    bw.write(sb.toString());
    bw.flush();
  }
  static void productPolynomial(double[][] p, double[][] q) {
    fft(p, false);
    fft(q, false);
    for (int i = 0; i < p.length; i++) {
      double re = reProduct(p[i], q[i]);
      double im = imProduct(p[i], q[i]);
      p[i][0] = re;
      p[i][1] = im;
    }
    fft(p, true);
  }
  static void fft(double[][] p, boolean isInverse) {
    int n = p.length;
    for (int i = 1, j = 0; i < n; i++) {
      int b = n/2;
      while (((j ^= b) & b) == 0) b /= 2;
      if (i < j) {
        double[] tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
      }
    }
    for (int k = 1; k < n; k *= 2) {
      double a = isInverse ? Math.PI/k : -Math.PI/k;
      double[] w = {Math.cos(a), Math.sin(a)};
      for (int i = 0; i < n; i += k*2) {
        double[] wp = {1.0, 0.0};
        for (int j = 0; j < k; j++) {
          double re = reProduct(p[i + j + k], wp);
          double im = imProduct(p[i + j + k], wp);
          double[] x = {p[i + j][0], p[i + j][1]};
          double[] y = {re, im};
          p[i + j][0] = x[0] + y[0];
          p[i + j][1] = x[1] + y[1];
          p[i + j + k][0] = x[0] - y[0];
          p[i + j + k][1] = x[1] - y[1];
          re = reProduct(wp, w);
          im = imProduct(wp, w);
          wp[0] = re;
          wp[1] = im;
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
  static double reProduct(double[] a, double[] b) {
    return a[0]*b[0] - a[1]*b[1];
  }
  static double imProduct(double[] a, double[] b) {
    return a[0]*b[1] + a[1]*b[0];
  }
}
