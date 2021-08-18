import java.io.*;
import java.util.*;

public class BOJ1311 {
  static int N;
  static int[][] D;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[][] D = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        D[i][j] = Integer.parseInt(st.nextToken());
      }
    }


  }
  // static int minCost(int depth, int sum, int bit) {
  //   if (depth == N) {
  //     return sum;
  //   }
  //   for (int i = 0; i < N; i++) {

  //   }
  // }
}
