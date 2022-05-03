import java.io.*;
import java.util.*;

public class BOJ1726 {
  private static class Info {
    int row, col, direction, move;
    Info(int row, int col, int direction) {
      this.row = row;
      this.col = col;
      this.direction = direction;
    }
    Info(int row, int col, int direction, int move) {
      this(row, col, direction);
      this.move = move;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int[][] map = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    st = new StringTokenizer(br.readLine());
    int sr = Integer.parseInt(st.nextToken())-1;
    int sc = Integer.parseInt(st.nextToken())-1;
    int sd = Integer.parseInt(st.nextToken())-1;
    Info start = new Info(sr, sc, sd);
    st = new StringTokenizer(br.readLine());
    int tr = Integer.parseInt(st.nextToken())-1;
    int tc = Integer.parseInt(st.nextToken())-1;
    int td = Integer.parseInt(st.nextToken())-1;
    Info target = new Info(tr, tc, td);

    int[] rowDi = {0, 0, 1, -1};
    int[] colDi = {1, -1, 0, 0};
    Queue<Info> que = new LinkedList<>();
    boolean[][][] visit = new boolean[row][col][4];
    visit[sr][sc][sd] = true;
    que.add(start);
    while (!que.isEmpty()) {
      Info crnt = que.poll();
      if (crnt.row == target.row && crnt.col == target.col && crnt.direction == target.direction) {
        bw.write(Integer.toString(crnt.move));
        bw.flush();
        return;
      }
      // turn
      for (int d = 0; d <= 3; d++) {
        if (crnt.direction < 2 && d < 2) continue;
        if (crnt.direction >= 2 && d >= 2) continue;
        if (visit[crnt.row][crnt.col][d]) continue;
        visit[crnt.row][crnt.col][d] = true;
        que.add(new Info(crnt.row, crnt.col, d, crnt.move+1));
      }
      // go
      for (int dist = 1; dist <= 3; dist++) {
        int nextRow = crnt.row + rowDi[crnt.direction]*dist;
        int nextCol = crnt.col + colDi[crnt.direction]*dist;
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) break;
        if (map[nextRow][nextCol] == 1) break;
        if (visit[nextRow][nextCol][crnt.direction]) continue;
        visit[nextRow][nextCol][crnt.direction] = true;
        que.add(new Info(nextRow, nextCol, crnt.direction, crnt.move+1));
      }
    }
  }
}
