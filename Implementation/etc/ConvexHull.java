import java.io.*;
import java.util.*;

public class ConvexHull {
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
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      points[i] = new Point(x, y);
    }
    ArrayList<Point> graham = grahamScan(points);
    bw.write("Convex Hull:\n");
    for (Point p : graham) {
      bw.write("(" + Long.toString(p.x) + ", " + Long.toString(p.y) + ")");
      bw.newLine();
    }
    bw.flush();
  }
  static ArrayList<Point> grahamScan(Point[] points) {
    int n = points.length;
    // 1. y-axis sort:
    mergeSort(points, 0, n-1, new Comparator<Point>() {
      @Override
      public int compare(Point p1, Point p2) {
        return (int) (p1.y != p2.y ? p1.y-p2.y : p1.x-p2.x);
      }
    });

    // 2. angle sort:
    Point start = points[0];
    mergeSort(points, 1, n-1, new Comparator<Point>() {
      @Override
      public int compare(Point p1, Point p2) {
        long ccw = ccw(start, p1, p2);
        if (ccw < 0) return 1;
        else if (ccw > 0) return -1;
        else return 0;
      }
    });
    
    // 3. Scan
    Deque<Point> deq = new LinkedList<>();
    for (Point p : points) {
      while (deq.size() >= 2) {
        Point peek = deq.removeLast();
        Point nxtpeek = deq.peekLast();
        if (ccw(nxtpeek, peek, p) > 0) {
          deq.addLast(peek);
          break;
        }
      }
      deq.addLast(p);
    }

    ArrayList<Point> result = new ArrayList<>();
    while (!deq.isEmpty()) result.add(deq.removeFirst());
    return result;
  }
  static void mergeSort(Point[] points, int lo, int hi, Comparator<Point> c) {
    if (lo == hi) return;
    int mid = (lo+hi)>>1;
    mergeSort(points, lo, mid, c);
    mergeSort(points, mid+1, hi, c);
    Point[] tmp = new Point[hi-lo+1];
    System.arraycopy(points, lo, tmp, 0, mid-lo+1);
    int ptr = lo, ptr1 = 0, ptr2 = mid+1;
    while (lo+ptr1 <= mid && ptr2 <= hi) {
      if (c.compare(tmp[ptr1], points[ptr2]) <= 0) points[ptr] = tmp[ptr1++];
      else points[ptr] = points[ptr2++];
      ptr++;
    }
    while (lo+ptr1 <= mid) {
      points[ptr] = tmp[ptr1++];
      ptr++;
    }
    while (ptr2 <= hi) {
      points[ptr] = points[ptr2++];
      ptr++;
    }
  }
  static long ccw(Point o, Point p1, Point p2) {
    return (p1.x-o.x)*(p2.y-o.y)-(p2.x-o.x)*(p1.y-o.y);
  }
}