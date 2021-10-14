import java.io.*;
import java.util.*;

public class BOJ6087 {
  static int row;
  static int col;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  static char[][] map;
  static Pair[] target;
  private static class Pair implements Comparable<Pair> {
    int row;
    int col;
    int numReflect;
    int direction;
    Pair(int row, int col, int numReflect, int direction) {
      this.row = row;
      this.col = col;
      this.numReflect = numReflect;
      this.direction = direction;
    }
    @Override
    public int compareTo(Pair other) {
      return this.numReflect - other.numReflect;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    col = Integer.parseInt(st.nextToken());
    row = Integer.parseInt(st.nextToken());
    map = new char[row][col];
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
    }
    target = findC();
    // bw.write(Dijkstra() + "\n");
    bw.write(ZeroOneBFS() + "\n");
    bw.flush();
  }
  static Pair[] findC() {
    int ptr = 0;
    Pair[] target = new Pair[2];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (map[i][j] == 'C') {
          target[ptr++] = new Pair(i, j, 0, -1);
        }
      }
    }
    return target;
  }
  static int Dijkstra() {
    int[][][] numReflect = new int[row][col][4];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        for (int k = 0; k < 4; k++) {
          numReflect[i][j][k] = Integer.MAX_VALUE;
        }
      }
    }
    for (int i = 0; i < 4; i++) {
      numReflect[target[0].row][target[0].col][i] = 0;
    }
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.add(target[0]);

    while (!pq.isEmpty()) {
      Pair crnt = pq.poll();
      if (crnt.row == target[1].row && crnt.col == target[1].col) {
        return crnt.numReflect;
      }
      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.row + rowDi[i];
        int nextCol = crnt.col + colDi[i];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        if (map[nextRow][nextCol] == '*') continue;
        int changeDirection = 0;
        if (crnt.direction != -1 && crnt.direction != i) changeDirection = 1; 
        if (numReflect[nextRow][nextCol][i] > crnt.numReflect + changeDirection) {
          numReflect[nextRow][nextCol][i] = crnt.numReflect + changeDirection;
          pq.add(new Pair(nextRow, nextCol, numReflect[nextRow][nextCol][i], i));
        }
      }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < 4; i++) {
      min = Math.min(min, numReflect[target[1].row][target[1].col][i]);
    }
    return min;
  }
  static int ZeroOneBFS() {
    int[][] marked = new int[row][col];
    Deque<Pair> deq = new LinkedList<>();
    deq.addLast(target[0]);
    for (int i = 0; i < row; i++) {
      Arrays.fill(marked[i], Integer.MAX_VALUE);
    }
    marked[target[0].row][target[0].col] = 0;
    while (!deq.isEmpty()) {
      Pair crnt = deq.removeFirst();
      if (crnt.row == target[1].row && crnt.col == target[1].col) {
        return crnt.numReflect;
      }
      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.row + rowDi[i];
        int nextCol = crnt.col + colDi[i];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        if (map[nextRow][nextCol] == '*') continue;
        if (marked[nextRow][nextCol] < crnt.numReflect) continue;
        if (crnt.direction == -1 || crnt.direction == i) {
          deq.addFirst(new Pair(nextRow, nextCol, crnt.numReflect, i));
        } else {
          deq.addLast(new Pair(nextRow, nextCol, crnt.numReflect + 1, i));
        }
        marked[nextRow][nextCol] = crnt.numReflect;
      }
    }
    // No Case
    return -1;
  }
}
