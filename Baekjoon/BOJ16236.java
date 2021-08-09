import java.io.*;
import java.util.*;

public class BOJ16236 {
  static int N;
  static int minDist;
  static int minX;
  static int minY;
  static int[][] check;
  static int[][] data;
  static int[] xDi = {-1, 0, 1, 0};
  static int[] yDi = {0, -1, 0, 1};
  static int sharkX;
  static int sharkY;
  static int result;
  static int sharkSize;
  static int eatCount;
  private static class Shark {
    int x;
    int y;
    Shark(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    N = Integer.parseInt(br.readLine());
    data = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
        if (data[i][j] == 9) {
          sharkX = i;
          sharkY = j;
          data[i][j] = 0; 
        }
      }
    }
    check = new int[N][N];
    sharkSize = 2;
    while (true) {
      init();
      bfs(sharkX, sharkY);

      if (minX != 21 && minY != 21) {
        result += check[minX][minY];
        eatCount++;

        if (eatCount == sharkSize) {
          eatCount = 0;
          sharkSize++;
        }
        data[minX][minY] = 0;
        sharkX = minX;
        sharkY = minY;
      } else {
        break;
      }
    }
    bw.write(result + "\n");
    bw.flush();
  }
  static void init() {
    minDist = 401;
    minX = 21;
    minY = 21;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        check[i][j] = -1;
      }
    }
  }
  static void bfs(int x, int y) {
    Queue<Shark> que = new LinkedList<>();
    check[x][y] = 0;
    que.add(new Shark(x, y));

    while (!que.isEmpty()) {
      Shark crnt = que.poll();
      x = crnt.x;
      y = crnt.y;
      for (int i = 0; i < 4; i++) {
        int nextX = x + xDi[i];
        int nextY = y + yDi[i];
        if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
          continue;
        }
        if (check[nextX][nextY] != -1 || data[nextX][nextY] > sharkSize) {
          continue;
        }
        check[nextX][nextY] = check[x][y] + 1;
        if (data[nextX][nextY] != 0 && data[nextX][nextY] < sharkSize) {
          if (minDist > check[nextX][nextY]) {
            minX = nextX;
            minY = nextY;
            minDist = check[nextX][nextY];
          } else if (minDist == check[nextX][nextY]) {
            if (minX == nextX) {
              if (minY > nextY) {
                minX = nextX;
                minY = nextY;
              }
            } else if (minX > nextX) {
              minX = nextX;
              minY = nextY;
            }
          }
        }
        que.add(new Shark(nextX, nextY));
      }
    }
  }
}
