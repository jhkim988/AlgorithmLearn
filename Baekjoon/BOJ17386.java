import java.io.*;
import java.util.*;

public class BOJ17386 {
  private static class Point implements Comparable<Point> {
    int x;
    int y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    Point diff(Point other) {
      return new Point(this.x - other.x, this.y - other.y);
    }
    @Override
    public int compareTo(Point other) { // y-axis
      if (this.y < other.y) {
        return -1;
      } else if (this.y > other.y) {
        return 1;
      } else {
        if (this.x < other.x) {
          return -1;
        } else if (this.x > other.x) {
          return 1;
        } else {
          return 0;
        }
      }
    }
    @Override
    public String toString() {
      return "<" + x + ", " + y + ">";
    }
  }
  private static class LineSeg implements Comparable<LineSeg> {
    Point p1;
    Point p2;
    LineSeg(Point p1, Point p2) {
      if (p1.compareTo(p2) < 0) {
        this.p1 = p1;
        this.p2 = p2;
      } else {
        this.p1 = p2;
        this.p2 = p1;
      }      
    }
    @Override
    public int compareTo(LineSeg other) {
      return this.p1.compareTo(other.p1);
    }
    @Override
    public String toString() {
      return p1 + " " + p2;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    LineSeg[] line = new LineSeg[2];
    for (int i = 0; i < 2; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      line[i] = new LineSeg(
        new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
      , new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
      );
    }
    Arrays.sort(line);
    Point[] points = {line[0].p1, line[1].p1, line[0].p2, line[1].p2};
    boolean flag = true;
    for (int i = 0; i < 4; i++) {
      flag = flag && (determine(points, i) > 0);
    }
    if (flag) {
      bw.write("1\n");
    } else {
      bw.write("0\n");
    }
    bw.flush();
  }
  static int determine(Point[] data, int start) {
    double det = ((double) (data[(1 + start) % 4].x - data[start % 4].x)) * ((double) (data[(2 + start) % 4].y - data[start % 4].y))
    - ((double) (data[(1 + start) % 4].y - data[start % 4].y)) * ((double) (data[(2 + start) % 4].x - data[start % 4].x));
    return det > 0L ? 1 : (det < 0L ? -1 : 0);
  }
}
