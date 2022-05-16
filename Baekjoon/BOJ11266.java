import java.io.*;
import java.util.*;

public class BOJ11266 {
  static int num = 1;
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
    boolean[] isAP = new boolean[v+1];
    Arrays.fill(isAP, true);
    int[] dfsNum = new int[v+1];
    for (int i = 1; i <= v; i++) {
      if (dfsNum[i] == 0) {
        dfs(graph, dfsNum, i);
        if (graph.get(i).size() <= 1) isAP[i] = false; 
      } else {
        int numChild = 0;
        for (int j : graph.get(i)) {
          if (dfsNum[j] < dfsNum[i]) continue;
          numChild++;
          for (int adj : graph.get(j)) {
            if (dfsNum[adj] < dfsNum[i]) isAP[i] = false;
          }
        }
        if (numChild == 0) isAP[i] = false;
      } 
    }
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

    System.out.println(Arrays.toString(dfsNum));
  }
  static void dfs(ArrayList<Queue<Integer>> graph, int[] dfsNum, int node) {
    dfsNum[node] = num++;
    for (int adj : graph.get(node)) {
      if (dfsNum[adj] != 0) continue;
      dfs(graph, dfsNum, adj);
    }
  }
}
