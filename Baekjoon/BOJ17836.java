import java.io.*;
import java.util.*;

public class BOJ17836 {
  private static class Pair {
    int row, col, time;
    boolean hasGram;
    Pair(int row, int col, int time, boolean hasGram) {
      this.row = row;
      this.col = col;
      this.time = time;
      this.hasGram = hasGram;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(st.nextToken());
    int[][] board = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int time = t+1;
    boolean hasGram = board[0][0] == 2;
    int[] rowDi = {-1, 0, 1 ,0};
    int[] colDi = {0, -1, 0, 1};
    boolean[][][] visit = new boolean[2][row][col];
    Queue<Pair> que = new LinkedList<>();
    visit[hasGram ? 1 : 0][0][0] = true;
    que.add(new Pair(0, 0, 0, hasGram));
    bfs: while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt.row + rowDi[k];
        int adjCol = crnt.col + colDi[k];
        boolean adjHasGram = crnt.hasGram;
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
        if (board[adjRow][adjCol] == 2) adjHasGram = true;
        if (visit[adjHasGram ? 1 : 0][adjRow][adjCol]) continue;
        if (!adjHasGram && board[adjRow][adjCol] == 1) continue; 
        if (adjRow == row-1 && adjCol == col-1) {
          time = crnt.time+1;
          break bfs;
        }
        que.add(new Pair(adjRow, adjCol, crnt.time+1, adjHasGram));
        visit[adjHasGram ? 1 : 0][adjRow][adjCol] = true;
      }
    }

    bw.write(time <= t ? Integer.toString(time) : "Fail");
    bw.newLine();
    bw.flush();
  }
}
