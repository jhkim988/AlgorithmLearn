import java.io.*;
import java.util.*;

public class BOJ17142 {
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  private static class Pair {
    int row, col, move;
    Pair(int row, int col, int move) {
      this.row = row;
      this.col = col;
      this.move = move;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int size = Integer.parseInt(st.nextToken());
    int numVirus = Integer.parseInt(st.nextToken());
    int numEmpty = size * size;
    ArrayList<Pair> putVirus = new ArrayList<>();
    int[][] stat = new int[size][size];
    for (int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        int input = Integer.parseInt(st.nextToken());
        stat[i][j] = input;
        if (input != 0) numEmpty--;
        if (input == 2) putVirus.add(new Pair(i, j, 0));
      }
    }
    
    boolean[] choose = new boolean[putVirus.size()];
    int answer = recur(0, 0, numVirus, numEmpty, putVirus, choose, stat);
    bw.write((answer >= size * size ? -1 : answer) + "\n");
    bw.flush();
  }
  static int recur(int depth, int numChoose, int numVirus, int numEmpty, ArrayList<Pair> putVirus, boolean[] choose, int[][] stat) {
    if (numChoose == numVirus) {
      return bfs(numEmpty, putVirus, choose, stat);
    }
    if (depth >= putVirus.size()) {
      return Integer.MAX_VALUE;
    }
    int val1 = recur(depth + 1, numChoose, numVirus, numEmpty, putVirus, choose, stat);
    choose[depth] = true;
    int val2 = recur(depth + 1, numChoose + 1, numVirus, numEmpty, putVirus, choose, stat);
    choose[depth] = false;
    return Math.min(val1, val2);
  }
  static int bfs(int numEmpty, ArrayList<Pair> putVirus, boolean[] choose, int[][] stat) {
    if (numEmpty == 0) return 0;
    int size = stat.length;
    int[][] visit = new int[size][size];
    Queue<Pair> que = new LinkedList<>();
    for (int i = 0; i < choose.length; i++) {
      if (!choose[i]) continue;
      Pair v = putVirus.get(i);
      visit[v.row][v.col] = 1;
      que.add(v);
    }
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int i = 0; i < 4; i++) {
        int adjRow = crnt.row + rowDi[i];
        int adjCol = crnt.col + colDi[i];
        if (adjRow < 0 || adjRow >= size || adjCol < 0 || adjCol >= size) continue;
        if (visit[adjRow][adjCol] != 0) continue;
        if (stat[adjRow][adjCol] == 1) continue;
        visit[adjRow][adjCol] = crnt.move + 2;
        if (stat[adjRow][adjCol] != 2) numEmpty--;
        if (numEmpty == 0) return crnt.move + 1;
        que.add(new Pair(adjRow, adjCol, crnt.move + 1));
      }
    }
    return Integer.MAX_VALUE;
  }
}