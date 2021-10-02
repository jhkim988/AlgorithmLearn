import java.io.*;
import java.util.*;

public class BOJ2146 {
  static int size;
  static int[][] map;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  private static class Pair {
    int row;
    int col;
    int dist;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
      dist = 0;
    }
    Pair(int row, int col, int dist) {
      this(row, col);
      this.dist = dist;
    }
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (this.getClass() != o.getClass()) return false;
      Pair other = (Pair) o;
      if (this.row == other.row && this.col == other.col) return true;
      return false;
    }
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (row ^ (row >>> 32));
      result = prime * result + (int) (col ^ (col >>> 32));
      return result;
    }
  }
  private static class Land {
    int mapIdx = 0;
    // HashSet<Pair> inner;
    HashSet<Pair> boundary;
    Land() {
      // this.inner = new HashSet<>();
      this.boundary = new HashSet<>();    
    }
    // void getBoundary() {
    //   for (Pair node : inner) {
    //     for (int i = 0; i < 4; i++) {
    //       int nextRow = node.row + rowDi[i];
    //       int nextCol = node.col + colDi[i];
    //       if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
    //       Pair next = new Pair(nextRow, nextCol);
    //       if (map[nextRow][nextCol] == 0 && !boundary.contains(next)) {
    //         boundary.add(next);
    //         map[nextRow][nextCol] = -1;
    //       }
    //     }
    //   }
    // }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    size = Integer.parseInt(br.readLine());
    map = new int[size][size];
    for (int i = 0; i < size; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // Find Land:
    ArrayList<Land> land = findLand();
    // for (Land lnd : land) {
    //   lnd.getBoundary();
    // }

    bw.write(minBridge(land) + "\n");
    bw.flush();

    // printMap();
  }
  static ArrayList<Land> findLand() {
    ArrayList<Land> land = new ArrayList<Land>();
    boolean[][] marked = new boolean[size][size];
    int landIdx = -1;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (map[i][j] <= 0) continue;
        if (marked[i][j]) continue;
        // Use BFS:
        landIdx++;
        land.add(new Land());
        Queue<Pair> que = new LinkedList<>();
        Pair start = new Pair(i, j);
        marked[i][j] = true;
        que.add(start);
        // land.get(landIdx).inner.add(start);
        land.get(landIdx).mapIdx = landIdx + 1;
        map[i][j] = landIdx + 1;
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int nextRow = crnt.row + rowDi[k];
            int nextCol = crnt.col + colDi[k];
            if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
            if (marked[nextRow][nextCol]) continue;
            Pair next = new Pair(nextRow, nextCol);
            if (map[nextRow][nextCol] == 0) {
              map[nextRow][nextCol] = -1;
              land.get(landIdx).boundary.add(next);
              continue;
            }
            if (map[nextRow][nextCol] < 0) continue;
            // land.get(landIdx).inner.add(next);
            que.add(next);
            marked[nextRow][nextCol] = true;
            map[nextRow][nextCol] = landIdx + 1;
          }
        }
      }
    }
    return land;
  }
  static int minBridge(ArrayList<Land> land) {
    int numLand = land.size();
    int min = 200;
    for (int i = 0; i < numLand; i++) {
      Land lnd = land.get(i);
      for (Pair boundary : lnd.boundary) {
        Queue<Pair> que = new LinkedList<>();
        boolean[][] marked = new boolean[size][size];
        que.add(boundary);
        marked[boundary.row][boundary.col] = true;
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          if (map[crnt.row][crnt.col] > 0 && map[crnt.row][crnt.col] != lnd.mapIdx) {
            if (crnt.dist < min) {
              min = crnt.dist;
              break;
            }
          }
          if (crnt.dist >= min) {
            break;
          }
          for (int k = 0; k < 4; k++) {
            int nextRow = crnt.row + rowDi[k];
            int nextCol = crnt.col + colDi[k];
            if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
            if (marked[nextRow][nextCol]) continue;
            marked[nextRow][nextCol] = true;
            que.add(new Pair(nextRow, nextCol, crnt.dist + 1));
          }
        }
      }      
    }
    return min;
  }
  static void printMap() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.printf("%3d ", map[i][j]);
      }
      System.out.println();
    }
  }
}
