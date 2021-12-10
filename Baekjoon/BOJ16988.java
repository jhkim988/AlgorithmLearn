import java.io.*;
import java.util.*;

public class BOJ16988 {
  enum STAT {EMPTY, BLACK, WHITE}
  private static class UnionFind {
    int[] id;
    int[] sz;
    STAT[] stat;
    UnionFind(int size) {
      id = new int[size];
      sz = new int[size];
      for (int i = 0; i < size; i++) {
        id[i] = i;
        sz[i] = 1;
        stat[i] = STAT.EMPTY;
      }
    }
    int root(int i) {
      while (i != id[i]) {
        i = id[i] = id[id[i]];
      }
      return i;
    }
    void union(int p, int q) {
      int pr = root(p);
      int qr = root(q);
      if (pr == qr) return;
      if (sz[pr] < sz[qr]) {
        id[pr] = qr;
        sz[qr] += sz[pr];
      } else {
        id[qr] = pr;
        sz[pr] += sz[qr];
      }
    }
    boolean isConnected(int p, int q) {
      return root(p) == root(q);
    }
  }
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numRow = Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    
    UnionFind uf = new UnionFind(numRow * numCol);
    for (int row = 0; row < numRow; row++) {
      st = new StringTokenizer(br.readLine());
      for (int col = 0; col < numCol; col++) {
        int ptr = row * numCol + row;
        int stat = Integer.parseInt(st.nextToken());
        uf.stat[ptr] = STAT.values()[stat];
        if (stat != 0) {
          for (int i = 0; i < 4; i++) {
            int nextRow = row + rowDi[i];
            int nextCol = col + colDi[i];
            int nextPtr = nextRow * numCol + nextCol;
            if (uf.stat[nextPtr] == STAT.values()[stat]) {
              uf.union(ptr, nextPtr);
            }
          }
        }
      }
    }

    HashMap<Integer, ArrayList<Pair>> groups = new HashMap<>();
    for (int row = 0; row < numRow; row++) {
      for (int col = 0; col < numCol; col++) {
        int ptr = row * numCol + col;
        if (uf.stat[ptr] != STAT.WHITE) continue;
        int root = uf.root(ptr);
        if (groups.containsKey(root)) {
          groups.get(root).add(new Pair(row, col));
        } else {
          ArrayList<Pair> g =  new ArrayList<>();
          g.add(new Pair(row, col));
          groups.put(root, g);
        }
      }
    }

    outer: for (ArrayList<Pair> group : groups.values()) {
      boolean[][] marked = new boolean[numRow][numCol];
      int numEmptyNeibor = 0;
      for (Pair p : group) {
        if (numEmptyNeibor > 2) continue outer;
        for (int i = 0; i < 4; i++) {
          int nextRow = p.row + rowDi[i];
          int nextCol = p.col + colDi[i];
          if (marked[nextRow][nextCol]) continue;
          marked[nextRow][nextCol] = true;
          numEmptyNeibor++;
        }
      }
    }
  }
}
