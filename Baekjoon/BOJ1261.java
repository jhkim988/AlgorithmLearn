import java.io.*;
import java.util.*;

public class BOJ1261 {
  private static class Node implements Comparable<Node> {
    int row;
    int col;
    int numBreak;
    Node(int row, int col, int numBreak) {
      this.row = row;
      this.col = col;
      this.numBreak = numBreak;
    }
    @Override
    public int compareTo(Node other) {
      return Integer.compare(this.numBreak, other.numBreak);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int col = Integer.parseInt(st.nextToken());
    int row = Integer.parseInt(st.nextToken());
    int[][] map = new int[row][col];
    for (int i = 0; i < row; i++) {
      String tmp = br.readLine();
      for (int j = 0; j < col; j++) {
        map[i][j] = tmp.charAt(j) - '0';
      }
    }

    bw.write(Dijkstra(row, col, map) + "\n");
    // bw.write(ZeroOneBFS(row, col, map) + "\n");
    bw.flush();

  }
  static int Dijkstra(int row, int col, int[][] map) {
    int[] xDi = {0, 1, 0, -1};
    int[] yDi = {1, 0, -1, 0};

    PriorityQueue<Node> que = new PriorityQueue<>();
    int[][] cost = new int[row][col];
    for (int i = 0; i < row; i++) {
      Arrays.fill(cost[i], Integer.MAX_VALUE);
    }
    Node start = new Node(0, 0, 0);
    cost[0][0] = 0;
    que.add(start);
    while (!que.isEmpty()) {
      Node crnt = que.poll();

      if (crnt.row == row - 1 && crnt.col == col - 1) {
        return crnt.numBreak;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.row + xDi[i];
        int nextCol = crnt.col + yDi[i];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        int nextCost = map[nextRow][nextCol] == 0 ? crnt.numBreak : crnt.numBreak + 1;
        if (cost[nextRow][nextCol] <= nextCost) continue;
        que.add(new Node(nextRow, nextCol, nextCost));
        cost[nextRow][nextCol] = nextCost;
      }
    }
    return -1;
  }
  static int ZeroOneBFS(int row, int col, int[][] map) {
    int[] xDi = {0, 1, 0, -1};
    int[] yDi = {1, 0, -1, 0};

    Node start = new Node(0, 0, 0);
    Deque<Node> deq = new LinkedList<>();
    int[][] cost = new int[row][col];
    for (int i = 0; i < row; i++) {
      Arrays.fill(cost[i], Integer.MAX_VALUE);
    }
    cost[0][0] = 0;
    deq.add(start);

    while (!deq.isEmpty()) {
      Node crnt = deq.removeFirst();

      if (crnt.row == row - 1 && crnt.col == col - 1) {
        return crnt.numBreak;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.row + xDi[i];
        int nextCol = crnt.col + yDi[i];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        if (map[nextRow][nextCol] == 0) {
          if (cost[nextRow][nextCol] <= cost[crnt.row][crnt.col]) continue;
          deq.addFirst(new Node(nextRow, nextCol, cost[crnt.row][crnt.col]));
          cost[nextRow][nextCol] = cost[crnt.row][crnt.col];
        } else {
          if (cost[nextRow][nextCol] <= cost[crnt.row][crnt.col] + 1) continue;
          deq.addLast(new Node(nextRow, nextCol, cost[crnt.row][crnt.col] + 1));
          cost[nextRow][nextCol] = cost[crnt.row][crnt.col] + 1;
        }
      }
    }
    return -1;
  }
}
