import java.io.*;
import java.util.*;

public class BOJ4179 {
  static final int INF = 2_000_000;
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] map = new char[row][];
    int[][] fire = new int[row][col];
    int[][] move = new int[row][col];
    for (int i = 0; i < row; i++) {
      Arrays.fill(fire[i], INF);
      Arrays.fill(move[i], INF);
    }
    Queue<Pair> firepos = new LinkedList<>();
    Pair start = new Pair(0, 0);
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
      for (int j = 0; j < col; j++) {
        if (map[i][j] == 'F') {
          firepos.add(new Pair(i, j));
          fire[i][j] = 0;
          map[i][j] = '.';
        } else if (map[i][j] == 'J') {
          start.row = i;
          start.col = j;
          map[i][j] = '.';
          move[i][j] = 0;
        }
      }
    }
    while (!firepos.isEmpty()) {
      Pair crnt = firepos.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt.row + rowDi[k];
        int adjCol = crnt.col + colDi[k];
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
        if (fire[adjRow][adjCol] != INF) continue;
        if (map[adjRow][adjCol] != '.') continue;
        fire[adjRow][adjCol] = fire[crnt.row][crnt.col] + 1;
        firepos.add(new Pair(adjRow, adjCol));
      }
    }
    // System.out.println("fire");
    // print(fire);
    Queue<Pair> bfsque = new LinkedList<>();
    bfsque.add(start);
    while (!bfsque.isEmpty()) {
      Pair crnt = bfsque.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt.row + rowDi[k];
        int adjCol = crnt.col + colDi[k];
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) {
          bw.write(Integer.toString(move[crnt.row][crnt.col] + 1));
          bw.newLine();
          bw.flush();
          // System.out.println("move");
          // print(move);
          return;
        }
        if (map[adjRow][adjCol] != '.') continue;
        if (move[adjRow][adjCol] != INF) continue;
        if (move[crnt.row][crnt.col] + 1 >= fire[adjRow][adjCol]) continue;
        move[adjRow][adjCol] = move[crnt.row][crnt.col] + 1;
        bfsque.add(new Pair(adjRow, adjCol));
      }
    }
    bw.write("IMPOSSIBLE\n");
    bw.flush();
    // System.out.println("move");
    // print(move);
  } 
  static void print(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        if (arr[i][j] == INF) System.out.print('-');
        else System.out.print(arr[i][j]);
      }
      System.out.println();
    }
  }
}
