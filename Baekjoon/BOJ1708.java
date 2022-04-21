import java.io.*;
import java.util.*;

public class BOJ1708 {
  private static class Point {
    double x, y;
    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
    double angle(Point other) {
      double diffx = other.x-x;
      double diffy = other.y-y;
      if (diffx > 0) {
        if (diffy >= 0) return Math.atan(diffy/diffx);
        return 2*Math.PI + Math.atan(diffy/diffx);
      } else if (diffx < 0) {
        return Math.PI + Math.atan(diffy/diffx);
      } else {
        if (diffy > 0) return Math.PI/2;
        else return Math.PI*3/2;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      double x = Double.parseDouble(st.nextToken());
      double y = Double.parseDouble(st.nextToken());
      points[i] = new Point(x, y);
    }
    Comparator<Point> yaxis = new Comparator<>(){
      public int compare(Point o1, Point o2) {
        if (o1.y != o2.y) return Double.compare(o1.y, o2.y);
        return Double.compare(o1.x, o2.x);
      }
    };
    Comparator<Point> angle = new Comparator<>(){
      public int compare(Point o1, Point o2) {
        return Double.compare(points[0].angle(o1), points[0].angle(o2));
      }
    };
    mergeSort(points, 0, n-1, yaxis);
    mergeSort(points, 1, n-1, angle);
    Stack<Integer> pstk = new Stack<>();
    Stack<Double> dstk = new Stack<>();
    pstk.add(0);
    for (int i = 1; i < n; i++) {
      double a = points[pstk.peek()].angle(points[i]);
      while (!dstk.isEmpty() && dstk.peek() >= a) {
        dstk.pop();
        pstk.pop();
        a = points[pstk.peek()].angle(points[i]);
      }
      dstk.push(a);
      pstk.push(i);
    }
    bw.write(Integer.toString(pstk.size()));
    bw.newLine();
    bw.flush();
  }
  @SuppressWarnings("unchecked")
  static <T> void mergeSort(T[] arr, int lo, int hi, Comparator<T> c) {
    if (lo >= hi) return;
    int mid = (lo + hi) >> 1;
    mergeSort(arr, lo, mid, c);
    mergeSort(arr, mid+1, hi, c);
    T[] aux = (T[]) (new Object[mid-lo+1]);
    System.arraycopy(arr, lo, aux, 0, mid-lo+1);
    int ptr1 = 0; // aux
    int ptr2 = mid+1; // arr
    int ptr = lo;
    while (ptr1 < mid-lo+1 && ptr2 <= hi && ptr <= hi) {
      while (ptr1 < mid-lo+1 && ptr2 <= hi && c.compare(aux[ptr1], arr[ptr2]) <= 0) {
        arr[ptr] = aux[ptr1];
        ptr++;
        ptr1++;
      }
      while (ptr1 < mid-lo+1 && ptr2 <= hi && c.compare(aux[ptr1], arr[ptr2]) > 0) {
        arr[ptr] = arr[ptr2];
        ptr++;
        ptr2++;
      }
    }
    while (ptr1 < mid-lo+1) {
      arr[ptr] = aux[ptr1];
      ptr++;
      ptr1++;
    }
    while (ptr2 <= hi) {
      arr[ptr] = arr[ptr2];
      ptr++;
      ptr2++;
    }
  }
}
