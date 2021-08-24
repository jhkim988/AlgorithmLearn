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
    @Override
    public boolean equals(Object p) {
      if (this == p) {
        return true;
      }
      if (this.getClass() != p.getClass()) {
        return false;
      }
      Pair other = (Pair) p;
      return this.row == other.row && this.col == other.col;
    }
    @Override
    public String toString() {
      return "<" + row + ", " + col + ">";
    }
  }
  private static class Simulation {
    static int[] rowDi = {-1, 0, 1, 0};
    static int[] colDi = {0, -1, 0, 1};

    char[][] stat;
    int row;
    int col;
    Pair red;
    Pair blue;
    Pair goal;
    boolean redInHole;
    boolean blueInHole;
    boolean holeFlag;

    Simulation (char[][] stat) {
      this.stat = stat;
      row = this.stat.length;
      col = this.stat[0].length;
      for (int i = 0; i < row; i++) {
        for(int j = 0; j < col; j++) {
          if (this.stat[i][j] == 'R') {
            red = new Pair(i, j);
            this.stat[i][j] = '.';
          } 
          if (this.stat[i][j] == 'B') {
            blue = new Pair(i, j);
            this.stat[i][j] = '.';
          }
          if (this.stat[i][j] == 'O') {
            goal = new Pair(i, j);
          }
        }
      }
      redInHole = false;
      blueInHole = false;
      holeFlag = false;
    }
    ArrayList<Pair> move(int direct, Pair crntRed, Pair crntBlue) {
      Pair redMove = moveTmp(direct, crntRed);
      if (holeFlag) {
        if (redMove.equals(goal)) {
          redInHole = true;
        }
        holeFlag = false;
      }
      Pair blueMove = moveTmp(direct, crntBlue);
      if (holeFlag) {
        if (blueMove.equals(goal)) {
          blueInHole = true;
        }
        holeFlag = false;
      } 
      if (redMove.row == blueMove.row && redMove.col == blueMove.col) {
        if (crntRed.row == crntBlue.row) { // horizontal
          if (crntRed.col < crntBlue.col) { // Red - Blue
            if (direct < 2) { // minus direction
              blueMove.col++;
            } else { // plus direction
              redMove.col--;
            }
          } else { // Blue - Red
            if (direct < 2) { // minus direction
              redMove.col++;
            } else {
              blueMove.col--;
            }
          }
        } else { // vertical
          if (crntRed.row < crntBlue.row) { // Red(up) - Blue(down)
            if (direct < 2) { // minus direction
              blueMove.row++;
            } else { // plus direction
              redMove.row--;
            }
          } else { // Blue(up) - Red(down)
            if (direct < 2) { // minus direction
              redMove.row++;
            } else {
              blueMove.row--;
            }
          }
        }
      } 
      ArrayList<Pair> result = new ArrayList<>();
      result.add(redMove);
      result.add(blueMove);
      return result;
    }
    Pair moveTmp(int direct, Pair pair) {
      int nextRow = pair.row + rowDi[direct];
      int nextCol = pair.col + colDi[direct];
      while (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && stat[nextRow][nextCol] != '#') {
        if (stat[nextRow][nextCol] == 'O') {
          holeFlag = true;
          return goal;
        } 
        nextRow += rowDi[direct];
        nextCol += colDi[direct];
      }
      return new Pair(nextRow - rowDi[direct], nextCol - colDi[direct]);
    }
    public String print(Pair rred, Pair bblue) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (rred.row == i && rred.col == j) {
            sb.append('R');
          } else if (bblue.row == i && bblue.col == j) {
            sb.append('B');
          } else {
            sb.append(stat[i][j]);
          }
        }
        sb.append('\n');
      }
      return sb.toString();
    }
  }
  private static class Action {
    int crnt;
    int count;
    Pair red;
    Pair blue;
    Action(int crnt, int count, Pair red, Pair blue) {
      this.crnt = crnt;
      this.count = count;
      this.red = new Pair(red.row, red.col);
      this.blue = new Pair(blue.row, blue.col);
    }
    @Override
    public String toString() {
      return "count: " + count + "/Move:" + crnt;
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
    Simulation simul = new Simulation(stat);
    // Pair initRed = new Pair(4, 3);
    // Pair initBlue = new Pair(4, 2);
    // ArrayList<Pair> redblue = simul.move(3, initRed, initBlue);
    // System.out.println(simul.print(redblue.get(0), redblue.get(1)));
    // redblue = simul.move(0, redblue.get(0), redblue.get(1));
    // System.out.println(simul.print(redblue.get(0), redblue.get(1)));

    Queue<Action> que = new LinkedList<>();
    Pair initRed = new Pair(simul.red.row, simul.red.col);
    Pair initBlue = new Pair(simul.blue.row, simul.blue.col);
    for (int i = 0; i < 4; i++) {
      que.add(new Action(i, 1, initRed, initBlue));
    }
    while (!que.isEmpty()) {
      Action action = que.poll();
      if (action.count > 10) {
        break;
      }
      ArrayList<Pair> redblue = simul.move(action.crnt, action.red, action.blue);
      // System.out.println("count: " + action.count + " / move: " + action.crnt);
      // System.out.println("red: " + redblue.get(0) + " / blue: " + redblue.get(1));
      // System.out.println(simul.print(action.red, action.blue));
      // System.out.println(simul.print(redblue.get(0), redblue.get(1)));
      if (simul.redInHole) {
        if (simul.blueInHole) {
          simul.redInHole = false;
          simul.blueInHole = false;
          continue;
        }
        // System.out.println("Find");
        bw.write(action.count + "\n");
        bw.flush();
        return;
      }
      if (simul.blueInHole) {
        simul.blueInHole = false;
        if (simul.redInHole) {
          simul.redInHole = false;
        }
        continue;
      }
      for (int i = 0; i < 4; i++) {
        if (action.crnt % 2 == i % 2) continue;
         que.add(new Action(i, action.count + 1, redblue.get(0), redblue.get(1)));
      }
    }
    // System.out.println("Here");
    bw.write("-1\n");
    bw.flush();
  }
}