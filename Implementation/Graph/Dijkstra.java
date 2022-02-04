import java.util.*;

public class Dijkstra {
  public static void main(String[] args) {
    // Graph:
    final int V = 20; // number of vertex, id: 1 ~ 20
    final int E = 10; // number of edge
    final int weightLim = 10;
    GraphWeighted graph = new GraphWeighted(V);
    
    for (int i = 0; i < E; i++) { 
      // Generate Random Edge
      int a = (int) (Math.random() * 20) + 1;
      int b = (int) (Math.random() * 20) + 1;
      int w = (int) (Math.random() * weightLim);
      graph.addEdge(a, b, w);
    }

    // Dijkstra Algorithm:
    // time complexity: O((|E| + |V|)log|V|) (min-priority queue) ~ O(|V|^2)
    // time complexity: O(|E| + |V|log|V|) (Fibonacci heap)
    // Use cost array greedly.
    final int INF = Integer.MAX_VALUE/2;
    int start = 1;
    int target = V;
    
    int[] cost = new int[V + 1];
    PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

    Arrays.fill(cost, INF);
    cost[start] = 0;
    pq.add(new Edge(start, 0));

    while (!pq.isEmpty()) {
      Edge crnt = pq.poll();
      if (crnt.weight > cost[crnt.end]) continue; // duplicate !!
      for (Edge adj : graph.get(crnt.end)) {
        // If we find a shorter way to cost[adj.end], Update cost[adj.end]
        if (cost[adj.end] <= cost[crnt.end] + adj.weight) continue;
        cost[adj.end] = cost[crnt.end] + adj.weight;
        pq.add(new Edge(adj.end, cost[adj.end]));
        if (adj.end == target) {
          // Find
          break;
        }
      }
    }
    // Not Found. cost: Integer.MAX_VALUE
  }
}
