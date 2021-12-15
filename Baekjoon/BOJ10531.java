import java.io.*;

public class BOJ10531 {
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
    // System.out.println(printPoly(product));
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
    int exp = 1;
    while (exp < len) exp *= 2;
    double[][] la = new double[exp][2];
    double[][] lb = new double[exp][2];
    for (int i = 0; i < a.length; i++) la[i][0] = a[i];
    for (int i = 0; i < b.length; i++) lb[i][0] = b[i];
    
    fft(la, exp);
    fft(lb, exp);
    double[][] result = new double[exp][2];
    for (int i = 0; i < exp; i++) {
      result[i][0] = la[i][0] * lb[i][0] - la[i][1] * lb[i][1];
      result[i][1] = la[i][0] * lb[i][1] + la[i][1] * lb[i][0];
    }
    fft(result, exp);
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
  static double[][] fft(double[][] poly, int n) {
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
    fft(even, n/2);
    fft(odd, n/2);

    for (int i = 0; i < n/2; i++) {
      double reUnit = Math.cos(-2 * Math.PI * i / n);
      double imUnit = Math.sin(-2 * Math.PI * i / n);
      poly[i][0] = even[i][0] + reUnit * odd[i][0] - imUnit * odd[i][1];
      poly[i][1] = even[i][1] + reUnit * odd[i][1] + imUnit * odd[i][0];
      poly[i + n/2][0] = even[i][0] - (reUnit * odd[i][0] - imUnit * odd[i][1]);
      poly[i + n/2][1] = even[i][1] - (reUnit * odd[i][1] + imUnit * odd[i][0]);
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