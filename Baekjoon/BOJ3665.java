import java.io.*;
import java.util.*;

public class BOJ3665 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int numTest = Integer.parseInt(br.readLine());
    while(numTest-- > 0) {
      int numTeam = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      int[] prevRank = new int[numTeam + 1];
      for (int i = 1; i <= numTeam; i++) {
        prevRank[i] = Integer.parseInt(st.nextToken()); // prevRank[i] team: rank i;
      }
      ArrayList<HashSet<Integer>> graph = new ArrayList<>();
      for (int i = 0; i <= numTeam; i++) {
        graph.add(new HashSet<>());
      }
      for (int i = 1; i <= numTeam; i++) {
        for (int j = i + 1; j <= numTeam; j++) {
          graph.get(prevRank[i]).add(prevRank[j]); // rank of team i was higher than team j's.
        }
      }

      int numChange = Integer.parseInt(br.readLine());
      for (int i = 0; i < numChange; i++) {
        st = new StringTokenizer(br.readLine());
        int crntHigher = Integer.parseInt(st.nextToken()); // prev lower
        int crntLower = Integer.parseInt(st.nextToken()); // prev higher
        if (!graph.get(crntLower).contains(crntHigher)) {
          int tmp = crntLower;
          crntLower = crntHigher;
          crntHigher = tmp;
        }
        graph.get(crntLower).remove(crntHigher);
        graph.get(crntHigher).add(crntLower);
      }

      // topology sort
      Stack<Integer> iter = useDFS(graph);
      if (iter.peek() == -1) {
        bw.write("IMPOSSIBLE\n");
        bw.flush();
      } else {
        while (!iter.isEmpty()) {
          int pop = iter.pop();
          if (iter.isEmpty()) {
            bw.write(pop + "\n");
          } else {
            bw.write(pop + " ");
          }
        }
        bw.flush();
      }      
    }
  }
  static Stack<Integer> useDFS(ArrayList<HashSet<Integer>> graph) {
    int V = graph.size() - 1;
    boolean[] marked = new boolean[V + 1];
    Stack<Integer> stk = new Stack<>();
    int code = 0;
    for (int i = 1; i <= V; i++) {
      if (marked[i]) continue;
      code = dfs(graph, i, marked, stk, 0);
      if (code < 0) {
        // code -1: HasCyle
        // code -2: NotUnique -> No case
        Stack<Integer> fail = new Stack<>();
        fail.add(code);
        return fail;
      }
    }
    return stk;
  }
  static int dfs(ArrayList<HashSet<Integer>> graph, int vertex, boolean[] marked, Stack<Integer> stk, int code) {
    marked[vertex] = true;
    for (int adj : graph.get(vertex)) {
      if (marked[adj]) {
        if (stk.contains(adj)) {
          continue;
        } else {
          return -1; // hasCyle
        }
      } else {
        int result = dfs(graph, adj, marked, stk, code);
        if (result < 0) {
          return -1;
        }
      }
    }
    stk.push(vertex);
    return code;
  }
}