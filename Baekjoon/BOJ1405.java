import java.io.*;
import java.util.*;

public class BOJ1405 {
  static int n;
  static double[] prob;
  static boolean[][] visit = new boolean[29][29];
  static int[] xDi = {0, 0, -1, 1};
  static int[] yDi = {1, -1, 0, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    prob = new double[4];
    for (int i = 0; i < 4; i++) {
      prob[i] = Double.parseDouble(st.nextToken()) * 0.01;
    }
    visit[14][14] = true;
    double p = recur(0, 14, 14, 1.0);
    bw.write(Double.toString(p));
    bw.flush();
  }
  static double recur(int depth, int x, int y, double p) {
    if (depth >= n) return 1.0;
    double crntP = 0;
    for (int i = 0; i < 4; i++) {
      int nextX = x + xDi[i], nextY = y + yDi[i];
      if (visit[nextX][nextY]) continue;
      visit[nextX][nextY] = true;
      crntP += prob[i]*recur(depth+1, nextX, nextY, p);
      visit[nextX][nextY] = false;
    }
    return crntP;
  }
}
