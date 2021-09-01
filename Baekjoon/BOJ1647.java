import java.io.*;
import java.util.*;

public class BOJ1647 {
  private static class Edge implements Comparable<Edge> {
    // Undirected Graph
    int start;
    int end;
    int weight;
    Edge(int start, int end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }
    @Override
    public int compareTo(Edge other) {
      return Integer.compare(this.weight, other.weight);
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
    int root (int i) {
      while (i != id[i]) {
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
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<Edge> edges = new ArrayList<>();
    UnionFind uf = new UnionFind(N);
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      edges.add(new Edge(start - 1, end - 1, weight));
    }

    Collections.sort(edges);
    int max = 0;
    int sum = 0;
    for (Edge e : edges) {
      if (uf.connected(e.start, e.end)) continue;
      uf.union(e.start, e.end);
      if (max < e.weight) {
        max = e.weight;
      }
      sum += e.weight;
    }

    bw.write((sum - max) + "\n");
    bw.flush();
  }
}