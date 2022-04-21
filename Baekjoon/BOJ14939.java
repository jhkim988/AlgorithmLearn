import java.io.*;

public class BOJ14939 {
  static int[] rowDi = {0, -1, 0, 1, 0};
  static int[] colDi = {0, 0, -1, 0, 1};
  public static void main(String [] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[][] input = new char[10][10];
    for (int i = 0; i < 10; i++) {
      input[i] = br.readLine().toCharArray();
    }
    recur(0, 0, input, 0);
  }
  static void inversion(int row, int col, char[][] input) {
    for (int i = 0; i < 5; i++) {
      int adjRow = row + rowDi[i];
      int adjCol = col + colDi[i];
      if (adjRow < 0 || adjRow >= 10 || adjCol < 0 || adjCol >= 10) continue;
      if (input[adjRow][adjCol] == '#') input[adjRow][adjCol] = 'O';
      else input[adjRow][adjCol] = '#';
    }
  }
  static int recur(int row, int col, char[][] input, int numInversion) {
    int min = 101;
    if (col == 10) {
      row++; col = 0;
    }
    if (row == 0) {
      min = Integer.min(min, recur(row, col+1, input, numInversion));
      inversion(row, col, input);
      min = Integer.min(min, recur(row, col+1, input, numInversion+1));
      inversion(row, col, input);
      return min;
    }
    int addInversion = 0;
    for (int r = 1; r < 10; r++) {
      for (int c = 0; c < 10; c++) {
        if (input[r-1][c] == 'O') {
          addInversion++;
          inversion(r, c, input);
          
        }
      }
    }
    return min;
  }
}