import java.io.*;
import java.util.*;

public class BOJ20149 {
  private static class Point implements Comparable<Point> {
    double x;
    double y;
    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
    Point diff(Point other) {
      return new Point(this.x - other.x, this.y - other.y);
    }
    Point add(Point other) {
      return new Point(this.x + other.x, this.y + other.y);
    }
    Point perp() {
      return new Point(-this.y, this.x);
    }
    double dot(Point other) {
      return this.x * other.x + this.y * other.y; 
    }
    Point scalar(double c) {
      return new Point(c * this.x, c * this.y);
    }
    @Override
    public int compareTo(Point other) {
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
    @Override
    public String toString() {
      return x + " " + y;
    }
    static int ccw(Point[] points, int start) {
      int len = points.length;
      Point vec1 = points[(start + 1) % len].diff(points[start % len]);
      Point vec2 = points[(start + 2) % len].diff(points[(start + 1) % len]);
      double det = vec1.x * vec2.y - vec1.y * vec2.x;
      return det > 0 ? 1 : det < 0 ? -1 : 0;
    }
  }
  private static class LineSeg implements Comparable<LineSeg> {
    Point[] end;
    LineSeg(Point p1, Point p2) {
      end = new Point[2];
      if (p1.compareTo(p2) < 0) {
        end[0] = p1;
        end[1] = p2;
      } else {
        end[0] = p2;
        end[1] = p1;
      }
    }
    Point intersection(LineSeg other) {
      // simple calculate, Not determine whether they intersect or not.
      Point vec1 = this.end[1].diff(this.end[0]);
      Point vec2 = other.end[1].diff(other.end[0]);
      Point constant = other.end[0].diff(this.end[0]);
      return vec2.scalar(-constant.dot(vec1.perp()) / vec2.dot(vec1.perp())).add(other.end[0]);
    }
    @Override
    public int compareTo(LineSeg other) {
      return this.end[0].compareTo(other.end[0]);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    LineSeg[] line = new LineSeg[2];
    for (int i = 0; i < 2; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      line[i] = new LineSeg(start, end);
    }

    Arrays.sort(line);
    Point[] points = {line[0].end[0], line[1].end[0], line[0].end[1], line[1].end[1]};
    boolean isParallel = isParallel(points);
    // same end point
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        if (line[0].end[i].equals(line[1].end[j])) {
          bw.write("1\n");
          if (!isParallel || line[0].end[1].equals(line[1].end[0])) {
            bw.write(line[0].end[i] + "\n");
          } 
          bw.flush();
          return;
        }
      }
    }
    
    // same line
    for (int i = 0; i < 2; i++) {
      if (Point.ccw(new Point[] {line[0].end[0], line[0].end[1], line[1].end[i]}, 0) == 0) {
        if (line[0].end[1].compareTo(line[1].end[i]) < 0) {
          bw.write("0\n");
          bw.flush();
          return;
        } else {
          bw.write("1\n");
          if (!isParallel) {
            bw.write(line[1].end[i] + "\n");
          }          
          bw.flush();
          return;
        }
      }
    } 
    if (Point.ccw(new Point[] {line[1].end[0], line[0].end[1], line[1].end[1]}, 0) == 0) {
      if (line[1].end[0].compareTo(line[0].end[1]) > 0 || line[1].end[1].compareTo(line[0].end[1]) < 0) {
        bw.write("0\n");
        bw.flush();
        return;
      } else {
        bw.write("1\n");
        if (!isParallel) {
          bw.write(line[0].end[1] + "\n");
        }        
        bw.flush();
        return;
      }
    }
    int prev = Point.ccw(points, 0);
    boolean sameDirection = true;
    for (int i = 1; i < 4; i++) {
      int crnt = Point.ccw(points, i);
      sameDirection = sameDirection && (crnt == prev);
      prev = crnt;
    }

    if (sameDirection) {
      bw.write("1\n");
      Point intersection = line[0].intersection(line[1]);
      if (!isParallel) {
        bw.write(intersection + "\n");
      }      
    } else {
      bw.write("0\n");
    }
    bw.flush();
  }
  static boolean isParallel(Point[] points) {
    for (int i = 0; i < points.length; i++) {
      if (Point.ccw(points, i) != 0) {
        return false;
      }
    }
    return true;
  }
}
