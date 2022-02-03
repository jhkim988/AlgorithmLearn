import java.util.*;

public class GraphWeighted {
  ArrayList<Queue<Edge>> graph;  
  GraphWeighted(int n) {
    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
  }
  void addEdge(int a, int b, int w, boolean bidirectional) {
    if (bidirectional) {
      graph.get(b).add(new Edge(a, w));
    }
    graph.get(a).add(new Edge(b, w));
  }
  void addEdge(int a, int b, int w) {
    graph.get(a).add(new Edge(b, w));
    graph.get(b).add(new Edge(a, w));}
  Queue<Edge> get(int i) {
    return graph.get(i);
  }
  int size() {
    return graph.size();
  }
}
class Edge {
  int end, weight;
  Edge(int end, int weight) {
    this.end = end;
    this.weight = weight;
  }
}