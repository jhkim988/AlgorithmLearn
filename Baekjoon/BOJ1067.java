import java.io.*;
import java.util.*;

public class BOJ1067 {
  static int len;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    len = 1;
    while (len < 2 * N) len <<= 1;
    double[][] a = new double[len][2];
    double[][] b = new double[len][2];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) a[i][0] = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) b[N - i - 1][0] = Integer.parseInt(st.nextToken());
    for (int i = len - N; i < len; i++) {
      a[i][0] = a[i - len + N][0];
    }
    convolution(a, b);
    double max = 0.0;
    for (int i = 0; i < len; i++) if (max < a[i][0]) max = a[i][0];
    bw.write((int) Math.round(max) + "\n");
    bw.flush();
  }
  static void convolution(double[][] a, double[][] b) {
    fft(a, false);
    fft(b, false);
    for (int i = 0; i < a.length; i++) {
      double re = reProduct(a[i], b[i]);
      double im = imProduct(a[i], b[i]);
      a[i][0] = re;
      a[i][1] = im;
    }
    fft(a, true);
  }
  static double reProduct(double[] a, double[] b) {
    return a[0]*b[0] - a[1]*b[1];
  }
  static double imProduct(double[] a, double[] b) {
    return a[0]*b[1] + a[1]*b[0];
  }
  static void fft(double[][] p, boolean isInverse) {
    int n = len;
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
