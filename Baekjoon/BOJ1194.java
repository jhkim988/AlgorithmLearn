import java.io.*;
import java.util.*;

public class BOJ1194 {
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] map = new char[row][];
    Pair start = new Pair(0, 0);
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
      for (int j = 0; j < col; j++) {
        if (map[i][j] == '0') {
          start.row = i;
          start.col = j;
        }
      }
    }
    
  }
}
