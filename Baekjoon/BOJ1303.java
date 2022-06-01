import java.io.*;
import java.util.*;

public class BOJ1303 {
  static int row, col;
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  static char[][] board;
  static boolean[][] visit;
  static Queue<Pair> que;
  static int numWhite = 0, numBlue = 0;
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
    StringTokenizer st = new StringTokenizer(br.readLine());
    col = Integer.parseInt(st.nextToken());
    row = Integer.parseInt(st.nextToken());
    board = new char[row][col];
    for (int i = 0; i < row; i++) board[i] = br.readLine().toCharArray();
    visit = new boolean[row][col];
    que = new LinkedList<>();

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (visit[i][j]) continue;
        bfs(i, j);
      }
    }

    bw.write(Integer.toString(numWhite));
    bw.write(' ');
    bw.write(Integer.toString(numBlue));
    bw.flush();
  }
  static void bfs(int r, int c) {
    int num = 1;
    visit[r][c] = true;
    que.add(new Pair(r, c));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt.row + rowDi[k];
        int adjCol = crnt.col + colDi[k];
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
        if (board[r][c] != board[adjRow][adjCol] || visit[adjRow][adjCol]) continue;
        que.add(new Pair(adjRow, adjCol));
        visit[adjRow][adjCol] = true;
        num++;
      }
    }
    if (board[r][c] == 'W') numWhite += num*num;
    else numBlue += num*num;
  }
}