import java.io.*;
import java.util.*;

public class BOJ1743 {
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
    int numTrash = Integer.parseInt(st.nextToken());
    char[][] map = new char[row+1][col+1];
    for (int i = 0; i <= row; i++) {
      Arrays.fill(map[i], '.');
    }
    for (int i = 0; i < numTrash; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      map[r][c] = '#';
    }
    int maxSize = 0;
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    boolean[][] visit = new boolean[row+1][col+1];
    for (int i = 1; i <= row; i++) {
      for (int j = 1; j <= col; j++) {
        if (map[i][j] == '.') continue;
        if (visit[i][j]) continue;
        int size = 1;
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(i, j));
        visit[i][j] = true;
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int adjRow = crnt.row + rowDi[k];
            int adjCol = crnt.col + colDi[k];
            if (adjRow < 0 || adjRow > row || adjCol < 0 || adjCol > col) continue;
            if (map[adjRow][adjCol] == '.') continue;
            if (visit[adjRow][adjCol]) continue;
            que.add(new Pair(adjRow, adjCol));
            visit[adjRow][adjCol] = true;
            size++;
          } 
        }
        if (maxSize < size) maxSize = size;
      }
    }  
    bw.write(Integer.toString(maxSize));
    bw.newLine();
    bw.flush();
  }
}
