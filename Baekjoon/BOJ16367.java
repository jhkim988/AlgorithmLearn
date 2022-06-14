import java.io.*;
import java.util.*;

public class BOJ16367 {
  private static class Tarjan {
    ArrayList<Queue<Integer>> graph;
    int id = 1;
    int[] index, lowlink, groupId;
    boolean[] onStack;
    Stack<Integer> stk;
    ArrayList<Stack<Integer>> result;
    Tarjan(ArrayList<Queue<Integer>> graph) {
      this.graph = graph;
      index = new int[graph.size()];
      lowlink = new int[graph.size()];
      groupId = new int[graph.size()];
      onStack =  new boolean[graph.size()];
      stk = new Stack<>();
      result = new ArrayList<>();
      init();
    }
    void init() {
      for (int i = 0; i < graph.size(); i++) {
        if (index[i] == 0) dfsTarjan(i);
      }
    }
    void dfsTarjan(int x) {
      index[x] = id;
      lowlink[x] = id;
      onStack[x] = true;
      stk.push(x);
      id++;
      for (int adj : graph.get(x)) {
        if (index[adj] == 0) {
          dfsTarjan(adj);
          lowlink[x] = Integer.min(lowlink[x], lowlink[adj]);
        } else if (onStack[adj]) {
          lowlink[x] = Integer.min(lowlink[x], index[adj]);
        }
      }
      if (lowlink[x] == index[x]) {
        Stack<Integer> component = new Stack<>();
        while (true) {
          int t = stk.pop();
          onStack[t] = false;
          groupId[t] = result.size();
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
    int k = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < 2*k; i++) {
      graph.add(new LinkedList<>());
    }
    int[] tmp = new int[3];
    while (n-- > 0) {
      // 2-SAT
      // x_i is Red = node i, x_i is Blue = node i+k
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 3; i++) {
        int lightNum = Integer.parseInt(st.nextToken())-1;
        char color = st.nextToken().charAt(0);
        tmp[i] = color == 'R' ? lightNum : lightNum+k;
      }
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (i == j) continue;
          graph.get(tmp[i] >= k ? tmp[i]-k : tmp[i]+k).add(tmp[j]);
        }
      }
    }
    char[] answer = answer(n, k, graph);
    if (answer == null) bw.write("-1");
    else bw.write(answer);
    bw.flush();
  }
  static char[] answer(int n, int k, ArrayList<Queue<Integer>> graph) {
    Tarjan tarjan = new Tarjan(graph);
    for (int i = 0; i < k; i++) {
      if (tarjan.groupId[i] == tarjan.groupId[i+k]) return null;
    }
    char[] result = new char[k];
    Queue<Integer> que = new LinkedList<>();
    boolean[] visit = new boolean[tarjan.result.size()];
    for (int sccId = tarjan.result.size()-1; sccId >= 0; sccId--) { // Topological Sort order
      visit[sccId] = true;
      que.add(sccId);
      for (int v : tarjan.result.get(sccId)) {
        if (v >= k) result[v-k] = 'B';
        else result[v] = 'R';
      }
      while (!que.isEmpty()) {
        int id = que.poll();
        for (int adj : graph.get(id)) {
          int adjId = tarjan.groupId[adj];
          if (visit[adjId]) continue;
          que.add(adjId);
          visit[adjId] = true;
          for (int v : tarjan.result.get(adjId)) {
            if (v >= k) result[v-k] = 'B';
            else result[v] = 'R';
          }
        }
      }
    }
    return result;
  }
}
