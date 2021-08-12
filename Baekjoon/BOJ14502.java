import java.io.*;
import java.util.*;

public class BOJ14502 {
  private static class Pair {
    int x;
    int y;
    Pair (int x, int y) {
      this.x = x;
      this.y = y;
    }
    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj.getClass() != getClass()) {
        return false;
      }
      Pair other = (Pair) obj;
      if (this.x == other.x && this.y == other.y) {
        return true;
      }
      return false;
    }
    @Override
    public int hashCode() {
      int result = (int) x ^ (x >>> 32);
      return result * 31 + (int) y ^ (y >>> 32);
    }
    @Override
    public String toString() {
      return "<" + x + ", " + y + ">";
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] xDi4 = {-1, 0, 1, 0};
    int[] yDi4 = {0, 1, 0, -1};

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] data = new int[N][M];
    ArrayList<Pair> virus = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
        if (data[i][j] == 2) {
          virus.add(new Pair(i, j));
        }
      }
    }

    int maxSafety = 0;
    for (int num1 = 0; num1 < M*N; num1++) {
      if (data[num1/M][num1%M] != 0) continue;
      for (int num2 = num1 + 1; num2 < M*N; num2++) {
        if (data[num2/M][num2%M] != 0) continue;
        for (int num3 = num2 + 1; num3 < M*N; num3++) {
          if (data[num3/M][num3%M] != 0) continue;
          int[][] tmp = new int[N][M];
          for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
              tmp[i][j] = data[i][j];
            }
          }
          tmp[num1/M][num1%M] = 1;
          tmp[num2/M][num2%M] = 1;
          tmp[num3/M][num3%M] = 1;

          Queue<Pair> que = new LinkedList<>();
          for (Pair v : virus) {
            que.add(v);
          }
          while (!que.isEmpty()) {
            Pair crnt = que.poll();
            for (int i = 0; i < 4; i++) {
              int nextX = crnt.x + xDi4[i];
              int nextY = crnt.y + yDi4[i];
              if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
              if (tmp[nextX][nextY] != 0) continue;
              tmp[nextX][nextY] = 2;
              que.add(new Pair(nextX, nextY));
            }
          }
          
          int count = 0;
          for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
              if (tmp[i][j] == 0) {
                count++;
              }
            }
          }

          if (maxSafety < count) {
            maxSafety = count;
          }
        }
      } 
    }

    bw.write(maxSafety + "\n");
    bw.flush();
  }
}
