import java.io.*;
import java.util.*;

public class BOJ10971 {
  static int N;
  static int[][] data;
  static int minSum = 1_000_000 * 10 + 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    data = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    boolean[] marked = new boolean[N];
    marked[0] = true;
    recur(1, 0, 0, marked);
    bw.write(minSum + "\n");
    bw.flush();
  }  
  static void recur(int depth, int sum, int prev, boolean[] marked) {
    if (depth == N) {
      if (data[prev][0] == 0) return;
      sum += data[prev][0];
      if (sum < minSum) {
        minSum = sum;
      }
      return;
    }
    for (int crnt = 0; crnt < N; crnt++) {
      if (marked[crnt]) continue;
      if (data[prev][crnt] == 0) continue;
      marked[crnt] = true;
      recur(depth + 1, sum + data[prev][crnt], crnt, marked);
      marked[crnt] = false;
    }
  }
}
