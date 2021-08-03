import java.io.*;
import java.util.*;

public class BOJ7562 {
  private static class Position {
    int x;
    int y;
    int count;
    Position (int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());
    String[] tmp;
    for (int i = 0; i < numTest; i++) {
      int len = Integer.parseInt(br.readLine());
      tmp = br.readLine().split(" ");
      int startX = Integer.parseInt(tmp[0]);
      int startY = Integer.parseInt(tmp[1]);
      tmp = br.readLine().split(" ");
      int targetX = Integer.parseInt(tmp[0]);
      int targetY = Integer.parseInt(tmp[1]);

      bw.write(knight(len, startX, startY, targetX, targetY) + "\n");
    }
    bw.flush();
  }
  static int knight(int len, int startX, int startY, int targetX, int targetY) {
    int[] xDi = new int[] {-2, -1, 1, 2, -2, -1, 1, 2};
    int[] yDi = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
    
    Queue<Position> que = new LinkedList<>();
    boolean[][] marked = new boolean[len][len];
    Position start = new Position(startX, startY, 0);
    marked[startX][startY] = true;
    que.add(start);
  
    while (!que.isEmpty()) {
      Position crnt = que.poll();
      if (crnt.x == targetX && crnt.y == targetY) {
        return crnt.count;
      }
      for (int i = 0; i < 8; i++) {
        int nextX = crnt.x + xDi[i];
        int nextY = crnt.y + yDi[i];
        if (0 <= nextX && nextX < len && 0 <= nextY && nextY < len) {
          if (!marked[nextX][nextY]) {
            que.add(new Position(nextX, nextY, crnt.count + 1));
            marked[nextX][nextY] = true;
          }
        }
      }
    }
    return -1;
  }
}
