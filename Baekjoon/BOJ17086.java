import java.io.*;
import java.util.*;

public class BOJ17086 {
  private static class Pair {
    int row, col, move;
    Pair(int row, int col, int move) {
      this.row = row;
      this.col = col;
      this.move = move;
    }
  }
  public static void main(String[] args) throws IOException {
    int[] rowDi = {-1, 0, 1, -1, 1, -1, 0, 1};
    int[] colDi = {-1, -1, -1, 0, 0, 1, 1, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numRow = Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    int[][] data = new int[numRow][numCol];
    Queue<Pair> que = new LinkedList<>();
    for (int i = 0; i < numRow; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < numCol; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
        if (data[i][j] == 0) continue;
        que.add(new Pair(i, j, 1));
      }
    }
    int max = 0;
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      // System.out.println("crnt: " + crnt.row + ", " + crnt.col + ", " + crnt.move);
      for (int i = 0; i < 8; i++) {
        int adjRow = crnt.row + rowDi[i];
        int adjCol = crnt.col + colDi[i];
        if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
        if (data[adjRow][adjCol] != 0) continue;
        que.add(new Pair(adjRow, adjCol, crnt.move + 1));
        data[adjRow][adjCol] = crnt.move + 1;
        max = Math.max(max, crnt.move + 1);
      }
    }

    bw.write((max - 1) + "\n");
    bw.flush();
  }
}
