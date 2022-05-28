import java.io.*;
import java.util.*;

public class BOJ3830 {
  private static class UnionFind {
    int[] id;
    long[] weight;
    UnionFind(int n) {
      this.id = new int[n];
      this.weight = new long[n];
      for (int i = 0; i < n; i++) {
        id[i] = i;
      }
    }
    void act(int i) {
      while (id[i] != id[id[i]]) {
        weight[i] += weight[id[i]];
        id[i] = id[id[i]];
      }
    }
    int root(int i) {
      while (i != id[i]) {
        weight[i] += weight[id[i]];
        i = id[i] = id[id[i]];
      }
      return i;
    }
    void union(int p, int q, int w) {
      int prt = root(p);
      int qrt = root(q);
      act(p); act(q);
      if (prt == qrt) return;
      if (w + weight[p] <= weight[q]) {
        id[prt] = qrt;
        weight[prt] = weight[q] - weight[p] - w;
      } else {
        id[qrt] = prt;
        weight[qrt] = weight[p] + w - weight[q];
      }
    }
    boolean isConnected(int p, int q) {
      return root(p) == root(q);
    }
    long weight(int i) {
      act(i);
      return weight[i];
    }
    long query(int p, int q) {
      // if (!isConnected(p, q)) -> Unknown
      return weight(q) - weight(p);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    while (n != 0 && m != 0) {
      UnionFind uf = new UnionFind(n+1);
      while (m-- > 0) {
        st = new StringTokenizer(br.readLine());
        char type = st.nextToken().charAt(0);
        if (type == '!') {
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          int w = Integer.parseInt(st.nextToken());
          uf.union(a, b, w);
        } else {
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          if (uf.isConnected(a, b)) {
            bw.write(Long.toString(uf.query(a, b)));
            bw.newLine();
          } else {
            bw.write("UNKNOWN\n");
          }
        }
      }
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
    }
    bw.flush();
  }
}
