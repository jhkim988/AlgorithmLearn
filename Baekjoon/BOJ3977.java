import java.io.*;
import java.util.*;

public class BOJ3977 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private static class Tarjan {
    int id, sccId;
    int[] index, lowlink;
    boolean[] onStack;
    Stack<Integer> stk;
    ArrayList<Queue<Integer>> graph;
    ArrayList<ArrayList<Integer>> sccList;
    int[] scc;
    Tarjan(ArrayList<Queue<Integer>> graph) {
      id = 1;
      index = new int[graph.size()];
      lowlink = new int[graph.size()];
      onStack = new boolean[graph.size()];
      stk = new Stack<>();
      this.graph = graph;
      sccList = new ArrayList<>();
      scc = new int[graph.size()];
    }
    void init() {
      for (int i = 0; i < graph.size(); i++) {
        if (index[i] == 0) dfs(i);
      }
    }
    void dfs(int x) {
      index[x] = id;
      lowlink[x] = id;
      id++;
      stk.push(x);
      onStack[x] = true;
      for (int adj : graph.get(x)) {
        if (index[adj] == 0) {
          dfs(adj);
          lowlink[x] = Integer.min(lowlink[x], lowlink[adj]);
        } else if (onStack[adj]) {
          lowlink[x] = Integer.min(lowlink[x], index[adj]);
        }
      }

      if (lowlink[x] == index[x]) {
        ArrayList<Integer> sccMember = new ArrayList<>();
        while (true) {
          int t = stk.pop();
          onStack[t] = false;
          sccMember.add(t);
          scc[t] = sccId;
          if (t == x) break;
        }
        sccList.add(sccMember);
        sccId++;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      // useKosaraju();
      useTarjan();
      if (br.readLine() == null) break;
      bw.newLine();
    }
    bw.flush();
  }
  static void dfs(int node, boolean[] visit, ArrayList<Queue<Integer>> graph, Stack<Integer> stk) {
    if (visit[node]) return;
    visit[node] = true;
    for (int adj : graph.get(node)) {
      if (visit[adj]) continue;
      dfs(adj, visit, graph, stk);
    }
    stk.push(node);
  }
  static void dfs(int node, boolean[] visit, ArrayList<Queue<Integer>> graph) {
    if (visit[node]) return;
    visit[node] = true;
    for (int adj : graph.get(node)) {
      if (visit[adj]) continue;
      dfs(adj, visit, graph);
    }
  }
  static void dfs(int node, boolean[] visit, ArrayList<Queue<Integer>> graph, ArrayList<Integer> member) {
    if (visit[node]) return;
    visit[node] = true;
    for (int adj : graph.get(node)) {
      if (visit[adj]) continue;
      dfs(adj, visit, graph, member);
      member.add(adj);
    }
  }
  static void useKosaraju() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    ArrayList<Queue<Integer>> reverse = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new LinkedList<>());
      reverse.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
      reverse.get(b).add(a);
    }
    Stack<Integer> stk = new Stack<>();
    boolean[] visit = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (visit[i]) continue;
      dfs(i, visit, graph, stk);
    }
    ArrayList<Integer> source = new ArrayList<>();
    Arrays.fill(visit, false);
    while (!stk.isEmpty()) {
      int node = stk.pop();
      if (visit[node]) continue;
      dfs(node, visit, graph);
      source.add(node);
      if (source.size() > 1) break;
    }
    if (source.size() > 1) {
      bw.write("Confused\n");
    } else {
      ArrayList<Integer> sccMember = new ArrayList<>();
      Arrays.fill(visit, false);
      int s = source.get(0);
      sccMember.add(s);
      dfs(s, visit, reverse, sccMember);
      Collections.sort(sccMember);
      for (int mem : sccMember) {
        bw.write(Integer.toString(mem));
      }
    }

  }
  static void useTarjan() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
    }
    Tarjan tarjan = new Tarjan(graph);
    tarjan.init();
    
    ArrayList<Queue<Integer>> restricted = new ArrayList<>();
    for (int i = 0; i < tarjan.sccId; i++) {
      restricted.add(new LinkedList<>());
    }

    int[] indegree = new int[tarjan.sccId];
    for (int a = 0; a < graph.size(); a++) {
      for (int b : graph.get(a)) {
        if (tarjan.scc[a] == tarjan.scc[b]) continue;
        indegree[tarjan.scc[b]]++;
      }
    }

    int zeroIndegreeIdx = 0;
    int numZeroIndegree = 0;
    for (int i = 0; i < indegree.length; i++) {
      if (indegree[i] == 0) {
        numZeroIndegree++;
        zeroIndegreeIdx = i;
      }
      if (numZeroIndegree > 1) break;
    }
    if (numZeroIndegree > 1) {
      bw.write("Confused\n");
    } else {
      Collections.sort(tarjan.sccList.get(zeroIndegreeIdx));
      for (int idx : tarjan.sccList.get(zeroIndegreeIdx)) {
        bw.write(Integer.toString(idx));
        bw.newLine();
      }
    }
  }
}
