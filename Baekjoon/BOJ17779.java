import java.io.*;
import java.util.*;

public class BOJ17779 {
  static int n;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    int[][] map = new int[n][n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        for (int d1 = 1; j-d1 >= 0; d1++) {
          for (int d2 = 1; j+d2 < n && i+d1+d2<n && j+d2<n; d2++) {
            int val = areafill(map, i, j, d1, d2);
            min = Integer.min(min, val);
          }
        }
      }
    }

    bw.write(Integer.toString(min));
    bw.flush();
  }
  static int areafill(int[][] map, int i, int j, int d1, int d2) {
    int area1 = 0, area2 = 0, area3 = 0, area4 = 0, area5 = 0;
    for (int dd2 = 0; dd2 <= d2; dd2++) {
      int diag = i+j+2*dd2;
      for (int dd1 = 0; dd1 <= d1; dd1++) {
        area5 += map[i+dd1+dd2][diag-(i+dd1+dd2)];
      } 
    }
    for (int dd2 = 0; dd2 < d2; dd2++) {
      int diag = i+j+1+2*dd2;
      for (int dd1 = 0; dd1 < d1; dd1++) {
        area5 += map[i+dd1+dd2+1][diag-(i+dd1+dd2+1)];
      }
    }
    for (int ii = 0; ii < i+d1; ii++) {
      for (int jj = 0; jj <= j; jj++) {
        if (ii+jj < (i+j)) area1 += map[ii][jj];
      }
    }
    for (int ii = 0; ii <= i+d2; ii++) {
      for (int jj = j+1; jj < n; jj++) {
        if (jj-ii > (j-i)) area2 += map[ii][jj];
      }
    }
    for (int ii = i+d1; ii < n; ii++) {
      for (int jj = 0; jj < j-d1+d2; jj++) {
        if (jj-ii < j-i-d1-d1) area3 += map[ii][jj];
      }
    }
    for (int ii = i+d2+1; ii < n; ii++) {
      for (int jj = j-d1+d2; jj < n; jj++) {
        if (ii+jj > (i+d2+j+d2)) area4 += map[ii][jj];
      }
    }
    // System.out.println("call: " + i + ", " + j + ", " + d1 + ", " + d2);
    // System.out.println(area1 + ", " + area2 + ", " + area3 + ", " + area4 + ", " + area5);
    int min = Integer.min(area1, Integer.min(area2, Integer.min(area3, Integer.min(area4, area5))));
    int max = Integer.max(area1, Integer.max(area2, Integer.max(area3, Integer.max(area4, area5))));
    return max-min;
  }
}
