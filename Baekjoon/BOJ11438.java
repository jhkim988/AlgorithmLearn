import java.io.*;
import java.util.*;

public class BOJ11438 {
  final static int log = 17; // log2(100_000);
  private static class Pair {
    int id, depth;
    Pair(int id, int depth) {
      this.id = id;
      this.depth = depth;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numNode = Integer.parseInt(br.readLine());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();    
    for (int i = 0; i <= numNode; i++) {
      graph.add(new LinkedList<>());
    }
    StringTokenizer st;
    for (int i = 1; i < numNode; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      graph.get(node1).add(node2);
      graph.get(node2).add(node1);
    }
    
    int[][] tree = new int[log][numNode + 1];
    boolean[] visit = new boolean[numNode + 1];
    Queue<Pair> que = new LinkedList<>();
    visit[1] = true;
    que.add(new Pair(1, 0));
    int[] depth = new int[numNode + 1];
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int next : graph.get(crnt.id)) {
        if (visit[next]) continue;
        tree[0][next] = crnt.id;
        visit[next] = true;
        depth[next] = crnt.depth + 1;
        que.add(new Pair(next, crnt.depth + 1));
      }
    }
    visit = null;
    que = null;
    for (int i = 1; i < log; i++) {
      for (int j = 1; j <= numNode; j++) {
        tree[i][j] = tree[i - 1][tree[i - 1][j]];
      }
    }
    int numQuery = Integer.parseInt(br.readLine());
    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int q1 = Integer.parseInt(st.nextToken());
      int q2 = Integer.parseInt(st.nextToken());
      if (depth[q2] < depth[q1]) {
        int tmp = q1;
        q1 = q2;
        q2 = tmp;
      } // depth[q1] < depth[q2]
      int diff = depth[q2] - depth[q1];
      for (int b = 0; b < log; b++) {
        if ((diff & (1 << b)) != 0) q2 = tree[b][q2];
      }
      if (q1 == q2) {
        bw.write(Integer.toString(q1));
        bw.newLine();
        bw.flush();
        continue;
      }
      for (int b = log - 1; b >= 0; b--) {
        if (tree[b][q1] != tree[b][q2]) {
          q1 = tree[b][q1];
          q2 = tree[b][q2];
        }
      }      
      bw.write(Integer.toString(tree[0][q1]));
      bw.newLine();
    }
    bw.flush();
  }
  // static int move(int id, int num, int[][] tree) {
  //   for (int b = 0; b < log; b++) {
  //     if ((num & (1 << b)) != 0) {
  //       id = tree[b][id];
  //     }
  //   }
  //   return id;
  // }
}
