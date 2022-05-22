import java.io.*;
import java.util.*;

public class BOJ1064 {
  private static class Point {
    int x, y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    double dist(Point other) {
      return Math.sqrt((this.x-other.x)*(this.x-other.x)+(this.y-other.y)*(this.y-other.y));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    Point[] input = new Point[3];
    for (int i = 0; i < 3; i++) {
      input[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    bw.write(Double.toString(answer(input)));
    bw.flush();
  }
  static boolean isColinear(Point p1, Point p2, Point p3) {
    double dx1 = p1.x-p2.x, dy1 = p1.y-p2.y;
    double dx2 = p1.x-p3.x, dy2 = p1.y-p3.y;
    if (dx1 != 0 && dx2 != 0) return Math.abs(dy1/dx1 - dy2/dx2) < 1e-9;
    return Math.abs(dx1/dy1 - dx2/dy2) < 1e-9;
  }
  static double answer(Point[] input) {
    if (isColinear(input[0], input[1], input[2])) return -1;
    double max = 0;
    double min = Double.MAX_VALUE;
    for (int i = 0; i < 3; i++) {
      double val = 2*(input[i].dist(input[(i+1)%3])+input[i].dist(input[(i+2)%3]));
      if (max < val) max = val;
      if (val < min) min = val;
    }
    return max-min;
  }
}
