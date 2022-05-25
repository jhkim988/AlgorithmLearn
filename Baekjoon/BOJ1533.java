import java.io.*;
import java.util.*;

public class BOJ1533 {
  static final int DIVISOR = 1_000_003;
  static long[][] tmp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(st.nextToken());
    long[][] mat = new long[5*n][5*n];    
    for (int i = 0; i < n; i++) {
      char[] charr = br.readLine().toCharArray();
      for (int j = 0; j < n; j++) {
        int val = charr[j] - '0';
        mat[i*5+val][j*5] = 1;
      }
    }
    tmp = new long[5*n][5*n];

    long[][] mat_t = pow(mat, t);
    long sum = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        sum = (sum+mat_t[(start-1)*5+i][(end-1)*5+j])%DIVISOR;
      }
    }
    bw.write(Long.toString(sum));
    bw.flush();
  }
  static long[][] pow(long[][] base, int exp) {
    int n = base.length;
    long[][] ret = new long[n][n]; 
    if (exp == 0) {
      for (int i = 0; i < n; i++) ret[i][i] = 1;
    } else if (exp == 1) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          ret[i][j] = base[i][j];
        }
      }
    } else {
      long[][] half = pow(base, exp/2);
      product(half, half);
      if (exp % 2 == 1) product(half, base);
      ret = half;
    }
    return ret;
  }
  static void product(long[][] m1, long[][] m2) {
    for (int i = 0; i < m1.length; i++) Arrays.fill(tmp[i], 0);
    for (int i = 0; i < m1.length; i++) {
      for (int j = 0; j < m2[0].length; j++) {
        for (int k = 0; k < m1[0].length; k++) {
          tmp[i][j] = (tmp[i][j]+(m1[i][k]*m2[k][j])%DIVISOR)%DIVISOR;
        }
      }
    }
    for (int i = 0; i < m1.length; i++) {
      System.arraycopy(tmp[i], 0, m1[i], 0, m1.length);
    }
  }
}
