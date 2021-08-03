import java.io.*;
import java.util.*;

public class BOJ7569 {
  static int width;
  static int length;
  static int height;

  private static class Point {
    int x;
    int y;
    int z;
    int count;
    Point(int x, int y, int z, int count) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.count = count;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    width = Integer.parseInt(st.nextToken());
    length = Integer.parseInt(st.nextToken());
    height = Integer.parseInt(st.nextToken());

    int[][][] box = new int[height][length][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < length; j++) {
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < width; k++) {
          box[i][j][k] = Integer.parseInt(st.nextToken());
        }
      }
    }

    bw.write(tomato(box) + "\n");
    bw.flush();
  }

  static int tomato(int[][][] box) {
    int[] xDi = {1, 0, 0, -1, 0, 0};
    int[] yDi = {0, 1, 0, 0, -1, 0};
    int[] zDi = {0, 0, 1, 0, 0, -1};

    Queue<Point> que = new LinkedList<>();
    int numNotZero = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < length; j++) {
        for (int k = 0; k < width; k++) {
          if (box[i][j][k] == 1) {
            que.add(new Point(k, j, i, 0));
            numNotZero++;
          }
          if (box[i][j][k] == -1) {
            numNotZero++;
          }
        }
      }
    }

    Point crnt = new Point(-1, -1, -1, -1);
    while (!que.isEmpty()) {
      crnt = que.poll();
      for (int i = 0; i < 6; i++) {
        int nextX = crnt.x + xDi[i];
        int nextY = crnt.y + yDi[i];
        int nextZ = crnt.z + zDi[i];
        if (0 <= nextX && nextX < width && 0 <= nextY && nextY < length && 0 <= nextZ && nextZ < height) {
          if (box[nextZ][nextY][nextX] == 0) {
            box[nextZ][nextY][nextX] = 1;
            que.add(new Point(nextX, nextY, nextZ, crnt.count + 1));
            numNotZero++;
          }
        }
      }
    }
    return numNotZero == width * length * height ? crnt.count : -1;
  }
}
