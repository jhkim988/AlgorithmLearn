import java.io.*;
import java.util.*;

public class BOJ14942 {
  private static class Edge {
    int end, weight;
    Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] energy = new int[n+1];
    for (int i = 1; i <= n; i++) {
      energy[i] = Integer.parseInt(br.readLine());
    }

    ArrayList<Queue<Edge>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) graph.add(new LinkedList<>());
    for (int i = 1; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graph.get(a).add(new Edge(b, w));
      graph.get(b).add(new Edge(a, w));
    }

    int exp = 1;
    int height = 0;
    while (exp < n) {
      exp <<= 1;
      height++;
    }
    int[][] pos = new int[height][n+1];
    long[][] cost = new long[height][n+1];
    
    // dp initialize: bfs from 1
    Queue<Integer> que = new LinkedList<>();
    que.add(1);
    pos[0][1] = 1;
    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (Edge child : graph.get(crnt)) {
        if (pos[0][child.end] != 0) continue;
        pos[0][child.end] = crnt;
        cost[0][child.end] = child.weight;
        que.add(child.end);
      }
    }

    for (int h = 1; h < height; h++) {
      for (int i = 1; i <= n; i++) {
        pos[h][i] = pos[h>>1][pos[h>>1][i]];
        cost[h][i] = cost[h>>1][i] + cost[h>>1][pos[h>>1][i]];
      }
    }

    for (int i = 1; i <= n; i++) {
      int h = height-1;
      int position = i;
      long useCost = 0;
      while (useCost < energy[i] && position != 1) {
        if (useCost + cost[h][position] <= energy[i]) {
          useCost += cost[h][position];
          position = pos[h][position];
        } else {
          if (h == 0) break;
          h >>= 1;
        }
      }
      bw.write(Integer.toString(position));
      bw.newLine();
    }
    bw.flush();
  }
}
