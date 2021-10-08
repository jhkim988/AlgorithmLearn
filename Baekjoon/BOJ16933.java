import java.io.*;
import java.util.*;

public class BOJ16933 {
  private static class Pair {
    int row;
    int col;
    int numCrush;
    int move;
    boolean day;
    Pair(int row, int col, int numCrush, int move, boolean day) {
      this.row = row;
      this.col = col;
      this.numCrush = numCrush;
      this.move = move;
      this.day = day;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int numCrush = Integer.parseInt(st.nextToken());

    char[][] map = new char[row][];
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
    }

    // BFS:
    int[] rowDi = {0, -1, 0, 1, 0};
    int[] colDi = {-1, 0, 1, 0, 0};
    Pair start = new Pair(0, 0, 0, 1, true);
    Queue<Pair> que = new LinkedList<>();
    boolean[][][] marked = new boolean[row][col][numCrush + 1];
    que.add(start);
    marked[0][0][0] = true;

    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.row == row - 1 && crnt.col == col - 1) {
        bw.write(crnt.move + "\n");
        bw.flush();
        return;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.row + rowDi[i];
        int nextCol = crnt.col + colDi[i];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        if (map[nextRow][nextCol] == '1' && !crnt.day) {
          que.add(new Pair(crnt.row, crnt.col, crnt.numCrush, crnt.move + 1, !crnt.day));
          continue;
        }
        int nextCrush = (map[nextRow][nextCol] == '1' && crnt.day) ? crnt.numCrush + 1 : crnt.numCrush;
        if (nextCrush > numCrush) continue;
        if (marked[nextRow][nextCol][nextCrush]) continue;
        que.add(new Pair(nextRow, nextCol, nextCrush, crnt.move + 1, !crnt.day));
        marked[nextRow][nextCol][nextCrush] = true;
      }
    }
    bw.write("-1\n");
    bw.flush();
  }
}
