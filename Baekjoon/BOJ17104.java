import java.io.*;
import java.util.*;

public class BOJ17104 {
  static double[] cos;
  static double[] sin;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final int lim = 500_000;
    int[] prime = new int[1_000_000];
    int[] primeGenerator = new int[lim]; // p = 2a + 1
    Arrays.fill(prime, 1);
    prime[0] = prime[1] = 0;
    for (int i = 2; i*i <= prime.length; i++) {
      if (prime[i] == 0) continue;
      for (int ptr = i+i; ptr < prime.length; ptr += i) {
        prime[ptr] = 0;
      }
    }

    for (int i = 0; i < prime.length; i++) {
      if (prime[i] == 0) continue;
      primeGenerator[(i - 1) >> 1] = 1;
    }
    primeGenerator[0] = 0;

    int[] sq = square(primeGenerator);
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int t = Integer.parseInt(br.readLine());
      if (t == 4) {
        bw.write('1');
      } else if (prime[t>>1] == 0) {
        bw.write(Integer.toString(((int) Math.round(sq[(t>>1) - 1])) / 2));
      } else {
        bw.write(Integer.toString(((int) Math.round(sq[(t>>1) - 1]) - 1) / 2 + 1));
      }
      bw.newLine();
    }
    bw.flush();
  }
  static int[] square(int[] _p) {
    int fftLen = 1;
    while (fftLen < _p.length + _p.length) fftLen <<= 1;
    int trigInitLen = fftLen >> 1;
    double angle = Math.PI/trigInitLen;
    cos = new double[trigInitLen];
    sin = new double[trigInitLen];
    for (int i = 0; i < trigInitLen; i++) {
      cos[i] = Math.cos(angle * i);
      sin[i] = Math.sin(angle * i);
    }
    double[][] p = new double[fftLen][2];
    for (int i = 0; i < _p.length; i++) {
      p[i][0] = _p[i];
    }
    fft(p, false);
    for (int i = 0; i < fftLen; i++) {
      double re = p[i][0]*p[i][0] - p[i][1]*p[i][1];
      double im = p[i][0]*p[i][1] + p[i][1]*p[i][0];
      p[i][0] = re;
      p[i][1] = im;
    }
    fft(p, true);
    int[] r = new int[_p.length + _p.length - 1];
    for (int i = 0; i < r.length; i++) {
      r[i] = (int) Math.round(p[i][0]);
    }
    return r;
  }
  static void fft(double[][] p, boolean isInverse) {
    int n = p.length;
    int v = n >> 1;
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
      for (int i = 0; i < n; i += 2*k) {
        for (int j = 0; j < k; j++) {
          int trigIdx = v / k * j;
          double c = cos[trigIdx];
          double s = sin[trigIdx];
          if (isInverse) s = -s;
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
      for (int i = 0; i < n; i++) {
        p[i][0] /= n;
        p[i][1] /= n;
      }
    }
  }
}
