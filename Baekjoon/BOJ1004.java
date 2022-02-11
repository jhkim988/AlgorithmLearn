import java.io.*;
import java.util.*;

public class BOJ1004 {
  private static class Circle {
    int cx, cy, r;
    Circle(int cx, int cy, int r) {
      this.cx = cx;
      this.cy = cy;
      this.r = r;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int sx = Integer.parseInt(st.nextToken());
      int sy = Integer.parseInt(st.nextToken());
      int tx = Integer.parseInt(st.nextToken());
      int ty = Integer.parseInt(st.nextToken());
      int numPlanet = Integer.parseInt(br.readLine());
      Circle[] planet = new Circle[numPlanet];
      for (int i = 0; i < numPlanet; i++) {
        st = new StringTokenizer(br.readLine());
        int cx = Integer.parseInt(st.nextToken());
        int cy = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        planet[i] = new Circle(cx, cy , r);
      }

      boolean[] startPointInclude = new boolean[numPlanet];
      boolean[] targetPointInclude = new boolean[numPlanet];
      for (int i = 0; i < numPlanet; i++) {
        startPointInclude[i] = getRelation(sx, sy, planet[i]);
        targetPointInclude[i] = getRelation(tx, ty, planet[i]);
      }

      int num = 0;
      for (int i = 0; i < numPlanet; i++) {
        if (startPointInclude[i] ^ targetPointInclude[i]) num++;
      }
      bw.write(Integer.toString(num));
      bw.newLine();
    }
    bw.flush();
  }
  static boolean getRelation(int x, int y, Circle c) {
    return (x-c.cx)*(x-c.cx) + (y-c.cy)*(y-c.cy) < c.r*c.r;
  }
}
