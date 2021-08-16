import java.io.*;
import java.util.*;

public class BOJ15681 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int Q = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      graph.get(end).add(start);
    }
    int start = R;
    int[] size = new int[N + 1];
    boolean[] marked = new boolean[N + 1];
    dfs(start, size, marked, graph);

    for (int i = 0; i < Q; i++) {
      bw.write(size[Integer.parseInt(br.readLine())] + "\n");
    }
    bw.flush();
  }
  static void dfs(int crnt, int[] size, boolean[] marked, ArrayList<Queue<Integer>> graph) {
    size[crnt] = 1;
    marked[crnt] = true;
    for (int next : graph.get(crnt)) {
      if (marked[next]) continue;
      dfs(next, size, marked, graph);
      size[crnt] += size[next];
    }
  }
}
