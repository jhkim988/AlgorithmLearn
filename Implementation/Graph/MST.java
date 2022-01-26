import java.util.*;

public class MST {
  private static class Pair {
    int start, end, weight;
    Pair(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    Pair(int start, int end, int weight) {
      this(end, weight);
      this.start = start;
    }
  }
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
  static ArrayList<Queue<Pair>> kruskal(ArrayList<Queue<Pair>> graph) {
    // 1. Sort edges by weight
    // 2. choose edge with minimum weight.
    // 3. Add the edge if there is no cycle. (<< Determind by Union Find)
    ArrayList<Pair> allEdge = new ArrayList<>();
    for (int i = 0; i < graph.size(); i++) {
      for (Pair p : graph.get(i)) allEdge.add(p); // if p has no 'start', new Pair(i, p.end, p.weigth)
    } // O(E)
    Collections.sort(allEdge, (a, b) -> a.weight - b.weight); // O(E log E)
    
    int total = 0; // min weight
    UnionFind uf = new UnionFind(graph.size());
    ArrayList<Pair> treeEdge = new ArrayList<>();
    for (Pair p : allEdge) {
      if (uf.isConnected(p.start, p.end)) continue;
      uf.union(p.start, p.end); // UnionFind > O(1)
      treeEdge.add(p);
      total += p.weight;
    }

    ArrayList<Queue<Pair>> tree = new ArrayList<>();
    for (int i = 0; i < graph.size(); i++) tree.add(new LinkedList<>());
    for (Pair p : treeEdge) {
      tree.get(p.start).add(new Pair(p.end, p.weight));
    }
    return tree;
  }
  static ArrayList<Queue<Pair>> prim(ArrayList<Queue<Pair>> graph) {
    // Greedy Algorithm:
    // 1. use dist[] array(like Dijkstra), O(V^2)
    // 2. use PriorityQueue, O(V log E)
    ArrayList<Queue<Pair>> tree = new ArrayList<>();
    boolean[] selected = new boolean[graph.size()];
    int[] dist = new int[graph.size()]; // dist[i]: min dist at tree -> i
    Arrays.fill(dist, Integer.MAX_VALUE);
    // start: zero
    dist[0] = 0;
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
      int prev = -1;
      int weight = 0;
      for (Pair p : graph.get(now)) {
        if (!selected[p.end]) continue;
        prev = p.end;
        weight = p.weight;
        break;
      }
      tree.get(prev).add(new Pair(now, weight));
      for (Pair p : graph.get(now)) {
        if (selected[p.end]) continue;
        dist[p.end] = Integer.min(dist[p.end], p.weight);
      }
    }
    return tree;
  }
  public static void main(String[] args) {

  }
}
