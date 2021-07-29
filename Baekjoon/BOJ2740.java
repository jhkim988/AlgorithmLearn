import java.io.*;
import java.util.StringTokenizer;

public class BOJ2740 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int rowA = Integer.parseInt(st.nextToken());
    int colA = Integer.parseInt(st.nextToken());
    int[][] matA = new int[rowA][colA];

    for (int i = 0; i < rowA; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < colA; j++) {
        matA[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    st = new StringTokenizer(br.readLine());
    int rowB = Integer.parseInt(st.nextToken());
    int colB = Integer.parseInt(st.nextToken());
    int[][] matB = new int[rowB][colB];
    for (int i = 0; i < rowB; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < colB; j++) {
        matB[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] matC = product(matA, matB, rowA, colA, rowB, colB);
    for (int i = 0; i < rowA; i++) {
      for (int j = 0; j < colB - 1; j++) {
       bw.write(matC[i][j] + " ");
      }
      bw.write(matC[i][colB - 1] + "\n");
    }
    bw.flush();
  }
  static int[][] product(int[][] A, int[][] B, int rowA, int colA, int rowB, int colB) {
    assert colA == rowB;
    int[][] C = new int[rowA][colB];
    for (int i = 0; i < rowA; i++) {
      for (int j = 0; j < colB; j++) {
        for (int k = 0; k < colA ; k++) {
          C[i][j] += A[i][k] * B[k][j];
        }
      }
    }
    return C;
  }
}
