import java.io.*;
import java.util.*;

public class BOJ14868 {
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  private static class Pair {
    int id, row, col, time;
    Pair(int id, int row, int col, int time) {
      this.id = id;
      this.row = row;
      this.col = col;
      this.time = time;
    }
  }
  private static class UnionFind {
    int remainder;
    int[] id, sz;
    UnionFind(int k) {
      this.remainder = k;
      id = new int[k];
      sz = new int[k];
      for (int i = 0; i < k; i++) {
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
      int prt = root(p), qrt = root(q);
      if (prt == qrt) return;
      remainder--;
      if (sz[prt] < sz[qrt]) {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
      } else {
        id[qrt] = prt;
        sz[prt] += sz[qrt];
      }
    }
    int getRemainder() {
      return remainder;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    Queue<Pair> que = new LinkedList<>();
    int[][] world = new int[n][n];
    for (int i = 1; i <= k; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken())-1;
      int c = Integer.parseInt(st.nextToken())-1;
      que.add(new Pair(i, r, c, 0));
      world[r][c] = i;
    }

    UnionFind uf = new UnionFind(k);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (world[i][j] == 0) continue;
        for (int l = 0; l < 4; l++) {
          int adjRow = i + rowDi[l];
          int adjCol = j + colDi[l];
          if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
          if (world[adjRow][adjCol] == 0) continue;
          uf.union(world[i][j]-1, world[adjRow][adjCol]-1);
        }
      }
    }
    if (uf.getRemainder() == 1) {
      bw.write("0");
      bw.flush();
      return;
    }
    int time = 0;
    while (!que.isEmpty() && uf.getRemainder() > 1) {
      if (que.peek().time != time) time++;
      Pair p = que.poll();
      for (int i = 0; i < 4; i++) {
        int adjRow = p.row+rowDi[i];
        int adjCol = p.col+colDi[i];
        if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
        if (world[adjRow][adjCol] == 0) {
          world[adjRow][adjCol] = p.id;
          for (int j = 0; j < 4; j++) {
            int rr = adjRow + rowDi[j];
            int cc = adjCol + colDi[j];
            if (rr < 0 || rr >= n || cc < 0 || cc >= n) continue;
            if (world[rr][cc] == 0) continue;
            uf.union(world[adjRow][adjCol]-1, world[rr][cc]-1);
          }
          que.add(new Pair(p.id, adjRow, adjCol, p.time+1));
        } else {
          uf.union(p.id-1, world[adjRow][adjCol]-1);
        } 
      }
    }
    bw.write(Integer.toString(time+1));
    bw.flush();
  }
}
