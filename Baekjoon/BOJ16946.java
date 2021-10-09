import java.io.*;
import java.util.*;

public class BOJ16946 {
  private static class UnionFind {
    int id[];
    int sz[];
    UnionFind(int size) {
      id = new int[size];
      sz = new int[size];
      for (int i = 0; i < size; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }
    int root(int i) {
      while (i != id[i]) {
        id[i] = id[id[i]];
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
    boolean isConnected(int p, int q) {
      return root(p) == root(q);
    }
  }
  static int row;
  static int col;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    char[][] map = new char[row][];
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
    }
    bw.write(useUF(map));
    bw.flush();
  }
  static String useUF(char[][] map) {
    UnionFind uf = init(map);
    StringBuilder sb = new StringBuilder();
    HashSet<Integer> neighbor = new HashSet<>();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (map[i][j] == '0') {
          sb.append(0);
        } else {
          int sum = 1;
          for (int k = 0; k < 4; k++) {
            int nextRow = i + rowDi[k];
            int nextCol = j + colDi[k];
            if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
            if (map[nextRow][nextCol] == '1') continue;
            neighbor.add(uf.root(nextRow * col + nextCol));
          }
          for (int id : neighbor) {
            sum += uf.sz[id];
          }
          sb.append(sum % 10);
          neighbor.clear();
        }
      }
      sb.append('\n');
    }
    return sb.toString();
  }
  static UnionFind init(char[][] map) {
    UnionFind uf = new UnionFind(row * col);
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (map[i][j] == '1') continue;
        int originId = i * col + j;
        for (int k = 0; k < 4; k++) {
          int nextI = i + rowDi[k];
          int nextJ = j + colDi[k];
          if (nextI < 0 || nextI >= row || nextJ < 0 || nextJ >= col) continue;
          if (map[nextI][nextJ] == '1') continue;
          int nextId = nextI * col + nextJ;
          // if (uf.isConnected(originId, nextId)) continue;
          uf.union(originId, nextId);
        }
      }
    }
    return uf;
  }
}