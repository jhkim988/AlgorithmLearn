import java.io.*;

public class BOJ22289_test {
  static int digit = 1_000;
  public static void main(String[] args) throws IOException {
    while (true) {
      long r1 = (long) (Math.random() * 1_000_000);
      long r2 = (long) (Math.random() * 1_000_000);
      String s1 = r1 + "";
      String s2 = r2 + "";
      long sol = mySol(s1, s2);
      long ans = r1 * r2;
      
      if (sol != ans) {
        System.out.println("HERE");
        System.out.println("r1: " + r1 + ", s1: " + s1);
        System.out.println("r2: " + r2 + ", s2: " + s2);
        System.out.println("sol: " + sol);
        System.out.println("ans: " + ans);
        return;
      }
    }
    
  }
  static long mySol(String r1, String r2) {
    char[] first = r1.toCharArray();
    char[] second = r2.toCharArray();
    if (first[0] == '0' || second[0] == '0') {
      return 0;
    }
    int len = 1;
    int sumLen = first.length + second.length;
    int bundle = 3;
    while (len <= (sumLen/bundle)) len <<= 1;
    // for (int i = 0; i < bundle; i++) digit *= 10;
    double[][] p = new double[len][2];
    double[][] q = new double[len][2];
    for (int i = 0; i <= first.length/bundle; i++) {
      int val = 0;
      int base = 1;
      for (int j = 0; j < bundle; j++) {
        if (first.length - bundle*i - j - 1 < 0) break;
        val += base * (first[first.length - bundle*i - j - 1] - '0');
        base *= 10;
      }
      p[i][0] = val;
    }
    for (int i = 0; i <= second.length/bundle; i++) {
      int val = 0;
      int base = 1;
      for (int j = 0; j < bundle; j++) {
        if (second.length - bundle*i - j - 1 < 0) break;
        val += base * (second[second.length - bundle*i - j - 1] - '0');
        base *= 10;
      }
      q[i][0] = val;
    }
    productPolynomial(p, q);
    return Long.parseLong(print(p));
  }
  static String print(double[][] p) {
    int len = p.length;
    long[] result = new long[len];
    for (int i = 0; i < len; i++) {
      long val = (long) Math.round(p[i][0]);
      if (i < len - 1) p[i + 1][0] += val / digit;
      result[i] = val % digit;
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
    // sb.append('\n');
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
