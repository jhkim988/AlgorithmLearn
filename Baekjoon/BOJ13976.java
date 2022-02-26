import java.io.*;

public class BOJ13976 {
  static final long DIVISOR = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long n = Long.parseLong(br.readLine());
    if ((n & 1) != 0) {
      bw.write("0\n");
      bw.flush();
      return;
    }
    long[][] mat = {{3, 1}, {2, 1}};
    mat = pow(mat, n/2-1);
    long[][] answer = new long[2][1];
    answer[0][0] = 3;
    answer[1][0] = 2;
    answer = product(mat, answer);
    bw.write(Long.toString(answer[0][0]));
    bw.newLine();
    bw.flush();
  }
  static long[][] copy(long[][] origin) {
    long[][] tmp = new long[origin.length][origin[0].length];
    for (int i = 0; i < tmp.length; i++) {
      System.arraycopy(origin[i], 0, tmp[i], 0, tmp[i].length);
    }
    return tmp;
  }
  static long[][] product(long[][] a, long[][] b) {
    long[][] result = new long[a.length][b[0].length];
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        for (int k = 0; k < b.length; k++) {
          result[i][j] += (a[i][k]*b[k][j]) % DIVISOR;
          result[i][j] %= DIVISOR;
        }
      }
    }
    return result;
  }
  static long[][] pow(long[][] mat, long exp) {
    long[][] half = copy(mat);
    long[][] acc = {{1, 0}, {0, 1}};
    for (long bit = 1; bit <= exp; bit <<= 1) {
      if ((exp & bit) != 0) {
        acc = product(acc, half);
      }
      half = product(half, half);
    }
    return acc;
  }  
}
