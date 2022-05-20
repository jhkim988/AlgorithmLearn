import java.io.*;
import java.util.*;

public class BOJ5719 {
  private static class Edge {
    int end; long weight;
    Edge(int end, long weight) {
      this.end = end;
      this.weight = weight;
    }
    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      if (this.getClass() != other.getClass()) return false;
      Edge o = (Edge) other;
      return this.end == o.end;
    }
    @Override
    public int hashCode() {
      return end;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    while (v != 0 && e != 0) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      ArrayList<HashSet<Edge>> graph = new ArrayList<>();
      ArrayList<HashSet<Edge>> reverse = new ArrayList<>();
      for (int i = 0; i < v; i++) {
        graph.add(new HashSet<>());
        reverse.add(new HashSet<>());
      }
      while (e-- > 0) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        graph.get(a).add(new Edge(b, w));
        reverse.get(b).add(new Edge(a, w));
      }

      long[] dist = shortestDist(graph, start, end);
      removeShortestPath(graph, reverse, dist, start, end);
      dist = shortestDist(graph, start, end);
      bw.write(dist[end] == Long.MAX_VALUE/2 ? "-1" : Long.toString(dist[end]));
      bw.newLine();

      st = new StringTokenizer(br.readLine());
      v = Integer.parseInt(st.nextToken());
      e = Integer.parseInt(st.nextToken());
    }
    bw.flush();
  }
  static long[] shortestDist(ArrayList<HashSet<Edge>> graph, int start, int end) {
    int v = graph.size();
    long[] dist = new long[v];
    Arrays.fill(dist, Long.MAX_VALUE/2);
    dist[start] = 0;
    Queue<Edge> que = new LinkedList<>();
    que.add(new Edge(start, 0));
    while (!que.isEmpty()) {
      Edge crnt = que.poll();
      if (crnt.weight > dist[crnt.end]) continue;
      for (Edge next : graph.get(crnt.end)) {
        if (dist[next.end] <= dist[crnt.end] + next.weight) continue;
        dist[next.end] = dist[crnt.end] + next.weight;
        que.add(new Edge(next.end, dist[next.end]));
      }
    }
    return dist;
  }
  static void removeShortestPath(ArrayList<HashSet<Edge>> graph, ArrayList<HashSet<Edge>> reverse, long[] dist, int start, int end) {
    long shortest = dist[end];
    boolean[] visit = new boolean[graph.size()];
    Queue<Edge> que = new LinkedList<>();
    Queue<Integer> remove = new LinkedList<>();
    que.add(new Edge(end, 0));
    visit[end] = true;
    while (!que.isEmpty()) {
      Edge crnt = que.poll();
      for (Edge next : reverse.get(crnt.end)) {
        if (dist[next.end] + next.weight + crnt.weight != shortest) continue;
        if (!visit[next.end]) que.add(new Edge(next.end, crnt.weight + next.weight));
        visit[next.end] = true;
        remove.add(next.end);
      }
      while (!remove.isEmpty()) {
        int r = remove.poll();
        graph.get(r).remove(new Edge(crnt.end, 0));
      }
    }
  }
}