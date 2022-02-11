import java.io.*;
import java.util.*;

public class BOJ11758 {
  static class Point{
    int x;
    int y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    Comparator<Point> polarOrder() {
      return new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
          double arc1 = Math.atan(((double) (p1.y - y)) / ((double) p1.x - x));
          double arc2 = Math.atan(((double) (p2.y - y)) / ((double) p2.x - x));
          if (p1.x - x < 0) {
            arc1 += Math.PI;
          }
          if (p2.x - x < 0) {
            arc2 += Math.PI;
          }
          // System.out.println(arc1 + " " + arc2);
          if (Math.abs(arc2 - arc1) < Math.PI) {
            return Double.compare(arc1, arc2);
          } else if (Math.abs(arc2 - arc1) > Math.PI) {
            return Double.compare(arc2, arc1);
          } else {
            return 0;
          }
        } 
      };
    }
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (getClass() != o.getClass()) {
        return false;
      }
      Point other = (Point) o;
      if (this.x == other.x && this.y == other.y) {
        return true;
      }
      return false;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    Point[] data = new Point[3];
    for (int i = 0; i < 3; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int first = Integer.parseInt(st.nextToken());
      int second = Integer.parseInt(st.nextToken());
      data[i] = new Point(first, second);
    }
    // bw.write(- data[0].polarOrder().compare(data[1], data[2]) + "\n");
    bw.write(determine(data) + "\n");
    bw.flush();
  }
  static int determine(Point[] data) {
    int det = (data[1].x - data[0].x)*(data[2].y - data[0].y) - (data[1].y - data[0].y) * (data[2].x - data[0].x);
    return det > 0 ? 1 : (det < 0 ? -1 : 0);
  }
}
