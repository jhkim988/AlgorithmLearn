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
  private static class Kosaraju {
    // 1. DFS가 끝나는 순서로 Stack에 담는다.
    // 2. Stack에서 꺼내면서 역방향 그래프에서 DFS를 한다.
    // 3. 역방향 그래프와 연결돼 있으면 같은 SCC이다.
    // pf) 1. 역방향 그래프 DFS는 같은 SCC의 모든 원소를 순회한다.
    // 2. 두 SCC가 있을 때 많아야 한 가지 방향의 간선만 존재할 수 있다.
    Graph graph, reverse;
    private Stack<Integer> stk;
    private boolean[] visit;
    private int[] scc;
    Kosaraju(Graph graph) {
      this.graph = graph;
      this.reverse = graph.reverse();
      stk = new Stack<>();
      visit = new boolean[graph.size()];
      scc = new int[graph.size()];
    }
    void kosaraju() {
      int sccId = 0;
      for (int i = 0; i < graph.size(); i++) {
        if (!visit[i]) dfs(i);
      }
      while (!stk.isEmpty()) {
        int top = stk.pop();
        if (scc[top] != 0) continue;
        dfsRev(top, ++sccId);
      }
    }
    void dfs(int node) {
      visit[node] = true;
      for (int adj : graph.get(node)) {
        if (!visit[adj]) dfs(adj);
      }
      stk.push(node);
    }
    void dfsRev(int node, int id) {
      scc[node] = id;
      for (int adj : reverse.get(node)) {
        if (scc[adj] != 0) continue;
        dfsRev(adj, id);
      }
    }
  }
  static int[] kosaraju(Graph graph) {
    // non recursive
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
  
  private static class Tarjan {
    Graph graph;
    int id;
    int[] index;
    int[] lowlink;
    boolean[] onStack;
    Stack<Integer> stk;
    Tarjan(Graph graph) {
      id = 1;
      index = new int[graph.size()];
      lowlink = new int[graph.size()];
      onStack = new boolean[graph.size()];
      stk = new Stack<>();
      this.graph = graph;
    }
    Stack<Stack<Integer>> tarjan() {
      Stack<Stack<Integer>> result = new Stack<>();
      for (int i = 0; i < graph.size(); i++) {
        if (index[i] == 0) dfsTarzan(i, result);
      }
      return result;
    }
    void dfsTarzan(int x, Stack<Stack<Integer>> result) {
      index[x] = id;
      lowlink[x] = id;
      id++;
      stk.push(x);
      for (int edge : graph.get(x)) {
        if (index[edge] == 0) {
          dfsTarzan(edge, result);
          lowlink[x] = Integer.min(lowlink[x], lowlink[edge]);
        } else if (onStack[edge]) {
          lowlink[x] = Integer.min(lowlink[x], index[edge]);
        }
      }
      if (lowlink[x] == index[x]) {
        Stack<Integer> component = new Stack<>();
         while (true) {
          int t = stk.pop();
          onStack[t] = false;
          component.push(t);
          if (t == x) break;
         }
         result.push(component);
      }
    }
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

    Kosaraju k = new Kosaraju(graph);
    k.kosaraju();
    System.out.println(Arrays.toString(k.scc));
    System.out.println(Arrays.toString(kosaraju(graph)));
    int id = 0;
    Stack<Stack<Integer>> scc = new Tarjan(graph).tarjan();
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