import java.io.*;
import java.util.*;

public class BOJ16395 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st  = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int[][] comb = new int[n][n];
    for (int i = 0; i < n; i++) {
      comb[i][0] = comb[i][i] = 1;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < i; j++) {
        comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
      }
    }
    bw.write(Integer.toString(comb[n-1][k-1]));
    bw.flush();
  }
}
