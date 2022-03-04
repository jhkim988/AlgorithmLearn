import java.io.*;
import java.util.*;

public class BOJ11559 {
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  private static class Simulation {
    static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    int row, col;
    char[][] field;
    Simulation(char[][] field) {
      this.field = field;
      row = field.length;
      col = field[0].length;
    }
    boolean pop() {
      boolean flag = false;
      Queue<Pair> que = new LinkedList<>();
      boolean[][] visit = new boolean[row][col];
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (field[i][j] == '.') continue;
          if (visit[i][j]) continue;
          Queue<Pair> delete = new LinkedList<>();
          char color = field[i][j];
          Pair start = new Pair(i, j);
          que.add(start);
          delete.add(start);
          visit[i][j] = true;
          while (!que.isEmpty()) {
            Pair crnt = que.poll();
            for (int k = 0; k < 4; k++) {
              int adjRow = crnt.row + rowDi[k];
              int adjCol = crnt.col + colDi[k];
              if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
              if (visit[adjRow][adjCol]) continue;
              if (field[adjRow][adjCol] != color) continue;
              visit[adjRow][adjCol] = true;
              Pair adj = new Pair(adjRow, adjCol);
              que.add(adj);
              delete.add(adj); 
            }
          }
          if (delete.size() >= 4) {
            flag = true;
            while (!delete.isEmpty()) {
              Pair crnt = delete.poll();
              field[crnt.row][crnt.col] = '.';
            }
          }
        }
      }
      return flag;
    }
    void down() {
      Queue<Character> que = new LinkedList<>();
      for (int c = 0; c < col; c++) {
        for (int r = row-1; r >= 0; r--) {
          if (field[r][c] == '.') continue;
          que.add(field[r][c]);
          field[r][c] = '.';
        }
        int ptr = row-1;
        while (!que.isEmpty()) {
          field[ptr--][c] = que.poll();
        } 
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[][] field = new char[12][];
    for (int i = 0; i < 12; i++) {
      field[i] = br.readLine().toCharArray();
    }
    Simulation simul = new Simulation(field);
    int numChain = 0;
    while (simul.pop()) {
      numChain++;
      simul.down();
    }
    bw.write(Integer.toString(numChain));
    bw.newLine();
    bw.flush();
  }
}
