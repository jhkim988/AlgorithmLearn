import java.io.*;
import java.util.*;

public class BOJ17371 {
  private static class Point {
    int x, y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    double dist(Point other) {
      return Math.sqrt((this.x-other.x)*(this.x-other.x) + (this.y-other.y)*(this.y-other.y));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    int minIdx = 0;
    double min = Double.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      double max = 0;
      for (int j = 0; j < n; j++) {
        if (i == j) continue;
        double dist = points[i].dist(points[j]);
        if (max < dist) {
          max = dist;
        }
      }
      if (max < min) {
        min = max;
        minIdx = i;
      }
    }
    bw.write(Double.toString(points[minIdx].x));
    bw.write(' ');
    bw.write(Double.toString(points[minIdx].y));
    bw.flush();
  }
}
