import java.io.*;
import java.util.*;

public class BOJ4803 {
  static ArrayList<Queue<Integer>> graph;
  static boolean[] marked;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int V = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());
    int numTest = 1;
    while (!(V == 0 && E == 0)) {
      graph = new ArrayList<>();
      for (int i = 0; i <= V; i++) {
        graph.add(new LinkedList<>());
      }
      for (int i = 0; i < E; i++) {
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken()); 
        graph.get(start).add(end);
        graph.get(end).add(start);
      }
      marked = new boolean[V + 1];
      int numTree = 0;
      for (int i = 1; i <= V; i++) {
        if (marked[i]) {
          continue;
        }
        boolean hasCycle = dfs(i, -1, false);
        if (hasCycle) {
          continue;
        }
        numTree++;
      }
      if (numTree == 0) {
        bw.write("Case " + numTest + ": No trees.\n");
      } else if (numTree == 1) {
        bw.write("Case " + numTest + ": There is one tree.\n");
      } else {
        bw.write("Case " + numTest + ": A forest of " + numTree + " trees.\n");
      }      
      numTest++;
      st = new StringTokenizer(br.readLine());
      V = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());
    }
    bw.flush();
  }
  static boolean dfs(int start, int prev, boolean hasCycle) {
    marked[start] = true;
    for (int next : graph.get(start)) {
      if (next != prev && marked[next]) {
        hasCycle = true;
      } else if (!marked[next]) {
        hasCycle = hasCycle || dfs(next, start, hasCycle);
      }
    }
    return hasCycle;
  }
}
