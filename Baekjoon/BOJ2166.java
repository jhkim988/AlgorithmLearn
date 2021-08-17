import java.io.*;
import java.util.*;

public class BOJ2166 {
  static int N;
  private static class Point implements Comparable<Point> {
    double x;
    double y;
    Point(double x, double y) {
      this.x = x;
      this.y = y;
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
    public double dist(Point other) {
      return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
    public Comparator<Point> polarOrder() {
      return new Comparator<Point>(){
        @Override
        public int compare(Point p1, Point p2) {
          double arc1 = (double) ((p1.y - y)) / (double) ((p1.x - x));
          double arc2 = (double) ((p2.y - y)) / (double) ((p2.x - x));
          return Double.compare(arc1, arc2);
        }
      };
    }
    static double triangleArea(Point p1, Point p2, Point p3) {
      double len = (p1.dist(p2) + p2.dist(p3) + p3.dist(p1)) / 2;
      return Math.sqrt(len * (len - p1.dist(p2)) * (len - p2.dist(p3)) * (len - p3.dist(p1)));
    }
    @Override
    public String toString() {
      return "<" + x + ", " + y + ">";
    }
  }  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    Point[] data = new Point[N + 1];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      data[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    data[N] = data[0];
    double area = area(data);
    System.out.printf("%.1f\n", area);
  }
  static double area(Point[] data) {
    double areaSum = 0;
    for (int i = 0; i < N; i++) {
      areaSum += data[i].x * data[i + 1].y - data[i + 1].x * data[i].y;
    }
    return Math.abs(areaSum) * 0.5;
  }
}
