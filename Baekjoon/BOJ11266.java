import java.io.*;
import java.util.*;

public class BOJ11266 {
  static int num = 1;
  static int[] dfsNum;
  static boolean[] isAP;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= v; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
      graph.get(b).add(a);
    }
    isAP = new boolean[v+1];
    dfsNum = new int[v+1];
    for (int i = 1; i <= v; i++) if (dfsNum[i] == 0) dfs(graph, i, true);
    int numAP = 0;
    for (int i = 1; i <= v; i++) if (isAP[i]) numAP++;
    bw.write(Integer.toString(numAP));
    bw.newLine();
    for (int i = 1; i <= v; i++) {
      if (!isAP[i]) continue;
      bw.write(Integer.toString(i));
      bw.write(' ');
    }
    bw.flush();
  }
  static int dfs(ArrayList<Queue<Integer>> graph, int node, boolean isRoot) {
    int min = num;
    int numChild = 0;
    dfsNum[node] = num++;
    for (int adj : graph.get(node)) {
      if (dfsNum[adj] == 0) {
        numChild++;
        int low = dfs(graph, adj, false);
        if (!isRoot && low >= dfsNum[node]) isAP[node] = true;
        min = Integer.min(min, low);
      } else {
        min = Integer.min(min, dfsNum[adj]);
      }
    }
    if (isRoot) isAP[node] = numChild >= 2;
    return min;
  }
}
