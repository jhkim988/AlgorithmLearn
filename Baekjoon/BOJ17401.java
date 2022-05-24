import java.io.*;
import java.util.*;

public class BOJ17401 {
  static final long DIVISOR = 1_000_000_007;
  static long[][] tmp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int T = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());

    long[][][] matrices = new long[T][N][N];
    for (int t = 0; t < T; t++) {
      int m = Integer.parseInt(br.readLine());
      while (m-- > 0) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        matrices[t][a-1][b-1] = c;
      }
    }
    tmp =  new long[N][N];
    int R = D%T;
    long[][] product_T = new long[N][N];
    for (int i = 0; i < N; i++) product_T[i][i] = 1;
    long[][] product_R = new long[N][N];
    for (int i = 0; i < N; i++) product_R[i][i] = 1;

    for (int i = 0; i < T; i++) {
      if (i == R) {
        for (int j = 0; j < N; j++) {
          System.arraycopy(product_T[j], 0, product_R[j], 0, N);
        }
      }
      product(product_T, matrices[i]);
    }
    long[][] answer = new long[N][N];
    answer = pow(product_T, D/T);
    product(answer, product_R);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        bw.write(Long.toString(answer[i][j]));
        bw.write(' ');
      }
      bw.newLine();
    }
    bw.flush();
  }
  static void product(long[][] m1, long[][] m2) {
    int N = m1.length;
    for (int i = 0; i < N; i++) {
      Arrays.fill(tmp[i], 0);
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < N; k++) {
          tmp[i][j] = (tmp[i][j] + (m1[i][k]*m2[k][j])%DIVISOR)%DIVISOR;
        }
      }
    }
    for (int i = 0; i < N; i++) {
      System.arraycopy(tmp[i], 0, m1[i], 0, N);
    }
  }
  static long[][] pow(long[][] m, int exp) {
    int n = m.length;
    long[][] result = new long[n][n];
    if (exp == 0) {
      for (int i = 0; i < n; i++) {
        result[i][i] = 1;
      }
    } else if (exp == 1) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          result[i][j] = m[i][j];
        }
      }
    } else {
      result = pow(m, exp/2);
      product(result, result);
      if (exp%2 == 1) product(result, m);
    }
    return result;
  }
}
