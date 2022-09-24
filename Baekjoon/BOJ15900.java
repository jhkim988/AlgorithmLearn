import java.io.*;
import java.util.*;

public class BOJ15900 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < n; ++i) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    int[] visit = new int[n+1];
    Arrays.fill(visit, -1);
    Queue<Integer> que = new LinkedList<>();
    visit[1] = 0;
    que.add(1);
    long sum = 0;
    while (!que.isEmpty()) {
      int crnt = que.poll();
      boolean isLeaf = true;
      for (int adj : graph.get(crnt)) {
        if (visit[adj] != -1) continue;
        visit[adj] = visit[crnt]+1;
        que.add(adj);
        isLeaf = false;
      }
      if (isLeaf) {
        sum += visit[crnt];
      }
    }
    bw.write(sum % 2 == 0 ? "No" : "Yes");
    bw.flush();
  }
}
