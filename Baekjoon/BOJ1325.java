import java.io.*;
import java.util.*;

public class BOJ1325 {
  private static class Tarjan {
    ArrayList<Queue<Integer>> graph;
    int id;
    int[] index, lowlink, sccId;
    boolean[] onStack;
    Stack<Integer> stk;
    ArrayList<HashSet<Integer>> scc;
    Tarjan(ArrayList<Queue<Integer>> graph) {
      this.graph = graph;
      id = 1;
      index = new int[graph.size()];
      lowlink = new int[graph.size()];
      sccId = new int[graph.size()];
      onStack = new boolean[graph.size()];
      stk = new Stack<>();
      tarjan();
    }
    void tarjan() {
      scc = new ArrayList<>();
      for (int i = 1; i < graph.size(); i++) {
        if (index[i] == 0) dfsTarjan(i, scc);
      }
    }
    void dfsTarjan(int x, ArrayList<HashSet<Integer>> result) {
      index[x] = id;
      lowlink[x] = id;
      id++;
      stk.push(x);
      onStack[x] = true;
      for (int edge : graph.get(x)) {
        if (index[edge] == 0) {
          dfsTarjan(edge, result);
          lowlink[x] = Integer.min(lowlink[x], lowlink[edge]);
        } else if (onStack[edge]) {
          lowlink[x] = Integer.min(lowlink[x], index[edge]);
        }
      }
      if (lowlink[x] == index[x]) {
        HashSet<Integer> que = new HashSet<>();
        while (true) {
          int t = stk.pop();
          onStack[t] = false;
          sccId[t] = result.size();
          que.add(t);
          if (t == x) break;
        }
        result.add(que);
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) graph.add(new LinkedList<>());
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(b).add(a);
    }
    Tarjan tarjan = new Tarjan(graph);
    ArrayList<HashSet<Integer>> scc = tarjan.scc;
    ArrayList<Queue<Integer>> dag = new ArrayList<>();
    for (int i = 0; i < scc.size(); i++) dag.add(new LinkedList<>());
    for (int i = 1; i <= n; i++) {
      for (int adj : graph.get(i)) {
        if (tarjan.sccId[i] == tarjan.sccId[adj]) continue;
        dag.get(tarjan.sccId[i]).add(tarjan.sccId[adj]);
      } 
    }
    for (int i = 0; i < scc.size(); i++) {
      System.out.print(i + ": ");
      for (int v : scc.get(i)) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
    ArrayList<Integer> answerSCCID = new ArrayList<>();
    int[] dp = new int[dag.size()];
    int max = 0;
    for (int id = 0; id < dag.size(); id++) {
      boolean[] visit = new boolean[dag.size()];
      int val = dfs(id, dag, dp, tarjan, visit);
      System.out.println("id: " + id + "/ val: " + val);
      if (max < val) {
        max = val;
        answerSCCID = new ArrayList<>();
        answerSCCID.add(id);
      } else if (max == val) {
        answerSCCID.add(id);
      }
    }
    ArrayList<Integer> answer = new ArrayList<>();
    for (int sccid : answerSCCID) {
      for (int v : scc.get(sccid)) {
        answer.add(v);
      }
    }
    Collections.sort(answer);
    bw.write(Integer.toString(answer.get(0)));
    for (int i = 1; i < answer.size(); i++) {
      bw.write(' ');
      bw.write(Integer.toString(answer.get(i)));
    }
    bw.newLine();
    bw.flush();
  }
  static int dfs(int v, ArrayList<Queue<Integer>> graph, int[] dp, Tarjan tarjan, boolean[] visit) {
    if (dp[v] != 0) return dp[v];
    int sum = tarjan.scc.get(tarjan.sccId[v]).size();
    for (int adj : graph.get(v)) {
      if (visit[adj]) continue;
      visit[adj] = true;
      sum += dfs(adj, graph, dp, tarjan, visit);
    }
    return dp[v] = sum;
  }
}
