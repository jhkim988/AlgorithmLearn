import java.io.*;
import java.util.*;

public class BOJ14503 {
  static int numRow;
  static int numCol;
  static final int[] rowDi = {-1, 0, 1, 0};
  static final int[] colDi = {0, 1, 0, -1};
  private static class Simulation {
    int numClear = 0;
    int[][] map;
    boolean[][] visit = new boolean[numRow][numCol];
    int posRow, posCol, direction;
    Simulation(int[][] map, int startRow, int startCol, int startDirection) {
      this.map = map;
      this.posRow = startRow;
      this.posCol = startCol;
      this.direction = startDirection;
    }
    boolean validLeft() {
      int leftDirection = direction == 0 ? 3 : direction - 1;
      int leftRow = posRow + rowDi[leftDirection];
      int leftCol = posCol + colDi[leftDirection];
      if (leftRow < 0 || leftRow >= numRow || leftCol < 0 || leftCol >= numCol) return false;
      if (map[leftRow][leftCol] == 1) return false;
      if (visit[leftRow][leftCol]) return false;
      return true;
    }
    void rotateLeft() {
      direction = direction == 0 ? 3 : direction - 1;
    }
    void goFront() {
      posRow += rowDi[direction];
      posCol += colDi[direction];
    }
    void goBack() {
      posRow -= rowDi[direction];
      posCol -= colDi[direction];
    }
    void clear() {
      visit[posRow][posCol] = true;
      numClear++;
    }
    boolean fourDirectionCheck() {
      for (int i = 0; i < 4; i++) {
        int adjRow = posRow + rowDi[i];
        int adjCol = posCol + colDi[i];
        if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
        if (!visit[adjRow][adjCol] && map[adjRow][adjCol] == 0) return false;
      }
      return true;
    }
    boolean checkEnd() {
      if (posRow < 0 || posRow >= numRow || posCol < 0 || posCol >= numCol) return true;
      if (map[posRow][posCol] == 1) return true;
      return false;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    numRow = Integer.parseInt(st.nextToken());
    numCol = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int startRow = Integer.parseInt(st.nextToken());
    int startCol = Integer.parseInt(st.nextToken());
    int startDirection = Integer.parseInt(st.nextToken());
    int[][] map = new int[numRow][numCol];
    for (int i = 0; i < numRow; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < numCol; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Simulation simul = new Simulation(map, startRow, startCol, startDirection);
    outer: while (true) {
      simul.clear();
      while (true) {
        if (simul.fourDirectionCheck()) {
          simul.goBack();
          if (simul.checkEnd()) break outer;
          continue;
        }
        if (simul.validLeft()) {
          simul.rotateLeft();
          simul.goFront();
          continue outer;
        } else {
          simul.rotateLeft();
          continue;
        }
      }
    }
    bw.write(simul.numClear + "\n");
    bw.flush();
  }  
}
