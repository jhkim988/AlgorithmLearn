import java.io.*;
import java.util.*;

public class BOJ16924 {
  static int R;
  static int C;
  static int RCmin;
  static int[] rowAdd = {-1, 0, 1, 0};
  static int[] colAdd = {0, 1, 0, -1};
  private static class Pair {
    int row;
    int col;
    int size;
    Pair(int row, int col, int size) {
      this.row = row;
      this.col = col;
      this.size = size;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    RCmin = Math.min(R, C);
    char[][] map = new char[R][];
    char[][] cmp = new char[R][C];
    for (int i = 0; i < R; i++) {
      map[i] = br.readLine().toCharArray();
      Arrays.fill(cmp[i], '.');
    }
    Queue<Pair> que = new LinkedList<>();
    for (int i = 1; i < R; i++) {
      for (int j = 1; j < C; j++) {
        if (map[i][j] == '.') continue;
        add(i, j, que, map, cmp);
      }
    }
    if (!equal(map, cmp)) {
      bw.write("-1\n");
    } else {
      StringBuilder sb = new StringBuilder();
      sb.append(que.size()).append('\n');
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        sb.append(crnt.row + 1).append(' ').append(crnt.col + 1).append(' ').append(crnt.size).append('\n');
      }
      bw.write(sb.toString());
    }
    bw.flush();
  }
  static void add(int cRow, int cCol, Queue<Pair> que, char[][] map, char[][] cmp) {
    int[] rowNext = {cRow, cRow, cRow, cRow};
    int[] colNext = {cCol, cCol, cCol, cCol};
    outerLoop: for (int size = 1; size < RCmin; size++) {
      for (int i = 0; i < 4; i++) {
        rowNext[i] += rowAdd[i];
        colNext[i] += colAdd[i];
        if (rowNext[i] < 0 || rowNext[i] >= R || colNext[i] < 0 || colNext[i] >= C) {
          break outerLoop;
        }
        if (map[rowNext[i]][colNext[i]] != '*') break outerLoop;
      }
      cmp[cRow][cCol] = '*';
      for (int i = 0; i < 4; i++) {
        cmp[rowNext[i]][colNext[i]] = '*';
      }
      // System.out.println("add: " + cRow + ", " + cCol + ", " + size);
      que.add(new Pair(cRow, cCol, size));
    }
  }
  static boolean equal(char[][] map1, char[][] map2) {
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (map1[i][j] != map2[i][j]) return false;
      }
    }
    return true;
  }
  static void print(char[][] map) {
    for (int i = 0; i < R; i++) {
      System.out.println(map[i]);
    }
  }
}
