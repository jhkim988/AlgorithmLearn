import java.io.*;
import java.util.*;

public class BOJ16918 {
  static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
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
    int n = Integer.parseInt(st.nextToken());

    // state[i][j]: 폭탄이 설치된 시간.
    int[][] state = new int[row][col];
    for (int i = 0; i < row; i++) {
      char[] input = br.readLine().toCharArray();
      for (int j = 0; j < col; j++) {
        state[i][j] = input[j] == 'O' ? 1 : 0;
      }
    }

    Queue<Pair> que = new LinkedList<>();

    int time = 3;
    while (time <= n+1) {
      if (time % 2 != 0) {
        // 폭탄 설치:
        for (int i = 0; i < row; i++) {
          for (int j = 0; j < col; j++) {
            if (state[i][j] == 0) state[i][j] = time;
          }
        }
      } else {
      // 폭파:
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (state[i][j] != time-3) continue;
          que.add(new Pair(i, j));
          for (int k = 0; k < 4; k++) {
            int adjRow = i + rowDi[k];
            int adjCol = j + colDi[k];
            if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
            que.add(new Pair(adjRow, adjCol));
          } 
        }
      }
      while (!que.isEmpty()) {
        Pair p = que.poll();
        state[p.row][p.col] = 0;
      }
      }
      time++;
    }

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        bw.write(state[i][j] == 0 ? '.' : 'O');
      }
      bw.newLine();
    }
    bw.flush();
  }
}
