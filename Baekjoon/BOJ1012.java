import java.io.*;
import java.util.*;

public class BOJ1012 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    int numTest = Integer.parseInt(br.readLine());    
    for (int i = 0; i < numTest; i++) {
      solve();
    }
  }
  static void solve() throws IOException {
    StringTokenizer st;
    st= new StringTokenizer(br.readLine());
    int width = Integer.parseInt(st.nextToken());
    int height = Integer.parseInt(st.nextToken());
    int numVege = Integer.parseInt(st.nextToken());

    int[][] field = new int[width][height];
    for (int i = 0; i < numVege; i++) {
      st = new StringTokenizer(br.readLine());
      field[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
    }

    boolean[][] marked = new boolean[width][height];
    int count = 0;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (!marked[i][j] && field[i][j] == 1) {
          dfs(field, i, j, marked);
          count++;
        }
      }
    }
    bw.write(count + "\n");
    bw.flush();
  }
  static void dfs(int[][] field, int xIdx, int yIdx, boolean[][] marked) {
    marked[xIdx][yIdx] = true;
    if (xIdx + 1 < field.length && field[xIdx + 1][yIdx] == 1 && !marked[xIdx + 1][yIdx]) {
      dfs(field, xIdx + 1, yIdx, marked);
    }
    if (xIdx - 1 >= 0 && field[xIdx - 1][yIdx] == 1 && !marked[xIdx - 1][yIdx]) {
      dfs(field, xIdx - 1, yIdx, marked);
    }
    if (yIdx + 1 < field[0].length && field[xIdx][yIdx + 1] == 1 && !marked[xIdx][yIdx + 1]) {
      dfs(field, xIdx, yIdx + 1, marked);
    }
    if (yIdx - 1 >= 0 && field[xIdx][yIdx - 1] == 1 && !marked[xIdx][yIdx - 1]) {
      dfs(field, xIdx, yIdx - 1, marked);
    }
  }
}
