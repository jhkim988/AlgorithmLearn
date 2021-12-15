import java.io.*;

public class BOJ10531 {
  static double[] cos;
  static double[] sin;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] shoot = new int[N];
    int max = 0;
    for (int i = 0; i < N; i++) {
      int input = Integer.parseInt(br.readLine()); 
      shoot[i] = input;
      if (max < input) max = input;
    }
    int M = Integer.parseInt(br.readLine());
    int[] dist = new int[M];
    for (int i = 0; i < M; i++) dist[i] = Integer.parseInt(br.readLine());

    int[] poly = new int[max + 1];
    for (int i = 0; i < N; i++) poly[shoot[i]] = 1;

    int[] product = polynomialProduct(poly, poly);
    int sum = 0;
    for (int i = 0; i < M; i++) {
      if (dist[i] < product.length && product[dist[i]] != 0 || dist[i] < poly.length && poly[dist[i]] != 0) sum++;
    }
    bw.write(sum + "\n");
    bw.flush();
  }
  static String printPoly(double[][] poly) {
    StringBuilder sb = new StringBuilder();
    sb.append('(').append(poly[0][0]).append(" + ").append(poly[0][1]).append("i)");
    for (int i = 1; i < poly.length; i++) {
      sb.append(" +(").append(poly[i][0]).append(" + ").append(poly[i][1]).append("i)x^").append(i);
    }
    return sb.toString();
  }
  static String printPoly(int[] poly) {
    StringBuilder sb = new StringBuilder();
    sb.append(poly[0]);
    for (int i = 1; i < poly.length; i++) {
      sb.append(" + ").append(poly[i]).append("x^").append(i);
    }
    return sb.toString();
  }
  static int[] polynomialProduct(int[] a, int[] b) {
    int len = a.length + b.length - 1;
    int exp= 1;
    int idx = 0;
    while (exp < len) {
      exp *= 2;
      idx++;
    }
    cos = new double[exp];
    sin = new double[exp];
    for (int i = 0; i < exp; i++) {
      cos[i] = Math.cos(-2 * Math.PI * i / exp);
      sin[i] = Math.sin(-2 * Math.PI * i / exp);
    }

    double[][] la = new double[exp][2];
    double[][] lb = new double[exp][2];
    for (int i = 0; i < a.length; i++) la[i][0] = a[i];
    for (int i = 0; i < b.length; i++) lb[i][0] = b[i];
    
    double[][] fa = fft(la, exp, idx);
    double[][] fb = fft(lb, exp, idx);
    double[][] result = new double[exp][2];
    for (int i = 0; i < exp; i++) {
      result[i][0] = fa[i][0] * fb[i][0] - fa[i][1] * fb[i][1];
      result[i][1] = fa[i][0] * fb[i][1] + fa[i][1] * fb[i][0];
    }
    result = fft(result, exp, idx);
    for (int i = 0; i < exp/2; i++) {
      result[i][0] /= exp;
      result[i][1] /= exp;
      result[exp - i - 1][0] /= exp;
      result[exp - i - 1][1] /= exp;
    }
    for (int i = 1; i < exp/2; i++) {
      double[] tmp = result[i];
      result[i] = result[exp - i];
      result[exp - i] = tmp;
    }
    int[] round = new int[exp];
    for (int i = 0; i < exp; i++) round[i] = (int) Math.round(result[i][0]);
    return round;
  }
  static double[][] fft_recursive(double[][] poly, int n) {
    if (n == 1) return poly;
    double[][] even = new double[n][2];
    double[][] odd = new double[n][2];
    for (int i = 0; i < n; i++) {
      if (i % 2 == 0) {
        even[i / 2][0] = poly[i][0];
        even[i / 2][1] = poly[i][1];
      } else {
        odd[i / 2][0] = poly[i][0];
        odd[i / 2][1] = poly[i][1];
      }
    }
    double[][] feven = fft_recursive(even, n/2);
    double[][] fodd = fft_recursive(odd, n/2);
    double[][] result = new double[n][2];

    for (int i = 0; i < n/2; i++) {
      double reUnit = Math.cos(-2 * Math.PI * i / n);
      double imUnit = Math.sin(-2 * Math.PI * i / n);
      result[i][0] = feven[i][0] + reUnit * fodd[i][0] - imUnit * fodd[i][1];
      result[i][1] = feven[i][1] + reUnit * fodd[i][1] + imUnit * fodd[i][0];
      result[i + n/2][0] = feven[i][0] - (reUnit * fodd[i][0] - imUnit * fodd[i][1]);
      result[i + n/2][1] = feven[i][1] - (reUnit * fodd[i][1] + imUnit * fodd[i][0]);
    }
    return result;
  }
  static double[][] fft(double[][] poly, int n, int idx) {
    // Bit-reverse
    int j = 0;
    int n2 = n/2;
    for (int i = 1; i < n - 1; i++) {
      int n1 = n2;
      while (j >= n1) {
        j -= n1;
        n1 /= 2;
      }
      j += n1;
    
      if (i < j) {
        double t1 = poly[i][0];
        poly[i][0] = poly[j][0];
        poly[j][0] = t1;
        t1 = poly[i][1];
        poly[i][1] = poly[j][1];
        poly[j][1] = t1; 
      }
    }

    // FFT
    int n1 = 0;
    n2 = 1;
    
    for (int i = 0; i < idx; i++) {
      n1 = n2;
      n2 += n2;
      int a = 0;
      for (j = 0; j < n1; j++) {
        double c = cos[a];
        double s = sin[a];
        a += 1 << (idx - i - 1);

        for (int k = j; k + n1 < n; k += n2) {
          double t1 = c * poly[k + n1][0] - s * poly[k + n1][1];
          double t2 = s * poly[k + n1][0] + c * poly[k + n1][1];
          poly[k + n1][0] = poly[k][0] - t1;
          poly[k + n1][1] = poly[k][1] - t2;
          poly[k][0] += t1;
          poly[k][1] += t2;
        }
      }
    }

    return poly;
  }
  static double[][] dft(double[][] poly, int n) {
    double[][][] mat = new double[n][n][2];
    double[][] result = new double[n][2];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        mat[i][j][0] = Math.cos(-2 * Math.PI * i * j / n);
        mat[i][j][1] = Math.sin(-2 * Math.PI * i * j / n);
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        double re = mat[i][j][0] * poly[j][0] - mat[i][j][1] * poly[j][1];
        double im = mat[i][j][1] * poly[j][0] + mat[i][j][0] * poly[j][1];
        result[i][0] += re;
        result[i][1] += im; 
      }
    }
    return result;
  }
}