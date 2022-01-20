import java.io.*;
import java.util.*;

public class BOJ22289_BundleOne {
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
    int sumLen = first.length + second.length;
    while (len < sumLen) len <<= 1;    
    double[][] p = new double[len][2];
    double[][] q = new double[len][2];
    for (int i = 0; i < first.length; i++) p[first.length - i - 1][0] = first[i] - '0';
    for (int i = 0; i < second.length; i++) q[second.length - i - 1][0] = second[i] - '0';
    first = second = null;
    productPolynomial(p, q);
    for (int i = 0; i < len - 1; i++) {
      p[i][0] = Math.round(p[i][0]);
      p[i + 1][0] += Math.floor(p[i][0] / 10);
      p[i][0] %= 10;
    }
    // String[] tmp = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",}; // caching
    int nonzero = len;
    while (p[--nonzero][0] < 1e-3);
    for (int i = nonzero; i >= 0; i--) {
      bw.write(Integer.toString((int) p[i][0]));
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
