import java.io.*;
import java.util.*;

public class BOJ7576 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int width;
  static int height;

  static int[] xDi = {-1, 0, 1, 0};
  static int[] yDi = {0, -1, 0, 1};

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
    StringTokenizer st = new StringTokenizer(br.readLine());
    width = Integer.parseInt(st.nextToken());
    height = Integer.parseInt(st.nextToken());
    int[][] box = new int[height][width];

    for (int i = 0; i < height; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < width; j++) {
        box[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    bw.write(tomato(box) + "\n");
    bw.flush();
  }
  static int tomato(int[][] box) {
    Queue<Point> que = new LinkedList<>();
    int numOne = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (box[i][j] == 1) {
          que.add(new Point(i, j, 0));
          numOne++;
        }
        if (box[i][j] == -1) {
          numOne++;
        }
      }
    }

    Point crnt = new Point(0, 0, -1);
    while (!que.isEmpty()) {
      crnt = que.poll();
      for (int i = 0; i < 4; i++) {
        int nextX = crnt.x + xDi[i];
        int nextY = crnt.y + yDi[i];
        if (0 <= nextX && nextX < height && 0 <= nextY && nextY < width) {
          if (box[nextX][nextY] == 0) {
            box[nextX][nextY] = 1;
            numOne++;
            que.add(new Point(nextX, nextY, crnt.count + 1));
          }
        }
      }
    }
    return numOne == width * height ? crnt.count : -1;
  }
}
