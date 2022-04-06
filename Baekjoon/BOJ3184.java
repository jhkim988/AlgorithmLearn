import java.io.*;
import java.util.*;
public class BOJ3184 {
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

    // bfs:
    int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    int numSheep = 0, numWolf = 0;
    Queue<Pair> bfsque = new LinkedList<>();
    boolean[][] visit = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (visit[i][j]) continue;
        if (map[i][j] == '#') continue;
        int localSheep = 0, localWolf = 0;
        if (map[i][j] == 'o') localSheep++;
        else if (map[i][j] == 'v') localWolf++;
        visit[i][j] = true;
        bfsque.add(new Pair(i, j));
        while (!bfsque.isEmpty()) {
          Pair crnt = bfsque.poll();
          for (int k = 0; k < 4; k++) {
            int adjRow = crnt.row + rowDi[k];
            int adjCol = crnt.col + colDi[k];
            if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
            if (visit[adjRow][adjCol]) continue;
            if (map[adjRow][adjCol] == '#') continue;
            if (map[adjRow][adjCol] == 'o') localSheep++;
            if (map[adjRow][adjCol] == 'v') localWolf++;
            visit[adjRow][adjCol] = true;
            bfsque.add(new Pair(adjRow, adjCol));
          }
        }
        if (localSheep > localWolf) localWolf = 0;
        else localSheep = 0;
        numSheep += localSheep;
        numWolf += localWolf;
      }
    }
    bw.write(Integer.toString(numSheep));
    bw.write(' ');
    bw.write(Integer.toString(numWolf));
    bw.newLine();
    bw.flush();
  }
}
