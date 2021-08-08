import java.io.*;
import java.util.*;

public class BOJ10026 {
  static int[] xDi = {-1, 0, 1, 0};
  static int[] yDi = {0, -1, 0, 1};
  private static class Pair {
    int x;
    int y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    char[][] display = new char[N][N];
    for (int i = 0; i < N; i++) {
      String tmp = br.readLine();
      for (int j = 0; j < N; j++) {
        display[i][j] = tmp.charAt(j);
      }
    }

    int numArea = 0;
    boolean[][] marked = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (marked[i][j]) {
          continue;
        }
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(i, j));
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int nextX = crnt.x + xDi[k];
            int nextY = crnt.y + yDi[k];
            if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
              if (!marked[nextX][nextY] && display[nextX][nextY] == display[crnt.x][crnt.y]) {
                que.add(new Pair(nextX, nextY));
                marked[nextX][nextY] = true;
              }
            }
          }
        }
        numArea++;
      }
    }

    int numAreaRG = 0;
    marked = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (marked[i][j]) {
          continue;
        }
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(i, j));
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int nextX = crnt.x + xDi[k];
            int nextY = crnt.y + yDi[k];
            if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
              if (!marked[nextX][nextY] && redgreen(display[nextX][nextY], display[crnt.x][crnt.y])) {
                que.add(new Pair(nextX, nextY));
                marked[nextX][nextY] = true;
              }
            }
          }
        }
        numAreaRG++;
      }
    }
    bw.write(numArea + " " + numAreaRG + "\n");
    bw.flush();
  }
  static boolean redgreen(char x, char y) {
    if (x == 'B' && y == 'B') {
      return true;
    }
    if (x == 'B' && y != 'B') {
      return false;
    }
    if (x != 'B' && y == 'B') {
      return false;
    }
    return true;
  }
}
