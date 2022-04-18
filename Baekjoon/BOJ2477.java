import java.io.*;
import java.util.*;

public class BOJ2477 {
  private static class Point implements Comparable<Point> {
    int x, y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    @Override
    public int compareTo(Point other) {
      if (this.y != other.y) return this.y-other.y;
      return this.x-other.x;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numMelon = Integer.parseInt(br.readLine());
    Point[] parr = new Point[6];
    parr[0] = new Point(0, 0);
    int[] xDi = {1, -1, 0, 0};
    int[] yDi = {0, 0, -1, 1};
    for (int i = 1; i < 6; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int direction = Integer.parseInt(st.nextToken())-1;
      int dist = Integer.parseInt(st.nextToken());
      parr[i] = new Point(parr[i-1].x + xDi[direction]*dist, parr[i-1].y + yDi[direction]*dist);
    }
    Arrays.sort(parr);
    int area = (parr[1].x-parr[0].x)*(parr[2].y-parr[1].y) + (parr[5].x-parr[4].x)*(parr[4].y-parr[3].y);
    bw.write(Integer.toString(area*numMelon));
    bw.newLine();
    bw.flush();
  }
}
