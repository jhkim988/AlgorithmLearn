import java.io.*;
import java.util.*;

public class BOJ2887 {
  private static Point[] points;
  private static class Point {
    static int count = 0;
    long x;
    long y;
    long z;
    int id;
    Point(long x, long y, long z) {
      this.x = x;
      this.y = y;
      this.z = z;
      id = count++;
    }
    long dist(Point other) {
      return Math.min(Math.abs(this.x - other.x), Math.min(Math.abs(this.y - other.y), Math.abs(this.z - other.z)));
    }
  }
  private static class Edge implements Comparable<Edge> {
    Point start;
    Point end;
    long dist;
    Edge(Point start, Point end) {
      this.start = start;
      this.end = end;
      this.dist = start.dist(end);
    }
    @Override
    public int compareTo(Edge other) {
      return Long.compare(this.dist, other.dist);
    }
  }
  private static class UnionFind {
    int[] id;
    int[] sz;
    UnionFind(int N) {
      id = new int[N];
      sz = new int[N];
      for (int i = 0; i < N; i++) {
        id[i] = i;
        sz[i] = i;
      }
    }
    int root(int i) {
      while(i != id[i]) {
        id[i] = id[id[i]];
        i = id[i];
      }
      return i;
    }
    void union(int p, int q) {
      int prt = root(p);
      int qrt = root(q);
      if (prt == qrt) return;
      if (sz[prt] < sz[qrt]) {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
      } else {
        id[qrt] = prt;
        sz[prt] += sz[qrt];
      }
    }
    boolean connected(int p, int q) {
      return root(p) == root(q);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    points = new Point[N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      points[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
    }
    ArrayList<Edge> edges = new ArrayList<>();
    // Too Many Edges...
    // for (int i = 1; i <= N; i++) {
    //   for (int j = i + 1; j <= N; j++) {
    //     edges.add(new Edge(i, j));
    //   }
    // }
    ArrayList<Comparator<Point>> comparators = new ArrayList<>();
    comparators.add(new Comparator<Point>(){
      @Override
      public int compare(Point p1, Point p2) {
        return Long.compare(p1.x, p2.x);
      }
    });
    comparators.add(new Comparator<Point>(){
      @Override
      public int compare(Point p1, Point p2) {
        return Long.compare(p1.y, p2.y);
      }
    });
    comparators.add(new Comparator<Point>(){
      @Override
      public int compare(Point p1, Point p2) {
        return Long.compare(p1.z, p2.z);
      }
    });

    for (Comparator<Point> cmp : comparators) {
      Arrays.sort(points, cmp);
      for (int i = 1; i < N; i++) {
        edges.add(new Edge(points[i - 1], points[i]));
      }
    }

    Collections.sort(edges);
    long sum = 0;
    UnionFind uf = new UnionFind(N);
    for (Edge e : edges) {
      if (uf.connected(e.start.id, e.end.id)) continue;
      uf.union(e.start.id, e.end.id);
      sum += e.dist;
    }
    bw.write(sum + "\n");
    bw.flush();
  }
}
