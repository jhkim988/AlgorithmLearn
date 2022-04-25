import java.io.*;
import java.util.*;

public class BOJ11376 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    int[] employee = new int[2*n+1];
    int[] work = new int[m+1];
    
    for (int i = 0; i <= 2*n; i++) graph.add(new LinkedList<>());
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken());
      while (w-- > 0) {
        int input = Integer.parseInt(st.nextToken());
        graph.get(2*i-1).add(input);
        graph.get(2*i).add(input);
      }
    }

    int numMatch = 0;
    boolean[] visit = new boolean[2*n+1];
    for (int i = 1; i <= 2*n; i++) {
      if (employee[i] != 0) continue;
      Arrays.fill(visit, false);
      if (dfs(i, graph, visit, employee, work)) numMatch++;
    }

    bw.write(Integer.toString(numMatch));
    bw.newLine();
    bw.flush();
  }
  static boolean dfs(int a, ArrayList<Queue<Integer>> graph, boolean[] visit, int[] employee, int[] work) {
    visit[a] = true;
    for (int b : graph.get(a)) {
      if (work[b] == 0 || (!visit[work[b]] && dfs(work[b], graph, visit, employee, work))) {
        employee[a] = b;
        work[b] = a;
        return true;
      }
    }
    return false;
  }
}
