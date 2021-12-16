import java.io.*;
import java.util.*;

public class BOJ17134 {
  public static void main(String[] args) throws IOException {
    int len = 1 << 21;
    double[][] oddPrimes = new double[len][2];
    double[][] evenSemiPrimes = new double[len][2];
    init(oddPrimes, evenSemiPrimes);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int input = Integer.parseInt(br.readLine());
      bw.write(((long) Math.round(oddPrimes[input][0])) + "\n");
    }
    bw.flush();
  }
  static double reProduct(double[] a, double[] b) {
    return a[0]*b[0] - a[1]*b[1];
  }
  static double imProduct(double[] a, double[] b) {
    return a[0]*b[1] + a[1]*b[0];
  }
  static void init(double[][] oddPrimes, double[][] evenSemiPrimes) {
    int lim = 1_000_000;
    boolean[] primeFilter = eratosthenes(lim);
    for (int i = 3; i <= lim; i += 2) if (primeFilter[i]) oddPrimes[i][0] = 1.0;
    evenSemiPrimes[4][0] = 1.0;
    for (int i = 3; 2*i <= lim; i += 2) if (primeFilter[i]) evenSemiPrimes[2*i][0] = 1.0;
    polyProduct(oddPrimes, evenSemiPrimes);
  }
  static boolean[] eratosthenes(int size) {
    boolean[] result = new boolean[size];
    Arrays.fill(result, true);
    result[0] = result[1] = false;    
    int step = 1;
    while (step * step < size) {
      while (!result[++step]);
      for (int ptr = 2 * step; ptr < size; ptr += step) result[ptr] = false;
    }
    return result;
  }
  static void polyProduct(double[][] p, double[][] q) {
    ffp(p, false);
    ffp(q, false);
    for (int i = 0; i < p.length; i++) {
      double re = reProduct(p[i], q[i]);
      double im = imProduct(p[i], q[i]);
      p[i][0] = re;
      p[i][1] = im;
    }
    ffp(p, true);
  }
  static void ffp(double[][] p, boolean isInverse) {
    int n = 1 << 21;
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
}
