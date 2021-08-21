import java.io.*;
import java.util.*;

public class BOJ1007 {
  static double minLen = Double.MAX_VALUE;
  private static class Point {
    long x;
    long y;
    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }
    @Override
    public String toString() {
      return "<" + x + ", " + y + ">";
    }
    double norm() {
      return dist(new Point(0, 0));
    }
    double dist(Point other) {
      return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
    Point add(Point other) {
      return new Point(this.x + other.x, this.y + other.y);
    }
    Point diff(Point other) {
      return new Point(this.x - other.x, this.y - other.y);
    }
    // Point scalarProduct(int c) {
    //   return new Point(this.x * c, this.y * c);
    // }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int numPoint = Integer.parseInt(br.readLine());
      Point[] data = new Point[numPoint];
      for (int i = 0; i < numPoint; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        data[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
      }
      minLen = Double.MAX_VALUE;
      minVecLenSum(data, numPoint);
      bw.write(minLen + "\n");
    }
    bw.flush();
  }
  static void minVecLenSum(Point[] data, int numPoint) {
    boolean[] marked = new boolean[numPoint];
    recur(0, 0, data, numPoint,marked);
  } 
  static void recur(int cursor, int numPick, Point[] data, int numPoint, boolean[] marked) {
    if (numPick == numPoint / 2) {
      // int bit = 0;
      // for (int i = 0; i < numPoint; i++) {
      //   if (marked[i]) {
      //     bit = bit | (1 << i);
      //   }
      // }
      // System.out.println(Integer.toBinaryString(bit));
      Point end = new Point(0, 0);
      Point start = new Point(0, 0);
      for (int i = 0; i < numPoint; i++) {
        if (marked[i]) {
          end = end.add(data[i]);
        } else {
          start = start.add(data[i]);
        }
      }
      double result = end.diff(start).norm();
      if (result < minLen) {
        minLen = result;
      }
      return;
    }
    if (cursor == numPoint) {
      return;
    }
    marked[cursor] = true;
    recur(cursor + 1, numPick + 1, data, numPoint, marked);
    marked[cursor] = false;
    recur(cursor + 1, numPick, data, numPoint, marked);
  }
  // Time Limit Exceeded
  // static void minVecLenSum(Point[] data, int numPoint) {
  //   boolean[] marked = new boolean[numPoint];
  //   marked[0] = true;
  //   for (int i = 1; i < numPoint; i++) {
  //     marked[i] = true;
  //     Point sum = data[0].diff(data[i]);
  //     backtraking(0, i, 2, numPoint, sum, data, marked);
  //     backtraking(i, 0, 2, numPoint, sum.scalarProduct(-1), data, marked);
  //     marked[i] = false;
  //   } 
  // }
  // static void backtraking(int pick1, int pick2, int depth, int numPoint, Point vecSum, Point[] data, boolean[] marked) {
  //   if (depth == numPoint) {
  //     if (vecSum.norm() < minLen) {
  //       minLen = vecSum.norm();
  //     }
  //   }
  //   for (int i = 0; i < numPoint; i++) {
  //     if (marked[i]) continue;
  //     for (int j = i + 1; j < numPoint; j++) {
  //       if (marked[j]) continue;
  //       marked[i] = true;
  //       marked[j] = true;
  //       Point sum = vecSum.add(data[i].diff(data[j]));
  //       backtraking(i, j, depth + 2, numPoint, sum, data, marked);
  //       backtraking(j, i, depth + 2, numPoint, sum.scalarProduct(-1), data, marked);
  //       marked[i] = false;
  //       marked[j] = false;
  //     }      
  //   }
  // }

}
