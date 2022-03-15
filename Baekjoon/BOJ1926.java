import java.io.*;
import java.util.*;

public class BOJ1926 {
  private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
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
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int[][] paint = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        paint[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int numPaint = 0;
    int maxSize = 0;
    Queue<Pair> que = new LinkedList<>();
    boolean[][] visit = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (paint[i][j] == 0) continue;
        if (visit[i][j]) continue;
        numPaint++;
        int size = 1;
        que.add(new Pair(i, j));
        visit[i][j] = true;
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int adjRow = crnt.row + rowDi[k];
            int adjCol = crnt.col + colDi[k];
            if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
            if (visit[adjRow][adjCol]) continue;
            if (paint[adjRow][adjCol] == 0) continue;
            size++;
            visit[adjRow][adjCol] = true;
            que.add(new Pair(adjRow, adjCol));
          }
        }
        if (maxSize < size) maxSize = size;;
      }
    }
    bw.write(Integer.toString(numPaint));
    bw.newLine();
    bw.write(Integer.toString(maxSize));
    bw.newLine();
    bw.flush();
  }
}
