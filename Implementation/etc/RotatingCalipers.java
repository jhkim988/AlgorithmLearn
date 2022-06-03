import java.util.*;

public class RotatingCalipers {
  private static class Point {
    long x, y;
    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }
  }
  private static Point p1 = null, p2 = null;
  public static void main(String[] args) {
    int n = 10;
    Point[] points = new Point[n]; // convex
    
    rotCalipers(points);
  }
  static void rotCalipers(Point[] points) {
    int n = points.length;
    if (n <= 1) return; // do nothing
    if (n == 2) {
      p1 = points[0];
      p2 = points[1];
      return;
    }

    // 1. Find antipodal of p0: pk
    // antipodal of p0: Max area of triangle p(n-1) p0 pk
    int k = 1;
    while (area2(points[n-1], points[0], points[k+1]) > area2(points[n-1], points[0], points[k])) k++;
  
    // 2. Loop: i(0 ~ k) j(k~n-1)
    long max = 0;
    for (int i = 0, j = k; i <= k && j < n; i++) {
      long dist2 = dist2(points[i], points[j]);
      if (max < dist2) {
        max = dist2;
        p1 = points[i];
        p2 = points[j];
      }
      while (j < n && area2(points[i], points[j], points[(j+1)%n]) > area2(points[i], points[j], points[j])) {
        dist2 = dist2(points[i], points[(j+1)%n]);
        if (max < dist2) {
          max = dist2;
          p1 = points[i];
          p2 = points[(j+1)%n];
        }
        j++;
      }
    }
  }
  static long ccw(Point o, Point p1, Point p2) {
    return (p1.x-o.x)*(p2.y-o.y)-(p2.x-o.x)*(p1.y-o.y);
  }
  static long area2(Point p1, Point p2, Point p3) {
    return Math.abs(ccw(p1, p2, p3));
  }
  static long dist2(Point p1, Point p2) {
    return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
  }
}
