import java.io.*;
import java.util.*;

public class BOJ4963 {
  private static class Pair {
    int wid;
    int hei;
    Pair(int wid, int hei) {
      this.wid = wid;
      this.hei = hei;
    }
  }
  private static class UnionFind {
    int[] id;
    int[] sz;
    int numComponent;
    UnionFind(int numNode) {
      id = new int[numNode];
      sz = new int[numNode];
      numComponent = numNode;
      for (int i = 0; i < numNode; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }
    private int root(int i) {
      while (i != id[i]) {
        i = id[i] = id[id[i]];
      }
      return i;
    }
    void union(int p, int q) {
      int prt = root(p);
      int qrt = root(q);
      if (prt == qrt) return;
      numComponent--;
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

    int w = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());
    while (w != 0 && h != 0) {
      int[][] map = new int[h][w];
      for (int i = 0; i < h; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < w; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // int numIsland = useBFS(map);
      int numIsland = useUF(map);
      bw.write(numIsland + "\n");

      st = new StringTokenizer(br.readLine());
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());
    }
    bw.flush();
  }
  static int useBFS(int[][] map) {
    int[] xDi = {0, 1, 1, 1, 0, -1, -1, -1};
    int[] yDi = {1, 1, 0, -1, -1, -1, 0, 1};
    int numIsland = 0;
    int h = map.length;
    int w = map[0].length;
    boolean[][] marked = new boolean[h][w];
    Queue<Pair> que = new LinkedList<>();
    for (int wid = 0; wid < w; wid++) {
      for (int hei = 0; hei < h; hei++) {
        if (marked[hei][wid]) continue;
        if (map[hei][wid] == 0) continue;
        numIsland++;
        Pair start = new Pair(wid, hei);
        marked[hei][wid] = true;
        que.add(start);
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int i = 0; i < 8; i++) {
            int nextWid = crnt.wid + xDi[i];
            int nextHei = crnt.hei + yDi[i];
            if (nextWid < 0 || nextWid >= w || nextHei < 0 || nextHei >= h) continue;
            if (marked[nextHei][nextWid]) continue;
            if (map[nextHei][nextWid] == 0) continue;
            marked[nextHei][nextWid] = true;
            que.add(new Pair(nextWid, nextHei));
          }
        }
      }
    }
    return numIsland;
  }
  static int useUF(int[][] map) {
    int h = map.length;
    int w = map[0].length;

    int[] xDi = {0, 1, 1, 1, 0, -1, -1, -1};
    int[] yDi = {1, 1, 0, -1, -1, -1, 0, 1};
    UnionFind uf = new UnionFind(w * h); // map[i][j] -> i * w + j, node -> node/w, node%w
    int numZero = 0;
    for (int wid = 0; wid < w; wid++) {
      for (int hei = 0; hei < h; hei++) {
        if (map[hei][wid] == 0) {
          numZero++;
          continue;
        }
        int crntNode = hei * w + wid;
        for (int i = 0; i < 8; i++) {
          int nextWid = wid + xDi[i];
          int nextHei = hei + yDi[i];
          if (nextWid < 0 || nextWid >= w || nextHei < 0 || nextHei >= h) continue;
          if (map[nextHei][nextWid] == 0) continue;
          int nextNode = nextHei * w + nextWid;
          if (uf.isConnected(crntNode, nextNode)) continue;
          uf.union(crntNode, nextNode);
        }
      }
    }
    return uf.numComponent - numZero;
  }
}
