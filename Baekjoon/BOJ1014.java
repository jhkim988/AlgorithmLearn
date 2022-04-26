import java.io.*;
import java.util.*;

public class BOJ1014 {
  static boolean[] visit;
  static ArrayList<Queue<Integer>> graph;
  static int[] group1, group2;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    int[] rowDi = {-1, -1, 0, 0};
    int[] colDi = {-1, 1, -1, 1};
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      char[][] room = new char[row][];
      int numX = 0;
      for (int r = 0; r < row; r++) {
        room[r] = br.readLine().toCharArray();
        for (int c = 0; c < col; c++) {
          if (room[r][c] == 'x') numX++;
        }
      }
      // node: (r,c) -> r*row+c
      graph = new ArrayList<>();
      for (int p = 0; p < row*col; p++) {
        graph.add(new LinkedList<>());
      }
      for (int r = 0; r < row; r++) {
        for (int c = 0; c < col; c++) {
          if (room[r][c] != '.') continue;
          for (int k = 0; k < 4; k++) {
            int adjRow = r + rowDi[k];
            int adjCol = c + colDi[k];
            if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
            if (room[adjRow][adjCol] != '.') continue;
            graph.get(r*col+c).add(adjRow*col+adjCol);
            graph.get(adjRow*col+adjCol).add(r*col+c);
          }
        }
      }

      // bfs grouping
      Queue<Integer> bfsque = new LinkedList<>();
      int[] color = new int[row*col];
      for (int i = 0; i < row*col; i++) {
        if (color[i] != 0) continue;
        color[i] = 1;
        bfsque.add(i);
        while (!bfsque.isEmpty()) {
          int crnt = bfsque.poll();
          for (int adj :graph.get(crnt)) {
            if (color[adj] != 0) continue;
            color[adj] = (color[crnt] == 1 ? -1 : 1);
            bfsque.add(adj);
          }
        }
      }

      int match = 0;
      visit = new boolean[row*col];
      group1 = new int[row*col];
      group2 = new int[row*col];
      Arrays.fill(group1, -1);
      Arrays.fill(group2, -1);

      for (int i = 0; i < row*col; i++) {
        if (group1[i] != -1 || color[i] == -1) continue;
        Arrays.fill(visit, false);
        if (dfs(i)) match++;
      }
      bw.write(Integer.toString(row*col - numX - match));
      bw.newLine();
    }
    bw.flush();
  }
  static boolean dfs(int i) {
    visit[i] = true;
    for (int adj : graph.get(i)) {
      if (group2[adj] == -1 || (!visit[group2[adj]] && dfs(group2[adj]))) {
        group1[i] = adj;
        group2[adj] = i;
        return true;
      }
    }
    return false;
  }
}
