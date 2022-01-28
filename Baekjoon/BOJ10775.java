import java.io.*;

public class BOJ10775 {
  private static class UnionFind {
    int[] id, sz, mn;
    UnionFind(int n) {
      id = new int[n];
      sz = new int[n];
      mn = new int[n];
      for (int i = 0; i < n; i++) {
        id[i] = i;
        sz[i] = 1;
        mn[i] = i;
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
      if (sz[prt] > sz[qrt]) {
        id[qrt] = prt;
        sz[prt] += sz[qrt];
        mn[prt] = Integer.min(mn[prt], mn[qrt]);
      } else {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
        mn[qrt] = Integer.min(mn[prt], mn[qrt]);
      }
    }
    // boolean isConnected(int p, int q) {
    //   return root(p) == root(q);
    // }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numGate = Integer.parseInt(br.readLine());
    int numPlain = Integer.parseInt(br.readLine());
    int count = 0;
    UnionFind uf = new UnionFind(numGate + 1);
    boolean[] mark = new boolean[numGate + 1];
    for (int i = 0; i < numPlain; i++) {
      int gate = Integer.parseInt(br.readLine());
      if (mark[gate]) {
        int min = uf.mn[uf.root(gate)] - 1;
        if (min <= 0) break;
        mark[min] = true;
        uf.union(min, min + 1);
        if (min > 0 && mark[min - 1]) uf.union(min - 1, min);
      } else {
        mark[gate] = true;
        if (gate > 0 && mark[gate - 1]) uf.union(gate - 1, gate);
        if (gate < numGate && mark[gate + 1]) uf.union(gate, gate + 1);
      }
      count++;
    }
    bw.write(Integer.toString(count));
    bw.newLine();
    bw.flush();
  }
}
