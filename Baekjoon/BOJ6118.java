import java.io.*;
import java.util.*;

public class BOJ6118 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= v; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
      graph.get(b).add(a);
    }
    final int INF = Integer.MAX_VALUE/2;
    int max = 0;
    int[] dist = new int[v+1];
    Arrays.fill(dist, INF);    
    Queue<Integer> que = new LinkedList<>();
    dist[1] = 0;
    que.add(1);
    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (int next : graph.get(crnt)) {
        if (dist[next] != INF) continue;
        dist[next] = dist[crnt] + 1;
        que.add(next);
        max = Integer.max(max, dist[next]);
      }
    }

    int idx = v;
    int num = 0;
    for (int i = v; i > 0; i--) {
      if (dist[i] != max) continue;
      idx = i;
      num++;
    }
    bw.write(Integer.toString(idx));
    bw.write(' ');
    bw.write(Integer.toString(max));
    bw.write(' ');
    bw.write(Integer.toString(num));
    bw.flush();
  }
}
