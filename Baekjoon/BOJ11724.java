import java.io.*;
import java.util.*;

public class BOJ11724 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int numV = Integer.parseInt(st.nextToken());
    int numE = Integer.parseInt(st.nextToken());

    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= numV; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < numE; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      graph.get(x).add(y);
      graph.get(y).add(x);
    }

    int numComp = bfs(graph);
    bw.write(numComp + "\n");
    bw.flush();
  }
  static int bfs(ArrayList<Queue<Integer>> graph) {
    int count = 0;
    Queue<Integer> que = new LinkedList<>();
    boolean[] marked = new boolean[graph.size()];
    for (int i = 1; i < graph.size(); i++) {
      if (!marked[i]) {
        que.add(i);
        marked[i] = true;
        while (!que.isEmpty()) {
          int crnt = que.poll();
          for (int next : graph.get(crnt)) {
            if (!marked[next]) {
              marked[next] = true;
              que.add(next);
            }
          }
        }      
        count++;
      }
    }
    return count;
  }
}
