import java.util.*;

public class MST {
  private static class UnionFind {
    int[] id, sz;
    UnionFind(int n) {
      id = new int[n];
      sz = new int[n];
      for (int i = 0; i < n; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }

    int root(int i) {
      while (id[i] != i) {
        i = id[i] = id[id[i]];
      }
      return i;
    }

    void union(int p, int q) {
      int prt = root(p);
      int qrt = root(q);
      if (prt == qrt) return;
      if (sz[prt] > sz[qrt]) {
        id[qrt] = prt;
        sz[prt] += sz[qrt];
      } else {
        id[prt] = qrt;
        sz[qrt] += sz[prt];
      }
    }

    boolean isConnected(int p, int q) {
      return root(p) == root(q);
    }
  }
  static GraphWeighted kruskal(GraphWeighted graph) {
    // 1. Sort edges by weight
    // 2. choose edge with minimum weight.
    // 3. Add the edge if there is no cycle. (<< Determind by Union Find)
    ArrayList<Edge> allEdge = new ArrayList<>();
    for (int i = 0; i < graph.size(); i++) {
      for (Edge edge : graph.get(i)) {
        edge.start = i;
        allEdge.add(edge);
      }
    } // O(E)
    Collections.sort(allEdge, (a, b) -> a.weight - b.weight); // O(E log E)
    
    int total = 0; // min weight
    UnionFind uf = new UnionFind(graph.size());
    ArrayList<Edge> treeEdge = new ArrayList<>();
    for (Edge edge : allEdge) {
      if (uf.isConnected(edge.start, edge.end)) continue;
      uf.union(edge.start, edge.end); // UnionFind > O(1)
      treeEdge.add(edge);
      total += edge.weight;
    }

    GraphWeighted tree = new GraphWeighted(graph.size());
    for (Edge edge : treeEdge) {
      tree.addEdge(edge.start, edge.end, edge.weight, false);
    }
    return tree;
  }
  static GraphWeighted prim(GraphWeighted graph) {
    // Greedy Algorithm:
    // 1. use dist[] array(like Dijkstra), O(V^2)
    // 2. use PriorityQueue, O(V log E)
    GraphWeighted tree = new GraphWeighted(graph.size());
    boolean[] selected = new boolean[graph.size()];
    int[] dist = new int[graph.size()]; // dist[i]: min dist at tree -> i
    int[] parent = new int[graph.size()];
    Arrays.fill(dist, Integer.MAX_VALUE);
    // start: zero
    int start = 0;
    dist[start] = 0;
    int total = 0;
    for (int i = 0; i < graph.size() - 1; i++) {
      int now = -1;
      int min_dist = Integer.MAX_VALUE;
      for (int j = 0; j < graph.size(); j++) {
        if (!selected[j] && min_dist > dist[j]) {
          min_dist = dist[j];
          now = j;
        }
      }
      if (now < 0) return null; // not connected
      total += min_dist;
      selected[now] = true;
      // tree -> now
      if (now != start) tree.addEdge(parent[now], now, min_dist);
      for (Edge p : graph.get(now)) {
        if (selected[p.end]) continue;
        if (p.weight < dist[p.end]) {
          dist[p.end] = p.weight;
          parent[p.end] = now;
        }
      }
    }
    return tree;
  }
  public static void main(String[] args) {

  }
}
