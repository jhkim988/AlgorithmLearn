import java.io.*;
import java.util.*;

public class BOJ1197 {
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
      while (i != id[i]) {
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
  private static class Edge implements Comparable<Edge> {
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
      return this.weight - other.weight;
    }    
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int V = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());
    ArrayList<Edge> edges = new ArrayList<>();
    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      edges.add(new Edge(start, end, weight));
    }

    // Kruskal ALgorithm
    Collections.sort(edges);
    int sum = 0;
    UnionFind uf = new UnionFind(V);
    for (int i = 0; i < E; i++) {
      Edge edge = edges.get(i);
      if (uf.connected(edge.start - 1, edge.end - 1)) {
        continue;
      }
      uf.union(edge.start - 1, edge.end - 1);
      sum += edge.weight;
    }

    bw.write(sum + "\n");
    bw.flush();
  }  
}
