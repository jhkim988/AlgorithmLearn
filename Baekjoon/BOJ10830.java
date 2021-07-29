import java.io.*;
import java.util.*;

public class BOJ10830 {
  static TreeMap<Long, int[][]> tm = new TreeMap<>(); 
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int size = Integer.parseInt(st.nextToken());
    long pow = Long.parseLong(st.nextToken());

    int[][] mat = new int[size][size];
    for (int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        mat[i][j] = Integer.parseInt(st.nextToken()) % 1000;
      }
    }

    int[][] result = power(mat, pow);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size - 1; j++) {
        bw.write(result[i][j] + " ");
      }
      bw.write(result[i][size - 1] + "\n");
    }
    bw.flush();
  }
  static int[][] power(int[][] A, long pow) {
    int size = A.length;
    if (pow == 0) {
      int[][] result = new int[size][size];
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
    int[][] result = product(power(A, pow / 2), power(A, pow - pow / 2), size, size, size, size);
    tm.put(pow, result);
    return result;
  }
  static int[][] product(int[][] A, int[][] B, int rowA, int colA, int rowB, int colB) {
    assert colA == rowB;
    int[][] C = new int[rowA][colB];
    for (int i = 0; i < rowA; i++) {
      for (int j = 0; j < colB; j++) {
        for (int k = 0; k < colA ; k++) {
          C[i][j] += (A[i][k] * B[k][j]) % 1000;
          C[i][j] %= 1000;
        }
      }
    }
    return C;
  }
}
