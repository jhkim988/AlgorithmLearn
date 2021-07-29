import java.io.*;
import java.util.*;

public class BOJ11444 {
  static TreeMap<Long, long[][]> tm = new TreeMap<>();
  static long p = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long N = Long.parseLong(br.readLine());
    
    long[][] fibo = new long[2][1];
    fibo[0][0] = 1;
    fibo[1][0] = 0;
    long[][] mat = new long[2][2];
    mat[0][0] = 1;
    mat[0][1] = 1;
    mat[1][0] = 1;
    mat[1][1] = 0;

    bw.write(product(power(mat, N), fibo, 2, 2, 2, 1)[1][0] + "\n");
    bw.flush();
  }
  static long[][] power(long[][] A, long pow) {
    int size = A.length;
    if (pow == 0) {
      long[][] result = new long[size][size];
      for (int i = 0; i < size; i++) {
        result[i][i] = 1;
      }
      return result;
    }
    if (pow == 1) {
      return A;
    }
    if (tm.containsKey(pow)) {
      return tm.get(pow);
    }    
    long[][] result = product(power(A, pow / 2), power(A, pow - pow / 2), size, size, size, size);
    tm.put(pow, result);
    return result;
  }
  static long[][] product(long[][] A, long[][] B, int rowA, int colA, int rowB, int colB) {
    assert colA == rowB;
    long[][] C = new long[rowA][colB];
    for (int i = 0; i < rowA; i++) {
      for (int j = 0; j < colB; j++) {
        for (int k = 0; k < colA ; k++) {
          C[i][j] += (A[i][k] * B[k][j]) % p;
          C[i][j] %= p;
        }
      }
    }
    return C;
  }
}
