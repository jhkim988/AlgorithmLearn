import java.io.*;
import java.util.*;

public class BOJ20058 {
  static Queue<Integer> que;
  static int[] rowDi = {0, 1, 0, -1}, colDi = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
    
    int size = 1 << n;
    int[][] board = new int[size][size];
    for (int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    que = new LinkedList<>();

    st = new StringTokenizer(br.readLine());
    while (q-- > 0) {
      int l = Integer.parseInt(st.nextToken());
      int subSize = 1<<l;
      for (int i = 0; i < size/subSize; i++) {
        for (int j = 0; j < size/subSize; j++) {
          rotation(board, i*subSize, j*subSize, subSize);
        }
      }
      melt(board);
    }

    int sum = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        sum += board[i][j];
      }
    }

    int big = getBig(board);

    bw.write(Integer.toString(sum));
    bw.newLine();
    bw.write(Integer.toString(big));
    bw.flush();

  }
  static void rotation(int[][] board, int row, int col, int l) {
    int[][] tmp = new int[l][l];
    for (int i = 0; i < l; i++) {
      for (int j = 0; j < l; j++) {
        tmp[i][j] = board[row+i][col+j];
      }
    }
    for (int i = 0; i < l; i++) {
      for (int j = 0; j < l; j++) {
        board[row+j][col+l-1-i] = tmp[i][j];
      }
    }
  }

  static void melt(int[][] board) {
    que.clear();
    int size = board.length;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (board[i][j] <= 0) continue;
        int numAdjIce = 0;
        for (int k = 0; k < 4; k++) {
          int nextRow = i + rowDi[k];
          int nextCol = j + colDi[k];
          if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
          if (board[nextRow][nextCol] == 0) continue;
          numAdjIce++;
        }
        if (numAdjIce <= 2) que.add(i*size+j);
      }
    }
    while (!que.isEmpty()) {
      int ptr = que.poll();
      board[ptr/size][ptr%size]--;
    }
  }

  static int getBig(int[][] board) {
    int size = board.length;
    boolean[][] visit = new boolean[size][size];

    int max = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (visit[i][j]) continue;
        if (board[i][j] == 0) continue;
        que.clear();
        int val = 1;
        visit[i][j] = true;
        que.add(i*size+j);
        while (!que.isEmpty()) {
          int ptr = que.poll();
          for (int k = 0; k < 4; k++) {
            int adjRow = ptr/size + rowDi[k];
            int adjCol = ptr%size + colDi[k];
            if (adjRow < 0 || adjRow >= size || adjCol < 0 || adjCol >= size) continue;
            if (visit[adjRow][adjCol] || board[adjRow][adjCol] == 0) continue;
            visit[adjRow][adjCol] = true;
            val++;
            que.add(adjRow*size+adjCol);
          }
        }
        if (max < val) max = val;
      }
    }
    return max;
  }
}
