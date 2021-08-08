import java.io.*;
import java.util.*;

public class BOJ11403 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numV = Integer.parseInt(br.readLine());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= numV; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i <= numV; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= numV; j++) {
        if (Integer.parseInt(st.nextToken()) == 1) {
          graph.get(i).add(j);
        }
      }
    }
    
    int[][] mat = new int[numV + 1][numV + 1];
    for (int i = 1; i <= numV; i++) {
      Queue<Integer> que = new LinkedList<>();      
      boolean[] marked = new boolean[numV + 1];
      que.add(i);
      while (!que.isEmpty()) {
        int crnt = que.poll();
        for (int next : graph.get(crnt)) {
          if (!marked[next]) {
            marked[next] = true;
            que.add(next);
            mat[i][next] = 1;
          }
        }
      }
    }

    for (int i = 1; i <= numV; i++) {
      for (int j = 1; j < numV; j++) {
        bw.write(mat[i][j] + " ");
      }
      bw.write(mat[i][numV] + "\n");
    }
    bw.flush();
  }
}
