import java.io.*;
import java.util.*;

public class BOJ2667 {
  static int N = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    N = Integer.parseInt(br.readLine());
    
    int[][] data = new int[N][N];
    boolean[][] marked = new boolean[N][N];

    String[] tmp;
    for (int i = 0; i < N; i++) {
      tmp = br.readLine().split("");
      for (int j = 0; j < N; j++) {
        data[i][j] = Integer.parseInt(tmp[j]);
      }
    }
    
    PriorityQueue<Integer> result = new PriorityQueue<Integer>();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (!marked[i][j] && data[i][j] == 1) {
          dfs(data, i, j, marked, result);
        }        
      }
    }
    bw.write(result.size() + "\n");
    while (!result.isEmpty()) {
      bw.write(result.poll() + "\n");
    }
    bw.flush();
  }
  static void dfs(int[][] data, int xIdx, int yIdx, boolean[][] marked, PriorityQueue<Integer> result) {
    int counter = recur(data, xIdx, yIdx, marked, 0);
    result.add(counter);
  }
  static int recur(int[][] data, int xIdx, int yIdx, boolean[][] marked, int counter) {
    // neighbors: xIdx +- 1, yIdx +-1
    marked[xIdx][yIdx] = true;
    counter++;
    if (xIdx + 1 < N && !marked[xIdx + 1][yIdx] && data[xIdx + 1][yIdx] == 1) {
      counter = recur(data, xIdx + 1, yIdx, marked, counter);
    }
    if (xIdx - 1 >= 0 && !marked[xIdx - 1][yIdx] && data[xIdx - 1][yIdx] == 1) {
      counter = recur(data, xIdx - 1, yIdx, marked, counter);
    }
    if (yIdx + 1 < N && !marked[xIdx][yIdx + 1] && data[xIdx][yIdx + 1] == 1) {
      counter = recur(data, xIdx, yIdx + 1, marked, counter);
    }
    if (yIdx - 1 >= 0 && !marked[xIdx][yIdx - 1] && data[xIdx][yIdx - 1] == 1) {
      counter = recur(data, xIdx, yIdx - 1, marked, counter);
    }
    return counter;
  }
}
