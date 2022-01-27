import java.io.*;
import java.util.*;

public class BOJ17435 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int m = Integer.parseInt(br.readLine());
    int[] f = new int[m + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= m; i++) {
      f[i] = Integer.parseInt(st.nextToken());
    }
    int max = 500_000;
    int log = 0;
    while (1 << log < max) log++;
    int[][] sparse = new int[log + 1][m + 1]; // sparse[l][i] = f_(2^l)(i)
    for (int i = 1; i <= m; i++) {
      sparse[0][i] = f[i];
    }
    for (int l = 1; l <= log; l++) {
      for (int i = 1; i <= m ; i++) {
        sparse[l][i] = sparse[l - 1][sparse[l - 1][i]];
      }
    }

    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      for (int bit = 0; bit <= log; bit++) {
        if ((n & 1 << bit) != 0) {
          x = sparse[bit][x];
        }
      }
      bw.write(Integer.toString(x));
      bw.newLine();
    }
    bw.flush();
  }
}
