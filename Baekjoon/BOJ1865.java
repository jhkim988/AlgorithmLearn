import java.io.*;
import java.util.*;

public class BOJ1865 {
  private static class Pair {
    int end;
    int weight;
    Pair(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
  }
  static final int INF = 25_000_001;
  static int V;
  static int M;
  static int W;
  static ArrayList<Queue<Pair>> graph;
  static boolean[] marked;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      V = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      graph = new ArrayList<>();
      for (int i = 0; i <= V; i++) {
        graph.add(new LinkedList<>());
      }
      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());
        graph.get(start).add(new Pair(end, weight));
        graph.get(end).add(new Pair(start, weight));
      }
      for (int i = 0; i < W; i++) {
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());
        graph.get(start).add(new Pair(end, -weight));
      }
      marked = new boolean[V + 1];
      boolean possible = false;
      for (int i = 1; i <= V; i++) {
        if (marked[i]) {
          continue;
        }
        possible = possible || bellmanFord(i);
        if (possible) {
          break;
        }
      }
      if (possible) {
        bw.write("YES\n");
      } else {
        bw.write("NO\n");
      }
    }
    bw.flush();
  }
  static boolean bellmanFord(int start) {
    if (V == 1) {
      return W > 0;
    }
    marked[start] = true;
    int[] dist = new int[V + 1]; // dist[i]: distance of start to i
    for (int i = 1; i <= V; i++) {
      dist[i] = INF;
    }
    dist[start] = 0;
    for (int i = 1; i <= V; i++) {
      for (int j = 1; j <= V; j++) {
        for (Pair next : graph.get(j)) {          
          if (dist[j] == INF) {
            break;
          }
          marked[next.end] = true;
          if (dist[next.end] > dist[j] + next.weight) {
            dist[next.end] = dist[j] + next.weight;
            if (i == V) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
