import java.io.*;
import java.util.*;

public class BOJ1956 {
  static final int INF = 4_000_001;
  private static class Pair {
    int end;
    int weight;
    Pair(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int numV = Integer.parseInt(st.nextToken());
    int numE = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    for (int i = 0; i <= numV; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < numE; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
    }
    // Floyd Warshall
    int[][] dist = new int[numV + 1][numV + 1];
    for (int i = 1; i <= numV; i++) {
      for (int j = 1; j <= numV; j++) {
        dist[i][j] = INF;
      }
    }
    for (int i = 1; i <= numV; i++) {
      dist[i][i] = 0;
    }
    for (int i = 1; i <= numV; i++) {
      for (Pair next : graph.get(i)) {
        if (dist[i][next.end] > next.weight) {
          dist[i][next.end] = next.weight;
        }
      }
    }

    for (int k = 1; k <= numV; k++) {
      for (int i = 1; i <= numV; i++) {
        for (int j = 1; j <= numV; j++) {
          if (dist[i][j] > dist[i][k] + dist[k][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }

    int minCycle = INF;
    for (int i = 1; i <= numV; i++) {
      for (int j = i + 1; j <= numV; j++) {
        if (minCycle > dist[i][j] + dist[j][i]) {
          minCycle = dist[i][j] + dist[j][i];
        }
      }
    }
    if (minCycle >= INF) {
      bw.write("-1\n");
    } else {
      bw.write(minCycle + "\n");
    }
    bw.flush();
  }
}
