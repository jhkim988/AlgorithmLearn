import java.io.*;

public class BOJ17104 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final int lim = 1_000_001;
    final int fftLen = 1 << 21;
    int[] notprime = new int[lim];
    double[][] p = new double[fftLen][2];
    notprime[0] = notprime[1] = 1;
    for (int i = 2; i < lim; i++) {
      if (notprime[i] == 1) continue;
      p[i][0] = 1;
      for (int ptr = i * 2; ptr < lim; ptr += i) {
        notprime[ptr] = 1;
      }
    }
    double[][] result = square(p);
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int t = Integer.parseInt(br.readLine());
      if (notprime[t >> 1] == 1) {
        bw.write(Integer.toString(((int) Math.round(result[t][0])) / 2));
      } else {
        bw.write(Integer.toString(((int) Math.round(result[t][0]) - 1) / 2 + 1));
      }
      bw.newLine();
    }
    bw.flush();
  }
  static double[][] square(double[][] p) {
    int fftLen = 1 << 21;
    int trigInitLen = fftLen >> 2;
    double angle = Math.PI/(trigInitLen >> 1);
    double[] cos = new double[trigInitLen];
    double[] sin = new double[trigInitLen];
    for (int i = 0; i < trigInitLen; i++) {
      cos[i] = Math.cos(angle * i);
      sin[i] = Math.sin(angle * i);
    }
    
    fft(p, false, cos, sin);
    for (int i = 0; i < fftLen; i++) {
      double re = p[i][0]*p[i][0] - p[i][1]*p[i][1];
      double im = p[i][0]*p[i][1] + p[i][1]*p[i][0];
      p[i][0] = re;
      p[i][1] = im;
    }
    fft(p, true, cos, sin);
    return p;
  }
  static void fft(double[][] p, boolean isInverse, double[] cos, double[] sin) {
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
          if (trigIdx >= (v >> 1)) trigIdx = v - trigIdx;
          double c = cos[trigIdx];
          double s = sin[trigIdx] * (isInverse ? -1 : 1);
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
