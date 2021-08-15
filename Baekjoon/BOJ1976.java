import java.io.*;
import java.util.*;

public class BOJ1976 {
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
    private int root(int i) {
      while (id[i] != i) {
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
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    UnionFind uf = new UnionFind(N + 1);
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        if (Integer.parseInt(st.nextToken()) == 1) {
          uf.union(i, j);
        }
      }
    }
    st = new StringTokenizer(br.readLine());
    int first = Integer.parseInt(st.nextToken());
    for (int i = 1; i < M; i++) {
      int city = Integer.parseInt(st.nextToken());
      if (!uf.connected(first, city)) {
        bw.write("NO");
        bw.flush();
        return;
      }
    }
    bw.write("YES");
    bw.flush();
    return;
  }
}
