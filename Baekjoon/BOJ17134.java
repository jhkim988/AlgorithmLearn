import java.io.*;
import java.util.*;

public class BOJ17134 {
  public static void main(String[] args) throws IOException {
    final int len = 1_000_000;
    boolean[] primeFilter = eratosthenes(len);
    int[] oddPrimes = new int[len];
    int[] evenSemiPrimes = new int[len];
    for (int i = 3; i < len; i++) if (primeFilter[i]) oddPrimes[i] = 1;
    for (int i = 2; 2*i < len; i++) if (primeFilter[i]) evenSemiPrimes[2*i] = 1;
    int[] poly = polyProduct(oddPrimes, evenSemiPrimes);

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int input = Integer.parseInt(br.readLine());
      bw.write(poly[input]);
      bw.write('\n');
    }
    bw.flush();
  }
  static boolean[] eratosthenes(int size) {
    boolean[] result = new boolean[size];
    Arrays.fill(result, true);
    result[0] = result[1] = false;    
    int step = 1;
    while (step * step < size) {
      while (!result[++step]);
      for (int ptr = 2 * step; ptr < size; ptr += step) result[ptr] = false;
    }
    return result;
  }
  static int[] polyProduct(int[] p, int[] q) {
    int max = Math.max(p.length, q.length);
    int len = 1;
    int exp = 0;
    while (len < max) {
      len *= 2;
      exp++;
    }
    double[][] fproduct = new double[len][2];
    double[][] fp = fft(p, false);
    double[][] fq = fft(q, false);
    for (int i = 0; i < len; i++) {
      fproduct[i][0] = fp[i][0] * fq[i][0] - fp[i][1] * fq[i][1];
      fproduct[i][1] = fp[i][0] * fq[i][1] + fp[i][1] * fq[i][0];
    }
    double[][] product = ffp(fproduct, true);
    int[] result = new int[len];
    for (int i = 0; i < len; i++) result[i] = (int) Math.round(product[i][0]);
    return result;
  }
  static double[][] ffp(double[][] p, boolean isInverse) {
    
  }
}
