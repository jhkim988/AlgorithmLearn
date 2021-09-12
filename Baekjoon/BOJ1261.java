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

    int[] xDi = {0, 1, 0, -1};
    int[] yDi = {1, 0, -1, 0};

    PriorityQueue<Node> que = new PriorityQueue<>();
    int[][] cost = new int[row][col];
    for (int i = 0; i < row; i++) {
      Arrays.fill(cost[i], Integer.MAX_VALUE);
    }
    Node start = new Node(0, 0, 0);
    que.add(start);
    while (!que.isEmpty()) {
      Node crnt = que.poll();

      if (crnt.row == row - 1 && crnt.col == col - 1) {
        bw.write(crnt.numBreak + "\n");
        bw.flush();
        break;
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
  }
}
