import java.io.*;
import java.util.*;

public class BOJ2170 {
  private static class Pair {
    int x, y;
    Pair(int x, int y) {
      this.x = x; 
      this.y = y;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Pair[] points = new Pair[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      points[i] = new Pair(x, y);
    }
    Arrays.sort(points, (a, b) -> a.x==b.x ? a.y - b.y : a.x - b.x);
    int len = 0;
    int start = points[0].x;
    int max = points[0].y;
    for (int i = 0; i < n; i++) {
      if (points[i].x <= max) {
       if (max < points[i].y) max = points[i].y; 
      } else {
        len += max - start;
        start = points[i].x;
        max = points[i].y;
      }
    }
    len += max - start; // last
    bw.write(Integer.toString(len));
    bw.newLine();
    bw.flush();
  }  
}
