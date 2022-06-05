import java.io.*;
import java.util.*;

public class BOJ3187 {
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] map = new char[row][];
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
    }

    int totalSheep = 0, totalWolf = 0;
    int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    boolean[][] visit = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (visit[i][j] || map[i][j] == '#') continue;
        int sheep = 0, wolf = 0;
        if (map[i][j] == 'v') wolf++;
        if (map[i][j] == 'k') sheep++;
        visit[i][j] = true;
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(i, j));
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int adjRow = crnt.row + rowDi[k];
            int adjCol = crnt.col + colDi[k];
            if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
            if (visit[adjRow][adjCol] || map[adjRow][adjCol] == '#') continue;
            if (map[adjRow][adjCol] == 'v') wolf++;
            if (map[adjRow][adjCol] == 'k') sheep++;
            visit[adjRow][adjCol] = true;
            que.add(new Pair(adjRow, adjCol)); 
          }
        }
        if (sheep > wolf) totalSheep += sheep;
        else totalWolf += wolf;
      }
    }
    bw.write(Integer.toString(totalSheep));
    bw.write(' ');
    bw.write(Integer.toString(totalWolf));
    bw.flush();
  }
}
