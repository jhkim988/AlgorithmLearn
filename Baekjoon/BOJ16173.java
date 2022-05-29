import java.io.*;
import java.util.*;

public class BOJ16173 {
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[][] board = new int[n][n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[] rowDi = {1, 0}, colDi = {0, 1};
    Queue<Pair> que = new LinkedList<>();
    boolean[][] visit = new boolean[n][n];
    Pair start = new Pair(0, 0);
    visit[0][0] = true;
    que.add(start);
  
    while(!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int k = 0; k < 2; k++) {
        int nextRow = crnt.row + board[crnt.row][crnt.col]*rowDi[k];
        int nextCol = crnt.col + board[crnt.row][crnt.col]*colDi[k];
        if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
        if (visit[nextRow][nextCol]) continue;
        if (nextRow == n-1 && nextCol == n-1) {
          bw.write("HaruHaru\n");
          bw.flush();
          return;
        }
        visit[nextRow][nextCol] = true;
        que.add(new Pair(nextRow, nextCol));
      }
    }

    bw.write("Hing\n");
    bw.flush();
  }
}
