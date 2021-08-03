import java.io.*;
import java.util.*;

public class BOJ2178 {
  static int row = 0;
  static int col = 0;

  static int[] xDi = {-1, 0, 1, 0};
  static int[] yDi = {0, -1, 0, 1};

  private static class Tuple {
    int x;
    int y;
    Tuple(int x, int y) {
      this.x = x;
      this.y = y;
    }
    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Tuple other = (Tuple) obj;
      if (this.x == other.x && this.y == other.y) {
        return true;
      }
      return false;
    }
  
    @Override
    public int hashCode() {
      int result = (int) x ^ (x >>> 32);
      result = 31 * result + (int) y ^ (y >>> 32);
      return result;
    }
  }
  private static class Point {
    int x;
    int y;
    int count;
    Point(int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] tmp = br.readLine().split(" ");
    row = Integer.parseInt(tmp[0]);
    col = Integer.parseInt(tmp[1]);
    int[][] maze = new int[row][col];
    // boolean[][] marked = new boolean[row][col];

    String inputTmp;
    for (int i = 0; i < row; i++) {
      inputTmp = br.readLine();
      for (int j = 0; j < col; j++) {
        maze[i][j] = inputTmp.charAt(j) - '0';
      }
    }
    bw.write(bfs(maze) + "\n");
    bw.flush();
  }
  
  static int mybfs(int[][] maze, int xIdx, int yIdx, boolean[][] marked) {
    int count = 1;
    Queue<Tuple> que = new LinkedList<Tuple>(); // [xIdx, yIdx]
    HashMap<Tuple, Tuple> edgeTo = new HashMap<>();
    que.add(new Tuple(xIdx, yIdx));
    marked[xIdx][yIdx] = true;

    while (!que.isEmpty()) {
      Tuple node = que.poll();
      if (node.x == row - 1 && node.y == col - 1) {
        break;
      }
      for (int i = 0; i < 4; i++) {
        Tuple adj = new Tuple(node.x + xDi[i], node.y + yDi[i]);
        if (adj.x < row && adj.x >= 0 && adj.y < col && adj.y >= 0) {
          if (maze[adj.x][adj.y] == 1 && !marked[adj.x][adj.y]) {
            marked[adj.x][adj.y] = true;
            que.add(adj);
            edgeTo.put(adj, node); 
          }
        }
      }
    }

    Tuple start = new Tuple(0, 0);
    Tuple goal = new Tuple(row - 1, col - 1);
    Tuple tmp = edgeTo.get(goal);
    while (!tmp.equals(start)) {
      tmp = edgeTo.get(tmp);
      count++;
    }
    return count + 1; // Error
  }
  static int bfs(int[][] maze) {
    Queue<Point> que = new LinkedList<>();
    Point start = new Point(0, 0, 1);
    que.add(start);

    while (!que.isEmpty()) {
      Point crnt = que.poll();

      if (crnt.x == row - 1 && crnt.y == col - 1) {
        return crnt.count;
      }

      for (int i = 0; i < 4; i++) {
        int nextX = crnt.x + xDi[i];
        int nextY = crnt.y + yDi[i];
        if (0 <= nextX && nextX < row && 0 <= nextY && nextY < col) {
          if (maze[nextX][nextY] == 1) {
            maze[nextX][nextY] = 0;
            que.add(new Point(nextX, nextY, crnt.count + 1));
          }
        }
      }
    }
    return -1;
  }
}
