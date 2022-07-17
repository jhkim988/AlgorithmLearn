import java.io.*;
import java.util.*;

public class BOJ2174 {
  static int[] rowDi = {0, -1, 0, 1}, colDi = {1, 0, -1, 0};
  private static class Pair {
    int row, col, direction;
    Pair(int row, int col, char d) {
      this.row = row;
      this.col = col;
      switch (d) {
        case 'E': {
          this.direction = 0;
          break;
        }
        case 'N': {
          this.direction = 1;
          break;
        }
        case 'W': {
          this.direction = 2;
          break;
        }
        case 'S': {
          this.direction = 3;
          break;
        }
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int col = Integer.parseInt(st.nextToken());
    int row = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int numRobot = Integer.parseInt(st.nextToken());
    int numOrder = Integer.parseInt(st.nextToken());
    
    int[][] occupied = new int[row][col];
    Pair[] robots = new Pair[numRobot+1];
    for (int i = 1; i <= numRobot; i++) {
      st = new StringTokenizer(br.readLine());
      int c = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());
      char d = st.nextToken().charAt(0);
      robots[i] = new Pair(row-r, c-1, d);
      occupied[row-r][c-1] = i;
    }
    boolean wrong = false;
    while (numOrder-- > 0) {
      st = new StringTokenizer(br.readLine());
      boolean out = false;
      boolean collision = false;
      int id = Integer.parseInt(st.nextToken());
      char order = st.nextToken().charAt(0);
      int repeat = Integer.parseInt(st.nextToken());
      int nextRow = robots[id].row;
      int nextCol = robots[id].col;
      if (order == 'F') {
        while (repeat-- > 0 && !out && !collision) {
          nextRow += rowDi[robots[id].direction];
          nextCol += colDi[robots[id].direction];
          if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) out = true;
          if (!out && occupied[nextRow][nextCol] != 0) collision = true;
          if (!out && !collision) {
            occupied[robots[id].row][robots[id].col] = 0;
            robots[id].row = nextRow;
            robots[id].col = nextCol;
            occupied[robots[id].row][robots[id].col] = id;
          }
        }
      } else if (order == 'L') {
        robots[id].direction += repeat%4;
        robots[id].direction %= 4;
      } else if (order == 'R') {
        robots[id].direction += (4-repeat%4);
        robots[id].direction %= 4;
      }
      if (out) {
        bw.write("Robot ");
        bw.write(Integer.toString(id));
        bw.write(" crashes into the wall\n");
        wrong = true;
        break;
      } else if (collision) {
        bw.write("Robot ");
        bw.write(Integer.toString(id));
        bw.write(" crashes into robot ");
        bw.write(Integer.toString(occupied[nextRow][nextCol]));
        bw.newLine();
        wrong = true;
        break;
      }
    }
    if (!wrong) bw.write("OK\n");
    bw.flush();
  }
  static void print(int[][] occupied) {
    System.out.println("------------------------");
    for (int i = 0; i < occupied.length; i++) {
      for (int j = 0; j < occupied[0].length; j++) {
        System.out.print(occupied[i][j] + " ");
      }
      System.out.println();
    }
  }
}
