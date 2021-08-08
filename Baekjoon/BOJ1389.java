import java.io.*;
import java.util.*;

public class BOJ1389 {
  private static class Pair {
    int node;
    int depth;
    Pair(int node, int depth) {
      this.node = node;
      this.depth = depth;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());

    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      graph.get(x).add(y);
      graph.get(y).add(x);
    }
    
    int[] result = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      Queue<Pair> que = new LinkedList<>();
      boolean[] marked = new boolean[N + 1];
      que.add(new Pair(i, 0));
      marked[i] = true;
      while (!que.isEmpty()) {
        Pair crnt = que.poll();
        for (int next : graph.get(crnt.node)) {
          if (!marked[next]) {
            que.add(new Pair(next, crnt.depth + 1));
            marked[next] = true;
            result[i] += crnt.depth + 1;
          }
        }
      }
    }
    int minIdx = 1;
    for (int i = 1; i <= N; i++) {
      if (result[i] < result[minIdx]) {
        minIdx = i;
      }
    }
    bw.write(minIdx + "\n");
    bw.flush();
  }
}
