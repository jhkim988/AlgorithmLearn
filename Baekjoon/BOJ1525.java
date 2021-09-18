import java.io.*;
import java.util.*;

public class BOJ1525 {
  private static class Simulation {
    int stat;
    int emptyRow;
    int emptyCol;
    int count;
    Simulation(int stat, int emptyRow, int emptyCol, int count) {
      this.stat = stat;
      this.emptyRow = emptyRow;
      this.emptyCol = emptyCol;
      this.count = count; 
    }
    boolean isComplete() {
      int copy = stat;
      if (copy % 10 != 0) return false;
      copy /= 10;
      int comp = 8;
      while (copy != 0) {
        if (copy % 10 != comp) {
          return false;
        } 
        copy /= 10;
        comp--;
      }
      return true;
    }
    Simulation move(int nextRow, int nextCol) {
      int nextEmpty = nextRow * 3 + nextCol;
      int nextEmptyDigit = 1;
      
      for (int i = 0; i < 8 - nextEmpty; i++) {
        nextEmptyDigit *= 10;
      }

      int crntEmpty = 3 * emptyRow + emptyCol;
      int emptyDigit = 1;
      for (int i = 0; i < 8 - crntEmpty; i++) {
        emptyDigit *= 10;
      }

      int swapNum = (stat / nextEmptyDigit) % 10;
      int nextStat = stat + swapNum * (emptyDigit - nextEmptyDigit);
      return new Simulation(nextStat, nextRow, nextCol, count + 1);
    }

    static int dblArrToInt(int[][] board) {
      int stat = 0;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          stat = stat * 10 + board[i][j];
        }
      }
      return stat;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      Stack<Integer> stk = new Stack<>();
      int copy = stat;
      while (copy != 0) {
        stk.push(copy % 10);
        copy /= 10;
      }

      int num = 1;
      while (!stk.isEmpty()) {
        sb.append(stk.pop());
        if (num % 3 == 0) {
          sb.append('\n');
        }
        num++;
      }

      return sb.toString();
    }
  }
  private static class Pair {
    int row;
    int col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] board = new int[3][3];
    Pair empty = null;
    for (int i = 0; i < 3; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        int input = Integer.parseInt(st.nextToken());
        board[i][j] = input;
        if (input == 0) {
          empty = new Pair(i, j);
        }
      }
    }

    bw.write(BFS(board, empty) + "\n");
    bw.flush();
  }
  static int BFS(int[][] board, Pair empty) {
    int[] rowDi = {0, 1, 0, -1};
    int[] colDi = {1, 0, -1, 0};
    Queue<Simulation> que = new LinkedList<>();
    HashSet<Integer> hs = new HashSet<>();
    Simulation start = new Simulation(Simulation.dblArrToInt(board), empty.row, empty.col, 0);
    que.add(start);
    hs.add(start.stat);
    while (!que.isEmpty()) {
      Simulation crnt = que.poll();
      if (crnt.isComplete()) {
        return crnt.count;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.emptyRow + rowDi[i];
        int nextCol = crnt.emptyCol + colDi[i];
        if (nextRow < 0 || nextRow >= 3 || nextCol < 0 || nextCol >= 3) continue;
        Simulation next = crnt.move(nextRow, nextCol);
        if (hs.contains(next.stat)) continue;
        hs.add(next.stat);
        que.add(next);
      }
    }
    return -1;
  }
}
