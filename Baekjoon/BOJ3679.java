import java.io.*;
import java.util.*;

public class BOJ3679 {
  static Point[] aux;
  private static class Point {
    int x, y, idx;
    Point(int x, int y, int idx) {
      this.x = x;
      this.y = y;
      this.idx = idx;
    }

  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      Point[] points = new Point[n];
      for (int i = 0; i < n; i++) {
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        points[i] = new Point(x, y, i);
      }
      
      aux = new Point[n];
      mergeSort(points, new Comparator<Point>() {
        // Sort by y value:
        @Override
        public int compare(Point p1, Point p2) {
          if (p1.y != p2.y) return p1.y-p2.y;
          return p1.x-p2.x;
        }
      }, 0, n-1);
      
      Point start = points[0];
      mergeSort(points, new Comparator<Point>() {
        // sort by angle
        @Override
        public int compare(Point p1, Point p2) {
          long ccw = ccw(start, p1, p2);
          if (ccw > 0) return -1;
          else if (ccw < 0) return 1;
          if (p1.y != p2.y) return p1.y-p2.y;
          return p1.x-p2.x;
        }
      }, 1, n-1);
      
      // first/last colinear exception
      int num = 1;
      int ptr = n-1;
      while (ccw(start, points[ptr-1], points[n-1]) == 0) {
        ptr--;
        num++;
      }
      for (int i = 0; i < num/2; i++) {
        Point tmp = points[ptr+i];
        points[ptr+i] = points[n-1-i];
        points[n-1-i] = tmp;
      }

      for (int i = 0; i < n; i++) {
        bw.write(Integer.toString(points[i].idx));
        bw.write(' ');
      }
      bw.newLine();
    }
    bw.flush();
  }
  static void mergeSort(Point[] points, Comparator<Point> c, int lo, int hi) {
    if (lo == hi) return;
    int mid = (lo + hi) >> 1;
    mergeSort(points, c, lo, mid);
    mergeSort(points, c, mid+1, hi);
    System.arraycopy(points, lo, aux, lo, hi-lo+1);
    int ptr1 = lo;
    int ptr2 = mid+1;
    int ptr = lo;
    
    while (ptr1 <= mid && ptr2 <= hi) {
      if (c.compare(aux[ptr1], points[ptr2]) < 0) {
        points[ptr] = aux[ptr1++];
      } else {
        points[ptr] = points[ptr2++];
      }
      ptr++;
    }
    while (ptr1 <= mid) {
      points[ptr] = aux[ptr1];
      ptr++; ptr1++;
    }
    while (ptr2 <= hi) {
      points[ptr] = points[ptr2];
      ptr++; ptr2++;
    }
  }
  static long ccw(Point p1, Point p2, Point p3) {
    return (p2.x-p1.x)*(p3.y-p1.y)-(p2.y-p1.y)*(p3.x-p1.x);
  }
}
