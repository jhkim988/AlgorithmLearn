import java.io.*;
import java.util.*;

public class BOJ7420 {
  private static class Point {
    int x, y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    public double dist(Point other) {
      return Math.sqrt((this.x-other.x)*(this.x-other.x)+(this.y-other.y)*(this.y-other.y));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int l = Integer.parseInt(st.nextToken());
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      points[i] = new Point(x, y);
    }
    mergeSort(points, 0, n-1, new Comparator<Point>(){
      // y axis sort:
      @Override
      public int compare(Point p1, Point p2) {
        return p1.y!=p2.y ? p1.y-p2.y : p1.x-p2.x;
      }
    });
    Point start = points[0];
    mergeSort(points, 1, n-1, new Comparator<Point>(){
      // angle sort:
      @Override
      public int compare(Point p1, Point p2) {
        return -ccw(start, p1, p2);
      }
    });

    ArrayList<Point> convexhull = convexhull(points);
    double dist = 0;
    for (int i = 0; i < convexhull.size(); i++) {
      dist += convexhull.get(i).dist(convexhull.get((i+1)%convexhull.size()));
    }
    bw.write(Integer.toString((int) (Math.round(dist+2*Math.PI*l))));
    bw.flush();
  }
  static void mergeSort(Point[] arr, int lo, int hi, Comparator<Point> c) {
    if (lo == hi) return;
    int mid = (lo + hi) >> 1;
    mergeSort(arr, lo, mid, c);
    mergeSort(arr, mid+1, hi, c);
    Point[] tmp = new Point[mid-lo+1];
    System.arraycopy(arr, lo, tmp, 0, mid-lo+1);
    int ptr = lo, ptr1 = 0, ptr2 = mid+1;
    while (lo+ptr1 <= mid && ptr2 <= hi) {
      if (c.compare(tmp[ptr1], arr[ptr2]) <= 0) arr[ptr++] = tmp[ptr1++];
      else arr[ptr++] = arr[ptr2++];
    }
    while (lo+ptr1 <= mid) arr[ptr++] = tmp[ptr1++];
    // while (ptr2 <= hi) arr[ptr++] = arr[ptr2++];
  }
  static int ccw(Point o, Point p1, Point p2) {
    return (p1.x-o.x)*(p2.y-o.y)-(p2.x-o.x)*(p1.y-o.y);
  }
  static ArrayList<Point> convexhull(Point[] points) {
    // points: sorted
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
    ArrayList<Point> ret = new ArrayList<>();
    while (!deq.isEmpty()) ret.add(deq.removeFirst());
    return ret;
  }
}
