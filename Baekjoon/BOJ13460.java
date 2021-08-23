import java.io.*;
import java.util.*;

public class BOJ13460 {
  private static class Pair {
    int row;
    int col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  private static class Simulation {
    static int[] xDi = {-1, 0, 1, 0};
    static int[] yDi = {0, -1, 0, 1};

    char[][] stat;
    Pair red;
    Pair blue;
    Pair goal;
    boolean redInHole;
    boolean blueInHole;
    Simulation (char[][] stat, Pair red, Pair blue, Pair goal) {
      this.stat = stat;
      this.red = red;
      this.blue = blue;
      this.goal = goal;
      redInHole = false;
      blueInHole = false;
    }
    void move(int direct) {
      int redNextRow = red.row + xDi[direct];
      int redNextCol = red.col + yDi[direct];
      
    }
    void moveLeft() {
      while (this.red.col > 0) {
        if (stat[this.red.row][this.red.col - 1] == '#') {
          break;
        } else if (stat[this.red.row][this.red.col - 1] == 'B') {
          if (this.blue.col > 0 && stat[this.blue.row][this.blue.col - 1] == '#') {
            break;
          } else {
            while (this.blue.col > 0) {
              if (stat[this.blue.row][this.blue.col - 1] == '#') {
                break;
              } else if (stat[this.blue.row][this.blue.col - 1] == 'O') {
                stat[this.blue.row][this.blue.col] = '.';
                blueInHole = true;
              } else { // empty space
                stat[this.blue.row][this.blue.col] = '.';
                stat[this.blue.row][this.blue.col - 1] = 'B';
                this.blue.col--;
              }
            }
          }
        } else if (stat[this.red.row][this.red.col - 1] == 'O') {
          stat[this.red.row][this.red.col] = '.';
          redInHole = true;
        } else { // empty space
          stat[this.red.row][this.red.col] = '.';
          stat[this.red.row][this.red.col - 1] = 'R';
          this.red.col--;
        }
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] stat = new char[row][col];
    for (int i = 0; i < row; i++) {
      stat[i] = br.readLine().toCharArray();
    }

  }
}
