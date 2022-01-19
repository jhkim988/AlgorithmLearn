import java.io.*;

public class BOJ2748 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    long[][] mat = {{1L, 1L}, {1L, 0L}};
    mat = matrixPower(mat, N);
    long[][] init = {{0L}, {1L}};
    long[][] result = matrixProduct(mat, init);
    bw.write(Long.toString(result[0][0]));
    bw.newLine();
    bw.flush();
  }
  static long[][] matrixPower(long[][] mat, int power) {
    if (power == 0) {
      long[][] result = {{1L, 0L}, {0L, 1L}};
      return result;
    } else if (power == 1) {
      long[][] result = {{mat[0][0], mat[0][1]}, {mat[1][0], mat[1][1]}};
      return result; 
    } else {
      long[][] half = matrixPower(mat, power/2);
      if (power % 2 == 0) {
        return matrixProduct(half, half);
      } else {
        return matrixProduct(mat, matrixProduct(half, half));
      }
    }
  }
  static long[][] matrixProduct(long[][] A, long[][] B) {
    // must A[0].length == B.length
    long[][] result = new long[A.length][B[0].length];
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < B[0].length; j++) {
        for (int k = 0; k < A[0].length; k++) {
          result[i][j] += A[i][k]*B[k][j];
        }
      }
    }
    return result;
  }
}
