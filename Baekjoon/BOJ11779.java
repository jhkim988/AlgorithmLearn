import java.io.*;
import java.util.*;

public class BOJ11779 {
  private static class Pair implements Comparable<Pair> {
    int end;
    long weight;
    Pair prev;
    Pair(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    Pair(int end, long weight, Pair prev) {
      this.end = end;
      this.weight = weight;
      this.prev = prev;
    }
    @Override
    public int compareTo(Pair other) {
      return Long.compare(this.weight, other.weight);
    }    
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int V = Integer.parseInt(br.readLine());
    int E = Integer.parseInt(br.readLine());
    final long INF = 100_000L * 100_000L + 1L;
    
    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    for (int i = 0; i <= V; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < E; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
    }
    StringTokenizer st = new StringTokenizer(br.readLine());
    int dest = Integer.parseInt(st.nextToken());
    int arvl = Integer.parseInt(st.nextToken());

    // Dijkstra Algorithm
    long dist[] = new long[V + 1];
    for (int i = 0; i <= V; i++) {
      dist[i] = INF;
    }
    dist[dest] = 0;
    PriorityQueue<Pair> que = new PriorityQueue<>();
    que.add(new Pair(dest, 0));
    Pair crnt = new Pair(-1, 0); // dummy;
    while (!que.isEmpty()) {
      crnt = que.poll();
      if (crnt.end == arvl) {
        break;
      }
      for (Pair next : graph.get(crnt.end)) {
        if (dist[next.end] > dist[crnt.end] + next.weight) {
          dist[next.end] = dist[crnt.end] + next.weight;
          que.add(new Pair(next.end, dist[next.end], crnt));
        }
      }
    }

    Stack<Integer> path = new Stack<>();
    Pair tour = crnt;
    while (tour != null) {
      path.push(tour.end);
      tour = tour.prev;
    }
    bw.write(dist[arvl] + "\n");
    bw.write(path.size() + "\n");
    while (!path.isEmpty()) {
      bw.write(path.pop() + " ");
    }
    bw.write("\n");
    bw.flush();
  }
}
