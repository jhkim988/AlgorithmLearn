import java.io.*;
import java.util.*;

public class BOJ2206 {
  static int row;
  static int col;

  private static class Point {
    int x;
    int y;
    int count;
    boolean crashed;
    Point(int x, int y, int count, boolean crashed) {
      this.x = x;
      this.y = y;
      this.count = count;
      this.crashed = crashed;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] tmp = br.readLine().split(" ");
    row = Integer.parseInt(tmp[0]);
    col = Integer.parseInt(tmp[1]);
    
    int[][] maze = new int[row][col];

    String line;
    for (int i = 0 ; i < row; i++) {
      line = br.readLine();
      for (int j = 0; j < col; j++) {
        maze[i][j] = line.charAt(j) - '0';
      }
    }

    bw.write(solve(maze) + "\n");
    bw.flush();
  }
  static int solve(int[][] maze) {
    int[] xDi = new int[] {1, 0, -1, 0};
    int[] yDi = new int[] {0, 1, 0, -1};

    // marked: -1, after crash: -2, crash position: -3
    Queue<Point> que = new LinkedList<>();
    Point start = new Point(0, 0, 1, false);
    que.add(start);
    maze[0][0] = -1;

    while (!que.isEmpty()) {
      Point crnt = que.poll();
      if (crnt.x == row - 1 && crnt.y == col - 1) {
        return crnt.count;
      }
      for (int i = 0; i < 4; i++) {
        int nextX = crnt.x + xDi[i];
        int nextY = crnt.y + yDi[i];
        if (0 <= nextX && nextX < row && 0 <= nextY && nextY < col) {
          if (maze[nextX][nextY] == 0 || (!crnt.crashed && maze[nextX][nextY] == -2)) {
              que.add(new Point(nextX, nextY, crnt.count + 1, crnt.crashed));
              if (crnt.crashed) {
                maze[nextX][nextY] = -2;
              } else {
                maze[nextX][nextY] = -1;
              }
          }
          if (!crnt.crashed && maze[nextX][nextY] == 1) {
            que.add(new Point(nextX, nextY, crnt.count + 1, true));
            maze[nextX][nextY] = -3;
          }
        }
      }
    }
    return -1;
  }
}
