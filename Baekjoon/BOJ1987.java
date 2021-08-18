import java.io.*;
import java.util.*;

public class BOJ1987 {
  static int xDi[] = {-1, 0, 1, 0};
  static int yDi[] = {0, -1, 0, 1};
  static boolean[] marked;
  static char[][] table;
  static int R, C, max;
  private static class Pair {
    int i;
    int j;
    int count;
    Pair(int i, int j, int count) {
      this.i = i;
      this.j = j;
      this.count = count;
    } 
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    table = new char[R][C];
    for (int i = 0; i < R; i++) {
      table[i] = br.readLine().toCharArray();
    }
    marked = new boolean['Z' - 'A' + 1];
    max = 0;
    Pair start = new Pair(0, 0, 1);
    dfs(start);
    bw.write(max + "\n");
    bw.flush();
  }
  static void dfs(Pair crnt) {
    marked[table[crnt.i][crnt.j] - 'A'] = true;
    if (max < crnt.count) {
      max = crnt.count;
    }
    for (int k = 0; k < 4; k++) {
      int nextI = crnt.i + xDi[k];
      int nextJ = crnt.j + yDi[k];
      if (nextI < 0 || nextI >= R || nextJ < 0 || nextJ >= C) continue;
      if (marked[table[nextI][nextJ]- 'A']) continue;
      dfs(new Pair(nextI, nextJ, crnt.count + 1));
    }
    marked[table[crnt.i][crnt.j] - 'A'] = false;
  }
}
