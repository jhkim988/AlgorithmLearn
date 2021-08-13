import java.io.*;
import java.util.*;

public class BOJ2638 {
  private static class Pair {
    int x;
    int y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  static int N;
  static int M;
  static int[] xDi = {-1, 0, 1, 0};
  static int[] yDi = {0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int[][] data = new int[N][M];
    int numCheese = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
        if (data[i][j] == 1) {
          numCheese++;
        }
      }
    }

    int time = 0;
    Queue<Pair> melt = new LinkedList<>();
    while (numCheese > 0) {
      bfs(data, melt);
      time++;
      while (!melt.isEmpty()) {
        Pair point = melt.poll();
        data[point.x][point.y] = 0;
        numCheese--;
      }
    }
    bw.write(time + "\n");
    bw.flush();
  }
  static void bfs(int[][] data, Queue<Pair> melt) {
    Pair start = new Pair(0, 0);
    boolean[][] marked = new boolean[N][M];
    int[][] count = new int[N][M];
    marked[0][0] = true;
    Queue<Pair> que = new LinkedList<>();
    que.add(start);
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int i = 0; i < 4; i++) { // x row, y col
        int nextX = crnt.x + xDi[i];
        int nextY = crnt.y + yDi[i];
        if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
        if (marked[nextX][nextY]) continue;
        if (data[nextX][nextY] == 1) {
          count[nextX][nextY]++;
          if (count[nextX][nextY] == 2) {
            melt.add(new Pair(nextX, nextY));
          }
          continue;
        }
        que.add(new Pair(nextX, nextY));
        marked[nextX][nextY] = true;
      }
    }
  }
}
