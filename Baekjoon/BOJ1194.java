import java.io.*;
import java.util.*;

public class BOJ1194 {
  private static class Pair {
    int row, col, key, turn;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
    Pair(int row, int col, int key, int turn) {
      this(row, col);
      this.key = key;
      this.turn = turn;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] map = new char[row][];
    Pair start = new Pair(0, 0);
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
      for (int j = 0; j < col; j++) {
        if (map[i][j] == '0') {
          start.row = i;
          start.col = j;
          map[i][j] = '.';
        }
      }
    }
    bw.write(Integer.toString(bfs(row, col, map, start)));
    bw.newLine();
    bw.flush();
  }
  static int bfs(int row, int col, char[][] map, Pair start) {
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    Queue<Pair> que = new LinkedList<>();
    boolean[][][] visit = new boolean[row][col][1<<7];
    que.add(start);
    visit[start.row][start.col][0] = true;
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt.row + rowDi[k];
        int adjCol = crnt.col + colDi[k];
        int adjKey = crnt.key;
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
        if (map[adjRow][adjCol] == '#') continue;
        if ('A' <= map[adjRow][adjCol] && map[adjRow][adjCol] <= 'F') {
          int key = map[adjRow][adjCol] - 'A';
          if ((crnt.key & (1<<key)) == 0) continue;
        }
        if (map[adjRow][adjCol] == '1') return crnt.turn+1;
        if ('a' <= map[adjRow][adjCol] && map[adjRow][adjCol] <= 'f') {
          int key = map[adjRow][adjCol] - 'A';
          adjKey = adjKey | (1<<key);
        }
        if (visit[adjRow][adjCol][adjKey]) continue;
        visit[adjRow][adjCol][adjKey] = true;
        que.add(new Pair(adjRow, adjCol, adjKey, crnt.turn+1));
      }
    }
    return -1;
  }
}
