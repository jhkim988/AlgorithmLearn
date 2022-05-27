import java.io.*;
import java.util.*;

public class BOJ4225 {
  private static Point[] tmp;
  private static class Point {
    long x, y;
    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    tmp = new Point[101];
    int numCase = 1;
    while (n != 0) {
      Point[] points = new Point[n];
      for (int i = 0; i < n; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        points[i] = new Point(x, y);
      }
      // Find Convex Hull
      merge(points, 0, n-1, new Comparator<Point>() {
        // y axis
        @Override
        public int compare(Point p1, Point p2) {
          if (p1.y > p2.y) return 1;
          else if (p1.y < p2.y) return -1;
          else if (p1.x > p2.x) return 1;
          else if (p1.x < p2.x) return -1;
          return 0;
        }
      });
      Point start = points[0];
      merge(points, 1, n-1, new Comparator<Point>() {
        // angle
        @Override
        public int compare(Point p1, Point p2) {
          long ccw = ccw(start, p1, p2);
          if (ccw > 0) return -1;
          else if (ccw < 0) return 1;
          if (p1.y > p2.y) return 1;
          else if (p1.y < p2.y) return -1;
          else if (p1.x > p2.x) return 1;
          else if (p1.x < p2.x) return -1;
          return 0;
        }
      });

      ArrayList<Point> convexHull = new ArrayList<>();
      Deque<Point> deq = new LinkedList<>();
      convexHull.add(start);
      deq.addLast(start);
      deq.addLast(points[1]);
      for (int i = 2; i < points.length; i++) {
        while (deq.size() >= 2) {
          Point peek = deq.removeLast();
          Point nxtpeek = deq.peekLast();
          if (ccw(nxtpeek, peek, points[i]) > 0) {
            deq.addLast(peek);
            break;
          }
        }
        deq.addLast(points[i]);
      }
      while (!deq.isEmpty()) convexHull.add(deq.removeLast());

      double min = Double.MAX_VALUE;
      for (int i = 0; i < convexHull.size()-1; i++) {
        double max = 0;
        for (int j = 0; j < convexHull.size(); j++) {
          if (j == i || j == i+1) continue;
          max = Double.max(max, dist(convexHull.get(i), convexHull.get(i+1), convexHull.get(j)));
        }
        min = Double.min(min, max);
      }
      bw.write("Case ");
      bw.write(Integer.toString(numCase++));
      bw.write(": ");
      bw.write(String.format("%.2f\n", Math.ceil(min*100)/100));
      n = Integer.parseInt(br.readLine());
    }
    bw.flush();
  }
  static void merge(Point[] arr, int lo, int hi, Comparator<Point> c) {
    if (lo == hi) return;
    int mid = (lo + hi) >> 1;
    merge(arr, lo, mid, c);
    merge(arr, mid+1, hi, c);
    System.arraycopy(arr, lo, tmp, lo, mid-lo+1);
    int ptr = lo, ptr1 = lo, ptr2 = mid+1;
    while (ptr1 <= mid && ptr2 <= hi) {
      if (c.compare(tmp[ptr1], arr[ptr2]) <= 0) arr[ptr++] = tmp[ptr1++];
      else arr[ptr++] = arr[ptr2++];
    }
    while (ptr1 <= mid) arr[ptr++] = tmp[ptr1++];
    while (ptr2 <= hi) arr[ptr++] = arr[ptr2++];
    
  }
  static long ccw(Point p0, Point p1, Point p2) {
    return (p1.x-p0.x)*(p2.y-p0.y)-(p1.y-p0.y)*(p2.x-p0.x);
  }
  static double dist(Point p0, Point p1, Point p) {
    // dist from line(p0-p1) to p
    double nx = p0.y-p1.y;
    double ny = p1.x-p0.x;
    double norm = Math.sqrt(nx*nx+ny*ny);
    nx /= norm;
    ny /= norm;
    return (p0.x-p.x)*nx + (p0.y-p.y)*ny;
  }
}