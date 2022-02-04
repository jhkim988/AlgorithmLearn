public class FFT {
  static double[] cos;
  static double[] sin;
  static long[] product(long[] _p, long[] _q) {
    int len = 1;
    while (len < _p.length + _q.length) len <<= 1;
    double[][] p = new double[len][2];
    double[][] q = new double[len][2];
    initTrig(len/2);
    init(p, _p); init(q, _q);
    fft(p, false); fft(q, false);
    double[][] r = new double[len][2];
    for (int i = 0; i < len; i++) {
      r[i][0] = p[i][0]*q[i][0] - p[i][1]*q[i][1];
      r[i][1] = p[i][0]*q[i][1] + p[i][1]*q[i][0];
    }
    fft(r, true);
    long[] result = new long[len];
    for (int i = 0; i < len; i++) {
      result[i] = (long) Math.round(r[i][0]);
    }
    return result;
  }
  static long[] product_highAccuracy(long[] _p, long[] _q) {
    int len = 1;
    while (len < _p.length + _q.length) len <<= 1;    
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
      result[i] = a + b + c;
    }

    return result;
  }
  static void init(double[][] p, long[] _p) {
    for (int i = 0; i < _p.length; i++) {
      p[i][0] = _p[i];
    }
  }
  static void initTrig(int len) {
    double a = Math.PI/len;
    cos = new double[len];
    sin = new double[len];
    for (int i = 0; i < len; i++) {
      cos[i] = Math.cos(a*i);
      sin[i] = Math.sin(a*i);
    }
  }
  static void fft(double[][] p, boolean isInverse) {
    int n = p.length;
    // bit reverse
    for (int i = 1, rev = 0; i < n; i++) {
      int bit = n >> 1;
      while (rev >= bit) {
        rev -= bit;
        bit >>= 1;
      }
      if (i < rev) {
        double[] tmp = p[i];
        p[i] = p[rev];
        p[rev] = tmp;
      }
    }

    for (int k = 1; k < n; k <<= 1) {
      for (int i = 0; i < n; i += 2*k) {
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
        }
      }
    }
  }
  public static void main(String[] args) {

  }
}
