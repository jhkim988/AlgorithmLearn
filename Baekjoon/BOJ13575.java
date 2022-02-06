import java.io.*;
import java.util.*;

public class BOJ13575 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    
    int max = 0;
    int[] input = new int[n];
    for (int i = 0; i < n; i++) {
      input[i] = Integer.parseInt(st.nextToken());
      if (max < input[i]) max = input[i];
    }

    int[] poly = new int[max + 1];
    for (int i = 0; i < n; i++) {
      poly[input[i]] = 1;
    }
    int[] result = power(poly, k);
    for (int i = 0; i < result.length; i++) {
      if (result[i] == 0) continue;
      bw.write(Integer.toString(i));
      bw.write(' ');
    }
    bw.newLine();
    bw.flush();
  }
  static int[] power(int[] base, int k) {
    if (k == 0) {
      int[] result = {1};
      return result;
    }
    if (k == 1) {
      int[] result = new int[base.length];
      System.arraycopy(base, 0, result, 0, base.length);
      return result;
    }
    int[] half = power(base, k/2);
    int[] sq = square(half);
    if ((k & 1) == 0) {
      return sq;
    } else {
      return product(sq, base);
    }
  }
  static int[] square(int[] _p) {
    int fftLen = 1;
    while (fftLen < _p.length + _p.length) fftLen <<= 1;
    double[][] h = new double[fftLen][2];
    for (int i = 0; i < _p.length; i++) {
      h[i][0] = _p[i];
    }
    fft(h, false);
    for (int i = 0; i < fftLen; i++) {
      double re = h[i][0]*h[i][0] - h[i][1]*h[i][1];
      double im = h[i][0]*h[i][1] + h[i][1]*h[i][0];
      h[i][0] = re;
      h[i][1] = im;
    }
    fft(h, true);
    int[] result = new int[_p.length + _p.length - 1];
    for (int i = 0; i < result.length; i++) {
      if ((int) Math.round(h[i][0]) == 0) continue;
      result[i] = 1;
    }
    return result;
  }
  static int[] product(int[] _p, int[] _q) {
    int fftLen = 1;
    while (fftLen < _p.length + _q.length) fftLen <<= 1;
    double[][] p = new double[fftLen][2];
    double[][] q = new double[fftLen][2];
    for (int i = 0; i < _p.length; i++) {
      p[i][0] = _p[i];
    }
    for (int i = 0; i < _q.length; i++) {
      q[i][0] = _q[i];
    }
    fft(p, false); fft(q, false);
    for (int i = 0; i < fftLen; i++) {
      double re = p[i][0]*q[i][0] - p[i][1]*q[i][1];
      double im = p[i][0]*q[i][1] + p[i][1]*q[i][0];
      p[i][0] = re;
      p[i][1] = im;
    }
    fft(p, true);
    int[] result = new int[_p.length + _q.length - 1];
    for (int i = 0; i < result.length; i++) {
      if ((int) Math.round(p[i][0]) == 0) continue;
      result[i] = 1;
    }
    return result;
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
      rev += bit;
      if (i < rev) {
        double[] tmp = p[i];
        p[i] = p[rev];
        p[rev] = tmp;
      }
    }

    for (int k = 1; k < n; k <<= 1) {
      double a = Math.PI / k * (isInverse ? -1 : 1);
      for (int i = 0; i < n; i += 2*k) {
        for (int j = 0; j < k; j++) {
          double c = Math.cos(a*j);
          double s = Math.sin(a*j);
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
