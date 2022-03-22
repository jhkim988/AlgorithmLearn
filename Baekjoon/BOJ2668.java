import java.io.*;
import java.util.*;

public class BOJ2668 {
  static int n;
  static int[] graph;
  static ArrayList<Integer> cycle;
  static boolean[] visit;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    graph = new int[n+1];
    for (int i = 1; i <= n; i++) {
      graph[i] = Integer.parseInt(br.readLine());
    }
    cycle = new ArrayList<>();
    visit = new boolean[n+1];
    for (int i = 1; i <= n; i++) {
      boolean[] onStack = new boolean[n+1];
      if (visit[i]) continue;
      dfs(i, onStack);
    }
    Collections.sort(cycle);
    bw.write(Integer.toString(cycle.size()));
    bw.newLine();
    for (int c : cycle) {
      bw.write(Integer.toString(c));
      bw.newLine();
    }
    bw.flush();
  }
  static int dfs(int v, boolean[] onStack) {
    if (onStack[v]) {
      return v;
    }
    if (visit[v]) return -1;
    onStack[v] = true;
    int val = dfs(graph[v], onStack);
    onStack[graph[v]] = false;
    visit[v] = true;
    if (val != -1) {
      cycle.add(v);
      if (val == v) return -1;
      return val;
    }     
    return -1;
  }
}
