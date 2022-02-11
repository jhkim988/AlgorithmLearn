import java.io.*;
import java.util.*;

public class BOJ1922 {
  private static class Pair {
    int start, end, cost;
    Pair(int start, int end, int cost) {
      this.start = start;
      this.end = end;
      this.cost = cost;
    }
  }
  private static class UnionFind {
    int[] id, sz;
    UnionFind(int n) {
      id = new int[n];
      sz = new int[n];
      for (int i = 0; i < n; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }
    int root(int i) {
      while (id[i] != i) {
        i = id[i] = id[id[i]];
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
    boolean isConnected(int p, int q) {
      return root(p) == root(q);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());
    Pair[] edge = new Pair[m];
    for (int i = 0; i < m; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      edge[i] = new Pair(start, end, cost);
    }
    Arrays.sort(edge, (a, b) -> a.cost - b.cost);
    UnionFind uf = new UnionFind(n+1);
    int cost = 0;
    for (Pair e : edge) {
      if (uf.isConnected(e.start, e.end)) continue;
      cost += e.cost;
      uf.union(e.start, e.end);
    }

    bw.write(Integer.toString(cost));
    bw.newLine();
    bw.flush();
  }
}
