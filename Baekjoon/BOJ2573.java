import java.io.*;
import java.util.*;

public class BOJ2573 {
  static int row, col;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  static int[][] map;
  private static class Pair {
    int r, c;
    Pair(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    map = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int numIce = 0;
    int year = 0;
    while ((numIce = numIce()) == 1) {
      melt();
      year++;
    }
    if (numIce == 0) {
      bw.write(Integer.toString(numIce));
    } else {
      bw.write(Integer.toString(year));
    }
    bw.newLine();
    bw.flush();
  }
  static void melt() {
    int[][] lazy = new int[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (map[i][j] != 0) continue;
        for (int k = 0; k < 4; k++) {
          int adjRow = i + rowDi[k];
          int adjCol = j + colDi[k];
          if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
          if (map[adjRow][adjCol] == 0) continue;
          lazy[adjRow][adjCol]--;
        }
      }
    }
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        map[i][j] += lazy[i][j];
        map[i][j] = Integer.max(map[i][j], 0);
      }
    }
  }
  static int numIce() {
    int num = 0;
    boolean[][] visit = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (map[i][j] == 0) continue;
        if (visit[i][j]) continue;
        num++;
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(i, j));
        visit[i][j] = true;
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int adjRow = crnt.r + rowDi[k];
            int adjCol = crnt.c + colDi[k];
            if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
            if (visit[adjRow][adjCol]) continue;
            if (map[adjRow][adjCol] == 0) continue;
            visit[adjRow][adjCol] = true;
            que.add(new Pair(adjRow, adjCol));
          }
        }
      }
    }
    return num;
  } 
}
