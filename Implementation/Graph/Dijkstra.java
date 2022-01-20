import java.util.*;

public class Dijkstra {
  private static class Node implements Comparable<Node> {
    int end;
    int weight;
    Node(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    @Override
    public int compareTo(Node other) {
      return this.weight - other.weight;
    }
  }
  public static void main(String[] args) {
    // Graph:
    final int V = 20; // number of vertex, id: 1 ~ 20
    final int E = 10; // number of edge
    final int weightLim = 10;
    ArrayList<Queue<Node>> graph = new ArrayList<>(); // ArrayList of Iterable Object
    
    // Graph Initialize:
    for (int i = 0; i <= V; i++) {
      graph.add(new LinkedList<>());
    }
    
    for (int i = 0; i < E; i++) { 
      // Generate Random Edge
      int start = (int) (Math.random() * 20) + 1;
      int end = (int) (Math.random() * 20) + 1;
      int weight = (int) (Math.random() * weightLim);
      graph.get(start).add(new Node(end, weight));
      // graph.get(end).add(new Node(start, weight)); // Undirected Graph
    }

    // Dijkstra Algorithm:
    // time complexity: O((|E| + |V|)log|V|) (min-priority queue) ~ O(|V|^2)
    // time complexity: O(|E| + |V|log|V|) (Fibonacci heap)
    // Use cost array greedly.
    int start = 1;
    int target = 20;
    Node startNode = new Node(start, 0);
    int[] cost = new int[V + 1];
    cost[start] = 0;
    
    // cost array initialize:
    for (int i = 1; i <= V; i++) {
      cost[i] = Integer.MAX_VALUE;
    }

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(startNode);
    while (!pq.isEmpty()) {
      Node crnt = pq.poll();

      if (crnt.end == target) {
        // Find. cost: cost[target]
        break;
      }

      for (Node adj : graph.get(crnt.end)) {
        // If we find a shorter way to cost[adj.end], Update cost[adj.end]
        if (cost[adj.end] <= cost[crnt.end] + adj.weight) continue;
        pq.add(new Node(adj.end, cost[crnt.end] + adj.weight));
        cost[adj.end] = cost[crnt.end] + adj.weight;
      }
    }
    // Not Found. cost: Integer.MAX_VALUE
  }
}
