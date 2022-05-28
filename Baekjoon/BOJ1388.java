import java.io.*;
import java.util.*;

public class BOJ1388 {
  static int row, col;
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
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    char[][] input = new char[row][col];
    for (int r = 0; r < row; r++) {
      input[r] = br.readLine().toCharArray();
    }
    
    int num = 0;
    boolean[][] visit = new boolean[row][col];
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < col; c++) {
        if (visit[r][c]) continue;
        num++;
        bfs(input, visit, r, c);
      }
    }
    bw.write(Integer.toString(num));
    bw.flush();
  }
  static void bfs(char[][] input, boolean[][] visit, int r, int c) {
    int idx = input[r][c] == '|' ? 0 : 1;
    Queue<Pair> que = new LinkedList<>();
    que.add(new Pair(r, c));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int k = idx; k < 4; k += 2) {
        int nextRow = crnt.row + rowDi[k];
        int nextCol = crnt.col + colDi[k];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        if (input[r][c] != input[nextRow][nextCol] || visit[nextRow][nextCol]) continue;
        visit[nextRow][nextCol] = true;
        que.add(new Pair(nextRow, nextCol));
      }
    }
  }
}