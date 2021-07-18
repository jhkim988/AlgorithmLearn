import java.io.*;
import java.util.Comparator;
import java.util.Arrays;

public class BOJ11651 {
  private static class Point implements Comparator<Point> {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compare(Point p1, Point p2) {
      if (p1.y < p2.y) {
        return -1;
      } else if (p1.y > p2.y) {
        return 1;
      } else {
        if (p1.x < p2.x) {
          return -1;
        } else if (p1.x > p2.x) {
          return 1;
        } else {
          return 0;
        }
      }
    }

    public String toString() {
      return x + " " + y;
    }
    
  }
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      int numPoints = Integer.parseInt(br.readLine());
      Point[] data = new Point[numPoints];
      
      for (int i = 0; i < numPoints; i++) {
        String[] tmp = br.readLine().split(" ");
        data[i] = new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
      }
      br.close();

      Arrays.sort(data, new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
          if (p1.y < p2.y) {
            return -1;
          } else if (p1.y > p2.y) {
            return 1;
          } else {
            if (p1.x < p2.x) {
              return -1;
            } else if (p1.x > p2.x) {
              return 1;
            } else {
              return 0;
            }
          }
        }     
      });
      for (int i = 0; i < numPoints; i++) {
        bw.write(data[i] + "\n");
      }
      bw.flush();
      bw.close();

    } catch (IOException e) {
      // do nothing
    }
  }
}
