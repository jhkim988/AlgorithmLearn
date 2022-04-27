import java.io.*;
import java.util.*;

public class BOJ19236 {
  static int[] rowDi = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] colDi = {0, -1, -1, -1, 0, 1, 1, 1};
  private static class Shark {
    int row, col, direction;
    Shark(int row, int col, int direction) {
      this.row = row;
      this.col = col;
      this.direction = direction;
    }
  }
  private static class Fish implements Cloneable {
    int row, col;
    Fish(int row, int col) {
      this.row = row;
      this.col = col;
    }
    @Override
    public Fish clone() {
      return new Fish(row, col);
    }
  }
  private static class Cell {
    int id, direction;
    boolean alive = true;
    Cell(int id, int direction) {
      this.id = id;
      this.direction = direction;
    }
    @Override
    public Cell clone() {
      Cell copy = new Cell(id, direction);
      copy.alive = this.alive;
      return copy;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    Cell[][] watertank = new Cell[4][4];
    Fish[] farr = new Fish[16];
    for (int i = 0; i < 4; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        int id = Integer.parseInt(st.nextToken())-1;
        int direction = Integer.parseInt(st.nextToken())-1; 
        watertank[i][j] = new Cell(id, direction);
        farr[id] = new Fish(i, j);
      }
    }
    Shark shark = new Shark(0, 0, watertank[0][0].direction);
    watertank[shark.row][shark.col].alive = false;
    int answer = recur(farr, watertank, shark, watertank[shark.row][shark.col].id);
    bw.write(Integer.toString(answer));
    bw.flush();
  }
  static void move(Fish[] farr, Cell[][] watertank, Shark shark) {
    for (int id = 0; id < 16; id++) {
      Fish fcrnt = farr[id];
      Cell ccrnt = watertank[fcrnt.row][fcrnt.col];
      if (!ccrnt.alive) continue;
      for (int k = 0; k < 8; k++, watertank[farr[id].row][farr[id].col].direction = (watertank[farr[id].row][farr[id].col].direction+1) % 8) {
        int adjRow = farr[id].row + rowDi[watertank[farr[id].row][farr[id].col].direction];
        int adjCol = farr[id].col + colDi[watertank[farr[id].row][farr[id].col].direction];
        if (adjRow < 0 || adjRow >= 4 || adjCol < 0 || adjCol >= 4) continue;
        if (adjRow == shark.row && adjCol == shark.col) continue;
        Cell cadj = watertank[adjRow][adjCol];
        watertank[adjRow][adjCol] = ccrnt;
        watertank[fcrnt.row][fcrnt.col] = cadj;
        Fish fadj = farr[cadj.id];
        int rowtmp = fcrnt.row;
        int coltmp = fcrnt.col;
        fcrnt.row = fadj.row;
        fcrnt.col = fadj.col;
        fadj.row = rowtmp;
        fadj.col = coltmp;
        break;
      }
    }
  }
  static int recur(Fish[] farr, Cell[][] watertank, Shark shark, int val) {
    System.out.println("shark: " + shark.row + ", " + shark.col + ", " + shark.direction);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        System.out.print("[" + watertank[i][j].id + ": " + watertank[i][j].direction + ", " + (watertank[i][j].alive ? "aliv]" : "dead]"));
      }
      System.out.println();
    }
    Fish[] fclone = farr.clone();
    Cell[][] cclone = new Cell[watertank.length][];
    for (int i = 0; i < 4; i++) cclone[i] = watertank[i].clone();
    move(fclone, cclone, shark);
    int max = 0;
    for (int k = 1; k < 4; k++) {
      int nextRow = shark.row + k*rowDi[shark.direction];
      int nextCol = shark.col + k*colDi[shark.direction];
      if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) break;
      if (!watertank[nextRow][nextCol].alive) continue;
      cclone[nextRow][nextCol].alive = false;
      max = Integer.max(max, recur(fclone, cclone, new Shark(nextRow, nextCol, cclone[nextRow][nextCol].direction), val+cclone[nextRow][nextCol].id));
      cclone[nextRow][nextCol].alive = true;
    }
    return max;
  }
}
