import java.io.*;
import java.util.*;

public class BOJ11377 {
  static int n, m, k;
  static int[][] graph;
  static int[] employee, work;
  static boolean[] visit;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    graph = new int[n+1][];
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int e = Integer.parseInt(st.nextToken());
      graph[i] = new int[e];
      for (int j = 0; j < e; j++) {
        graph[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    employee = new int[n+1];
    work = new int[m+1];
    visit = new boolean[n+1];

    int numMatch = 0;
    for (int i = 1; i <= n; i++) {
      Arrays.fill(visit, false);
      if (dfs(i)) numMatch++;
    }
    for (int i = 1; i <= n; i++) {
      Arrays.fill(visit, false);
      if (k > 0 && dfs(i)) {
        k--;
        numMatch++;
      }
      if (k == 0) break;
    }

    bw.write(Integer.toString(numMatch));
    bw.flush();
  }
  static boolean dfs(int a) {
    visit[a] = true;
    for (int b : graph[a]) {
      if (work[b] == 0 || (!visit[work[b]] && dfs(work[b]))) {
        employee[a] = b;
        work[b] = a;
        return true;
      }
    }
    return false;
  }
}
