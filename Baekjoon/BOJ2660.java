import java.io.*;
import java.util.*;

public class BOJ2660 {
  public static void main(String[] args) throws IOException {
    final int INF = 100;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[][] dist = new int[n+1][n+1];
    for (int i = 1; i <= n; i++) {
      Arrays.fill(dist[i], INF);
    }
    for (int i = 1; i <= n; i++) {
      dist[i][i] = 0;
    }
    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    while (a != -1 && b != -1) {
      dist[a][b] = dist[b][a] = 1;
      st = new StringTokenizer(br.readLine());
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
    }
    for (int k = 1; k <= n; k++) {
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }
    PriorityQueue<Integer> candidate = new PriorityQueue<>();
    int min = INF;
    for (int i = 1; i <= n; i++) {
      int max = 0;
      for (int j = 1; j <= n; j++) {
        if (max < dist[i][j]) max = dist[i][j];
      }
      if (max < min) {
        candidate.clear();
        min = max;
        candidate.add(i);
      } else if (max == min) {
        candidate.add(i);
      }
    }
    bw.write(Integer.toString(min));
    bw.write(' ');
    bw.write(Integer.toString(candidate.size()));
    bw.newLine();

    bw.write(Integer.toString(candidate.poll()));
    while (!candidate.isEmpty()) {
      bw.write(' ');
      bw.write(Integer.toString(candidate.poll()));
    }
    bw.flush();
  }
}
