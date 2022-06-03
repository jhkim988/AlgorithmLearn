import java.io.*;
import java.util.*;

public class BOJ10254 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static Point p1 = null, p2 = null;
  private static class Point {
    long x, y;
    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }
  }
  public static void main(String[] args) throws IOException {
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      int n = Integer.parseInt(br.readLine());
      ArrayList<Point> points = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        points.add(new Point(x, y));
      }
      ArrayList<Point> convexHull = convexHull(points);
      rotCalipers(convexHull);
      bw.write(Long.toString(p1.x));
      bw.write(' ');
      bw.write(Long.toString(p1.y));
      bw.write(' ');
      bw.write(Long.toString(p2.x));
      bw.write(' ');
      bw.write(Long.toString(p2.y));
      bw.newLine();
    }
    bw.flush();
  }
  static ArrayList<Point> convexHull(ArrayList<Point> points) {
    // y-axis sort:
    Collections.sort(points, new Comparator<Point>() {
      @Override
      public int compare(Point p1, Point p2) {
        return (int) (p1.y != p2.y ? p1.y-p2.y : p1.x-p2.x);
      }
    });

    // polar sort:
    Point start = points.get(0);
    mergeSort(points, 1, points.size()-1, new Comparator<Point>(){
      @Override
      public int compare(Point p1, Point p2) {
        long ccw = ccw(start, p1, p2);
        if (ccw < 0) return 1;
        else if (ccw > 0) return -1;
        return 0;
      }
    });
    
    // Graham Scan:
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
    ArrayList<Point> convexHull = new ArrayList<>();
    while (!deq.isEmpty()) {
      convexHull.add(deq.removeFirst());
    }
    return convexHull;
  }
  static void rotCalipers(ArrayList<Point> p) {
    int n = p.size();
    if (n == 2) {
      p1 = p.get(0);
      p2 = p.get(1);
      return;
    }

    long max = 0;
    
    // 1. Find antipodal of p0: pk
    // antipodal = max area triangle
    int k = 1;
    while (area2(p.get(n-1), p.get(0), p.get((k+1)%n)) > area2(p.get(n-1), p.get(0), p.get(k))) k++;
    
    // 2. Loop: i (0~k), j (k~n-1)
    for (int i = 0, j = k; i <= k && j < n; i++) {
      long dist2 = dist2(p.get(i), p.get(j)); 
      if (max < dist2) {
        max = dist2;
        p1 = p.get(i);
        p2 = p.get(j);
      }
      while (j < n && area2(p.get(i), p.get((i+1)%n), p.get((j+1)%n)) > area2(p.get(i), p.get((i+1)%n), p.get(j))) {
        dist2 = dist2(p.get(i), p.get((j+1)%n));
        if (max < dist2) {
          max = dist2;
          p1 = p.get(i);
          p2 = p.get((j+1)%n);
        }
        j++;
      }
    }
  }
  static void mergeSort(ArrayList<Point> arr, int lo, int hi, Comparator<Point> c) {
    if (lo == hi) return;
    int mid = (lo+hi)>>1;
    mergeSort(arr, lo, mid, c);
    mergeSort(arr, mid+1, hi, c);
    ArrayList<Point> tmp = new ArrayList<>();
    for (int i = lo; i <= mid; i++) tmp.add(arr.get(i));
    int ptr = lo, ptr1 = 0, ptr2 = mid+1;
    while (lo+ptr1 <= mid && ptr2 <= hi) {
      if (c.compare(tmp.get(ptr1), arr.get(ptr2)) <= 0) arr.set(ptr, tmp.get(ptr1++));
      else arr.set(ptr, arr.get(ptr2++));
      ptr++;
    }
    while (lo+ptr1 <= mid) {
      arr.set(ptr, tmp.get(ptr1++));
      ptr++;
    }
    while (ptr2 <= hi) {
      arr.set(ptr, arr.get(ptr2++));
      ptr++;
    }
  }
  static long ccw(Point o, Point p1, Point p2) {
    return (p1.x-o.x)*(p2.y-o.y)-(p1.y-o.y)*(p2.x-o.x);
  }
  static long dist2(Point p1, Point p2) {
    return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
  }
  static long area2(Point p1, Point p2, Point p3) {
    return Math.abs(ccw(p1, p2, p3));
  }
}
