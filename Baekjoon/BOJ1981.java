import java.io.*;
import java.util.*;

public class BOJ1981 {
  static int N;
  static int[][] data;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  static int MIN = 200;
  static int MAX = 0;
  private static class Pair {  
    int row;
    int col;
    Pair (int row, int col) {
      this.row = row;
      this.col = col;
    }
    public String toString() {
      return "(" + row + ", " + col + ")";
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    data = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
        if (data[i][j] < MIN) MIN = data[i][j];
        if (MAX < data[i][j]) MAX = data[i][j];
      }
    }
    int answer = parametricSearch();
    bw.write(answer + "\n");
    bw.flush();
  }
  static int parametricSearch() {
    int lo = 0;
    int hi = MAX - MIN;
    while (lo < hi) {
      int mid = (lo + hi) / 2;
      // System.out.println("lo: " + lo + ", hi: " + hi + ", mid: " + mid);
      if (bfs(mid)) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return hi;
  }
  static boolean bfs(int key) {
    Queue<Pair> que = new LinkedList<>();
    Pair start = new Pair(0, 0);
    for (int val = MIN; val <= MAX; val++) {
      boolean[][] marked = new boolean[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (val > data[i][j] || data[i][j] > val + key) marked[i][j] = true;
        }
      }
      que.add(start);
      // marked[0][0] = true;
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        // System.out.println("crnt: " + crnt);
        if (marked[crnt.row][crnt.col]) continue;
        marked[crnt.row][crnt.col] = true;
        if (crnt.row == N - 1 && crnt.col == N - 1) {
          return true;
        }
        for (int i = 0; i < 4; i++) {
          int nextRow = crnt.row + rowDi[i];
          int nextCol = crnt.col + colDi[i];
          if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
          que.add(new Pair(nextRow, nextCol));
        }
      }
    }
    return false;
  }
}