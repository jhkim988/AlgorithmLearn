import java.io.*;
import java.util.*;

public class BOJ1298 {
  static ArrayList<Queue<Integer>> graph;
  static boolean[] visit;
  static int[] student, notebook;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
    }

    visit = new boolean[1000];
    student = new int[n+1];
    notebook = new int[n+1];

    int numMatch = 0;
    for (int i = 1; i <= n; i++) {
      Arrays.fill(visit, false);
      if (dfs(i)) numMatch++;
    }

    bw.write(Integer.toString(numMatch));
    bw.flush();
  }
  static boolean dfs(int a) {
    visit[a] = true;
    for (int b : graph.get(a)) {
      if (notebook[b] == 0 || (!visit[notebook[b]] && dfs(notebook[b]))) {
        notebook[b] = a;
        student[a] = b;
        return true;
      }
    }
    return false;
  }
}
