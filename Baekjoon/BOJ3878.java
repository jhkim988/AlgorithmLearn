import java.io.*;
import java.util.*;

public class BOJ3878 {
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
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int nBlack = Integer.parseInt(st.nextToken());
      int nWhite = Integer.parseInt(st.nextToken());
      Point[] black = new Point[nBlack];
      Point[] white = new Point[nWhite];
      for (int i = 0; i < nBlack; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        black[i] = new Point(x, y); 
      }
      for (int i = 0; i < nWhite; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        white[i] = new Point(x, y); 
      }

      ArrayList<Point> blackhull = convexhull(black);
      ArrayList<Point> whitehull = convexhull(white);

      bw.write(isSeparable(blackhull, whitehull) && isSeparable(whitehull, blackhull)? "YES\n" : "NO\n");
    }
    bw.flush();
  }
  static ArrayList<Point> convexhull(Point[] points) {    
    int n = points.length;
    ArrayList<Point> ret = new ArrayList<>();
    if (n <= 2) {
      for (int i = 0; i < n; i++) {
        ret.add(points[i]);
      }
      return ret;
    }

    mergeSort(points, 0, n-1, new Comparator<Point>(){
      @Override
      public int compare(Point p1, Point p2) {
        return (int) (p1.y != p2.y ? p1.y-p2.y : p1.x-p2.x);
      }
    });
    Point start = points[0];
    mergeSort(points, 1, n-1, new Comparator<Point>(){
      @Override
      public int compare(Point p1, Point p2) {
        long ccw = ccw(start, p1, p2);
        if (ccw < 0) return 1;
        else if (ccw > 0) return -1;
        return 0;
      }
    });
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
    while (!deq.isEmpty()) ret.add(deq.removeFirst());
    return ret;
  }
  static void mergeSort(Point[] points, int lo, int hi, Comparator<Point> c) {
    if (lo == hi) return;
    int mid = (lo+hi)>>1;
    mergeSort(points, lo, mid, c);
    mergeSort(points, mid+1, hi, c);
    Point[] tmp = new Point[mid-lo+1];
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
  static boolean isSeparable(ArrayList<Point> set1, ArrayList<Point> set2) {
    for (Point p : set1) {
      boolean allccw = true;
      for (int i = 0; i < set2.size()-1; i++) {
        long ccw = ccw(set2.get(i), set2.get(i+1), p); 
        if (ccw == 0) {
          if (set2.get(i).x < p.x && p.x < set2.get(i+1).x || set2.get(i+1).x < p.x && p.x < set2.get(i).x) return false;
        } else {
          allccw = allccw && (ccw > 0);
        }
      }
      if (allccw) return false;
    }
    return true;
  }
}
