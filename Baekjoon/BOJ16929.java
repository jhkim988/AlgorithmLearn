import java.io.*;
import java.util.*;

public class BOJ16929 {
  static int[] xDi = {-1, 0, 1, 0};
  static int[] yDi = {0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int height = Integer.parseInt(st.nextToken());
    int width = Integer.parseInt(st.nextToken());
    char[][] table = new char[height][];
    for (int i = 0; i < height; i++) {
      table[i] = br.readLine().toCharArray();
    }
    bw.write(answer(table, height, width));
    bw.flush();
  }
  static String answer(char[][] table, int height, int width) {
    boolean[] flag = {false};
    boolean[][] marked = new boolean[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // start: table[i][j]
        if (marked[i][j]) continue;
        marked[i][j] = true;
        if (useDFS(marked, table, 0, i, j, i, j, height, width, flag)) return "Yes\n";
        marked[i][j] = false;
      }
    }
    return "No\n";
  }
  static boolean useDFS(boolean[][] marked, char[][] table, int depth, int startH, int startW, int h, int w, int height, int width, boolean[] flag) {
    char ch = table[h][w];
    for (int i = 0; i < 4; i++) {
      int nextH = h + xDi[i];
      int nextW = w + yDi[i];
      if (nextH < 0 || nextH >= height || nextW < 0 || nextW >= width) continue;
      if (ch != table[nextH][nextW]) continue;
      if (depth >= 3 && startH == nextH && startW == nextW) flag[0] = true;
      if (marked[nextH][nextW]) continue;
      marked[nextH][nextW] = true;
      useDFS(marked, table, depth + 1, startH, startW, nextH, nextW, height, width, flag);
      marked[nextH][nextW] = false;
      if (flag[0]) return true;
    }
    return false;
  }
}
