import java.io.*;

public class BOJ12850 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int D = Integer.parseInt(br.readLine());
    long[][] mat = {
      {0, 1, 1, 0, 0, 0, 0, 0},
      {1, 0, 1, 1, 0, 0, 0, 0},
      {1, 1, 0, 1, 1, 0, 0, 0},
      {0, 1, 1, 0, 1, 1, 0, 0},
      {0, 0, 1, 1, 0, 1, 0, 1},
      {0, 0, 0, 1, 1, 0, 1, 0},
      {0, 0, 0, 0, 0, 1, 0, 1},
      {0, 0, 0, 0, 1, 0, 1, 0}
    };
    long[][] ans = pow(mat, D);
    bw.write(ans[0][0] + "\n");
    bw.flush();

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        
      }
    }
  }
  static long[][] pow(long[][] base, int exp) {
    long[][] result = new long[base.length][base.length];
    if (exp == 0) {
      return  result;
    }
    if (exp == 1) {
      for (int i = 0; i < base.length; i++) {
        for (int j = 0; j < base.length; j++) {
          result[i][j] = base[i][j];
        }
      }
      return result;
    }
    long[][] half = pow(base, exp / 2);
    if (exp % 2 == 0) {
      return mult(half, half);
    } else {
      return mult(base, mult(half, half));
    }
  }
  static long[][] mult(long[][] A, long[][] B) {
    long[][] result = new long[A.length][A.length];
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) {
        for (int k = 0; k < A.length; k++) {
          result[i][j] += (A[i][k] * B[k][j]) % 1_000_000_007;
          result[i][j] %= 1_000_000_007;
        }
      }
    }
    return result;
  }
}
