import java.io.*;
import java.util.*;

public class BOJ17387 {
  private static class Point implements Comparable<Point> {
    int x;
    int y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    @Override
    public int compareTo(Point other) {
      // y-axis
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
    Point diff(Point other) {
      return new Point(this.x - other.x, this.y - other.y);
    }
  }
  private static class LineSeg implements Comparable<LineSeg> {
    Point[] endpoint;
    LineSeg(Point p1, Point p2) {
      endpoint = new Point[2];
      if (p1.compareTo(p2) < 0) {
        endpoint[0] = p1;
        endpoint[1] = p2;
      } else {
        endpoint[0] = p2;
        endpoint[1] = p1;
      }
    }
    @Override
    public int compareTo(LineSeg other) {
      return this.endpoint[0].compareTo(other.endpoint[0]);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    LineSeg[] line = new LineSeg[2];
    for (int i = 0; i < 2; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      Point p1 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      Point p2 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      line[i] = new LineSeg(p1, p2);
    }

    // has common end point?
    boolean hasCommonEndPoint = false;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        hasCommonEndPoint = hasCommonEndPoint || line[0].endpoint[i].equals(line[1].endpoint[j]);
      }
    }
    if (hasCommonEndPoint) {
      bw.write("1\n");
      bw.flush();
      return;
    }

    // y-axis sort
    Arrays.sort(line);

    // same line
    for (int i = 0; i < 2; i++) {
      if (ccw(new Point[] {line[0].endpoint[0], line[0].endpoint[1], line[1].endpoint[i]}, 0) == 0) {
        if (line[0].endpoint[1].compareTo(line[1].endpoint[i]) < 0) {
          bw.write("0\n");
        } else {
          bw.write("1\n");
        }
        bw.flush();
        return;
      }
    }
    for (int i = 0; i < 2; i++) {
      if (ccw(new Point[] {line[1].endpoint[0], line[1].endpoint[1], line[0].endpoint[i]}, 0) == 0) {
        if (line[1].endpoint[1].compareTo(line[0].endpoint[1]) < 0 || line[1].endpoint[0].compareTo(line[0].endpoint[1]) > 0) {
          bw.write("0\n");
        } else {
          bw.write("1\n");
        }
        bw.flush();
        return;
      }
    }

    Point[] points = {line[0].endpoint[0], line[1].endpoint[0], line[0].endpoint[1], line[1].endpoint[1]};
    boolean sameDirection = true;
    int prev = ccw(points, 0);
    for (int i = 1; i < 4; i++) {
      int crnt = ccw(points, i);
      sameDirection = sameDirection && (crnt == prev);
      prev = crnt;
    }
    if (sameDirection) {
      bw.write("1\n");
    } else {
      bw.write("0\n");
    }
    bw.flush();
  }
  static int ccw(Point[] points, int start) {
    int numPoints = points.length;
    Point vec1 = points[(1 + start) % numPoints].diff(points[start % numPoints]);
    Point vec2 = points[(2 + start) % numPoints].diff(points[(1 + start) % numPoints]);
    long det = ((long) vec1.x) * ((long) vec2.y) - ((long) vec1.y) * ((long) vec2.x);
    return det > 0 ? 1 : (det < 0 ? -1 : 0);
  }
}
