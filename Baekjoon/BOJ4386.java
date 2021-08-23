import java.io.*;
import java.util.*;

public class BOJ4386 {
  static Point[] stars;
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
    int root(int p) {
      while (p != id[p]) {
        p = id[p];
      }
      return p;
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
  private static class Point {
    double x;
    double y;
    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
    double dist(Point other) {
      return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
  }
  private static class Edge implements Comparable<Edge> {
    int start;
    int end;
    double dist;
    Edge(int start, int end) {
      this.start = start;
      this.end = end;
      this.dist = stars[start].dist(stars[end]);
    }
    @Override
    public int compareTo(Edge other) {
      return Double.compare(this.dist, other.dist);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    stars = new Point[N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      double x = Double.parseDouble(st.nextToken());
      double y = Double.parseDouble(st.nextToken());
      stars[i] = new Point(x, y);
    }

    ArrayList<Edge> edges = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i == j) continue;
        edges.add(new Edge(i, j));
      }
    }
    Collections.sort(edges);
    UnionFind uf = new UnionFind(N);
    double sum = 0.0;
    for (Edge edge : edges) {
      if (uf.connected(edge.start, edge.end)) {
        continue;
      }
      uf.union(edge.start, edge.end);
      sum += edge.dist;
    }
    bw.write(sum + "\n");
    bw.flush();
  }  
}
