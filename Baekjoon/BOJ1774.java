import java.io.*;
import java.util.*;

public class BOJ1774 {
  static Point[] points;
  private static class Point {
    long x;
    long y;
    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }
    double dist(Point other) {
      return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
  }
  private static class Edge implements Comparable<Edge> {
    // index of Point array
    int start;
    int end;
    double dist;
    Edge(int start, int end) {
      this.start = start;
      this.end = end;
      this.dist = points[start].dist(points[end]);
    }
    @Override
    public int compareTo(Edge other) {
      return Double.compare(this.dist, other.dist);
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
        sz[i] = 1;
      }
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
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    points = new Point[N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    ArrayList<Edge> edges = new ArrayList<>();
    UnionFind uf = new UnionFind(N + 1);
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      uf.union(start, end);
    }
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (i == j) continue;
        edges.add(new Edge(i, j));
      }
    }

    Collections.sort(edges);    
    double sum = 0.0;
    for (Edge e : edges) {
      if (uf.connected(e.start, e.end)) {
        continue;
      }
      uf.union(e.start, e.end);
      sum += e.dist;
    }

    bw.write(String.format("%.2f", sum) + "\n");
    bw.flush();
  }
}