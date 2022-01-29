import java.util.*;

public class SCC {
  private static class Graph {
    ArrayList<Queue<Integer>> graph;
    Graph(int v) {
      graph = new ArrayList<>();
      for (int i = 0; i < v; i++) {
        graph.add(new LinkedList<>());
      }
    }
    void addEdge(int start, int end) {
      graph.get(start).add(end);
    }
    Graph reverse() {
      Graph reverse = new Graph(graph.size());
      for (int i = 0; i < graph.size(); i++) {
        for (int end : graph.get(i)) {
          reverse.addEdge(end, i);
        }
      }
      return reverse;
    }
    int size() {
      return graph.size();
    }
    Queue<Integer> get(int i) {
      return graph.get(i);
    }
  }
  static int[] kosaraju(Graph graph) {
    int sccId = 0;
    int[] scc = new int[graph.size()];
    Stack<Integer> stk = new Stack<>();
    boolean[] visit = new boolean[graph.size()];
    for (int i = 0; i < graph.size(); i++) {
      if (visit[i]) continue;
      // dfs:
      Stack<Integer> dfs = new Stack<>();
      dfs.push(i);
      visit[i] = true;
      while (!dfs.isEmpty()) {
        boolean addVertex = false;
        int crnt = dfs.peek();
        for (int end : graph.get(crnt)) {
          if (visit[end]) continue;
          dfs.push(end);
          visit[end] = true;
          addVertex = true;
        }
        if (!addVertex) { // no way
          stk.push(crnt);
          while (!dfs.isEmpty()) stk.push(dfs.pop());
        }
      }
    }

    Graph reverse = graph.reverse();
    visit = new boolean[graph.size()];
    while (!stk.isEmpty()) {
      int i = stk.pop();
      if (visit[i]) continue;
      Stack<Integer> dfs = new Stack<>();
      visit[i] = true;
      dfs.push(i);
      while (!dfs.isEmpty()) {
        int crnt = dfs.pop();
        for (int end : reverse.get(crnt)) {
          if (visit[end]) continue;
          dfs.push(end);
          visit[end] = true;
        }
        scc[crnt] = sccId;
      }
      scc[i] = sccId++;
    }

    return scc;
  }
  static Stack<Stack<Integer>> tarzan(Graph graph) {
    int id = 1;
    int[] scc = new int[graph.size()];
    boolean[] finished = new boolean[graph.size()];
    Stack<Integer> stk = new Stack<>();
    Stack<Stack<Integer>> result = new Stack<>();
    for (int i = 0; i < graph.size(); i++) {
      if (scc[i] == 0) dfsTarzan(graph, i, id++, scc, stk, finished, result);
    }
    return result;
  }
  static int dfsTarzan(Graph graph, int x, int id, int[] scc, Stack<Integer> stk, boolean[] finished, Stack<Stack<Integer>> result) {
    scc[x] = id;
    stk.push(x);
    int parent = scc[x];
    for (int edge : graph.get(x)) {
      if (scc[edge] == 0) {
        parent = Integer.min(parent, dfsTarzan(graph, edge, id + 1, scc, stk, finished, result));
      } else if (!finished[edge]) {
        parent = Integer.min(parent, scc[edge]);
      }
    }
    if (parent == scc[x]) {
      Stack<Integer> component = new Stack<>();
       while (true) {
        int t = stk.pop();
        finished[t] = true;
        component.push(t);
        if (t == x) break;
       }
       result.push(component);
    }
    return parent;
  }
  public static void main(String[] args) {
    Graph graph = new Graph(8);
    graph.addEdge(1, 4);
    graph.addEdge(1, 6); 
    graph.addEdge(2, 7); 
    graph.addEdge(3, 7); 
    graph.addEdge(4, 5); 
    graph.addEdge(5, 1); 
    graph.addEdge(6, 7); 
    graph.addEdge(7, 2); 
    graph.addEdge(7, 3);

    // System.out.println(Arrays.toString(kosaraju(graph)));
    int id = 0;
    Stack<Stack<Integer>> scc = tarzan(graph);
    while (!scc.isEmpty()) {
      System.out.println("scc: " + id++);
      Stack<Integer> c = scc.pop();
      while (!c.isEmpty()) {
        System.out.print(c.pop() + " ");
      }
      System.out.println();
    }
  }
}