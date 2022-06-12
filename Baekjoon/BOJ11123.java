import java.io.*;
import java.util.*;

public class BOJ11123 {
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
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
    int t = Integer.parseInt(br.readLine());
    while (t--> 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      char[][] map = new char[row][col];
      for (int i = 0; i < row; i++) map[i] = br.readLine().toCharArray();
      int answer = bfs(map, row, col);
      bw.write(Integer.toString(answer));
      bw.newLine();
    }
    bw.flush();
  }
  static int bfs(char[][] map, int row, int col) {
    int num = 0;
    Queue<Pair> que = new LinkedList<>();
    boolean[][] visit = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (visit[i][j] || map[i][j] == '.') continue;
        que.add(new Pair(i, j));
        visit[i][j] = true;
        num++;
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int adjRow = crnt.row + rowDi[k];
            int adjCol = crnt.col + colDi[k];
            if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
            if (visit[adjRow][adjCol] || map[adjRow][adjCol] == '.') continue;
            que.add(new Pair(adjRow, adjCol));
            visit[adjRow][adjCol] = true;
          }
        }
      }
    }
    return num;
  }
}
