import java.io.*;
import java.util.*;

public class BOJ2422 {
  static int N;
  static int M;
  static boolean[][] check;
  static int count = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    check = new boolean[N + 1][N + 1];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      check[a][b] = true;
      check[b][a] = true;
    }

    int[] choose = new int[3];
    for (int i = 1; i <= N; i++) {
      choose[0] = i;
      recur(0, i, choose);
    }

    bw.write(count + "\n");
    bw.flush();
  }
  static void recur(int depth, int prev, int[] choose) {
    // System.out.println("call: " + depth + ", " + prev);
    if (depth >= 2) {
      count++;
      return;
    }
    outerLoop: for (int i = prev + 1; i <= N; i++) {
      for (int j = 0; j <= depth; j++) {
        if (check[i][choose[j]]) continue outerLoop;
      }
      choose[depth + 1] = i;
      recur(depth + 1, i, choose);
    }
  }
}