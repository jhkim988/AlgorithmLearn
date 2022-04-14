import java.io.*;
import java.util.*;

public class BOJ3109 {
  static int row, col;
  static char[][] map;
  static int[] rowDi = {-1, 0, 1};
  static boolean[][] visit;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    map = new char[row][];
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
    }
    int num = 0;
    visit = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      if (dfs(i, 0)) num++;
    }
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }
  static boolean dfs(int currentRow, int currentCol) {
    if (currentCol == col-1) return true;
    for (int k = 0; k < 3; k++) {
      int nextRow = currentRow + rowDi[k];
      if (nextRow < 0 || nextRow >= row) continue;
      if (map[nextRow][currentCol + 1] != '.') continue;
      if (visit[nextRow][currentCol + 1]) continue;
      visit[nextRow][currentCol + 1] = true;
      if (dfs(nextRow, currentCol + 1)) return true;
      // visit[nextRow][currentCol + 1] = false; // 다시 방문해도 실패하기 때문에 해제할 필요가 없다.
    }
    return false;
  }
}