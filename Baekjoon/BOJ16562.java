import java.io.*;
import java.util.*;

public class BOJ16562 {
  private static class UnionFind {
    int[] id, sz, min;
    UnionFind(int n, int[] min) {
      this.id = new int[n];
      this.sz = new int[n];
      this.min = min;
      for (int i = 0; i < n; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }
    int root(int x) {
      while (x != id[x]) {
        x = id[x] = id[id[x]];
      }
      return x;
    }
    void union(int p, int q) {
      int prt = root(p);
      int qrt = root(q);
      if (prt == qrt) return;
      if (sz[prt] < sz[qrt]) {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
        min[qrt] = Integer.min(min[qrt], min[prt]);
      } else {
        id[qrt] = prt;
        sz[prt] += sz[qrt];
        min[prt] = Integer.min(min[prt], min[qrt]);
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] min = new int[n];
    for (int i = 0; i < n; i++) min[i] = Integer.parseInt(st.nextToken());
    
    UnionFind uf = new UnionFind(n, min);
    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int p = Integer.parseInt(st.nextToken())-1;
      int q = Integer.parseInt(st.nextToken())-1;
      uf.union(p, q);
    }

    int cost = 0;
    boolean[] isFriend = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (isFriend[uf.root(i)]) continue;
      cost += uf.min[uf.root(i)];
      isFriend[uf.root(i)] = true;
    }

    if (cost > k) bw.write("Oh no");
    else bw.write(Integer.toString(cost));
    bw.flush();
  }
}
