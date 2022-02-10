import java.io.*;
import java.util.*;

public class BOJ18352 {
  private static class Pair {
    int node, dist;
    Pair(int node, int dist) {
      this.node = node;
      this.dist = dist;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());

    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
    }

    ArrayList<Integer> answer = new ArrayList<>();
    boolean[] visit = new boolean[n+1];
    Queue<Pair> que = new LinkedList<>();
    visit[start] = true;
    que.add(new Pair(start, 0));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.dist > k) break;
      for (int adj : graph.get(crnt.node)) {
        if (visit[adj]) continue;
        visit[adj] = true;
        que.add(new Pair(adj, crnt.dist + 1));
        if (crnt.dist + 1 == k) {
          answer.add(adj);
        }
      }
    }

    Collections.sort(answer);
    if (answer.size() == 0) {
      bw.write("-1\n");
    } else {
      for (int node : answer) {
        bw.write(Integer.toString(node));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
