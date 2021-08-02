import java.io.*;
import java.util.*;

public class BOJ2178 {
  static int row = 0;
  static int col = 0;

  static int[] xDi = {-1, 0, 1, 0};
  static int[] yDi = {0, -1, 0, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] tmp = br.readLine().split(" ");
    row = Integer.parseInt(tmp[0]);
    col = Integer.parseInt(tmp[1]);
    int[][] maze = new int[row][col];
    boolean[][] marked = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      tmp = br.readLine().split("");
      for (int j = 0; j < col; j++) {
        maze[i][j] = Integer.parseInt(tmp[j]);
      }
    }
    bw.write(bfs(maze, 0, 0, marked) + "\n");
    bw.flush();
  }
  static int bfs(int[][] maze, int xIdx, int yIdx, boolean[][] marked) {
    int count = 1;
    Queue<int[]> que = new LinkedList<int[]>(); // [xIdx, yIdx]
    
    que.add(new int[] {xIdx, yIdx});
    marked[xIdx][yIdx] = true;

    while (!que.isEmpty()) {
      int[] node = que.poll();
      if (node[0] == row - 1 && node[1] == col - 1) {
        return count;
      }
      for (int i = 0; i < 4; i++) {
        if (node[0] + xDi[i] < row && node[0] + xDi[i] >= 0 && node[1] + yDi[i] < col && node[1] + yDi[i] >= 0) {
          if (maze[node[0] + xDi[i]][node[1] + yDi[i]] == 1 && !marked[node[0] + xDi[i]][node[1] + yDi[i]]) {
            marked[node[0] + xDi[i]][node[1] + yDi[i]] = true;
            que.add(new int[] {node[0] + xDi[i], node[1] + yDi[i]});
            count++;
          }
        }
      }
    }

    return -1; // Error
  }
}
