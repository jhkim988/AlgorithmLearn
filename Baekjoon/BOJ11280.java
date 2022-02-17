import java.io.*;
import java.util.*;

public class BOJ11280 {
  private static class Tarjan {
    ArrayList<Queue<Integer>> graph;
    int n, id;
    int[] index, lowlink;
    boolean[] onStack;
    Stack<Integer> stk;
    Tarjan(ArrayList<Queue<Integer>> graph, int n) {
      this.n = n;
      this.graph = graph;
      id = 1;
      index = new int[graph.size()];
      lowlink = new int[graph.size()];
      onStack = new boolean[graph.size()];
      stk = new Stack<Integer>();
    }
    boolean init() {
      for (int i = 0; i < graph.size(); i++) {
        if (index[i] == 0) if (!dfs(i)) return false;
      }
      return true;
    }
    boolean dfs(int x) {
      index[x] = id;
      lowlink[x] = id;
      onStack[x] = true;
      stk.push(x);
      id++;
      for (int adj : graph.get(x)) {
        if (index[adj] == 0) {
          if (!dfs(adj)) return false;
          lowlink[x] = Integer.min(lowlink[x], lowlink[adj]);
        } else if (onStack[adj]) {
          lowlink[x] = Integer.min(lowlink[x], index[adj]);
        }
      }
      if (lowlink[x] == index[x]) {
        HashSet<Integer> hs = new HashSet<>();
        while (true) {
          int t = stk.pop();
          onStack[t] = false;
          if (t > n && hs.contains(t - n)) return false;
          if (t <= n && hs.contains(t + n)) return false; 
          hs.add(t);
          if (t == x) break;
        }
      }
      return true;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    int numNode = n<<1;
    for (int i = 0; i <= numNode; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      addEdge(-a, b, graph, n);
      addEdge(-b, a, graph, n);
    }

    boolean answer = new Tarjan(graph, n).init();
    if (answer) bw.write("1\n");
    else bw.write("0\n");
    bw.flush();
  }
  static void addEdge(int a, int b, ArrayList<Queue<Integer>> graph, int n) {
    if (a < 0) a = -a + n;
    if (b < 0) b = -b + n;
    graph.get(a).add(b);
  }
}
