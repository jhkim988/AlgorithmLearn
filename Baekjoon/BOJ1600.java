import java.io.*;
import java.util.*;

public class BOJ1600 {
  private static class Pair {
    int row, col, move, K;
    Pair(int row, int col, int move, int K) {
      this.row = row;
      this.col = col;
      this.move = move;
      this.K = K;
    }
  }
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int K = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numCol = Integer.parseInt(st.nextToken());
    int numRow = Integer.parseInt(st.nextToken());
    int[][] map = new int[numRow][numCol];
    for (int i = 0; i < numRow; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < numCol; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    bw.write(bfs(numRow, numCol, K, map) + "\n");
    bw.flush();
  }
  static int bfs(int numRow, int numCol, int K, int[][] map) {
    int[] knightRow = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] knightCol = {1, 2, 2, 1, -1, -2, -2, -1};
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};

    Pair start = new Pair(0, 0, 0, 0);
    int targetRow = numRow - 1;
    int targetCol = numCol - 1;
    Queue<Pair> que = new LinkedList<>();
    boolean[][][] visit = new boolean[numRow][numCol][K + 1];
    que.add(start);
    visit[0][0][0] = true;

    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.row == targetRow && crnt.col == targetCol) {
        return crnt.move;
      }
      if (crnt.K < K) {
        for (int i = 0; i < 8; i++) {
          int nextRow = crnt.row + knightRow[i];
          int nextCol = crnt.col + knightCol[i];
          if (nextRow < 0 || nextRow >= numRow || nextCol < 0 || nextCol >= numCol) continue;
          if (visit[nextRow][nextCol][crnt.K + 1]) continue;
          if (map[nextRow][nextCol] == 1) continue;
          visit[nextRow][nextCol][crnt.K + 1] = true;
          que.add(new Pair(nextRow, nextCol, crnt.move + 1, crnt.K + 1));
        }
      }
      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.row + rowDi[i];
        int nextCol = crnt.col + colDi[i];
        if (nextRow < 0 || nextRow >= numRow || nextCol < 0 || nextCol >= numCol) continue;
        if (visit[nextRow][nextCol][crnt.K]) continue;
        if (map[nextRow][nextCol] == 1) continue;
        visit[nextRow][nextCol][crnt.K] = true;
        que.add(new Pair(nextRow, nextCol, crnt.move + 1, crnt.K));
      }
    }
    return -1;
  }
}
