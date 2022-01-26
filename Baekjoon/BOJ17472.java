import java.io.*;
import java.util.*;

public class BOJ17472 {
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  private static class Edge {
    int start, end, weight;
    Edge(int start, int end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }
  }
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
        sz[i] = i;
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
    int numRow = Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    int[][] map = new int[numRow][numCol];
    for (int i = 0; i < numRow; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < numCol; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    ArrayList<Queue<Edge>> graph = makeGraph(map);
    bw.write(Integer.toString(mstCost(graph)));
    bw.newLine();
    bw.flush();
  }
  static ArrayList<Queue<Edge>> makeGraph(int[][] map) {
    int numRow = map.length;
    int numCol = map[0].length;

    // labeling land with id
    int numLand = 0;
    int[][] mapNew = new int[numRow][numCol];
    
    boolean[][] visit = new boolean[numRow][numCol];
    for (int r = 0; r < numRow; r++) {
      for (int c = 0; c < numCol; c++) {
        if (map[r][c] == 0) continue;
        if (visit[r][c]) continue;
        // BFS:
        mapNew[r][c] = ++numLand;
        visit[r][c] = true;
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(r, c));
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int nextRow = crnt.row + rowDi[k];
            int nextCol = crnt.col + colDi[k];
            if (nextRow < 0 || nextRow >= numRow || nextCol < 0 || nextCol >= numCol) continue;
            if (map[nextRow][nextCol] == 0) continue;
            if (visit[nextRow][nextCol]) continue;
            mapNew[nextRow][nextCol] = numLand;
            visit[nextRow][nextCol] = true;
            que.add(new Pair(nextRow, nextCol));
          }
        }
      }
    }

    // dist[i][j]: minimum dist between i-land and j-land
    final int INF = Integer.max(numRow, numCol) + 1;
    int[][] dist = new int[numLand + 1][numLand + 1];
    for (int i = 0; i <= numLand; i++) Arrays.fill(dist[i], INF);
    for (int i = 1; i <= numLand; i++) dist[i][i] = 0; 
    for (int r = 0; r < numRow; r++) {
      for (int c = 0; c < numCol; c++) {
        if (mapNew[r][c] == 0) continue;
        int crntId = mapNew[r][c];
        for (int k = 0; k < 4; k++) {
          int processId = crntId;
          int nextRow = r + rowDi[k];
          int nextCol = c + colDi[k];
          if (nextRow < 0 || nextRow >= numRow || nextCol < 0 || nextCol >= numCol) continue;
          int d = map[r][c] == 0 ? 1 : 0;
          while (0 <= nextRow && nextRow < numRow && 0 <= nextCol && nextCol < numCol) {
            if (mapNew[nextRow][nextCol] > 0 && mapNew[nextRow][nextCol] != processId) {
              // another land:
              int anotherId = mapNew[nextRow][nextCol];            
              if (d >= 2) {
                dist[processId][anotherId] = Integer.min(dist[processId][anotherId], d);
                dist[anotherId][processId] = Integer.min(dist[anotherId][processId], d);
              }
              break;
            } else if (mapNew[nextRow][nextCol] == 0) {
              d++;
            } else {
              break;
            }
            nextRow += rowDi[k];
            nextCol += colDi[k];
          }
        }
      }
    }

    ArrayList<Queue<Edge>> graph = new ArrayList<>();
    for (int i = 0; i <= numLand; i++) graph.add(new LinkedList<>());
    for (int i = 1; i <= numLand; i++) {
      for (int j = i + 1; j <= numLand; j++) {
        if (dist[i][j] == INF) continue; 
        graph.get(i).add(new Edge(i, j, dist[i][j]));
        graph.get(j).add(new Edge(j, i, dist[i][j]));
      }
    }
    return graph;
  }
  static int mstCost(ArrayList<Queue<Edge>> graph) {
    // use Kruskal Algorithm:
    int V = graph.size() - 1; // start 1 index
    UnionFind uf = new UnionFind(V + 1); // start 0 index
    
    ArrayList<Edge> allEdge = new ArrayList<>();
    for (int i = 1; i <= V; i++) {
      for (Edge e : graph.get(i)) {
        allEdge.add(e);
      }
    }

    int cost = 0;
    Collections.sort(allEdge, (a, b) -> a.weight - b.weight);
    for (Edge e : allEdge) {
      if (uf.isConnected(e.start, e.end)) continue;
      cost += e.weight;
      uf.union(e.start, e.end);
    }
    for (int i = 2; i <= V; i++) {
      if (uf.isConnected(1, i)) continue;
      return -1;
    }
    return cost;
  }
}
