import java.io.*;
import java.util.*;

public class BOJ20183 {
  static int numV, numE, start, target;
  static long have;
  private static class Edge {
    int end;
    long weight;
    Edge(int end, long weight) {
      this.end = end;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    numV = Integer.parseInt(st.nextToken());
    numE = Integer.parseInt(st.nextToken());
    start = Integer.parseInt(st.nextToken());
    target = Integer.parseInt(st.nextToken());
    have = Long.parseLong(st.nextToken());

    ArrayList<Queue<Edge>> graph = new ArrayList<>();
    for (int i = 0; i <= numV; i++) {
      graph.add(new LinkedList<>());
    }

    int max = 0;
    for (int i = 0; i < numE; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graph.get(a).add(new Edge(b, w));
      graph.get(b).add(new Edge(a, w));
      if (max < w) max = w;
    }

    int lo = 0;
    int hi = max;
    if (!check(hi, graph)) {
      bw.write("-1\n");
      bw.flush();
      return;
    }
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (check(mid, graph)) {
        hi = mid;
      } else {
        lo = mid;
      }
    } 

    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int maxVal, ArrayList<Queue<Edge>> graph) {
    long[] dist = new long[graph.size()];
    PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> (int) (a.weight - b.weight));
    Arrays.fill(dist, Long.MAX_VALUE/2);
    dist[start] = 0;
    pq.add(new Edge(start, 0));
    while (!pq.isEmpty()) {
      Edge crnt = pq.poll();
      if (crnt.weight > dist[crnt.end]) continue; // duplicate node!!
      for (Edge next : graph.get(crnt.end)) {
        if (maxVal < next.weight) continue;
        if (have < dist[crnt.end] + next.weight) continue;
        if (next.end == target) return true;
        if (dist[crnt.end] + next.weight < dist[next.end]) {
          dist[next.end] = dist[crnt.end] + next.weight;
          pq.add(new Edge(next.end, dist[next.end]));
        }         
      }
    }
    return dist[target] < Long.MAX_VALUE/2;
  }
}
