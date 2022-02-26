import java.io.*;
import java.util.*;

public class BOJ11375 {
  static int[] employee, work, graph[];
  static boolean[] visit;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    employee = new int[n+1];
    work = new int[m+1];
    graph = new int[n+1][];
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int num = Integer.parseInt(st.nextToken());
      graph[i] = new int[num];
      for (int k = 0; k < num; k++) {
        graph[i][k] = Integer.parseInt(st.nextToken());
      }
    }
    int max = 0;
    visit = new boolean[n+1];
    for (int i = 1; i <= n; i++) {
      if (employee[i] != 0) continue;
      Arrays.fill(visit, false);
      if (dfs(i)) max++;
    }
    bw.write(Integer.toString(max));
    bw.newLine();
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
