import java.io.*;
import java.util.*;

public class BOJ21610 {
  static int n;
  static int[] rowDi = {0, -1, -1, -1, 0, 1, 1, 1}, colDi = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] diagRowDi = {-1, -1, 1, 1}, diagColDi = {-1, 1, -1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int[][] board = new int[n][n];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    boolean[][] cloud = new boolean[n][n];
    for (int i = n-2; i < n; i++) {
      for (int j = 0; j < 2; j++) {
        cloud[i][j] = true;
      }
    }

    Queue<Integer> que = new LinkedList<>();
    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken())-1;
      int s = Integer.parseInt(st.nextToken());
      cloud = move(cloud, d, s);
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (cloud[i][j]) {
            board[i][j]++;
            que.add(i*n+j);
          }
        }
      }
      while (!que.isEmpty()) {
        int crnt = que.poll();
        int row = crnt/n;
        int col = crnt%n;
        int num = 0;
        for (int k = 0; k < 4; k++) {
          int nextRow = row + diagRowDi[k];
          int nextCol = col + diagColDi[k];
          if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
          if (board[nextRow][nextCol] != 0) num++;
        }
        board[row][col] += num;
      }
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (cloud[i][j]) {
            cloud[i][j] = false;
          } else {
            cloud[i][j] = board[i][j] >= 2;
            if (cloud[i][j]) board[i][j] -= 2;
          }
        }
      }
    }

    int tot = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        tot += board[i][j];
      }
    }
    bw.write(Integer.toString(tot));
    bw.flush();
  }
  static boolean[][] move(boolean[][] cloudOld, int direction, int dist) {
    boolean[][] cloudNew = new boolean[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int moveRow = (i + dist*rowDi[direction] + n*100)%n;
        int moveCol = (j + dist*colDi[direction] + n*100)%n;
        cloudNew[moveRow][moveCol] = cloudOld[i][j];
      }
    }
    return cloudNew;
  }
  static void print(int[][] board) {
    System.out.println("------------------------------");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
  static void printCloud(boolean[][] cloud) {
    System.out.println("------------------------------");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(cloud[i][j] ? 'O' : 'X');
      }
      System.out.println();
    }
  }
}