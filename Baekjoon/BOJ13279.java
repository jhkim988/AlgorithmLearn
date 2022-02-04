import java.io.*;
import java.util.*;

public class BOJ13279 {
  static final long DIVISOR = 100_003;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    long[] arr = new long[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Long.parseLong(st.nextToken());
    }

    long[] p = init(arr, 0, n - 1);

    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      int a = Integer.parseInt(br.readLine());
      bw.write(Long.toString(p[n - a]));
      bw.newLine();
    }
    bw.flush();
  }
  static long[] init(long[] arr, int start, int end) {
    if (start == end) {
      long[] result = {arr[start], 1};
      return result;
    }
    int mid = (start + end) / 2;
    long[] left = init(arr, start, mid);
    long[] right = init(arr, mid+1, end);
    return product(left, right);
  }
  static long[] product(long[] _p, long[] _q) {
    int len = 1;
    while (len < _p.length + _q.length) len <<= 1;
    double[][] p = new double[len][2];
    double[][] q = new double[len][2];
    for (int i = 0; i < _p.length; i++) {
      p[i][0] = _p[i];
    }
    for (int i = 0; i < _q.length; i++) {
      q[i][0] = _q[i];
    }
    fft(p, false); fft(q, false);
    double[][] r = new double[len][2];
    for (int i = 0; i < len; i++) {
      r[i][0] = p[i][0]*q[i][0] - p[i][1]*q[i][1];
      r[i][1] = p[i][0]*q[i][1] + p[i][1]*q[i][0];
    }
    fft(r, true);
    long[] result = new long[_p.length + _q.length - 1];
    for (int i = 0; i < result.length; i++) {
      result[i] = ((long) (Math.round(r[i][0]))) % DIVISOR;
    }
    return result;
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
      double a = Math.PI/k;
      if (isInverse) a = -a;
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
