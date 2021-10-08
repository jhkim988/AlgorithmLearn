import java.io.*;
import java.util.*;

public class BOJ14442 {
  private static class Pair {
    int row;
    int col;
    int numCrush;
    int move;
    Pair(int row, int col, int numCrush, int move) {
      this.row = row;
      this.col = col;
      this.numCrush = numCrush;
      this.move = move;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int numCrush = Integer.parseInt(st.nextToken());

    int[][] map = new int[row][col];
    for (int i = 0; i < row; i++) {
      String tmp = br.readLine();
      for (int j = 0; j < col; j++) {
        map[i][j] = tmp.charAt(j) - '0';
      }
    }

    // BFS:
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    Pair start = new Pair(0, 0, 0, 0);
    Queue<Pair> que = new LinkedList<>();
    boolean[][][] marked = new boolean[row][col][numCrush + 1];
    que.add(start);
    marked[0][0][0] = true;

    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.row == row - 1 && crnt.col == col - 1) {
        bw.write((crnt.move + 1) + " ");
        bw.flush();
        return;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.row + rowDi[i];
        int nextCol = crnt.col + colDi[i];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        int nextCrush = crnt.numCrush + (map[nextRow][nextCol] == 0 ? 0 : 1);
        if (nextCrush > numCrush) continue;
        if (marked[nextRow][nextCol][nextCrush]) continue;
        que.add(new Pair(nextRow, nextCol, nextCrush, crnt.move + 1));
        marked[nextRow][nextCol][nextCrush] = true;
      }
    }
    bw.write("-1\n");
    bw.flush();
  }
}
