import java.io.*;
import java.util.*;

public class BOJ11780 {
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
    StringTokenizer st;

    int V = Integer.parseInt(br.readLine());
    int E = Integer.parseInt(br.readLine());
    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    for (int i = 0; i <= V; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
    }

    // Floyd Warshall
    final long INF = 100_000L * 100_000L + 1L;
    long[][] dist = new long[V + 1][V + 1];
    int[][] next = new int[V + 1][V + 1];
    for (int i = 0; i <= V; i++) {
      for (int j = 0; j <= V; j++) {
        dist[i][j] = INF;
      }
    }
    for (int i = 1; i <= V; i++) {
      for (Pair adj : graph.get(i)) {
        if (dist[i][adj.end] > adj.weight) {
          dist[i][adj.end] = adj.weight;
          next[i][adj.end] = adj.end;
        }
      }
    }
    for (int i = 0; i <= V; i++) {
      dist[i][i] = 0;
    }
    for (int k = 1; k <= V; k++) {
      for (int i = 1; i <= V; i++) {
        for (int j = 1; j <= V; j++) {
          if (dist[i][k] >= INF || dist[k][j] >= INF) continue;
          if (dist[i][j] > dist[i][k] + dist[k][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
            next[i][j] = next[i][k];
          }
        }
      } 
    }

    for (int i = 1; i <= V; i++) {
      for (int j = 1; j < V; j++) {
        if (dist[i][j] >= INF) {
          bw.write("0 ");
        } else {
          bw.write(dist[i][j] + " ");
        }
      }
      if (dist[i][V] >= INF) {
        bw.write("0\n");
      } else {
        bw.write(dist[i][V] + "\n");
      }
    }
    for (int i = 1; i <= V; i++) {
      for (int j = 1; j <= V; j++) {
        if (next[i][j] == 0) {
          bw.write("0\n");
        } else {
          Queue<Integer> path = new LinkedList<>();
          int start = i;
          int end = j;
          path.add(start);
          while (start != end) {
            start = next[start][end];
            path.add(start);
          }
          bw.write(path.size() + " ");
          bw.write(path.poll() + "");
          while (!path.isEmpty()) {
            bw.write(" " + path.poll());
          }
          bw.write("\n");
        }
      }
    }
    bw.flush();
  }
}
