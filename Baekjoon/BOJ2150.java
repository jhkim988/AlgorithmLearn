import java.io.*;
import java.util.*;

public class BOJ2150 {
  private static class Graph {
    ArrayList<Queue<Integer>> graph;
    Graph(int n) {
      graph = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        graph.add(new LinkedList<>());
      }
    }
    void addEdge(int a, int b) {
      graph.get(a).add(b);
    }
    int size() {
      return graph.size();
    }
    Queue<Integer> get(int i) {
      return graph.get(i);
    }
  }
  private static class SCC {
    int id = 1;
    Graph graph;
    int[] index;
    int[] lowlink; 
    boolean[] onStack;
    Stack<Integer> stk;
    SCC(Graph graph) {
      this.graph = graph;
      index = new int[graph.size()];
      lowlink = new int[graph.size()];
      lowlink = new int[graph.size()];
      onStack = new boolean[graph.size()];
      stk = new Stack<>();
    }
    ArrayList<ArrayList<Integer>> getSCC() {
      return tarzan();
    }
    ArrayList<ArrayList<Integer>> tarzan() {
      ArrayList<ArrayList<Integer>> result = new ArrayList<>();
      for (int i = 0; i < graph.size(); i++) {
        if (index[i] == 0) dfsTarzan(i, result);
      }
      return result;
    }
    void dfsTarzan(int x, ArrayList<ArrayList<Integer>> result) {
      index[x] = id;
      lowlink[x] = id;
      stk.push(x);
      id++;
      onStack[x] = true;
      for (int end : graph.get(x)) {
        if (index[end] == 0) {
          dfsTarzan(end, result);
          lowlink[x] = Integer.min(lowlink[x], lowlink[end]);
        } else if (onStack[end]) {
          lowlink[x] = Integer.min(lowlink[x], index[end]);
        }
      }
      if (lowlink[x] == index[x]) {
        ArrayList<Integer> component = new ArrayList<>();
        while (true) {
          int t = stk.pop();
          onStack[t] = false;
          component.add(t);
          if (t == x) break;
        }
        result.add(component);
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    Graph graph = new Graph(v + 1);
    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.addEdge(a, b);
    }

    ArrayList<ArrayList<Integer>> scc = new SCC(graph).getSCC();
    for (int i = 0; i < scc.size(); i++) {
      Collections.sort(scc.get(i));
    }
    Collections.sort(scc, (a, b) -> a.get(0) - b.get(0));

    bw.write(Integer.toString(scc.size() - 1)); // zero..
    bw.newLine();
    for (int i = 1; i < scc.size(); i++) {
      ArrayList<Integer> component = scc.get(i);
      for (int node : component) {
        bw.write(Integer.toString(node));
        bw.write(' ');
      }
      bw.write("-1\n");
    }
    bw.flush();
  }
}
