import java.io.*;
import java.util.*;

public class BOJ2533 {
  static int N;
  static int root = 1;
  static ArrayList<Queue<Integer>> graph;
  static boolean[] marked;
  static int[][] minEAArr;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      graph.get(end).add(start);
    }
    
    marked = new boolean[N + 1];
    minEAArr = new int[N + 1][2]; // minEAArr[i][0]: root contain, minEAArr[i][1]: not contain 

    minEA(root, -1);
    bw.write(Math.min(minEAArr[root][0], minEAArr[root][1]) + "\n");
    bw.flush();

    // System.out.println("###");
    // for (int i = 1; i <= N; i++) {
    //   System.out.println(minEAArr[i][0] + " " + minEAArr[i][1]);
    // }
  }
  static int minEA(int crnt, int prev) {
    if (marked[crnt]) {
      return Math.min(minEAArr[crnt][0], minEAArr[crnt][1]);
    }
    if (graph.get(crnt).size() == 1 && crnt != root) {
      // leaf node
      minEAArr[crnt][0] = 1;
      minEAArr[crnt][1] = 0;
      return 1;
    }
    marked[crnt] = true;
    minEAArr[crnt][0] = 1;
    for (int next : graph.get(crnt)) {
      if (next == prev) continue;
      minEA(next, crnt);
      minEAArr[crnt][0] += Math.min(minEAArr[next][0], minEAArr[next][1]);
      minEAArr[crnt][1] += minEAArr[next][0];
    }
    return Math.min(minEAArr[crnt][0], minEAArr[crnt][1]);
  }
}
