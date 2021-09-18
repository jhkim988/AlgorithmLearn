import java.io.*;
import java.util.*;

public class BOJ1525 {
  private static class Simulation {
    int[][] board;
    Pair empty;
    int count;
    Simulation(int[][] board, Pair empty, int count) {
      this.board = new int[3][3];
      for (int i = 0; i < 3; i++) {
        this.board[i] = board[i].clone();
      }
      this.empty = empty;
      this.count = count; 
    }
    boolean isComplete() {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (i == 2 && j == 2 && board[2][2] != 0) {
            return false;
          }
          if (!(i == 2 && j == 2) && board[i][j] != i * 3 + j + 1) {
            return false;
          }
        }
      } 
      return true;
    }
    Simulation move(int nextRow, int nextCol) {
      int[][] newBoard = new int[3][3];
      for (int i = 0; i < 3; i++) {
        newBoard[i] = board[i].clone();
      }
      // swap:
      int tmp = newBoard[empty.row][empty.col];
      newBoard[empty.row][empty.col] = newBoard[nextRow][nextCol];
      newBoard[nextRow][nextCol] = tmp;
      return new Simulation(newBoard, new Pair(nextRow, nextCol), count + 1);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (this.getClass() != o.getClass()) return false;
      Simulation other = (Simulation) o;
      
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (this.board[i][j] != other.board[i][j]) return false;
        }
      }
      return true;
    }
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;

      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          result = prime * result + (int) (board[i][j] ^ (board[i][j] >>> 32));
        }
      }

      return result;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 3; i++) {
        sb.append(Arrays.toString(board[i])).append('\n');
      }
      sb.append('\n');  
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
    HashSet<Simulation> hs = new HashSet<>();
    Simulation start = new Simulation(board, empty, 0);
    que.add(start);
    hs.add(start);
    while (!que.isEmpty()) {
      Simulation crnt = que.poll();
      if (crnt.isComplete()) {
        return crnt.count;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.empty.row + rowDi[i];
        int nextCol = crnt.empty.col + colDi[i];
        if (nextRow < 0 || nextRow >= 3 || nextCol < 0 || nextCol >= 3) continue;
        Simulation next = crnt.move(nextRow, nextCol);
        if (hs.contains(next)) continue;
        hs.add(next);
        que.add(next);
      }
    }
    return -1;
  }
}
