import java.io.*;
import java.util.*;

public class BOJ16932 {
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
    private UnionFind(int n, boolean inner) {
      id = new int[n];
      sz = new int[n];
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
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numRow = Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    int[][] data = new int[numRow][numCol];
    ArrayList<Integer> ones = new ArrayList<>();
    UnionFind uf = new UnionFind(numRow * numCol);
    for (int row = 0; row < numRow; row++) {
      st = new StringTokenizer(br.readLine());
      for (int col = 0; col < numCol; col++) {
        data[row][col] = Integer.parseInt(st.nextToken());
        if (data[row][col] == 0) continue;
        ones.add(row * numCol + col);
        for (int i = 0; i < 4; i++) {
          int adjRow = row + rowDi[i];
          int adjCol = col + colDi[i];
          if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
          if (data[adjRow][adjCol] == 0) continue;
          uf.union(row * numCol + col, adjRow * numCol + adjCol); 
        }
      }
    }
    
    HashSet<Integer> candi = new HashSet<>();
    for (int ptr : ones) {
      for (int i = 0; i < 4; i++) {
        int row = ptr / numCol + rowDi[i];
        int col = ptr % numCol + colDi[i];
        if (row < 0 || row >= numRow || col < 0 || col >= numCol) continue;
        if (data[row][col] == 1) continue;
        candi.add(row * numCol + col);
      }
    }

    int max = 0;
    HashSet<Integer> adj = new HashSet<>();
    for (int ptr : candi) {
      int row = ptr / numCol;
      int col = ptr % numCol;
      for (int i = 0; i < 4; i++) {
        int adjRow = row + rowDi[i];
        int adjCol = col + colDi[i];
        if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
        if (data[adjRow][adjCol] == 0) continue;
        adj.add(uf.root(adjRow * numCol + adjCol));
      }
      int sum = 1;
      for (int adjPtr : adj) {
        sum += uf.sz[adjPtr];
      }
      max = Math.max(max, sum);
      // System.out.println("(" + row + ", " + col + "): " + sum);
      adj.clear();
    }

    bw.write(max + "\n");
    bw.flush();
  }
}
