import java.io.*;
import java.util.*;

public class BOJ16926 {
  private static class Pair {
    int row;
    int col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int[][] origin = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        origin[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    StringBuilder sb = new StringBuilder();
    int[][] answer = new int[N][M];
    int numOrbit = Math.min(N, M) / 2;
    Queue<Pair> que = new LinkedList<>();
    for (int orbit = 0; orbit < numOrbit; orbit++) {
      int rowPtr = orbit;
      int colPtr = orbit;
      
    }

    for (int i = 0; i < N; i++) {
      sb.append(answer[i][0]);
      for (int j = 1; j < M; j++) {
        sb.append(' ').append(answer[i][j]);
      }
      sb.append('\n');
    }
  }
}
