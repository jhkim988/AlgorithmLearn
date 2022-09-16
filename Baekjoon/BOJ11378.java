  import java.io.*;
  import java.util.*;

  public class BOJ11378 {
    static int[][] graph;
    static int[] work;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      graph = new int[n+1][];
      for (int i = 1; i <= n; i++) {
        st = new StringTokenizer(br.readLine());
        int numWork = Integer.parseInt(st.nextToken());
        graph[i] = new int[numWork];
        for (int j = 0; j < numWork; j++) {
          graph[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      work = new int[m+1];
      visit = new boolean[n+1];
      int numMatch = 0;

      for (int i = 1; i <= n; i++) {
        Arrays.fill(visit, false);
        if (dfs(i)) {
          numMatch++;
        }
      }
      for (int i = 1; i <= n; i++) {
        Arrays.fill(visit, false);
        while (k > 0) {
          if (!dfs(i)) break;
          numMatch++;
          k--;
        }
      }
      bw.write(Integer.toString(numMatch));
      bw.flush();
    }
    static boolean dfs(int a) {
      visit[a] = true;
      for (int b : graph[a]) {
        if (work[b] == 0 || (!visit[work[b]] && dfs(work[b]))) {
          work[b] = a;
          return true;
        }
      }
      return false;
    }
  }