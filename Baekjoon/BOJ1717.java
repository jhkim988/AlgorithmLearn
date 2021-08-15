import java.io.*;
import java.util.*;

public class BOJ1717 {
  private static class WeightedQuickUnionUF {
    int[] id;
    int[] sz;

    WeightedQuickUnionUF(int N) {
      id = new int[N];
      sz = new int[N];
      for (int i = 0; i < N; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }

    private int root(int i) {
      while(id[i] != i) {
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
    WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N + 1);
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int operator = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (operator == 0) {
        uf.union(a, b);
      } else {
        if (uf.connected(a, b)) {
          bw.write("YES\n");
        } else {
          bw.write("NO\n");
        }
      }
    }
    bw.flush();
  }
}
