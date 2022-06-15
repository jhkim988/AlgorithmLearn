import java.io.*;
import java.util.*;

public class BOJ13565 {
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
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
    int root(int x) {
      while (x != id[x]) {
        x = id[x] = id[id[x]];
      }
      return x;
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
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] grid = new char[row][col];
    for (int i = 0; i < row; i++) {
      grid[i] = br.readLine().toCharArray();
    }

    // boolean answer = bfs(row, col, grid);
    boolean answer = unionfind(row, col, grid);
    bw.write(answer ? "YES" : "NO");
    bw.flush();
  }
  static boolean bfs(int row, int col, char[][] grid) {
    boolean[][] visit = new boolean[row][col];
    Queue<Pair> que = new LinkedList<>();
    for (int j = 0; j < col; j++) {
      if (visit[0][j] || grid[0][j] == '1') continue;
      que.add(new Pair(0, j));
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        for (int k = 0; k < 4; k++) {
          int adjRow = crnt.row + rowDi[k];
          int adjCol = crnt.col + colDi[k];
          if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
          if (visit[adjRow][adjCol] || grid[adjRow][adjCol] == '1') continue;
          if (adjRow == row-1) return true;
          visit[adjRow][adjCol] = true;
          que.add(new Pair(adjRow, adjCol));
        }
      }
    }
    return false;
  }
  static boolean unionfind(int row, int col, char[][] grid) {
    UnionFind uf = new UnionFind(row*col+2);
    for (int j = 0; j < col; j++) {
      if (grid[0][j] == '0') uf.union(row*col, j);
      if (grid[row-1][j] == '0') uf.union(row*col+1, (row-1)*col + j);
    }
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == '1') continue;
        for (int k = 2; k < 4; k++) {
          int adjRow = i + rowDi[k];
          int adjCol = j + colDi[k];
          if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
          if (grid[adjRow][adjCol] == '1') continue;
          uf.union(i*col + j, adjRow*col + adjCol);
        }
      }
    }
    return uf.isConnected(row*col, row*col+1);
  }
}
