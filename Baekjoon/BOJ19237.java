import java.io.*;
import java.util.*;

public class BOJ19237 {
  static int n;
  static Shark[] sharks;
  static int[] rowDi = {-1, 1, 0, 0}, colDi = {0, 0, -1, 1};
  static int[][] smell, timestamp;
  private static class Pair {
    int id, row, col, direction;
    Pair(int id, int row, int col, int direction) {
      this.id = id;
      this.row = row;
      this.col = col;
      this.direction = direction;
    }
  }
  private static class Shark {
    int id, row, col, direction;
    boolean isOut;
    int[][] priority;
    Shark(int id, int row, int col) {
      this.id = id;
      this.row = row;
      this.col = col;
      isOut = false;
      priority = new int[4][4];
    }
    Pair nextPosition() {
      Pair ret = new Pair(0, n, n, -1);
      for (int d : priority[direction]) {
        int adjRow = this.row + rowDi[d];
        int adjCol = this.col + colDi[d];
        if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
        if (smell[adjRow][adjCol] == 0) {
          ret.row = adjRow;
          ret.col = adjCol;
          ret.id = id;
          ret.direction = d;
          return ret;
        } else if (ret.id == 0 && smell[adjRow][adjCol] == id) {
          ret.row = adjRow;
          ret.col = adjCol;
          ret.id = id;
          ret.direction = d;
        }
      }
      return ret;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    sharks = new Shark[m+1];
    smell = new int[n][n];
    timestamp = new int[n][n];
    for (int i = 0; i < n; i++) Arrays.fill(timestamp[i], -1);
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        smell[i][j] = Integer.parseInt(st.nextToken());
        if (smell[i][j] == 0) continue;
        sharks[smell[i][j]] = new Shark(smell[i][j], i, j);
        timestamp[i][j] = 0;
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= m; i++) {
      sharks[i].direction = Integer.parseInt(st.nextToken())-1;
    }

    int numShark = m;
    int t = 0;
    for (int i = 1; i <= m; i++) {
      for (int j = 0; j < 4; j++) {
        st = new StringTokenizer(br.readLine());
        for (int x = 0; x < 4; x++) {
          sharks[i].priority[j][x] = Integer.parseInt(st.nextToken())-1;
        }
      }
    }

    Queue<Pair> moveQue = new LinkedList<>();
    while (numShark != 1 && t < 1000) {
      t++;
      for (int i = 1; i <= m; i++) {
        if (sharks[i].isOut) continue;
        moveQue.add(sharks[i].nextPosition());
      }
      while (!moveQue.isEmpty()) {
        Pair move = moveQue.poll();
        // System.out.println("move: " + move.id + "-> " + move.row + ", " + move.col + ", " + move.direction);
        if (smell[move.row][move.col] != 0 && smell[move.row][move.col] < move.id) {
          numShark--;
          sharks[move.id].isOut = true;
          continue;
        } else if (smell[move.row][move.col] != 0 && smell[move.row][move.col] > move.id) {
          numShark--;
          sharks[smell[move.row][move.col]].isOut = true;
        }
        smell[move.row][move.col] = move.id;
        timestamp[move.row][move.col] = t;
        sharks[move.id].row = move.row;
        sharks[move.id].col = move.col;
        sharks[move.id].direction = move.direction;
      }
      
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (timestamp[i][j] != -1 && timestamp[i][j]+k <= t) {
            smell[i][j] = 0;
          }
        }
      }
    }
    if (numShark == 1) bw.write(Integer.toString(t));
    else bw.write("-1");
    bw.flush();
  }
}