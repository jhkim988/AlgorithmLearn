import java.io.*;
import java.util.*;

public class BOJ16964 {
  private static class Pair {
    int node;
    int numChild;
    Pair(int node, int numChild) {
      this.node = node;
      this.numChild = numChild;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int V = Integer.parseInt(br.readLine());

    // Graph Initialize:
    ArrayList<HashSet<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= V; i++) {
      graph.add(new HashSet<>());
    }
    for (int i = 1; i < V; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      graph.get(end).add(start);
    }

    // test data:
    int[] input = new int[V];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < V; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }

    bw.write(answer(graph, input) ? "1\n" : "0\n");
    bw.flush();
  }
  static boolean answer(ArrayList<HashSet<Integer>> graph, int[] input) {
    if (input[0] != 1) return false;
    int V = graph.size() - 1;
    int start = 1;
    int ptr = 1;
    Stack<Pair> stk = new Stack<>();
    boolean[] marked = new boolean[V + 1];
    marked[start] = true;
    stk.push(new Pair(start, graph.get(start).size()));
    while (ptr < V) {
      // check: input[ptr] is valid
      int check = input[ptr];
      Pair crnt = stk.peek();
      while (crnt.numChild == 0) {
        stk.pop();
        crnt = stk.peek();
      }
      if (graph.get(crnt.node).contains(check)) {
        stk.push(new Pair(check, graph.get(check).size() - 1));
        ptr++;
        crnt.numChild--;
      } else {
        return false;
      }
    }
    return true;
  }
}
