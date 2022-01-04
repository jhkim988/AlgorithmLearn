import java.io.*;
import java.util.*;

public class BOJ16234 {
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  private static class Pair {
    int row, col;
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
    int L = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int[][] population = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        population[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    int count = 0;
    while (true) {
      boolean hasNoUnion = true;
      int[][] visit = new int[N][N];
      int id = 1;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (visit[i][j] != 0) continue;
          int sum = population[i][j];
          int num = 1;
          visit[i][j] = id;
          Queue<Pair> que = new LinkedList<>();
          HashSet<Integer> hs = new HashSet<>();
          que.add(new Pair(i, j));
          hs.add(i * N + j);
          while (!que.isEmpty()) {
            Pair crnt = que.poll();
            for (int k = 0; k < 4; k++) {
              int adjRow = crnt.row + rowDi[k];
              int adjCol = crnt.col + colDi[k];
              if (adjRow < 0 || adjRow >= N || adjCol < 0 || adjCol >= N) continue;
              if (visit[adjRow][adjCol] != 0) continue;
              int diff = Math.abs(population[crnt.row][crnt.col] - population[adjRow][adjCol]);
              if (L <= diff && diff <= R) {
                que.add(new Pair(adjRow, adjCol));
                visit[adjRow][adjCol] = id;
                hasNoUnion = false;
                sum += population[adjRow][adjCol];
                num++;
                hs.add(adjRow * N + adjCol);
              }
            }
          }
          int val = sum / num;
          for (int ptr : hs) {
            int row = ptr/N;
            int col = ptr%N;
            population[row][col] = val;
          }
          id++;
        }
      } 
      if (hasNoUnion) break;
      else count++;
    }
    bw.write(count + "\n");
    bw.flush();
  }
}
