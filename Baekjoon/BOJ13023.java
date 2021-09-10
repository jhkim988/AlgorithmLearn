import java.io.*;
import java.util.*;

public class BOJ13023 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      graph.get(end).add(start);
    }

    for (int i = 0; i < N; i++) {
      boolean[] marked = new boolean[N];
      if (dfs(i, 0, graph, marked)) {
        bw.write("1\n");
        bw.flush();
        return;
      }
    }
    bw.write("0\n");
    bw.flush();
  }
  static boolean dfs(int start, int depth, ArrayList<Queue<Integer>> graph, boolean[] marked) {
    if (depth == 4) {
      return true;
    }
    marked[start] = true;
    boolean flag = false;
    for (int adj : graph.get(start)) {
      if (marked[adj]) continue;
      marked[adj] = true;
      flag = flag || dfs(adj, depth + 1, graph, marked);
      marked[adj] = false;
    }
    return flag;
  }
}
