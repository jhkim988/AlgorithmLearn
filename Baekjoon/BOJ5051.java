import java.io.*;

public class BOJ5051 {
  static double[] cos;
  static double[] sin;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    long answer = useFFT(n);

    bw.write(Long.toString(answer));
    bw.newLine();
    bw.flush();
  }

  static long useFFT(int n) {
    int fftlen = 1;
    while (fftlen < 2*n) {
      fftlen <<= 1;
    }
    // polynomial: having quadratic residue degree term.
    long[] qr = new long[n];
    for (long i = 1; i < n; i++) {
      qr[(int) ((i*i) % n)]++;
    }
    double[][] product = square(qr);
    long count = 0;
    for (int i = 0; i < fftlen; i++) {
      if (qr[i % n] == 0) continue;
      count += (long) (Math.round(product[i][0])) * qr[i % n]; // number of all pair (a, b) (a < b, a == b, a > b)
    }

    long same = 0;
    for (long i = 1; i < n; i++) {
      if (qr[(int) (2*i*i%n)] == 0) continue;
      same += qr[(int) (2*i*i%n)]; // number of a == b;
    }
    return (count - same) / 2 + same;
  }
  static double[][] square(long[] _p) {
    int len = 1;
    while (len < 2*_p.length ) len <<= 1;

    double a = Math.PI/(len >> 1);
    cos = new double[len >> 1];
    sin = new double[len >> 1];
    for (int i = 0; i < (len >> 1); i++) {
      cos[i] = Math.cos(a*i);
      sin[i] = Math.sin(a*i);
    }

    double[][] p = new double[len][2];
    for (int i = 0; i < _p.length; i++) {
      p[i][0] = _p[i];
    }
    fft(p, false);
    double[][] r = new double[len][2];
    for (int i = 0; i < len; i++) {
      r[i][0] = p[i][0]*p[i][0] - p[i][1]*p[i][1];
      r[i][1] = p[i][0]*p[i][1] + p[i][1]*p[i][0];
    }
    fft(r, true);
    return r;
  }
  static void fft(double[][] p, boolean isInverse) {
    int len = p.length;
    for (int i = 1, rev = 0; i < len; i++) {
      int bit = len >> 1;
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
    for (int k = 1; k < len; k <<= 1) {
      for (int i = 0; i < len; i += k*2) {
        for (int j = 0; j < k; j++) {
          double c = cos[(len >> 1) / k * j];
          double s = isInverse ? -sin[(len >> 1) / k * j] : sin[(len >> 1) / k * j];
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
        }
      }
    }
  
    if (isInverse) {
      for (int i = 0; i < len; i++) {
        p[i][0] /= len;
        p[i][1] /= len;
      }
    }
  }
}
