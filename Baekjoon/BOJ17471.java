import java.io.*;
import java.util.*;

public class BOJ17471 {
  static int[] population;
  static ArrayList<Queue<Integer>> graph;
  static int n, totalSum;
  static boolean[] visit;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    population = new int[n+1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      totalSum += population[i] = Integer.parseInt(st.nextToken());
    }
    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) graph.add(new LinkedList<>());
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int numNeibor = Integer.parseInt(st.nextToken());
      while (numNeibor-- > 0) {
        int adj = Integer.parseInt(st.nextToken());
        graph.get(i).add(adj);
        graph.get(adj).add(i);
      }
    }
    visit = new boolean[n+1];
    int answer = recur(0, 0);
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static int recur(int v, int acc) {
    if (v >= n) {
      int tStart = 1, fStart = 1;
      while (tStart <= n && !visit[tStart]) tStart++;
      while (fStart <= n && visit[fStart]) fStart++;
      if (tStart > n || fStart > n) return -1;
      boolean[] check = new boolean[n+1];
      int numCheck = bfs(tStart, check, true) + bfs(fStart, check, false);
      if (numCheck != n) return -1;
      return totalSum - acc > acc ? totalSum-2*acc : 2*acc - totalSum;      
    }
    visit[v+1] = true;
    int val1 = recur(v + 1, acc + population[v+1]);
    visit[v+1] = false;
    int val2 = recur(v + 1, acc);
    if (val1 == -1 && val2 == -1) return -1;
    if (val1 == -1) return val2;
    if (val2 == -1) return val1;
    return Integer.min(val1, val2);
  }
  static int bfs(int start, boolean[] check, boolean flag) {
    int numCheck = 1;
    Queue<Integer> que = new LinkedList<>();
    check[start] = true;
    que.add(start);
    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (int adj : graph.get(crnt)) {
        if (check[adj]) continue;
        if (visit[adj] != flag) continue;
        numCheck++;
        check[adj] = true;
        que.add(adj);
      }
    }
    return numCheck;
  }
}