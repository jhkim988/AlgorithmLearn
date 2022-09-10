import java.io.*;
import java.util.*;

public class BOJ2738 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int[][] mat = new int[n][m];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        mat[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        mat[i][j] += Integer.parseInt(st.nextToken());
      }
    }

    for(int i = 0; i < n; i++) {
      bw.write(Integer.toString(mat[i][0]));
      for (int j = 1; j < m; j++) {
        bw.write(' ');
        bw.write(Integer.toString(mat[i][j]));
      }
      bw.newLine();
    }
    bw.flush();
  }
}
