import java.io.*;
import java.util.*;

public class BOJ11281 {
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
        HashSet<Integer> sccMember = new HashSet<>();
        while (true) {
          int t = stk.pop();
          onStack[t] = false;
          if (t <= n && sccMember.contains(t+n)) return false;
          else if (t > n && sccMember.contains(t-n)) return false;
          sccId[t] = scc.size();
          sccMember.add(t);
          if (t == x) break;
        }
        scc.add(sccMember);
      }
      return true;
    }
    boolean[] getSolution() {
      boolean[] visit = new boolean[n+1];
      boolean[] result = new boolean[n+1];
      for (int i = scc.size()-1; i >= 0; i--) {
        for (int j : scc.get(i)) {
          boolean isPositive = n >= j;
          if (j > n) j -= n;
          if (visit[j]) result[j] = isPositive;
          else result[j] = !isPositive;
          visit[j] = true;
        }
      }
      return result;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int numNode = n<<1;
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
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
    Tarjan tarjan = new Tarjan(graph, n);
    boolean possible = tarjan.init();
    if (possible) {
      bw.write("1\n");
      boolean[] result = tarjan.getSolution();
      for (int i = 1; i < result.length; i++) {
        if (result[i]) bw.write("1 ");
        else bw.write("0 ");
      }
      bw.newLine();
    } else {
      bw.write("0\n");
    }
    bw.flush();
  }
  static void addEdge(int a, int b, ArrayList<Queue<Integer>> graph, int n) {
    if (a < 0) a = -a + n;
    if (b < 0) b = -b + n;
    graph.get(a).add(b);
  }
}
