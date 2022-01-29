import java.io.*;
import java.util.*;

public class BOJ17619 {
  private static class Pair {
    int id, first, last;
    Pair(int id, int first, int last) {
      this.id = id;
      this.first = first;
      this.last = last;
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
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
    Pair[] logs = new Pair[n];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int first = Integer.parseInt(st.nextToken());
      int last = Integer.parseInt(st.nextToken());
      logs[i] = new Pair(i + 1, first, last);
    }
    Arrays.sort(logs, (a, b) -> a.first - b.first);

    UnionFind uf = new UnionFind(n + 1);
    int ptr1 = 0;
    int ptr2 = 0;
    while (ptr1 < n) {
      ptr2 = ptr1 + 1;
      while (ptr2 < n && logs[ptr2].first <= logs[ptr1].last) {
        uf.union(logs[ptr1].id, logs[ptr2].id);
        if (logs[ptr1].last < logs[ptr2].last) {
          ptr1 = ptr2;
        }
        ptr2++;
      }
      ptr1++;
    }
    
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int first = Integer.parseInt(st.nextToken());
      int second = Integer.parseInt(st.nextToken());
      bw.write(uf.isConnected(first, second) ? "1\n" : "0\n");
    }
    bw.flush();
  }
}
