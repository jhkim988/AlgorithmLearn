import java.io.*;
import java.util.*;

public class BOJ3977 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
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
          bw.newLine();
        }
      }
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
}
