import java.io.*;
import java.util.*;

public class BOJ15683 {
  private static int MAX_DEPTH;
  private static int minValue;
  private static int N;
  private static int M;
  private static int[][] board;
  private static int numWall = 0;
  private static ArrayList<Pair> cctv;
  private static class Pair {
    int row;
    int col;
    int type;
    int rot = 0;
    Pair(int row, int col, int type) {
      this.row = row;
      this.col = col;
      this.type = type;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][M];
    cctv = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
        if (board[i][j] != 0 && board[i][j] != 6) {
          cctv.add(new Pair(i, j, board[i][j]));
        }
        if (board[i][j] == 6) {
          numWall++;
        }
      }
    }
    minValue = N * M;
    MAX_DEPTH = cctv.size();
    minValue -= numWall;
    // System.out.println("Before minValue: " + minValue);
    // System.out.println("MAX_DEPTH: " + MAX_DEPTH);
    recur(0);
    bw.write(minValue + "\n");
    bw.flush();
  }
  static void recur(int depth) {
    if (depth >= MAX_DEPTH) {
      int[] val = {N * M - numWall};
      int[][] tmpBoard = new int[N][M];
      for (int i = 0; i < N; i++) {
        tmpBoard[i] = board[i].clone();
      }
      for (Pair p : cctv) {
        switch (p.type) {
          case 1:
            mark(tmpBoard, p.rot, p.row, p.col, val);
            break;
          case 2:
            if (p.rot == 0) {
              mark(tmpBoard, 0, p.row, p.col, val);
              mark(tmpBoard, 2, p.row, p.col, val);
            } else {
              mark(tmpBoard, 1, p.row, p.col, val);
              mark(tmpBoard, 3, p.row, p.col, val);
            }
            break;
          case 3:
            if (p.rot == 0) {
              mark(tmpBoard, 0, p.row, p.col, val);
              mark(tmpBoard, 3, p.row, p.col, val);
            } else if (p.rot == 1) {
              mark(tmpBoard, 0, p.row, p.col, val);
              mark(tmpBoard, 1, p.row, p.col, val);
            } else if (p.rot == 2) {
              mark(tmpBoard, 1, p.row, p.col, val);
              mark(tmpBoard, 2, p.row, p.col, val);
            } else {
              mark(tmpBoard, 2, p.row, p.col, val);
              mark(tmpBoard, 3, p.row, p.col, val);
            }
            break;
          case 4:
            if (p.rot == 0) {
              mark(tmpBoard, 0, p.row, p.col, val);
              mark(tmpBoard, 2, p.row, p.col, val);
              mark(tmpBoard, 3, p.row, p.col, val);
            } else if (p.rot == 1) {
              mark(tmpBoard, 0, p.row, p.col, val);
              mark(tmpBoard, 1, p.row, p.col, val);
              mark(tmpBoard, 3, p.row, p.col, val);
            } else if (p.rot == 2) {
              mark(tmpBoard, 0, p.row, p.col, val);
              mark(tmpBoard, 1, p.row, p.col, val);
              mark(tmpBoard, 2, p.row, p.col, val);
            } else {
              mark(tmpBoard, 1, p.row, p.col, val);
              mark(tmpBoard, 2, p.row, p.col, val);
              mark(tmpBoard, 3, p.row, p.col, val);
            }
            break;
          case 5:
            for (int i = 0; i < 4; i++) {
              mark(tmpBoard, i, p.row, p.col, val);
            }            
            break;
        }
      }
      if (val[0] < minValue) {
        minValue = val[0];
      }

      // for (int i = 0; i < N; i++) {
      //   for (int j = 0; j < M; j++) {
      //     System.out.print(tmpBoard[i][j] + " ");
      //   }
      //   System.out.println();
      // }
      return;
    }

    Pair p = cctv.get(depth);
    switch (p.type) {
      case 1: case 3: case 4:
        for (int i = 0; i < 4; i++) {
          p.rot = i;
          recur(depth + 1);
        }
        break;
      case 2:
        for (int i = 0; i < 2; i++) {
          p.rot = i;
          recur(depth + 1);
        }
        break;
      case 5:
        recur(depth + 1);
        break;
      }
  }
  static void mark(int[][] tmp, int direct, int row, int col, int[] val) {
    switch (direct) {
      case 0: 
        for (int i = col; i < M; i++) {
          if (tmp[row][i] == 6) break;
          if (tmp[row][i] == -1) continue; 
          tmp[row][i] = -1;
          val[0]--;
        }
        break;
      case 1:
        for (int i = row; i < N; i++) {
          if (tmp[i][col] == 6) break;
          if (tmp[i][col] == -1) continue; 
          tmp[i][col] = -1;
          val[0]--;
        }
        break;
      case 2:
        for (int i = col; i >= 0; i--) {
          if (tmp[row][i] == 6) break;
          if (tmp[row][i] == -1) continue; 
          tmp[row][i] = -1;
          val[0]--;
        }
        break;
      case 3:
        for (int i = row; i >= 0; i--) {
          if (tmp[i][col] == 6) break;
          if (tmp[i][col] == -1) continue; 
          tmp[i][col] = -1;
          val[0]--;
        }
    }
  }
}
