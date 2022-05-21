import java.util.*;

public class Graph {
  ArrayList<Queue<Integer>> graph;
  Graph(int n) {
    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
  }
  void addEdge(int a, int b) {
    graph.get(a).add(b);
    graph.get(b).add(a);
  }
  Iterable<Integer> get(int i) {
    return graph.get(i);
  }
  int size() {
    return graph.size();
  }
}
