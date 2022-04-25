import java.io.*;
import java.util.*;

public class BOJ2188 {
  static boolean[] visit;
  static int[] calloc, ralloc;
  static ArrayList<Queue<Integer>> graph;
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
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int numWant = Integer.parseInt(st.nextToken());
      while (numWant-- > 0) {
        graph.get(i).add(Integer.parseInt(st.nextToken()));
      }
    }

    int numMatch = 0;
    visit = new boolean[n+1];
    calloc = new int[n+1];
    ralloc = new int[m+1];
    for (int i = 1; i <= n; i++) {
      if (calloc[i] != 0) continue;
      Arrays.fill(visit, false);
      if (dfs(i)) numMatch++;
    }

    bw.write(Integer.toString(numMatch));
    bw.flush();
  } 
  static boolean dfs(int cow) {
    visit[cow] = true;
    for (int wantRoom : graph.get(cow)) {
      if (ralloc[wantRoom] == 0 || (!visit[ralloc[wantRoom]] && dfs(ralloc[wantRoom]))) {
        ralloc[wantRoom] = cow;
        calloc[cow] = wantRoom;
        return true;
      }
    }
    return false;
  } 
}
