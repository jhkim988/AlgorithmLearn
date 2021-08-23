import java.io.*;
import java.util.*;

public class BOJ9372 {
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
    int root(int i) {
      while(i != id[i]) {
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
  private static class Edge {
    int start;
    int end;
    Edge(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int numTest = Integer.parseInt(br.readLine());
    while(numTest-- > 0) {
      st = new StringTokenizer(br.readLine());
      int V = Integer.parseInt(st.nextToken());
      int E = Integer.parseInt(st.nextToken());
      ArrayList<Edge> edges = new ArrayList<>();
      UnionFind uf = new UnionFind(V);
      for (int i = 0; i < E; i++) {
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        edges.add(new Edge(start, end));
      }
      int sum = 0;
      for (int i = 0; i < E; i++) {
        Edge edge = edges.get(i);
        if (uf.connected(edge.start - 1, edge.end - 1)) {
          continue;
        }
        uf.union(edge.start - 1, edge.end - 1);
        sum++;
      }
      bw.write(sum + "\n");
    }
    bw.flush();
  }
}
