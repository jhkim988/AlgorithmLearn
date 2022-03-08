import java.io.*;
import java.util.*;

public class BOJ1051 {
  static int[] rowDi = {1, 0, 1}, colDi = {0, 1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] rect = new char[row][col];
    for (int i = 0; i < row; i++) {
      rect[i] = br.readLine().toCharArray();
    }

    int max = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int possibleLen = Integer.min(row - i, col - j);
        int size = 1;
        char ch = rect[i][j];
        for (int k = 1; k < possibleLen; k++) {
          boolean possible = true;
          for (int l = 0; l < 3; l++) {
            int cornerRow = i + rowDi[l] * k;
            int cornerCol = j + colDi[l] * k;
            possible = possible && rect[cornerRow][cornerCol] == ch;
          }
          if (possible) size = k+1;
        }
        if (max < size) max = size;
      }
    }

    bw.write(Integer.toString(max*max));
    bw.newLine();
    bw.flush();
  }
}