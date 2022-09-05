import java.io.*;
import java.util.*;

public class BOJ2933 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int nrow, ncol;
  static char[][] cave;
  static int[] rowDi = {-1, 0, 1, 0}, colDi= {0, -1, 0, 1}; 
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    nrow = Integer.parseInt(st.nextToken());
    ncol = Integer.parseInt(st.nextToken());
    cave = new char[nrow][ncol];
    for (int i = 0; i < nrow; i++) {
      cave[i] = br.readLine().toCharArray();
    }

    int nquery = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());

    boolean leftTurn = true;
    while (nquery-- > 0) {
      int height = Integer.parseInt(st.nextToken());
      throwStick(height, leftTurn);
      down();
      leftTurn = !leftTurn;
    }
    print();
  }
  static void throwStick(int height, boolean leftTurn) {
    int moveDirection = leftTurn ? 1 : -1;
    for (int x = leftTurn ? 0 : ncol-1; x < ncol && x >= 0; x += moveDirection) {
      if (cave[nrow-height][x] == '.') continue;
      cave[nrow-height][x] = '.';
      break;
    }        
  }
  static void down() {
    Queue<Pair> que = new LinkedList<>();
    int[][] visit = new int[nrow][ncol];
    int code = 1;
    for (int i = nrow-1; i >= 0; i--) {
      for (int j = ncol-1; j >= 0; j--) {
        if (cave[i][j] == '.' || visit[i][j] != 0) continue;
        // bfs:
        visit[i][j] = code;
        que.add(new Pair(i, j));
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int k = 0; k < 4; k++) {
            int adjRow = crnt.row + rowDi[k];
            int adjCol = crnt.col + colDi[k];
            if (adjRow < 0 || adjRow >= nrow || adjCol < 0 || adjCol >= ncol) continue;
            if (visit[adjRow][adjCol] != 0 || cave[adjRow][adjCol] == '.') continue;
            visit[adjRow][adjCol] = code;
            que.add(new Pair(adjRow, adjCol));
          }
        }
        int min = findMin(visit, code);
        if (min != 1) {
          move(visit, code, min-1);
          return;
        }
        code++;
      }
    }
  }
  static int findMin(int[][] visit, int code) {
    int min = nrow;
    for (int j = 0; j < ncol; j++) {
      int last = 0;
      boolean findCode = false;
      boolean findButton = false;
      for (int i = 0; i < nrow; i++) {
        if (visit[i][j] == code) {
          findCode = true;
          last = i;
        }
        if (findCode && visit[i][j] != code && cave[i][j] == 'x') {
          findButton = true;
          if (i-last < min) min = i-last;
        }
      }
      if (findCode && !findButton) min = Integer.min(min, nrow-last);
    }
    return min;
  }
  static void move(int[][] visit, int code, int numDown) {
    for (int i = 0; i < nrow; i++) {
      for (int j = 0; j < ncol; j++) {
        if (visit[i][j] == code) cave[i][j] = '.';
      }
    }
    for (int i = 0; i < nrow; i++) {
      for (int j = 0; j < ncol; j++) {
        if (visit[i][j] == code) cave[i+numDown][j] = 'x';
      }
    }
  }
  static void print() throws IOException {
    bw.write(cave[0]);
    for (int i = 1; i < nrow; i++) {
      bw.newLine();
      bw.write(cave[i]);
    }
    bw.flush();
  }
}