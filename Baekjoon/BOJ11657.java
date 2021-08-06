import java.io.*;
import java.util.*;

public class BOJ11657 {
  static final long INF = 500L * 60_000_000L + 1;
  static int numV;
  static int numE;
  static ArrayList<Queue<Edge>> graph;
  static long[] dist;
  static boolean hasNegaticCyle = false;
  private static class Edge implements Comparable<Edge> {
    int end;
    long weight;
    Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    @Override
    public int compareTo(Edge other) {
      return (this.weight > other.weight) ? 1 : (this.weight == other.weight ? 0 : -1);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    numV = Integer.parseInt(st.nextToken());
    numE = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for (int i = 0; i <= numV; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < numE; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      // a -> b weight
      graph.get(a).add(new Edge(b, w));
    }

    dist = new long[numV + 1]; // dist[j]: start -> j, cost
    Arrays.fill(dist, INF);
    dist[1] = 0L;
    bellmanFord();
    if (hasNegaticCyle) {
      bw.write("-1\n");
      bw.flush();
      return;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 2; i <= numV; i++) {
      if (dist[i] == INF) {
        sb.append(-1 + "\n");
      } else {
        sb.append(dist[i] + "\n");
      }    
    }
    bw.write(sb.toString());
    bw.flush();
  }
  static void bellmanFord() {
    for (int i = 1; i <= numV; i++) {
      for (int j = 1; j <= numV; j++) {
        for (Edge next : graph.get(j)) {
          if (dist[j] != INF && dist[next.end] > dist[j] + next.weight) {
            dist[next.end] = dist[j] + next.weight;
            if (i == numV) {
              hasNegaticCyle = true;
            }
          }
        }
      }
    }
  }
}
