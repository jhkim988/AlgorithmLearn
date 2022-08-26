import java.io.*;
import java.util.*;

public class BOJ10216 {
  private static class Pair {
    int row, col, range;
    Pair(int row, int col, int range) {
      this.row = row;
      this.col = col;
      this.range = range;
    }
    int dist2(Pair other) {
      return (this.row-other.row)*(this.row-other.row) + (this.col-other.col)*(this.col-other.col);
    }
  }
  private static class UnionFind {
    int numGroup;
    int[] id, sz;
    UnionFind(int n) {
      this.numGroup = n;
      id = new int[n];
      sz = new int[n];
      for (int i = 0; i < n; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }

    int root(int x) {
      while (id[x] != x) {
        x = id[x] = id[id[x]];
      }
      return x;
    }

    void union(int p, int q) {
      int prt = root(p);
      int qrt = root(q);
      if (prt == qrt) return;
      numGroup--;
      if (sz[prt] < sz[qrt]) {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
      } else {
        id[qrt] = prt;
        sz[prt] += sz[qrt];
      }
    }
    int numGroup() {
      return numGroup;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int numPoint = Integer.parseInt(br.readLine());
      ArrayList<Pair> points = new ArrayList<>();
      for (int i = 0; i < numPoint; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int range = Integer.parseInt(st.nextToken());
        points.add(new Pair(row, col, range));
      }
      UnionFind uf = new UnionFind(numPoint);
      for (int pIdx = 0; pIdx < points.size(); pIdx++) {
        for (int qIdx = 0; qIdx < points.size(); qIdx++) {
          if (pIdx == qIdx) continue;
          Pair p = points.get(pIdx);
          Pair q = points.get(qIdx);
          if (p.dist2(q) > (p.range+q.range)*(p.range+q.range)) continue;
          uf.union(pIdx, qIdx);
        }
      }
      bw.write(Integer.toString(uf.numGroup()));
      bw.newLine();
    }
    bw.flush();
  }
}