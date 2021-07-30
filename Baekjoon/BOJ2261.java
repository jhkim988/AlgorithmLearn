import java.io.*;
import java.util.*;

public class BOJ2261 {
  private static class Point2D {
    int x;
    int y;
    Point2D(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  static long minDist(Point2D[] data) {
    // [-10001, 10001]
    return recur(data, -10001, 10001, -10001, 10001, 0, true);
  }
  static long recur(Point2D[] data, int xleft, int xright, int ydown, int yup, int idx, boolean isVertical) {
    long min1 = -1;
    long min2 = -1;
    if (isVertical) {
      min1 = recur(data, xleft, data[idx].x, ydown, yup, idx + 1, !isVertical);
      min2 = recur(data, data[idx].x, xright, ydown, yup, idx + 1, !isVertical);
    } else {
      min2 = recur(data, xleft, xright, ydown, data[idx].y, idx + 1, !isVertical);
      min2 = recur(data, xleft, xright, data[idx].y, yup, idx + 1, !isVertical);
    }

    long min = min1 < min2 ? min1 : min2;
    return min;
  }
  public static void main(String[] ags) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    Point2D[] data = new Point2D[N];
    String[] tmp;
    for (int i = 0; i < N; i++) {
      tmp = br.readLine().split(" ");
      data[i] = new Point2D(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
    }
    bw.write(minDist(data) + "\n");
    bw.flush();
  }
}
