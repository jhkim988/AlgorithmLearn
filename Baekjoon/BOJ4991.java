import java.io.*;
import java.util.*;

public class BOJ4991 {
  static final int MAX = 20*20*10;
  static int[] rowDi = {0, -1, 0, 1};
  static int[] colDi = {-1, 0, 1, 0};
  private static class Pair {
    int row, col, move;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
    Pair(int row, int col, int move) {
      this(row, col);
      this.move = move;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numCol = Integer.parseInt(st.nextToken());
    int numRow = Integer.parseInt(st.nextToken());

    testCase: while (numCol != 0 && numRow != 0) {
      char[][] room = new char[numRow][numCol];
      Pair start = null;
      ArrayList<Pair> place = new ArrayList<>();
      for (int i = 0; i < numRow; i++) {
        room[i] = br.readLine().toCharArray();
        for (int j = 0; j < numCol; j++) {
          if (room[i][j] == 'o') start = new Pair(i, j);
          else if (room[i][j] == '*') place.add(new Pair(i, j));
        }
      }
      place.add(start); // last = start
      int numNode = place.size(); // start + dirty
      int[][] dist = new int[numNode][numNode];
      for (int idx = 0; idx < numNode; idx++) {
        if (!bfs(numRow, numCol, room, idx, place, dist)) {
          bw.write("-1\n");
          st = new StringTokenizer(br.readLine());
          numCol = Integer.parseInt(st.nextToken());
          numRow = Integer.parseInt(st.nextToken());
          continue testCase;
        }
      }
      int minCost = dfs(dist, numNode - 1, 1 << (numNode - 1));
      bw.write(minCost + "\n");
      st = new StringTokenizer(br.readLine());
      numCol = Integer.parseInt(st.nextToken());
      numRow = Integer.parseInt(st.nextToken());
    }
    bw.flush();
  }
  static boolean bfs(int numRow, int numCol, char[][] room, int idx, ArrayList<Pair> place, int[][] dist) {
    // initialize
    Pair start = place.get(idx);
    int[][] visit = new int[numRow][numCol];
    for (int i = 0; i < numRow; i++) Arrays.fill(visit[i], MAX);
    visit[start.row][start.col] = -1;
    Queue<Pair> que = new LinkedList<>();
    que.offer(start);
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int i = 0; i < 4; i++) {
        int adjRow = crnt.row + rowDi[i];
        int adjCol = crnt.col + colDi[i];
        if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
        if (visit[adjRow][adjCol] < MAX) continue;
        if (room[adjRow][adjCol] == 'x') continue;
        visit[adjRow][adjCol] = crnt.move + 1;
        que.offer(new Pair(adjRow, adjCol, crnt.move + 1));
      }
    }
    for (int i = 0; i < place.size(); i++) {
      if (i == idx) continue;
      Pair p = place.get(i);
      dist[idx][i] = visit[p.row][p.col];
      if (dist[idx][i] >= MAX) return false;
    }
    return true;
  }
  static int dfs(int[][] dist, int idx, int stat) {
    if (stat == (1 << dist.length) - 1) return 0;
    int minCost = MAX;
    for (int i = 0; i < dist.length; i++) {
      if ((stat & (1 << i)) != 0) continue;
      minCost = Math.min(minCost, dfs(dist, i, stat | (1 << i)) + dist[idx][i]);
    }
    return minCost;
  }
}
