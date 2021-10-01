import java.io.*;
import java.util.*;

public class BOJ16940 {
  private static class Pair {
    int node;
    int dist;
    Pair (int node, int dist) {
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
    for (int i = 1; i < V; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      graph.get(end).add(start);
    } 
    Queue<Integer> input = new LinkedList<>();
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < V; i++) {
      input.add(Integer.parseInt(st.nextToken()));
    }

    bw.write(answer(graph, input) ? "1\n" : "0\n");
    bw.flush();
  }
  static boolean answer(ArrayList<Queue<Integer>> graph, Queue<Integer> input) {
    int V = graph.size() - 1;
    int start = 1;
    int[] level = new int[V + 1]; // level[i] = j; level of node i = j
    // BFS:
    boolean[] marked = new boolean[V + 1];
    Queue<Pair> que = new LinkedList<>();
    que.add(new Pair(start, 0));
    marked[start] = true;
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      level[crnt.node] = crnt.dist;
      for (int w : graph.get(crnt.node)) {
        if (marked[w]) continue;
        que.add(new Pair(w, crnt.dist + 1));
        marked[w] = true;
      }
    }
    int prev = 0;
    for (int v : input) {
      if (level[v] == prev) {
        
      } else if (level[v] == prev + 1) {
        prev++;

      } else {
        return false;
      }
    }
    return true;
  }
}
