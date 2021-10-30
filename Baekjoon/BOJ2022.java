import java.io.*;
import java.util.*;

public class BOJ2022 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    double x = Double.parseDouble(st.nextToken());
    double y = Double.parseDouble(st.nextToken());
    double c = Double.parseDouble(st.nextToken());
    final double err = Math.pow(10, -4);
    double lo = 0;
    double hi = Math.min(x, y);
    while (lo < hi) {
      double mid = (lo + hi) / 2;
      double height = getHeight(x, y, mid);
      // System.out.println("lo: " + lo + ", hi: " + hi + ", height: " + height);
      if (height <= c - err ) {
        hi = mid;
      } else if (height >= c + err) {
        lo = mid;
      } else {
        bw.write(mid + "\n");
        bw.flush();
        return;
      }
    }
  }
  static double getHeight(double x, double y, double dist) {
    double xHeight = Math.sqrt(x * x - dist * dist);
    double yHeight = Math.sqrt(y * y - dist * dist);
    return xHeight * yHeight / (xHeight + yHeight);
  }
}
