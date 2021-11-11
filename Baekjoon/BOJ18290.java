import java.io.*;
import java.util.*;

public class BOJ18290 {
  static int N;
  static int M;
  static int K;
  static int[][] map;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  static int ans = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    useRecur();
    bw.write(ans + "\n");
    bw.flush();
  }
  static void useRecur() {
    int[] bit = new int[N]; // bit[i]: status of map[i][]
    recur(0, bit, 0, 0);
  } 
  static int recur(int depth, int[] bit, int ptr, int val) {
    // System.out.println("call: " + depth + ", (" + (ptr/M) + ", " + (ptr%M) + "), val: " + val);
    if (depth >= K) {
      // for (int i = 0; i < N; i++) {
      //   for (int j = 0; j < M; j++) {
      //     if ((bit[i] & (1 << j)) != 0) {
      //       System.out.print("1 ");
      //     } else {
      //       System.out.print("0 ");
      //     }
      //   }
      //   System.out.println();
      // }
      ans = Math.max(ans, val);
      return val;
    }
    outerLoop: for (int i = ptr; i < M * N; i++) {
      // System.out.println("depth: " + depth + ", (" + (i/M) + ", " + (i%M) + "), val: " + val);
      innerLoop: for (int j = 0; j < 4; j++) {
        int nextRow = i/M + rowDi[j];
        int nextCol = i%M + colDi[j];
        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue innerLoop;
        if ((bit[nextRow] & (1 << nextCol)) != 0) continue outerLoop;
      }
      bit[i/M] |= 1 << i%M;
      recur(depth + 1, bit, i + 1, val + map[i/M][i%M]);
      bit[i/M] &= ~(1 << i%M);
    }
    return val;
  }
}
