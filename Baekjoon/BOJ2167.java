import java.io.*;
import java.util.*;

public class BOJ2167 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int[][] psum = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      psum[i][0] = Integer.parseInt(st.nextToken());
      for (int j = 1; j < col; j++) {
        psum[i][j] = psum[i][j-1] + Integer.parseInt(st.nextToken());
      }
    }
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int srow = Integer.parseInt(st.nextToken())-1;
      int scol = Integer.parseInt(st.nextToken())-1;
      int trow = Integer.parseInt(st.nextToken())-1;
      int tcol = Integer.parseInt(st.nextToken())-1;
      int answer = 0;
      for (int i = srow; i <= trow; i++) {
        answer += psum[i][tcol] - (scol == 0 ? 0 : psum[i][scol-1]);
      }
      bw.write(Integer.toString(answer));
      bw.newLine();
    }
    bw.flush();
  }
}