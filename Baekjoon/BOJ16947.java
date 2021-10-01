import java.io.*;
import java.util.*;

public class BOJ16947 {
  private static class Pair {
    int node;
    int dist;
    Pair(int node, int dist) {
      this.node = node;
      this.dist = dist;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int V = Integer.parseInt(br.readLine());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= V; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i <= V; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      graph.get(end).add(start);
    }

    HashSet<Integer> cycle = findCycle(graph);
    bw.write(answer(graph, cycle));
    bw.flush();
  }
  static HashSet<Integer> findCycle(ArrayList<Queue<Integer>> graph) {
    int V = graph.size() - 1;
    HashSet<Integer> cycle = new HashSet<>();
    Stack<Integer> stk = new Stack<>();
    boolean[] marked = new boolean[V + 1];
    int[] edgeTo = new int[V + 1]; // edgeTo[i] = j; j -> i
    stk.push(1);
    DFS: while (!stk.isEmpty()) {
      int v = stk.pop();
      marked[v] = true;
      for (int w : graph.get(v)) {
        if (edgeTo[v] != w && marked[w]) {
          // Find Cycle:
          int ptr = v;
          while (ptr != w) {
            cycle.add(ptr);
            ptr = edgeTo[ptr];
          }
          cycle.add(w);
          break DFS;
        }
        if (marked[w]) continue;
        stk.push(w);
        edgeTo[w] = v;
      }
    }    
    return cycle;
  }
  static String answerBFS(ArrayList<Queue<Integer>> graph, HashSet<Integer> cycle) {
    int V = graph.size() - 1;
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= V; i++) {
      // Use BFS, Find Shortest Distance to cycle.
      Queue<Pair> que = new LinkedList<>();
      boolean[] marked = new boolean[V + 1];
      que.add(new Pair(i, 0));
      marked[i] = true;
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        if (cycle.contains(crnt.node)) {
          sb.append(crnt.dist).append(' ');
          break;
        }
        for (int w : graph.get(crnt.node)) {
          if (marked[w]) continue;
          marked[w] = true;
          que.add(new Pair(w, crnt.dist + 1));
        }
      }
    }
    sb.append('\n');
    return sb.toString();
  }
  static String answer(ArrayList<Queue<Integer>> graph, HashSet<Integer> cycle) {
    // optimization
    int V = graph.size() - 1;
    int[] dist = new int[V + 1];
    for (int v : cycle) {
      Queue<Pair> que = new LinkedList<>();
      que.add(new Pair(v, 0));
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        dist[crnt.node] = crnt.dist;
        for (int w : graph.get(crnt.node)) {
          if (cycle.contains(w)) continue;
          if (dist[w] > 0) continue;
          que.add(new Pair(w, crnt.dist + 1));
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= V; i++) {
      sb.append(dist[i]).append(' ');
    }
    sb.append('\n');
    return sb.toString();
  }
}
