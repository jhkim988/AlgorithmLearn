import java.io.*;
import java.util.*;

public class BOJ16724 {
  private static class UnionFind {
    int numSet;
    int[] id, sz;
    UnionFind(int n) {
      numSet = n;
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
      numSet--;
      if (sz[prt] < sz[qrt]) {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
      } else {
        id[qrt] = prt;
        sz[prt] += sz[qrt];
      }
    }
  }
  static int numRow;
  static int numCol;
  static int getPtr(int i, int j) {
    return i * numCol + j;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    numRow = Integer.parseInt(st.nextToken());
    numCol = Integer.parseInt(st.nextToken());
    char[][] map = new char[numRow][];
    for (int i = 0; i < numRow; i++) {
      map[i] = br.readLine().toCharArray();
    }

    UnionFind uf = new UnionFind(numRow * numCol);
    for (int i = 0; i < numRow; i++) {
      for (int j = 0; j < numCol; j++) {
        switch (map[i][j]) {
          case 'U': {
            if (i <= 0) continue;
            uf.union(getPtr(i, j), getPtr(i - 1, j));
            break;
          } case 'L': {
            if (j <= 0) continue;
            uf.union(getPtr(i, j), getPtr(i, j - 1));
            break;
          } case 'R': {
            if (j >= numCol) continue;
            uf.union(getPtr(i, j), getPtr(i, j + 1));
            break;
          } case 'D': {
            if (i >= numRow) continue;
            uf.union(getPtr(i, j), getPtr(i + 1, j));
            break;
          }
        }
      }
    }
    
    bw.write(Integer.toString(uf.numSet));
    bw.newLine();
    bw.flush();
  }
}
