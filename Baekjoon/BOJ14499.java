import java.io.*;
import java.util.*;

public class BOJ14499 {
  static int[][] map;
  private static class Simulation {
    int posRow, posCol;
    int[] stat = {0, 0, 0, 0, 0, 0, 0}; // {top(0), bottom(1), front(2), back(3), left(4), right(5)}
    Simulation(int startRow, int startCol) {
      this.posRow = startRow;
      this.posCol = startCol;
    }
    void moveEast() {
      int tmp = stat[0];
      stat[0] = stat[4];
      stat[4] = stat[1];
      stat[1] = stat[5];
      stat[5] = tmp;
      posCol++;
      action();
    }
    void moveWest() {
      int tmp = stat[0];
      stat[0] = stat[5];
      stat[5] = stat[1];
      stat[1] = stat[4];
      stat[4] = tmp;
      posCol--;
      action();
    }
    void moveNorth() {
      int tmp = stat[0];
      stat[0] = stat[3];
      stat[3] = stat[1];
      stat[1] = stat[2];
      stat[2] = tmp;
      posRow--;
      action();
    }
    void moveSouth() {
      int tmp = stat[0];
      stat[0] = stat[2];
      stat[2] = stat[1];
      stat[1] = stat[3];
      stat[3] = tmp;
      posRow++;
      action();
    }
    void action() {
      if (map[posRow][posCol] == 0) map[posRow][posCol] = stat[1];
      else {
        stat[1] = map[posRow][posCol];
        map[posRow][posCol] = 0;
      }
    }
    int top() {
      return stat[0];
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numRow = Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    int startRow = Integer.parseInt(st.nextToken());
    int startCol = Integer.parseInt(st.nextToken());
    int numOper = Integer.parseInt(st.nextToken());
    map = new int[numRow][numCol];
    for (int i = 0; i < numRow; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < numCol; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    st = new StringTokenizer(br.readLine());
    int[] opers = new int[numOper];
    for (int i = 0; i < numOper; i++) {
      opers[i] = Integer.parseInt(st.nextToken());
    }
    StringBuilder sb = new StringBuilder();
    Simulation simul = new Simulation(startRow, startCol);
    for (int i = 0; i < numOper; i++) {
      switch (opers[i]) {
        case 1:
          if (simul.posCol == numCol - 1) continue;
          simul.moveEast();
          sb.append(simul.top()).append('\n');
          break;
        case 2:
          if (simul.posCol == 0) continue;
          simul.moveWest();
          sb.append(simul.top()).append('\n');
          break;
        case 3:
          if (simul.posRow == 0) continue;
          simul.moveNorth();
          sb.append(simul.top()).append('\n');
          break;
        default:
          if (simul.posRow == numRow - 1) continue;
          simul.moveSouth();
          sb.append(simul.top()).append('\n');
      }
    }
    bw.write(sb.toString());
    bw.flush();
  }
}