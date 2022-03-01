import java.io.*;
import java.util.*;

public class BOJ2636 {
  static int row, col;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  static int[][] cheese;
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
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    cheese = new int[row][col];
    int prev = 0;
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        cheese[i][j] = Integer.parseInt(st.nextToken());
        if (cheese[i][j] == 0) prev++;
      }
    }
    int time = 0;
    int numDelete = bfs();
    while (numDelete != 0) {
      prev = numDelete;
      numDelete = bfs();
      time++;
    }
    bw.write(Integer.toString(time));
    bw.newLine();
    bw.write(Integer.toString(prev));
    bw.newLine();
    bw.flush();
  }
  static int bfs() {
    int numDelete = 0;
    Queue<Pair> que = new LinkedList<>();
    boolean[][] visit = new boolean[row][col];
    Queue<Pair> boundary = new LinkedList<>();
    visit[0][0] = true;
    que.add(new Pair(0, 0));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt.row + rowDi[k];
        int adjCol = crnt.col + colDi[k];
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
        if (visit[adjRow][adjCol]) continue;
        visit[adjRow][adjCol] = true;
        Pair next = new Pair(adjRow, adjCol);
        if (cheese[adjRow][adjCol] == 1) {
          boundary.add(next);
          numDelete++;
        } else {
          que.add(next);
        }
      }
    }
    while (!boundary.isEmpty()) {
      Pair crnt = boundary.poll();
      cheese[crnt.row][crnt.col]--;
    } 
    return numDelete;
  }
}