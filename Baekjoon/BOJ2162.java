import java.io.*;
import java.util.*;

public class BOJ2162 {
  private static class Point implements Comparable<Point> {
    int x;
    int y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    @Override
    public int compareTo(Point other) {
      int ycmp = Integer.compare(this.y, other.y);
      int xcmp = Integer.compare(this.x, other.x);
      return ycmp != 0 ? ycmp : xcmp;
    } 
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (getClass() != o.getClass()) {
        return false;
      }
      Point other = (Point) o;
      if (this.x == other.x && this.y == other.y) {
        return true;
      }
      return false;
    }
    @Override
    public String toString() {
      return "<" + x + ", " + y + ">";
    }
    static int ccw(Point p1, Point p2, Point p3) {
      Point vec1 = new Point(p2.x - p1.x, p2.y - p1.y);
      Point vec2 = new Point(p3.x - p2.x, p3.y - p2.y);
      int det = vec1.x * vec2.y - vec1.y * vec2.x;
      return det > 0 ? 1 : (det < 0 ? -1 : 0);
    }
  }
  private static class LineSeg implements Comparable<LineSeg> {
    Point[] end;
    LineSeg(Point p1, Point p2) {
      end = new Point[2];
      if (p1.compareTo(p2) < 0) {
        end[0] = p1;
        end[1] = p2;
      } else {
        end[0] = p2;
        end[1] = p1;
      }
    }
    @Override
    public int compareTo(LineSeg other) {
      return this.end[0].compareTo(other.end[0]);
    }
    @Override
    public String toString() {
      return "(" + end[0] + " - " + end[1] + ")";
    }
    boolean intersect(LineSeg other) {
      LineSeg seg1;
      LineSeg seg2;
      if (this.compareTo(other) < 0) {
        seg1 = this;
        seg2 = other;
      } else {
        seg1 = other;
        seg2 = this;
      }
      Point[] points = {seg1.end[0], seg1.end[1], seg2.end[0], seg2.end[1]};

      // same end point
      for (int i = 0; i < 2; i++) {
        for (int j = 2; j < 4; j++) {
          if (points[i].equals(points[j])) {
            return true;
          }
        }
      }

      // same line
      for (int i = 2; i < 4; i++) {
        if (Point.ccw(points[0], points[1], points[i]) == 0) {
          if (points[0].compareTo(points[i]) < 0 && points[i].compareTo(points[1]) < 0) {
            return true;
          } else {
            return false;
          }
        }
      }
      if (Point.ccw(points[1], points[2], points[3]) == 0) {
        if (points[2].compareTo(points[1]) < 0 && points[1].compareTo(points[3]) < 0) {
          return true;
        } else {
          return false;
        }
      }

      // ccw
      points = new Point[] {seg1.end[0], seg2.end[0], seg1.end[1], seg2.end[1]};
      boolean sameDirection = true;
      int prev =  Point.ccw(points[0], points[1], points[2]);
      for (int i = 1; i < 4; i++) {
        int crnt = Point.ccw(points[i % 4], points[(i + 1) % 4], points[(i + 2) % 4]);
        sameDirection = sameDirection && (prev == crnt);
        prev = crnt;
      }
      return sameDirection;
    }
  }
  private static class UnionFind {
    int[] id;
    int[] sz;
    int num;
    UnionFind(int N) {
      id = new int[N];
      sz = new int[N];
      for (int i = 0; i < N; i++) {
        id[i] = i;
        sz[i] = 1;
      }
      num = N;
    }
    int root(int i) {
      while (i != id[i]) {
        i = id[i];
      }
      return i;
    }
    void union(int p, int q) {
      int prt = root(p);
      int qrt = root(q);
      if (prt == qrt) return;
      num--;
      if (sz[prt] < sz[qrt]) {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
      } else {
        id[qrt] = prt;
        sz[prt] += sz[qrt];
      }
    }
    // boolean connected(int p, int q) {
    //   return root(p) == root(q);
    // }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    UnionFind uf = new UnionFind(N);
    LineSeg[] line = new LineSeg[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      line[i] = new LineSeg(
        new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
        new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
      );
    }

    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        if (line[i].intersect(line[j])) {
          uf.union(i, j);
        }
      }
    }
    int max = 0;
    for (int i = 0; i < N; i++) {
      if (max < uf.sz[i]) {
        max = uf.sz[i];
      }
    }
    bw.write(uf.num + "\n");
    bw.write(max + "\n");
    bw.flush();
  }
}
