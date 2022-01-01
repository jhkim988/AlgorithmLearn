import java.io.*;
import java.util.*;

public class BOJ16967 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int H = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());
    int X = Integer.parseInt(st.nextToken());
    int Y = Integer.parseInt(st.nextToken());
    int[][] B = new int[H + X][W + Y];
    int[][] A = new int[H][W];
    for (int i = 0; i < H + X; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < W + Y; j++) {
        B[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        A[i][j] = B[i][j];
      }
    }

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        if (i + X < H && j + Y < W) A[i + X][j + Y] -= A[i][j];
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < H; i++) {
      sb.append(A[i][0]);
      for (int j = 1; j < W; j++) {
        sb.append(' ').append(A[i][j]);
      }
      sb.append('\n');
    }

    bw.write(sb.toString());
    bw.flush();
  }
}
