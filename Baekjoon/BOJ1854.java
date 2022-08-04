import java.io.*;
import java.util.*;

public class BOJ1854 {
  private static class Pair {
    int node;
    long cost;
    Pair (int node, long cost) {
      this.node = node;
      this.cost = cost;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      graph.get(a).add(new Pair(b, c));
    }

    final long INF = Long.MAX_VALUE/2;
    PriorityQueue<Pair> pq = new PriorityQueue<>(
      (p1, p2) -> Long.compare(p1.cost, p2.cost)
    );
    
    ArrayList<PriorityQueue<Long>> history = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      history.add(new PriorityQueue<>((a, b) -> Long.compare(b, a)));
    }

    pq.add(new Pair(1, 0));
    history.get(1).add(0L);

    while (!pq.isEmpty()) {
      Pair crnt = pq.poll();
      if (history.get(crnt.node).peek() < crnt.cost) continue;
      for (Pair adj : graph.get(crnt.node)) {
        if (history.get(adj.node).size() < k) {
          history.get(adj.node).add(crnt.cost+adj.cost);
          pq.add(new Pair(adj.node, crnt.cost+adj.cost));
        } else if (crnt.cost+adj.cost < history.get(adj.node).peek()) {
          history.get(adj.node).poll();
          history.get(adj.node).add(crnt.cost+adj.cost);
          pq.add(new Pair(adj.node, crnt.cost+adj.cost));
        }
      }
    }
    for (int idx = 1; idx <= n; idx++) {
      PriorityQueue<Long> h = history.get(idx);
      // System.out.println("idx: " + idx + "/h.size():" + h.size());
      // for (Long l : h) System.out.print(l + " ");
      // System.out.println();
      if (h.size() < k) {
        bw.write("-1\n");
      } else {
        bw.write(Long.toString(h.peek() == INF ? -1 : h.peek()));
        bw.newLine();
      }
    }
    bw.flush();
  }
}