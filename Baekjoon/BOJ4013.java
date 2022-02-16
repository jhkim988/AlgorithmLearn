import java.io.*;
import java.util.*;

public class BOJ4013 {
  private static class Tarjan {
    int id;
    int[] index, lowlink, sccId, cash;
    boolean[] onStack, rest;
    Stack<Integer> stk;
    ArrayList<Queue<Integer>> graph;
    ArrayList<ArrayList<Integer>> scc;
    ArrayList<Queue<Integer>> sccTree;
    ArrayList<Long> sccCash;
    ArrayList<Boolean> sccHasRest;
    Tarjan(ArrayList<Queue<Integer>> graph, int[] cash, boolean[] rest) {
      this.graph = graph;
      this.cash = cash;
      this.rest = rest;
      id = 1;
      index = new int[graph.size()];
      lowlink = new int[graph.size()];
      sccId = new int[graph.size()];
      onStack = new boolean[graph.size()];
      stk = new Stack<>();
      scc = new ArrayList<>();
      sccTree = new ArrayList<>();
      sccCash = new ArrayList<>();
      sccHasRest = new ArrayList<>();
    }
    void init() {
      for (int i = 1; i < graph.size(); i++) {
        if (index[i] == 0) dfs(i);
      }
      sccTree();
    }
    void dfs(int x) {
      index[x] = id;
      lowlink[x] = id;
      onStack[x] = true;
      stk.push(x);
      id++;
      for (int adj : graph.get(x)) {
        if (index[adj] == 0) {
          dfs(adj);
          lowlink[x] = Integer.min(lowlink[x], lowlink[adj]);
        } else if (onStack[adj]) {
          lowlink[x] = Integer.min(lowlink[x], index[adj]);
        }
      }
      if (lowlink[x] == index[x]) {
        long sum = 0;
        boolean hasRest = false;
        ArrayList<Integer> sccMem = new ArrayList<>();
        while (true) {
          int t = stk.pop();
          onStack[t] = false;
          sccMem.add(t);
          if (rest[t]) hasRest = true;
          sccId[t] = scc.size();
          sum += cash[t];
          if (t == x) break;
        }
        scc.add(sccMem);
        sccCash.add(sum);
        sccHasRest.add(hasRest);
      }
    }
    void sccTree() {
      for (int i = 0; i < scc.size(); i++) {
        sccTree.add(new LinkedList<>());
      }
      for (int a = 1; a < graph.size(); a++) {
        for (int b : graph.get(a)) {
          if (sccId[a] == sccId[b]) continue;
          sccTree.get(sccId[a]).add(sccId[b]);
        }
      }
    }
    long find(int start) {
      long[] dp = new long[sccTree.size()];
      Arrays.fill(dp, -1);
      return findRecur(start, dp);
    }
    long findRecur(int node, long[] dp) {
      if (dp[node] != -1) return dp[node];
      long tmp = 0;
      for (int adj : sccTree.get(node)) {
        tmp = Long.max(tmp, findRecur(adj, dp));
      }
      if (tmp != 0) return dp[node] = tmp + sccCash.get(node);
      if (sccHasRest.get(node)) return dp[node] = sccCash.get(node);
      else return dp[node] = 0;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
    }
    int[] cash = new int[n+1];
    for (int i = 1; i <= n; i++) {
      cash[i] = Integer.parseInt(br.readLine());
    }

    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int numRest = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    boolean[] rest = new boolean[n+1];
    for (int i = 0; i < numRest; i++) {
      rest[Integer.parseInt(st.nextToken())] = true;
    }

    Tarjan tarjan = new Tarjan(graph, cash, rest);
    tarjan.init();
    bw.write(Long.toString(tarjan.find(start)));
    bw.newLine();
    bw.flush();
  }
}
