import java.io.*;

public class BOJ3085 {
  private static class Simulation {
    int size;
    char[][] board;
    Simulation(int size, char[][] board) {
      this.size = size;
      this.board = board;
    }
    int getMax() {
      int[] xDi = {-1, 1, 0, 0};
      int[] yDi = {0, 0, -1, 1};

      int max = 0;
      for (int i = 0; i < size; i++) {
        int tmp = checkRow(i);
        if (max < tmp) {
          max = tmp;
        }
        tmp = checkCol(i);
        if (max < tmp) {
          max = tmp;
        }
      }

      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          for (int k = 0; k < 4; k++) {
            int nextI = i + xDi[k];
            int nextJ = j + yDi[k];
            if (nextI < 0 || nextI >= size || nextJ < 0 || nextJ >= size) continue;
            swap(i, j, nextI, nextJ);
            int tmp = Math.max(checkRow(i), checkCol(j));
            if (max < tmp) {
              max = tmp;
            }
            swap(i, j, nextI, nextJ);
          }
        }
      }
      return max;
    }
    void swap(int row1, int col1, int row2, int col2) {
      char tmp = board[row1][col1];
      board[row1][col1] = board[row2][col2];
      board[row2][col2] = tmp;
    }
    int checkRow(int row) {
      int max = 0;
      int ptr = 0;
      int val = 0;
      char ch = board[row][0];
      while (ptr < size) {
        ch = board[row][ptr];
        val = 0;
        while (ptr < size && ch == board[row][ptr]) {
          val++;
          ptr++;
        }
        if (max < val) {
          max = val;
        }
      }
      return max;
    }
    int checkCol(int col) {
      int max = 0;
      int ptr = 0;
      int val = 0;
      char ch = board[0][col];
      while (ptr < size) {
        ch = board[ptr][col];
        val = 0;
        while (ptr < size && ch == board[ptr][col]) {
          val++;
          ptr++;
        }
        if (max < val) {
          max = val;
        }
      }
      return max;
    }
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          sb.append(board[i][j]);
        }
        sb.append('\n');
      }
      return sb.toString();
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int size = Integer.parseInt(br.readLine());
    char[][] board = new char[size][];
    for (int i = 0; i < size; i++) {
      board[i] = br.readLine().toCharArray();
    }
    Simulation simul = new Simulation(size, board);
    bw.write(simul.getMax() + "\n");
    bw.flush();
  }  
}
