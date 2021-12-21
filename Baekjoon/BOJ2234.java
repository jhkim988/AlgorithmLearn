import java.io.*;
import java.util.*;

public class BOJ2234 {
  private static class UnionFind {
    int[] id;
    int[] sz;
    UnionFind(int n) {
      id = new int[n];
      sz = new int[n];
      for (int i = 0; i < n; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }
    int root(int i) {
      while (i != id[i]) {
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
    int numCol = Integer.parseInt(st.nextToken());
    int numRow = Integer.parseInt(st.nextToken());
    int[][] rooms = new int[numRow][numCol];
    for (int i = 0; i < numRow; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < numCol; j++) {
        rooms[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int[] rowDi = {0, -1, 0, 1};
    int[] colDi = {-1, 0, 1, 0};
    UnionFind uf = new UnionFind(numRow * numCol);
    for (int i = 0; i < numRow; i++) {
      for (int j = 0; j < numCol; j++) {
        for (int k = 0; k < 4; k++) {
          if ((rooms[i][j] & (1 << k)) != 0) continue;
          int adjRow = i + rowDi[k];
          int adjCol = j + colDi[k];
          if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
          if (i * numCol + j >= adjRow * numCol + adjCol) continue;
          uf.union(i * numCol + j, adjRow * numCol + adjCol);
        }
      }
    }
    
    HashSet<Integer> roots = new HashSet<>();
    for (int i = 0; i < numRow; i++) {
      for (int j = 0; j < numCol; j++) {
        roots.add(uf.root(i * numCol + j));
      }
    }

    int max = 0;
    for (int i = 0; i < numRow; i++) {
      for (int j = 0; j < numCol; j++) {
        int sz = uf.sz[i * numCol + j];
        if (max < sz) max = sz;
      }
    }
    
    int breakMax = 0;
    for (int i = 0; i < numRow; i++) {
      for (int j = 0; j < numCol; j++) {
        int idx = i * numCol + j;
        for (int k = 0; k < 4; k++) {
          if ((rooms[i][j] & (1 << k)) == 0) continue;
          int adjRow = i + rowDi[k];
          int adjCol = j + colDi[k];
          if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
          int adjIdx = adjRow * numCol + adjCol;
          if (idx >= adjIdx) continue;
          if (uf.isConnected(idx, adjIdx)) continue;
          int val = uf.sz[uf.root(idx)] + uf.sz[uf.root(adjIdx)];
          if (breakMax < val) breakMax = val;
        }
      }
    }

    bw.write(roots.size() + "\n");
    bw.write(max + "\n");
    bw.write(breakMax + "\n");
    bw.flush();
  }
}
