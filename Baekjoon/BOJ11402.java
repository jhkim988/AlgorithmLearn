import java.io.*;
import java.util.*;

public class BOJ11402 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long n = Long.parseLong(st.nextToken());
    long k = Long.parseLong(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    long[][] comb = new long[m][m];
    for (int i = 0; i < m; i++) comb[i][0] = comb[i][i] = 1;
    for (int i = 2; i < m; i++) {
      for (int j = 1; j <= i; j++) {
        comb[i][j] = (comb[i-1][j-1] + comb[i-1][j])%m;
      }
    }
    long val = 1;
    while (n != 0 || k != 0) {
      val *= comb[(int)(n%m)][(int)(k%m)];
      val %= m;
      n /= m;
      k /= m;
    }
    bw.write(Long.toString(val));
    bw.flush();
  }
}
