import java.io.*;
import java.util.*;

public class BOJ15685 {
  private static int[] xDi = {1, 0, -1, 0};
  private static int[] yDi = {0, -1, 0, 1};
  static boolean[][] map;
  private static class Pair {
    int x, y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    map = new boolean[101][101];
    while (n-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      add(x, y, d, g);
    }
    int num = check();
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }
  static Pair add(int x, int y, int d, int g) {
    if (g == 0) {
      if (0 <= x && x <= 100 && 0 <= y && y <= 100) {
        map[x][y] = true;
      }
      int endx = x + xDi[d];
      int endy = y + yDi[d];
      if (0 <= endx && endx <= 100 && 0 <= endy && endy <= 100) {
        map[endx][endy] = true;
      }
      return new Pair(endx, endy);
    }
    Pair half = add(x, y, d, g-1);
    int nextD = d == 0 ? 3 : d-1;
    int diffx = half.x - x;
    int diffy = half.y - y;
    int endx = half.x + diffy;
    int endy = half.y - diffx;
    add(endx, endy, nextD, g-1);
    return new Pair(endx, endy);
  }
  static int check() {
    int num = 0;
    for (int x = 0; x < 100; x++) {
      for (int y = 0; y < 100; y++) {
        if (map[x][y] && map[x+1][y] && map[x][y+1] && map[x+1][y+1]) num++;
      }
    }
    return num;
  }
}
