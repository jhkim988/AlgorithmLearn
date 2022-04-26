import java.io.*;
import java.util.*;

public class BOJ1867 {
  static boolean[] visit;
  static int[] row, col;
  static ArrayList<Queue<Integer>> graph;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    while (k-- > 0) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      graph.get(r).add(c);
    }

    int match = 0;
    row = new int[n+1];
    col = new int[n+1];
    visit = new boolean[n+1];
    for (int i = 1; i <= n; i++) {
      if (row[i] != 0) continue;
      Arrays.fill(visit, false);
      if (dfs(i)) match++;
    }
    bw.write(Integer.toString(match));
    bw.flush();
  }
  static boolean dfs(int r) {
    visit[r] = true;
    for (int c : graph.get(r)) {
      if (col[c] == 0 || (!visit[col[c]] && dfs(col[c]))) {
        row[r] = c;
        col[c] = r;
        return true;
      }
    }
    return false;
  }
}
