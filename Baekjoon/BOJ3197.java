import java.io.*;
import java.util.*;

public class BOJ3197 {
  static int row, col;
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  private static class Pair {
    int row, col, time;
    Pair(int row, int col, int time) {
      this.row = row;
      this.col = col;
      this.time = time;
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
    int root(int node) {
      while (node != id[node]) {
        node = id[node] = id[id[node]];
      }
      return node;
    }
    void union(int p, int q) {
      int prt = root(p);
      int qrt = root(q);
      if (prt == qrt) return;
      if (sz[prt] < sz[qrt]) {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
      } else {
        id[qrt] = id[prt];
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
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    Pair[] swan = new Pair[2];
    UnionFind uf = new UnionFind(row*col);
    Queue<Pair> waterQue = new LinkedList<>();
    char[][] lake = new char[row][col];
    for (int i = 0; i < row; i++) lake[i] = br.readLine().toCharArray();
    
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (lake[i][j] != 'X') {
          waterQue.add(new Pair(i, j, 0));
          for (int k = 0; k < 4; k++) {
            int adjRow = i + rowDi[k];
            int adjCol = j + colDi[k];
            if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
            if (lake[adjRow][adjCol] != 'X') {
              uf.union(i*col+j, adjRow*col+adjCol);
            } 
          }
        }
        if (lake[i][j] == 'L') {
          if (swan[0] == null) swan[0] = new Pair(i, j, 0);
          else swan[1] = new Pair(i, j, 0);
        }
      }
    }

    int t = 0;
    while (!uf.isConnected(swan[0].row*col+swan[0].col, swan[1].row*col+swan[1].col)) {
      while (!waterQue.isEmpty()) {
        if (waterQue.peek().time != t) {
          t++;
          break;
        }
        Pair water = waterQue.poll();
        for (int k = 0; k < 4; k++) {
          int adjRow = water.row + rowDi[k];
          int adjCol = water.col + colDi[k];
          if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
          if (lake[adjRow][adjCol] != 'X') continue;
          lake[adjRow][adjCol] = '.';
          waterQue.add(new Pair(adjRow, adjCol, water.time+1));
          for (int l = 0; l < 4; l++) {
            int rr = adjRow + rowDi[l];
            int cc = adjCol + colDi[l];
            if (rr < 0 || rr >= row || cc < 0 || cc >= col) continue;
            if (lake[rr][cc] == 'X') continue;
            uf.union(rr*col+cc, adjRow*col+adjCol);
          }
        }
      }
    }
    bw.write(Integer.toString(t));
    bw.flush();
  }
}