import java.io.*;
import java.util.*;

public class BOJ1613 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int prev = Integer.parseInt(st.nextToken());
      int next = Integer.parseInt(st.nextToken());
      graph.get(prev).add(next);
    }
    final int INF = 1000;
    int[][] front = new int[n+1][n+1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(front[i], INF);
    }
    for (int i = 1; i <= n; i++) {
      front[i][i] = 0;
      for (int el : graph.get(i)) {
        front[i][el] = 1;
      }
    }
    for (int k = 1; k <= n; k++) {
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          front[i][j] = Integer.min(front[i][j], front[i][k] + front[k][j]);
        }
      }
    }
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int f = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (front[f][b] == INF && front[b][f] == INF) {
        bw.write("0\n");
      } else if (front[f][b] < INF) {
        bw.write("-1\n");
      } else {
        bw.write("1\n");
      }
    }
    bw.flush();
  }
}