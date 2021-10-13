import java.io.*;
import java.util.*;

public class BOJ1080 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] matA = new char[row][];
    char[][] matB = new char[row][];
    for (int i = 0; i < row; i++) {
      matA[i] = br.readLine().toCharArray();
    }
    for (int i = 0; i < row; i++) {
      matB[i] = br.readLine().toCharArray();
    }

    if (row < 3 || col < 3) {
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (matA[i][j] != matB[i][j]) {
            bw.write("-1\n");
            bw.flush();
            return;
          }
        }
      }
      bw.write("0\n");
      bw.flush();
      return;
    }

    boolean[][] check = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (matA[i][j] != matB[i][j]) {
          check[i][j] = true;
        }
      }
    }

    int num = 0;
    for (int i = 0; i < row - 2; i++) {
      for (int j = 0; j < col - 2; j++) {
        if (check[i][j]) {
          filp(check, i, j);
          num++;
        }
      }
    }

    for (int i = 0; i < row - 2; i++) {
      for (int j = col - 2; j < col; j++) {
        if (check[i][j]) {
          bw.write("-1\n");
          bw.flush();
          return;
        }
      }
    }
    for (int i = row - 2; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (check[i][j]) {
          bw.write("-1\n");
          bw.flush();
          return;
        }
      }
    }
    bw.write(num + "\n");
    bw.flush();
  }
  static void filp(boolean[][] check, int rowIdx, int colIdx) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        check[rowIdx + i][colIdx + j] = !check[rowIdx + i][colIdx + j]; 
      }
    }
  }
}
