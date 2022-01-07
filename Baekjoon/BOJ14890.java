import java.io.*;
import java.util.*;

public class BOJ14890 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());
    int[][] map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int count = 0;
    row: for (int i = 0; i < N; i++) { // row
      int prev = map[i][0];
      for (int j = 1; j < N; j++) {
        int diff = map[i][j] - prev;
        if (diff == 1) {
          for (int k = 1; k <= L; k++) {
            if (j - k < 0) continue row;
            if (map[i][j - k] != prev) continue row; 
          }
          for (int k = 1; k <= L; k++) {
            if (j - L - k < 0) break;
            if (map[i][j - L - k] > prev) continue row;
          }
          prev = map[i][j];
        } else if (diff == -1) {
          for (int k = 0; k < L; k++) {
            if (j + k >= N) continue row;
            if (map[i][j + k] != map[i][j]) continue row;
          }
          for (int k = 0; k < L; k++) {
            if (j + L + k >= N) break;
            if (map[i][j + L + k] > map[i][j]) continue;
          }
          prev = map[i][j];
        } else if (diff == 0) {
          continue;
        } else {
          continue row;
        }
      }
      count++;
    }
    col: for (int j = 0; j < N; j++) { // col
      int prev = map[0][j];
      for (int i = 1; i < N; i++) {
        int diff = map[i][j] - prev;
        if (diff == 1) {
          for (int k = 1; k <= L; k++) {
            if (i - k < 0) continue col;
            if (map[i - k][j] != prev) continue col; 
          }
          for (int k = 1; k <= L; k++) {
            if (i - L - k < 0) break;
            if (map[i - L - k][j] > prev) continue col;
          }
          prev = map[i][j];
        } else if (diff == -1) {
          for (int k = 0; k < L; k++) {
            if (i + k >= N) continue col;
            if (map[i + k][j] != map[i][j]) continue col;
          }
          for (int k = 0; k < L; k++) {
            if (i + L + k >= N) break;
            if (map[i + L + k][j] > map[i][j]) continue col;
          }
          prev = map[i][j];
        } else if (diff == 0) {
          continue;
        } else {
          continue col;
        }
      }
      count++;
    }

    bw.write(count + "\n");
    bw.flush();
  }
}
