import java.io.*;
import java.util.*;

public class BOJ11404 {
  static final int INF = 10_000_001;
  private static class Pair {
    int node;
    int weight;
    Pair(int node, int weight) {
      this.node = node;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numV = Integer.parseInt(br.readLine());
    int numE = Integer.parseInt(br.readLine());

    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    for (int i = 0; i <= numV; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < numE; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
    }

    int[][] dist = new int[numV + 1][numV + 1];
    for (int i = 1; i <= numV; i++) {
      for (int j = 1; j <= numV; j++) {
        dist[i][j] = INF;
      }
    }
    for (int i = 1; i <= numV; i++) {
      for (Pair node : graph.get(i)) {
        if (dist[i][node.node] > node.weight) {
          dist[i][node.node] = node.weight;
        }        
      }
    }

    for (int i = 0; i <= numV; i++) {
      dist[i][i] = 0;
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

    for (int i = 1; i <= numV; i++) {
      for (int j = 1; j < numV; j++) {
        if (dist[i][j] == INF) {
          bw.write("0 ");
        } else {
          bw.write(dist[i][j] + " ");
        }
      }
      if (dist[i][numV] == INF) {
        bw.write("0\n");
      } else {
        bw.write(dist[i][numV] + "\n");
      }
    }
    bw.flush();
  }
}
