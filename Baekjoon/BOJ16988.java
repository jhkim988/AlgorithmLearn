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
      stat = new STAT[size];
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
    // boolean isConnected(int p, int q) {
    //   return root(p) == root(q);
    // }
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
        int ptr = row * numCol + col;
        int stat = Integer.parseInt(st.nextToken());
        uf.stat[ptr] = STAT.values()[stat];
        if (stat != 0) {
          for (int i = 0; i < 4; i++) {
            int nextRow = row + rowDi[i];
            int nextCol = col + colDi[i];
            if (nextRow < 0 || nextRow >= numRow || nextCol < 0 || nextCol >= numCol) continue;
            int nextPtr = nextRow * numCol + nextCol;
            if (uf.stat[nextPtr] == STAT.values()[stat]) {
              uf.union(ptr, nextPtr);
            }
          }
        }
      }
    }

    HashMap<Integer, ArrayList<Pair>> groups = new HashMap<>(); // <root ptr, group members>
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

    HashSet<Integer> allEmpty = new HashSet<>(); // candidate
    HashMap<Integer, ArrayList<Integer>> groupAdjEmpty= new HashMap<>(); // <root ptr, emptyNeibor>
    outer: for (int root : groups.keySet()) {
      ArrayList<Pair> group = groups.get(root);
      ArrayList<Integer> empty = new ArrayList<>();
      boolean[][] marked = new boolean[numRow][numCol];
      int numEmptyNeibor = 0;
      for (Pair p : group) {
        for (int i = 0; i < 4; i++) {
          int nextRow = p.row + rowDi[i];
          int nextCol = p.col + colDi[i];
          if (nextRow < 0 || nextRow >= numRow || nextCol < 0 || nextCol >= numCol) continue;
          if (marked[nextRow][nextCol]) continue;
          if (uf.stat[nextRow * numCol + nextCol] != STAT.EMPTY) continue;
          marked[nextRow][nextCol] = true;
          empty.add(nextRow * numCol + nextCol);
          numEmptyNeibor++;
          if (numEmptyNeibor > 2) continue outer;
        }
      }
      for (int emptyPtr: empty) {
        allEmpty.add(emptyPtr);
      }
      groupAdjEmpty.put(root, empty);
    }

    if (allEmpty.size() == 1) {
      HashSet<Integer> sum = new HashSet<>();
      for (int ptr : allEmpty) {
        int row = ptr/numCol;
        int col = ptr%numCol;
        for (int i = 0; i < 4; i++) {
          int adjRow = row + rowDi[i];
          int adjCol = col + colDi[i];
          if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
          int adjPtr = adjRow * numCol + adjCol;
          if (uf.stat[adjPtr] != STAT.WHITE) continue;
          int root = uf.root(adjPtr);
          if (!groupAdjEmpty.containsKey(root)) continue;
          sum.add(root);
        }
        int val = 0;
        for (int root : sum) {
          val += uf.sz[root];
        }
        bw.write(val + "\n");
        bw.flush();
        return;
      }
    }

    int max = 0;
    for (int ptr1 : allEmpty) {
      int row1 = ptr1/numCol;
      int col1 = ptr1%numCol;
      HashSet<Integer> g1 = new HashSet<>(); // For empty cell(ptr1), root of adjGroup
      for (int i = 0; i < 4; i++) {
        int adjRow = row1 + rowDi[i];
        int adjCol = col1 + colDi[i];
        if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
        int adjPtr = adjRow * numCol + adjCol;
        if (uf.stat[adjPtr] != STAT.WHITE) continue;
        int root = uf.root(adjPtr);
        if (!groupAdjEmpty.containsKey(root)) continue;
        g1.add(root);
      }
      for (int ptr2 : allEmpty) {
        if (ptr1 >= ptr2) continue;
        int row2 = ptr2/numCol;
        int col2 = ptr2%numCol;
        HashSet<Integer> g2 = new HashSet<>(); // For empty cell(ptr2), root of adjGroup
        for (int i = 0; i < 4; i++) {
          int adjRow = row2 + rowDi[i];
          int adjCol = col2 + colDi[i];
          if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
          int adjPtr = adjRow * numCol + adjCol;
          if (uf.stat[adjPtr] != STAT.WHITE) continue;
          int root = uf.root(adjPtr);
          if (!groupAdjEmpty.containsKey(root)) continue;
          g2.add(root);
        }

        HashSet<Integer> sum = new HashSet<>();
        for (int root1 : g1) {
          if (groupAdjEmpty.get(root1).size() == 1) {
            sum.add(root1);
          } else {
            if (groupAdjEmpty.get(root1).contains(ptr2)) {
              sum.add(root1);
            }
          }
        }
        for (int root2 : g2) {
          if (groupAdjEmpty.get(root2).size() == 1) {
            sum.add(root2);
          } else {
            if (groupAdjEmpty.get(root2).contains(ptr1)) {
              sum.add(root2);
            }
          }
        }
        int val = 0;
        for (int ptrSum : sum) {
          val += uf.sz[ptrSum];
        }
        if (max < val) max = val;
      }
    }

    bw.write(max + "\n");
    bw.flush();
  }
}
