import java.io.*;
import java.util.*;

public class BOJ11664 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    double[][] points = new double[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        points[i][j] = Double.parseDouble(st.nextToken());
      }
    }
    double err = Math.pow(10, -7);
    double d0 = dist(points[0], points[2]);
    double d1 = dist(points[1], points[2]);
    while (true) {
      double[] mid = midPoint(points[0], points[1]);
      double midDist = dist(midPoint(points[0], points[1]), points[2]);
      if (d0 <= d1 - err) {
        d1 = midDist;
        for (int i = 0; i < 3; i++) {
          points[1][i] = mid[i];
        }
      } else if (d0 >= d1 + err) {
        d0 = midDist;
        for (int i = 0; i < 3; i++) {
          points[0][i] = mid[i];
        }
      } else {
        bw.write(midDist + "\n");
        bw.flush();
        return;
      }
    }
  }
  static double dist(double[] point1, double[] point2) {
    double result = 0.0;
    for (int i = 0; i < 3; i++) {
      result += (point1[i] - point2[i]) * (point1[i] - point2[i]);
    }
    return Math.sqrt(result);
  }
  static double[] midPoint(double[] point1, double[] point2) {
    double[] mid = new double[3];
    for (int i = 0; i < 3; i++) {
      mid[i] = (point1[i] + point2[i]) / 2;
    }
    return mid;
  }
}
