import java.io.*;
import java.util.*;

public class BOJ3648 {
  private static class Tarjan {
    ArrayList<Queue<Integer>> graph;
    int n, id;
    int[] index, lowlink, sccId;
    boolean[] onStack;
    Stack<Integer> stk;
    ArrayList<HashSet<Integer>> scc;
    Tarjan(ArrayList<Queue<Integer>> graph, int n) {
      this.graph = graph;
      this.n = n;
      id = 1;
      index = new int[graph.size()];
      lowlink = new int[graph.size()];
      onStack = new boolean[graph.size()];
      stk = new Stack<>();
      scc = new ArrayList<>();
      sccId = new int[graph.size()];
    }
    boolean init() {
      // possible or not: 2-SAT
      for (int i = 1; i < graph.size(); i++) {
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
          if (t <= n && hs.contains(t+n)) return false;
          if (t > n && hs.contains(t-n)) return false;
          hs.add(t);
          sccId[t] = scc.size();
          if (t == x) break;
        }
        scc.add(hs);
      }
      return true;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = null;
    while ((input = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(input);
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      ArrayList<Queue<Integer>> graph = new ArrayList<>();
      int numNode = n<<1;
      for (int i = 0; i <= numNode; i++) {
        graph.add(new LinkedList<>());
      }
      int target = 1;
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        if (a == target || b == target) continue;
        if (a == -target && b == -target) {
          bw.write("no\n");
          bw.flush();
          return;
        } else if (a == -target) {
          addEdge(-b, b, graph, n);
        } else if (b == -target) {
          addEdge(-a, a, graph, n);
        } else {
          addEdge(-a, b, graph, n);
          addEdge(-b, a, graph, n);
        }
      }
      
      Tarjan tarjan = new Tarjan(graph, n);
      if (tarjan.init()) {
        bw.write("yes\n");
      } else {
        bw.write("no\n");
      }
      bw.flush();
    }
  }
  static void addEdge(int a, int b, ArrayList<Queue<Integer>> graph, int n) {
    if (a < 0) a = -a + n;
    if (b < 0) b = -b + n;
    graph.get(a).add(b);
  }
}
