import java.io.*;
import java.util.*;

public class BOJ11376 {
  static int[][] graph;
  static boolean[] visit;
  static int[] employee, work;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    graph = new int[2*n+1][];
    employee = new int[2*n+1];
    work = new int[m+1];
    
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken());
      graph[i*2-1] = new int[w];
      graph[i*2] = new int[w];
      for (int j = 0; j < w; j++) {
        int input = Integer.parseInt(st.nextToken());
        graph[i*2-1][j] = graph[i*2][j] = input; 
      }
    }

    int numMatch = 0;
    visit = new boolean[2*n+2];
    for (int i = 1; i < 2*n+1; i++) {
      if (employee[i] != 0) continue;
      Arrays.fill(visit, false);
      if (dfs(i)) numMatch++;
    }

    bw.write(Integer.toString(numMatch));
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
