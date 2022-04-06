import java.io.*;

public class BOJ1913 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int target = Integer.parseInt(br.readLine());
    int[][] board = new int[n][n];
    int findRow = 0, findCol = 0;
    int row = n/2;
    int col = n/2;
    int[] rowDi = {0, -1, 0, 1}, colDi = {-1, 0, 1, 0};
    int direction = 0;
    for (int i = 1; i <= n*n; i++) {
      board[row][col] = i;
      if (i == target) {
        findRow = row;
        findCol = col;
      }
      int nextDi = (direction + 1) % 4;
      int nextRow = row + rowDi[nextDi];
      int nextCol = col + colDi[nextDi];
      if (board[nextRow][nextCol] == 0) {
        direction = nextDi;
        row = nextRow;
        col = nextCol;
      } else {
        row += rowDi[direction];
        col += colDi[direction];
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        bw.write(Integer.toString(board[i][j]));
        bw.write(' ');
      }
      bw.newLine();
    } 
    bw.write(Integer.toString(findRow + 1));
    bw.write(' ');
    bw.write(Integer.toString(findCol + 1));
    bw.flush();
  }  
}
