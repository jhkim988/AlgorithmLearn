import java.io.*;
import java.util.*;

public class BOJ16945 {
  static int minCost = Integer.MAX_VALUE;
  static int[][] input;
  static boolean[] tmp = new boolean[10];
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    input = new int[3][3];
    for (int i = 0; i < 3; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        input[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int[] hist = new int[9];
    recur(0, 0, hist);
    bw.write(minCost + "\n");
    bw.flush();
  }
  static void recur(int depth, int cost, int[] hist) {
    if (depth >= 9) {
      int sum = 0;
      for (int i = 0; i < 3; i++) {
        sum += input[i][i];
      }
      if (sum != 15) return;
      minCost = Math.min(minCost, cost);
      return;
    }
    int row = depth / 3;
    int col = depth % 3;
    int origin = input[row][col];
    outer: for (int i = 1; i <= 9; i++) {
      for (int j = 0; j < depth; j++) if (hist[j] == i) continue outer;
      input[row][col] = i;
      hist[depth] = i;
      if (col == 2) {
        int sum = 0;
        for (int j = 0; j < 3; j++) sum += input[row][j];
        if (sum != 15) continue outer;
      }
      if (row == 2) {
        int sum = 0;
        for (int j = 0; j < 3; j++) sum += input[j][col];
        if (sum != 15) continue outer;
      }
      if (row == 2 && col == 0) {
        int sum = 0;
        for (int j = 0; j < 3; j++) sum += input[2 - j][j];
        if (sum != 15) continue outer;
      }
      recur(depth + 1, cost + Math.abs(origin - i), hist);
    }
    input[row][col] = origin;
  }
}
