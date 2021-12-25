import java.io.*;
import java.util.*;

public class BOJ16985 {
  static final int len = 5;
  static final int moveMAX = len*len*len+1;
  static final int[] xDi = {-1, 0, 1, 0};
  static final int[] yDi = {0, -1, 0, 1};
  static final int[] zDi = {-1, 1};
  private static class Pair {
    int x, y, z, move;
    Pair(int x, int y, int z, int move) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.move = move;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[][][] maze = new int[len][len][len];
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int k = 0; k < len; k++) {
          maze[i][j][k] = Integer.parseInt(st.nextToken());
        }
      }
    }
    boolean[] check = new boolean[len];
    int[][] stat = new int[2][len];
    int answer = recur(0, check, stat, maze, len * len * len + 1);

    bw.write(answer >= moveMAX ? "-1\n" : answer + "\n");
    bw.flush();
  }
  static int recur(int depth, boolean[] check, int[][] stat, int[][][] maze, int min) {
    if (depth >= 5) {
      int result = bfs(maze, stat, min);
      return result;
    }
    int val = min;
    for (int i = 0; i < len; i++) {
      if (check[i]) continue;
      stat[0][depth] = i;
      check[i] = true;
      for (int rot = 0; rot < 4; rot++) {
        stat[1][depth] = rot;
        val = Math.min(val, recur(depth + 1, check, stat, maze, val));
      }
      check[i] = false;
    }
    return val;
  }
  static int bfs(int[][][] maze, int[][] stat, int min) {
    int startX = getXY(0, 0, stat[1][0], true);
    int startY = getXY(0, 0, stat[1][0], false);
    if (maze[stat[0][0]][startX][startY] == 0) return moveMAX;
    boolean[][][] visit = new boolean[len][len][len];
    Queue<Pair> que = new LinkedList<>();
    Pair start = new Pair(startX, startY, 0, 0);
    que.add(start);
    visit[0][startX][startY] = true;
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.z == len - 1 && getXY(len - 1, len - 1, stat[1][crnt.z], true) == crnt.x && getXY(len - 1, len - 1, stat[1][crnt.z], false) == crnt.y) return crnt.move;
      if (crnt.move > min) return moveMAX;
      for (int i = 0; i < 4; i++) {
        int adjX = crnt.x + xDi[i];
        int adjY = crnt.y + yDi[i];
        if (adjX < 0 || adjX >= len || adjY < 0 || adjY >= len) continue;
        if (maze[stat[0][crnt.z]][adjX][adjY] == 0) continue;
        if (visit[crnt.z][adjX][adjY]) continue;
        que.add(new Pair(adjX, adjY, crnt.z, crnt.move + 1));
        visit[crnt.z][adjX][adjY] = true;
      }
      for (int i = 0; i < 2; i++) {
        int adjZ = crnt.z + zDi[i];
        if (adjZ < 0 || adjZ >= len) continue;
        int val = stat[1][adjZ] - stat[1][crnt.z];
        if (val < 0) val += 4;
        int nextX = getXY(crnt.x, crnt.y, val, true);
        int nextY = getXY(crnt.x, crnt.y, val, false);
        if (maze[stat[0][adjZ]][nextX][nextY] == 0) continue;
        if (visit[adjZ][nextX][nextY]) continue;
        que.add(new Pair(nextX, nextY, adjZ, crnt.move + 1));
        visit[adjZ][nextX][nextY] = true;
      }
    }
    return moveMAX;
  }
  static int getXY(int row, int col, int rotCode, boolean isX) {
    switch (rotCode) {
      case 3:
        return isX ? len - 1 - col : row;
      case 2:
        return isX ? len - 1 - row : len - 1 - col;
      case 1:
        return isX ? col : len - 1 - row;
      default:
        return isX ? row : col;
    }
  }
}
