import java.io.*;
import java.util.*;

public class BOJ6497 {
  private static class UnionFind {
    int[] id, sz;
    UnionFind(int n) {
      this.id = new int[n];
      this.sz = new int[n];
      for (int i = 0; i < n; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }
    int root(int p) {
      while (p != id[p]) {
        p = id[p] = id[id[p]];
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
    boolean isConnected(int p, int q) {
      return root(p) == root(q);
    }
  }
  private static class Edge implements Comparable<Edge> {
    int start, end, weight;
    Edge(int start, int end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }
    @Override
    public int compareTo(Edge other) {
      return this.weight-other.weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    while (v != 0 && e != 0) {
      int totalSum = 0;
      UnionFind uf = new UnionFind(v);
      Edge[] edges = new Edge[e];
      for (int i = 0; i < e; i++) {
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());
        edges[i] = new Edge(start, end, weight);
        totalSum += weight;
      }
      Arrays.sort(edges);
      int mstCost = 0;
      for (int i = 0; i < e; i++) {
        if (uf.isConnected(edges[i].start, edges[i].end)) continue;
        mstCost += edges[i].weight;
        uf.union(edges[i].start, edges[i].end);
      }
      bw.write(Integer.toString(totalSum - mstCost));
      bw.newLine();
      st = new StringTokenizer(br.readLine());
      v = Integer.parseInt(st.nextToken());
      e = Integer.parseInt(st.nextToken());
    }
    bw.flush();
  }
}
