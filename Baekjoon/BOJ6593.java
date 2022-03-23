import java.io.*;
import java.util.*;
public class BOJ6593 {
  private static class Pair {
    int level, row, col, time;
    Pair(int level, int row, int col) {
      this.level = level;
      this.row = row;
      this.col = col;
    }
    Pair(int level, int row, int col, int time) {
      this(level, row, col);
      this.time = time;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int L = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());
    int[] levelDi = {-1, 0, 0, 1, 0, 0};
    int[] rowDi = {0, -1, 0, 0, 1, 0};
    int[] colDi = {0, 0, -1, 0, 0, 1};
    nextTest: while (L != 0 && R != 0 && C != 0) {
      char[][][] map = new char[L][R][C];
      for (int i = 0; i < L; i++) {
        for (int j = 0; j < R; j++) {
          map[i][j] = br.readLine().toCharArray();
        }
        br.readLine();
      }
      Pair start = new Pair(0, 0, 0);
      Pair end = new Pair(0, 0, 0);
      for (int i = 0; i < L; i++) {
        for (int j = 0; j < R; j++) {
          for (int k = 0; k < C; k++) {
            if (map[i][j][k] == '.' || map[i][j][k] == '#') continue;
            else if (map[i][j][k] == 'S') {
              start.level = i;
              start.row = j;
              start.col = k;
            } else {
              end.level = i;
              end.row = j;
              end.col = k;
            }
            map[i][j][k] = '.';
          }
        }
      }
      boolean[][][] visit = new boolean[L][R][C];
      Queue<Pair> que = new LinkedList<>();
      visit[start.level][start.row][start.col] = true;
      que.add(start);
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        for (int k = 0; k < 6; k++) {
          int adjLevel = crnt.level + levelDi[k];
          int adjRow = crnt.row + rowDi[k];
          int adjCol = crnt.col + colDi[k];
          if (adjLevel < 0 || adjLevel >= L || adjRow < 0 || adjRow >= R || adjCol < 0 || adjCol >= C) continue;
          if (visit[adjLevel][adjRow][adjCol]) continue;
          if (map[adjLevel][adjRow][adjCol] != '.') continue;
          if (adjLevel == end.level && adjRow == end.row && adjCol == end.col) {
            bw.write("Escaped in ");
            bw.write(Integer.toString(crnt.time + 1));
            bw.write(" minute(s).");
            bw.newLine();
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            continue nextTest;
          }
          visit[adjLevel][adjRow][adjCol] = true;
          que.add(new Pair(adjLevel, adjRow, adjCol, crnt.time + 1));
        }
      }
      bw.write("Trapped!");
      bw.newLine();
      st = new StringTokenizer(br.readLine());
      L = Integer.parseInt(st.nextToken());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
    }
    bw.flush();
  }
}