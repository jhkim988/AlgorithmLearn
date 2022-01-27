import java.io.*;
import java.util.*;

public class BOJ1917 {
  static int[] rowDi = {-1, 0, 0, 1};
  static int[] colDi = {0, -1, 1, 0};
  private static class Hexahedron {
    // 0: bottom, 5: top
    // 1: right, 2: up, 3: left, 4: down
    int[] pos = {0, 1, 2, 3, 4, 5};
    boolean[] surface = new boolean[6];
    void move(int direction) {
      switch (direction) {
        case 0: {
          moveUp();
          break;
        }
        case 1: {
          moveLeft();
          break;
        }
        case 2: {
          moveRight();
          break;
        }
        case 3: {
          moveDown();
          break;
        }
      }
    }
    void moveLeft() {
      int[] posNew = {pos[3], pos[0], pos[2], pos[5], pos[4], pos[1]};
      pos = posNew;
    }
    void moveRight() {
      int[] posNew = {pos[1], pos[5], pos[2], pos[0], pos[4], pos[3]};
      pos = posNew;
    }
    void moveUp() {
      int[] posNew = {pos[2], pos[1], pos[5], pos[3], pos[0], pos[4]};
      pos = posNew;
    }
    void moveDown() {
      int[] posNew = {pos[4], pos[1], pos[0], pos[3], pos[5], pos[2]};
      pos = posNew;
    }
    boolean mark() {
      if (surface[pos[0]]) return false;
      return surface[pos[0]] = true;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = 3;
    while (numTest-- > 0) {
      int startRow = 0;
      int startCol = 0;
      int[][] input = new int[6][6];
      for (int i = 0; i < 6; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 6; j++) {
          input[i][j] = Integer.parseInt(st.nextToken());
          if (input[i][j] == 1) {
            startRow = i;
            startCol = j;
          }
        }
      }
      Hexahedron h = new Hexahedron();
      boolean[][] visit = new boolean[6][6];
      visit[startRow][startCol] = true;
      if (recur(input, startRow, startCol, h, visit) > 0) {
        bw.write("yes\n");
      } else {
        bw.write("no\n");
      }
    }
    bw.flush();
  }
  static int recur(int[][] input, int row, int col, Hexahedron h, boolean[][] visit) {
    if (!h.mark()) return -1;
    for (int k = 0; k < 4; k++) {
      int nextRow = row + rowDi[k];
      int nextCol = col + colDi[k];
      if (nextRow < 0 || nextRow >= 6 || nextCol < 0 || nextCol >= 6) continue;
      if (input[nextRow][nextCol] == 0) continue;
      if (visit[nextRow][nextCol]) continue;      
      visit[nextRow][nextCol] = true;
      h.move(k);
      int result = recur(input, nextRow, nextCol, h, visit);
      if (result < 0) return -1;
      else if (result > 0) return 1;
      boolean flag = true;
      for (int i = 0; i < 6; i++) {
        flag = flag && h.surface[i];
      }
      if (flag) return 1;
      visit[nextRow][nextCol] = false;
      h.move(3-k);
    }
    return 0;
  }
}
